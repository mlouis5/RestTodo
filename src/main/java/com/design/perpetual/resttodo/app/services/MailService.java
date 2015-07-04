/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.design.perpetual.resttodo.app.services;

import com.design.perpetual.resttodo.app.entities.Todo;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

/**
 *
 * @author Mac
 */
@Component
public class MailService {

    private static final String EMAIL = "home.broker.automation@gmail.com";

    @Autowired
    private MailSender mailSender;
    @Autowired
    private MimeMessageHelper message;
    
    public boolean sendMail(Todo todo) {
        if (Objects.nonNull(todo) && !todo.getSendTo().isEmpty()) {
            try {
                message.setFrom(EMAIL);
                String[] tos = todo.getSendTo().split(",\\s+");
                for (String to : tos) {
                    System.out.println("email: " + to);
                    message.addTo(to);
                }
                message.setSubject(todo.getType());                
                String text = createContent(todo);
                message.setText(text, true);

                JavaMailSenderImpl javaMailSender = (JavaMailSenderImpl) mailSender;
                javaMailSender.send(message.getMimeMessage());
                return true;
            } catch (MessagingException ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
            }
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
