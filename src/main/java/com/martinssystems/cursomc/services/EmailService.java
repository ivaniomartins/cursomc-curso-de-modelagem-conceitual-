package com.martinssystems.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.martinssystems.cursomc.domain.Pedido;

public interface EmailService {
	
void sendOrderConfirmationEmail(Pedido obj);

void sendEmail(SimpleMailMessage msg);
	

}
