package com.gogools.sb.service.mail;

import java.util.List;

public interface IMailservice {

	
	public Boolean sendMail(List<String> to, List<String> bcc, String subject, String text);
	
	public Boolean sendMail(List<String> to, List<String> bcc, String subject, String template, List<String> templateParams);
}
