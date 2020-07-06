package org.xi.multidatasourse.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.xi.multidatasourse.mapper.TransferApiMapper;
import org.xi.multidatasourse.model.TransferApiModel;
import org.xi.multidatasourse.model.TransferApiParamModel;
import org.xi.multidatasourse.service.TransferApiService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("transferApiService")
public class TransferApiServiceImpl implements TransferApiService {

    @Autowired
    TransferApiMapper transferApiMapper;

    @Autowired
    @Qualifier("trustRestTemplate")
    RestTemplate trustRestTemplate;

    @Autowired
    @Qualifier("basicRestTemplate")
    RestTemplate basicRestTemplate;

    @Override
    public Object getResponse(String moduleName, String apiName, JSONObject params) {

        TransferApiModel apiModel = getApi(moduleName, apiName);
        Map<String, String> queryMap = getTargetParams(params.getJSONObject("query"), apiModel.getQueryParams());
        Map<String, String> headerMap = getTargetParams(params.getJSONObject("header"), apiModel.getHeaderParams());
        Map<String, String> pathMap = getTargetParams(params.getJSONObject("path"), apiModel.getPathParams());

        Object postBody = params.get("body");
        if (postBody instanceof JSONObject) {
            JSONObject data = (JSONObject) postBody;
            apiModel.getBodyParams().forEach((key, value) -> {
                if (StringUtils.isNoneBlank(value.getDefaultValue()) && !data.containsKey(key)) {
                    data.put(key, value.getDefaultValue());
                }
            });
        }

        HttpMethod method = HttpMethod.resolve(apiModel.getRequestType().toUpperCase());
        if (method == null) method = HttpMethod.GET;
        String url = getUrl(apiModel.getUrl(), pathMap, queryMap);
        return getResponse(url, method, postBody, headerMap);
    }

    public Object getResponse(String url, HttpMethod method, Object data, Map<String, String> headerMap) {

        HttpHeaders headers = new HttpHeaders();
        headerMap.forEach((key, value) -> headers.add(key, value));

        if (method == null) method = HttpMethod.GET;
        ResponseEntity<String> exchange = trustRestTemplate.exchange(url, method, new HttpEntity<>(data, headers), String.class);
        if (HttpStatus.SC_OK != exchange.getStatusCodeValue()) {
            // todo
        }
        return exchange.getBody();
    }

    private String getUrl(String url, Map<String, String> pathParams, Map<String, String> queryParams) {
        if (pathParams != null && !queryParams.isEmpty()) {
            for (Map.Entry<String, String> entry : pathParams.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                url = url.replace(String.format("{%s}", key), value);
            }
        }
        if (queryParams == null || queryParams.isEmpty()) return url;
        String queryString = queryParams.entrySet().stream().map(item -> item.getKey() + "=" + item.getValue()).collect(Collectors.joining("&"));
        return url.contains("?") ? url + "&" + queryString : url + "?" + queryString;
    }


    private Map<String, String> getTargetParams(JSONObject params, Map<String, TransferApiParamModel> paramModelMap) {
        Map<String, String> map = new HashMap<>();
        if (paramModelMap != null) {
            paramModelMap.forEach((key, value) -> {
                if (StringUtils.isNoneBlank(value.getDefaultValue()))
                    map.put(key, value.getDefaultValue());
            });
        }
        if (params != null) {
            params.forEach((key, value) -> map.put(key, value.toString()));
        }
        return map;
    }


    private TransferApiModel getApi(String moduleName, String apiName) {
        TransferApiModel apiModel = transferApiMapper.getApi(moduleName, apiName);
        if (apiModel == null) return null;
        List<TransferApiParamModel> transferApiParamModels = transferApiMapper.getApiParams(apiModel.getId());
        Map<Integer, Map<String, TransferApiParamModel>> paramMap =
                transferApiParamModels.stream()
                        .collect(Collectors.groupingBy(item -> item.getParamPosition(),
                                Collectors.toMap(item -> item.getParamName(), item -> item)));

        apiModel.setQueryParams(paramMap.getOrDefault(1, new HashMap<>()));
        apiModel.setBodyParams(paramMap.getOrDefault(2, new HashMap<>()));
        apiModel.setHeaderParams(paramMap.getOrDefault(3, new HashMap<>()));
        apiModel.setPathParams(paramMap.getOrDefault(4, new HashMap<>()));

        return apiModel;
    }
}
