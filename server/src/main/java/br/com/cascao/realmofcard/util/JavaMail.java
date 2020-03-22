package br.com.cascao.realmofcard.util;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import br.com.cascao.realmofcard.domain.Cupom;

public class JavaMail {

	public static void mandarEmailCupom(String email, Cupom cupom) {

		Properties props = new Properties();
		/** Parametros de conexão com servidor Gmail */
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("zero1games800@gmail.com", "zero1games");
			}
		});

		/** Ativa Debug para sessão */
		session.setDebug(true);

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("zero1games800@gmail.com"));
			// Remetente

			Address[] toUser = InternetAddress.parse(email);

			message.setRecipients(Message.RecipientType.TO, toUser);
			message.setSubject("Cupom de Troca ");// Assunto
			message.setText("Codigo do cupom: " + cupom.getCodigo() + " | Valor:" + cupom.getValor());
			/** Método para enviar a mensagem criada */
			Transport.send(message);

			System.out.println("Feito!!!");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

}