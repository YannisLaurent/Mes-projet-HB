package fr.humanbooster.liaison.utils;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

public class Mailer {

																		private String motDePasse = "boostercdi0810";

	public boolean envoyerMail(String emailExpediteur, String emailDestinataire, String sujet, String contenu) {

		EmailAttachment attachment = new EmailAttachment();
		
		attachment.setDisposition(EmailAttachment.ATTACHMENT);
		attachment.setDescription("mon image");
		attachment.setName("John");

		MultiPartEmail email = new MultiPartEmail();
		email.setHostName("ns0.ovh.net");
		email.setSmtpPort(587);
		email.setAuthenticator(new DefaultAuthenticator("ylaurent@humanbooster.com", motDePasse));
		email.setSSLOnConnect(true);
		try {
			attachment.setURL(new URL("http://img0.mxstatic.com/singe/jeune-chimpanze-se-deplacant-dans-les-arbres_1528_w620.jpg"));
			email.setFrom(emailExpediteur);
			email.setSubject("petite phrase de Twan");
			email.setMsg(contenu);
			email.addTo(emailDestinataire);
			email.attach(attachment);
			email.send();
		} catch (EmailException | MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		System.out.println("Votre mail a bien été envoyé à " + emailDestinataire);
		return true;
	}
}
