package br.com.gmc.ouvidoria.utils;

import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class SendMimeMessage {

    private final JavaMailSender mailSender;
    private final GetHtmlContentFromTemplate getHtmlContentFromTemplate;

    public SendMimeMessage(JavaMailSender mailSender, GetHtmlContentFromTemplate getHtmlContentFromTemplate) {
        this.mailSender = mailSender;
        this.getHtmlContentFromTemplate = getHtmlContentFromTemplate;
    }

    public void sendSimpleMail(String to, String subject, String text) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);

            mailSender.send(mimeMessage);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendResetPasswordLink(String to, String subject, String link, String templateName) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false);

            helper.setTo(to);
            helper.setSubject(subject);

            Map<String, Object> model = new HashMap<>();
            model.put("RESET_LINK", link);

            String body = getHtmlContentFromTemplate.execute(model, templateName);
            helper.setText(body, true);

            mailSender.send(mimeMessage);

        } catch (MessagingException | IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
    }
}
