package org.xi.multidatasourse.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.xi.multidatasourse.model.TransferApiModel;
import org.xi.multidatasourse.service.TransferApiService;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/transfer")
public class TransferApiController {

    @Autowired
    TransferApiService transferApiService;

    @Autowired
    @Qualifier("trustRestTemplate")
    RestTemplate trustRestTemplate;

    @Autowired
    @Qualifier("basicRestTemplate")
    RestTemplate basicRestTemplate;

    /**
     * 数据回调接口
     *
     * @param module
     * @param api
     * @param params
     * @return
     */
    @PostMapping(value = "/{module}/{api}")
    public Object transfer(@PathVariable("module") String module, @PathVariable("api") String api, @RequestBody JSONObject params) {
        return transferApiService.getResponse(module, api, params);
    }

    @GetMapping(value = "/test")
    public Object transfer() throws Exception {

        String token = "";//getToken(GrowingIOConstants.SECRET,GrowingIOConstants.projectid,GrowingIOConstants.ai,GrowingIOConstants.XClientId);

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Client-Id", GrowingIOConstants.XClientId);
        headers.add("Authorization",token);

        String url="https://www.growingio.com/projects/"+GrowingIOConstants.projectid+"/rules.csv";

        ResponseEntity<String> exchange = trustRestTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), String.class);
        if (HttpStatus.SC_OK != exchange.getStatusCodeValue()) {
            // todo
        }
        return exchange.getBody();

//        return getResponse("http://m.icsoc.net/v2/wintelapi/detail/agentstate", "6018102101", "6a2b5464273cf761f82893535e7b9487", "2019-12-16");
    }

    /**
     * 获取最重要的认证
     *
     * @return
     */
    public String getToken(String secret, String project, String ai, String XClientId) {
        Long time = System.currentTimeMillis();
        String authToken = getAuth(secret, project, ai, time);

        String url = "https://www.growingio.com/auth/token?project=" + project + "&ai=" + ai + "&tm=" + time + "&auth=" + authToken + "";
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Client-Id", XClientId);

        ResponseEntity<String> exchange = trustRestTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(headers), String.class);
        String res = exchange.getBody();
        System.out.println("====" + JSONObject.parseObject(res).get("code"));
        return JSONObject.parseObject(res).get("code").toString();
    }

    public static String authToken(String secret, String project, String ai, Long tm) throws Exception {
        String message = "POST\n/auth/token\nproject=" + project + "&ai=" + ai + "&tm=" + tm;
        Mac hmac = Mac.getInstance("HmacSHA256");
        hmac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
        byte[] signature = hmac.doFinal(message.getBytes("UTF-8"));
        return Hex.encodeHexString(signature);
    }

    public static String getAuth(String secret, String project, String ai, Long time) {
        String authToken = null;
        try {
            authToken = authToken(secret, project, ai, time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authToken;
    }

    class GrowingIOConstants {
        public static final String SECRET="fd12afd44a57497da720ea30d01ac82b";
        public static final String XClientId="e3559969961948918269866c9ae41948";
        public static final String projectid="nPNYMGWR";
        public static final String ai="a3c49546c2476d21";

        public static final String APPSECRET="5c5e5f16e9434ebeb7a148f6df31dc7e";
        public static final String APPXClientId="ad9505114bbd44269278073b626cb1e0";
        public static final String APPprojectid="nomk5AvR";
        public static final String APPai="a06bcaaccd9e69e9";

        public static final String APPChartprojectid="noqwZDAR";
    }
}
