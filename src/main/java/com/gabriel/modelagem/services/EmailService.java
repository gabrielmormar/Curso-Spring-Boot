package com.gabriel.modelagem.services;

import org.springframework.mail.SimpleMailMessage;

import com.gabriel.modelagem.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
