/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.design.perpetual.resttodo.app.services;

import com.design.perpetual.resttodo.app.entities.Todo;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Folder;
import javax.mail.MessagingException;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

/**
 *
 * @author Mac
 */
@Component
public class MailService {

    private static final String EMAIL = "home.broker.automation@gmail.com";

//    @Autowired
//    private Folder inbox;
    @Autowired
    private MailSender mailSender;
    @Autowired
    private VelocityEngine velocityEngine;
    @Autowired
    private VelocityContext velocityContext;
    @Autowired
    private StringWriter stringWriter;
//    @Autowired
//    private Template template;
    @Autowired
    private MimeMessageHelper message;

//    public void notifyOf(Todo todo) {
////        if (Objects.nonNull(todo)) {
////            velocityEngine.init();
////            velocityContext.put("todo", todo);
//////            Template template = velocityEngine.getTemplate("todo.vm", "UTF-8");
////            template.merge(velocityContext, stringWriter);
////            sendMail(todo, stringWriter.toString());
//        }
//    }
    public boolean sendMail(Todo todo) {
        try {
            message.setFrom(EMAIL);
            message.setTo(todo.getSendTo());
            message.setSubject(todo.getType());

//            Map model = new HashMap();

            System.out.println(Arrays.toString(todo.modifiedValue()));

//            String text = VelocityEngineUtils.mergeTemplateIntoString(
//                    velocityEngine, "velocity/todo.vm", "UTF-8", model);

            String text = createContent(todo);

//            System.out.println("newText: " + newText);
//
//            System.out.println("text before replacement: " + text);
//            text = text.replace("<tr><td>replace</td></tr>", newText);

            message.setText(text, true);

            System.out.println("text: " + text);

            JavaMailSenderImpl javaMailSender = (JavaMailSenderImpl) mailSender;
            javaMailSender.send(message.getMimeMessage());
            return true;
        } catch (MessagingException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private String createContent(Todo todo) {
        String[] values = todo.modifiedValue();
        StringBuilder sb = new StringBuilder("<!DOCTYPE html><html><head><style>table, th, td {border: 1px solid black;}table{width: 50%;}th{text-transform: uppercase;}</style></head><body><table><tr><th>Todo</th></tr>");
        for (String elem : values) {
            System.out.println("elem: " + elem);
            sb.append("<tr>");
            sb.append("<td>");
            sb.append(elem);
            sb.append("</td>");
            sb.append("</tr>");
        }
        sb.append("</table></body></html>");
        return sb.toString();
    }
}
