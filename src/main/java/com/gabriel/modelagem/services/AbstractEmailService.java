package com.gabriel.modelagem.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.gabriel.modelagem.domain.Pedido;

public abstract class AbstractEmailService implements EmailService {
	
	@Value("${default.sender}")
	private String sender;
	
	@Override
	public void sendOrderConfirmationEmail(Pedido obj) {
		SimpleMailMessage simpleMail = prepareSimpleMailMessageFromPedido(obj);
		sendEmail(simpleMail);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido obj) {
		SimpleMailMessage simpleMail = new SimpleMailMessage();
		simpleMail.setTo(obj.getCliente().getEmail());
		simpleMail.setFrom(sender);
		simpleMail.setSubject("Pedido confirmado! Código: " + obj.getId());
		simpleMail.setSentDate(new Date(System.currentTimeMillis()));
		simpleMail.setText(obj.toString());
		return simpleMail;
	}
	
}
