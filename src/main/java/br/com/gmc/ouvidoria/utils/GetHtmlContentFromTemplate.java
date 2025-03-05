package br.com.gmc.ouvidoria.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;

@Component
public class GetHtmlContentFromTemplate {
    private final Configuration freemarkerConfig;

    public GetHtmlContentFromTemplate(Configuration freemarkerConfig) {
        this.freemarkerConfig = freemarkerConfig;
        this.freemarkerConfig.setLocale(new Locale("pt", "BR"));
    }

    public String execute(Map<String, Object> object, String templateName) throws IOException, TemplateException, IOException {
        Template template  = freemarkerConfig.getTemplate("mail/" + templateName + ".html");
        return FreeMarkerTemplateUtils.processTemplateIntoString(template, object);
    }
}
