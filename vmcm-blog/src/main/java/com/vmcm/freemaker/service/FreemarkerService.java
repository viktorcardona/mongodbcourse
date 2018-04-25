package com.vmcm.freemaker.service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Created by viccardo on 18/03/16.
 */
public final class FreemarkerService {

    private static final Configuration configuration = createConfiguration();

    private static Configuration createConfiguration(){
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
        configuration.setClassLoaderForTemplateLoading(FreemarkerService.class.getClassLoader(), "/");
        return configuration;
    }

    private static Configuration getConfiguration(){
        return configuration;
    }

    public static String processTemplate(String templateName)
            throws IOException, TemplateException {
        Template template = getConfiguration().getTemplate(templateName);
        return template.toString();
    }

    public static String processTemplate(String templateName, Map<String, Object> map)
            throws IOException, TemplateException {
        try(StringWriter writer = new StringWriter()){
            Template template = getConfiguration().getTemplate(templateName);
            template.process(map, writer);
            return writer.toString();
        }
    }
}
