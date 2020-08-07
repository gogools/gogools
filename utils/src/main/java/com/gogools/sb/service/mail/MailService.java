package com.gogools.sb.service.mail;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.gogools.enums.Emj;
import com.google.common.base.Charsets;
import com.google.common.io.ByteSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MailService implements IMailservice {
	
	@Autowired
	private JavaMailSender emailSender;
	
	@Autowired
	private ResourceLoader resourceLoader;

	@Override
	public Boolean sendMail(List<String> to, List<String> bcc, String subject, String text) {
		
		log.info(Emj.INFO + " Trying to send a email > subject:{}, to:{}, bcc{}, ", subject, to, bcc);
		
		if( to == null || to.isEmpty()) {
		
			log.info(Emj.ERR + " Email error, 'to' list null or empty: {} ", to);
			return false;
		}	

		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setTo( 		to.toArray(new String[0]) );
		message.setBcc( 	bcc.toArray(new String[0]) );
		message.setSubject( subject );
		message.setText( 	text );
		
		emailSender.send(message);
		
		return true;
	}

	@Override
	public Boolean sendMail(List<String> to, List<String> bcc, String subject, String templateName, List<String> templateParams) {
		
		log.info(Emj.INFO + " Trying to send a email > subject:{}, to:{}, bcc{}, template:{}, params:{}", subject, to, bcc, templateName, templateParams);
		
		if( to == null || to.isEmpty()) {
		
			log.info(Emj.ERR + " Email error, 'to' list null or empty: {} ", to);
			return false;
		}	
		
		if( bcc == null ) {
			
			bcc = new ArrayList<>();
		}	
		
		Resource resource = resourceLoader.getResource("classpath:"+templateName);
		
		if(!resource.exists()) {
			
			log.info(Emj.ERR + " Email error, template doesn't exist: classpath:{} ", templateName);
			return false;
		}

		
		SimpleMailMessage template = new SimpleMailMessage();
		String text;
		
		ByteSource byteSource = new ByteSource() {
			
	        @Override
	        public InputStream openStream() throws IOException {
	            return resource.getInputStream();
	        }
	    };
		
		try {
			
			template.setText( byteSource.asCharSource(Charsets.UTF_8).read() );			    
			
			text     = String.format(template.getText(), templateParams.toArray(new Object[0]));
			
		} catch (Exception e) {

			e.printStackTrace();
			log.info(Emj.ERR + " Email error, e: {} ", e.getMessage());
			return false;
		}

		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setTo( 		to.toArray(new String[0]) 				);
		message.setBcc( 	bcc.toArray(new String[0]) 				);
		message.setSubject( subject 								);
		message.setText( 	text								 	);
		
		emailSender.send(message);
		
		log.info(Emj.OUTBOX + " Email SENT, to: {}, subject: {} ", to, subject);
		
		return true;
	}
	
}
