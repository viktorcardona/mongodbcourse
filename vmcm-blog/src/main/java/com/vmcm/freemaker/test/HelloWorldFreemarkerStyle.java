package com.vmcm.freemaker.test;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Created by viccardo on 16/03/16.
 */
public class HelloWorldFreemarkerStyle {

    public static void main(String[] args) {
        //http://freemarker.org/docs/pgui_quickstart_createconfiguration.html
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
        configuration.setClassLoaderForTemplateLoading(HelloWorldFreemarkerStyle.class.getClassLoader(), "/");

        try {
            Template helloTemplate = configuration.getTemplate("hello.ftl");
            StringWriter writer = new StringWriter();
            Map<String, Object> map = new HashMap<>();
            map.put("name", "Isabella & Martin. FreeMarker Template");

            helloTemplate.process(map, writer);

            System.out.println(writer);
        }
        catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

}
