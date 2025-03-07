package br.com.gmc.ouvidoria.configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.application.client.settings")
@Getter
@Setter
@ToString
public class ClientSettings {
    private String title;
    private String name;
    private String favicon;
    private Media media;
    private LandingPage landingPage;

    @Getter
    @Setter
    @ToString
    public static class Media {
        private String instagram;
        private String facebook;
        private String linkedin;
        private String site;
        private String whatsapp;
    }

    @Getter
    @Setter
    @ToString
    public static class LandingPage {
        private String headerLogo;
        private String footerLogo;
        private String phone;
        private String contact;
        private String slogan;
    }
}
