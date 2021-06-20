package com.martinssystems.cursomc.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.martinssystems.cursomc.domain.Pedido;

public abstract class AbstractEmailService implements EmailService{

@Value("${default.sender}")
private String sender;	
	
@Override	
public void sendOrderConfirmationEmail(Pedido obj) {
	SimpleMailMessage msg = prepareMailMessagemFromPedido(obj);
	sendEmail(msg);
	
}

protected SimpleMailMessage prepareMailMessagemFromPedido(Pedido obj) {
	
	SimpleMailMessage sm = new SimpleMailMessage();
	sm.setTo(obj.getCliente().getEmail());
	sm.setFrom(sender);
	sm.setSubject("Pedido Confirmado! Código: " + obj.getId());
	sm.setSentDate(new Date(System.currentTimeMillis()));
	sm.setText(obj.toString());
	return sm;
}	

	
	
}
