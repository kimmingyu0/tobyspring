package com.intheeast.springframe.service;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.ArrayList;
import java.util.List;

public class RequestRepo extends JavaMailSenderImpl {
    private List<String> requests = new ArrayList<String>();

    public List<String> getRequests() {
        return requests;
    }

    public void send(SimpleMailMessage mailMessage) /*throws MailException*/ {
        super.send(mailMessage);
        requests.add(mailMessage.getTo()[0]);
    }

    public void send(SimpleMailMessage[] mailMessage) throws MailException {
    }
}