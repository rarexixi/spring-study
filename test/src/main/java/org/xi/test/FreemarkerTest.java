package org.xi.test;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class FreemarkerTest {

    static final Configuration configuration = new Configuration(Configuration.VERSION_2_3_30);

    public static String process(String template, Object model) {

        if (StringUtils.isBlank(template)) {
            return "";
        }

        try {
            StringWriter out = new StringWriter();
            new Template("template", new StringReader(template), configuration).process(model, out);
            return out.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) {
        Map<String, Object> model = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        data.put("jobId", 2);
        data.put("jobName", "job1");
        model.put("data", data);
        String ftl = "<#list data?keys as key>\n" +
                "key:${data[key]}\n" +
                "</#list>";
        System.out.println(process(ftl, model));
    }
}
