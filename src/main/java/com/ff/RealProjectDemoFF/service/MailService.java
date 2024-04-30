package com.ff.RealProjectDemoFF.service;



public interface MailService {

    public String sendActivationEmail (String toEmail, String subject, String body);

    public String getToken();

    public String sendUpdateEmail(String toEmail, String subject, String body);
}
