package com.Calendrify.Calendrify.Services;

import com.Calendrify.Calendrify.Healpers.Handlers.ResponseHandler;
import com.Calendrify.Calendrify.Models.BodyResponse.ContactUsMailBody;
import com.Calendrify.Calendrify.Models.BodyResponse.MailBody;
import freemarker.core.ParseException;
import freemarker.template.*;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

@Service
@SuppressWarnings("unchecked")
public class EmailSenderService {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private Configuration freemarkerConfig;

    public ResponseEntity<ResponseHandler> sendSimpleEmail(MailBody mailBody) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true); // Enable multipart mode for HTML content

            Map<String, Object> model = new HashMap<>();
            model.put("date", mailBody.getDate());

            model.put("title", mailBody.getTitle());
            model.put("hostBy", mailBody.getHostBy());

            model.put("description", mailBody.getDescription());
            model.put("url", mailBody.getUrl());
            model.put("startEndTime", mailBody.getStartEndTime());
            model.put("groupName", mailBody.getGroupName());

            model.put("location", mailBody.getLocation());

            // Set the loading location to src/main/resources/templates
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/");

            Template template = freemarkerConfig.getTemplate("mailTemplate.ftl");
            String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            String[] sendersList = mailBody.getToEmail().toArray(new String[0]);

            helper.setFrom("calendrify.go@gmail.com");
            helper.setTo(sendersList);
            helper.setText(text, true); // Use true for HTML content
            helper.setSubject(mailBody.getTitle());

            mailSender.send(message);
            System.out.println("mail Sent");
            return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse("Email Sent", true);
        } catch (MailException e) {
            System.out.println("================ Mail Error ============");
            e.printStackTrace();
            return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse(e.getMessage(), false);
        } catch (TemplateException | IOException | MessagingException e) {
            throw new RuntimeException(e);
        }
    }


    public ResponseEntity<ResponseHandler> sendContactUsEmail(ContactUsMailBody mailBody) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true); // Enable multipart mode for HTML content

            Map<String, Object> model = new HashMap<>();
            model.put("name", mailBody.getName());
            model.put("email", mailBody.getEmail());
            model.put("contact", mailBody.getContact());
            model.put("date", mailBody.getComplaintDate());
            model.put("message", mailBody.getMessage());
            // Set the loading location to src/main/resources/templates
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/");

            Template template = freemarkerConfig.getTemplate("contactusTemplate.ftl");
            String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

            helper.setFrom("calendrify.go@gmail.com");
            helper.setTo("calendrify.help@gmail.com");
            helper.setText(text, true); // Use true for HTML content
            helper.setSubject("Complaint");
            mailSender.send(message);
            System.out.println("mail Sent");
            return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse("Email Sent", true);
        } catch (MailException e) {
            System.out.println("================ Mail Error ============");
            e.printStackTrace();
            return (ResponseEntity<ResponseHandler>) ResponseHandler.GenerateResponse(e.getMessage(), false);
        } catch (TemplateException | IOException | MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
