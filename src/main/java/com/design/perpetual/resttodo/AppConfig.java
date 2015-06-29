/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.design.perpetual.resttodo;

import java.io.StringWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMessage;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 *
 * @author Mac
 */
@Configuration
public class AppConfig {

    @Bean
    public MailSender gmailMailSender() {
        JavaMailSenderImpl ms = new JavaMailSenderImpl();
        ms.setHost("smtp.gmail.com");
        ms.setPort(587);
        ms.setUsername(username());
        ms.setPassword(password());
        ms.setJavaMailProperties(gmailProps());

        return ms;
    }

    @Bean
    public MimeMessageHelper gmailMimeMessageHelper() {
        MimeMessage mm = ((JavaMailSenderImpl) gmailMailSender()).createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mm, "UTF-8");
        return message;
    }

    @Bean
    Properties gmailProps() {
        Properties props = new Properties();
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.mime.charset", "UTF-8");
        props.setProperty("mail.transport.protocol", "smtp");
        return props;
    }

    @Bean
    public String username() {
        return "home.broker.automation@gmail.com";
    }

    public String password() {
        return "irxfdnrinxefelmn";
    }

    @Bean
    public Folder inbox() throws MessagingException, Exception {
        Properties props = System.getProperties();
        props.setProperty("mail.store.protocol", "imaps");

        Session session = Session.getDefaultInstance(props, null);
        Store store = session.getStore("imaps");
        store.connect("imap.gmail.com", username(), password());
        Folder inbox = store.getFolder("INBOX");

        return inbox;
    }

    @Bean
    public VelocityEngine velocityEngine() {
        VelocityEngine ve = new VelocityEngine();
//        ve.setProperty("resource.loader", "class");
//        ve.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "class");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
//        ve.setProperty("classpath.resource.loader.path", "/");
        ve.init();
        return ve;
    }

    @Bean
    public VelocityContext velocityContext() {
        return new VelocityContext();
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public StringWriter stringWriter() {
        return new StringWriter();
    }

//    @Bean
//    public Template template() {
////        Resource resource = 
////           appContext.getResource("classpath:com/mkyong/common/testing.txt");
//        return velocityEngine().getTemplate("todo.vm", "UTF-8");
//    }
}
