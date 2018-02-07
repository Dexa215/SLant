package ProvaEmail;

import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import connections.Client;
import database.MQ_Read;

/**
 * @author Mauro
 *
 */

public class EmailSender {
	static String usr;
	static String pwd;
	static Client me;

	/**
	 * Questo metodo invia una email all'utente che si è appena registrato (con il
	 * codice temporaneo da utilizzare sul primo accesso)
	 * 
	 * @param to	destinatario
	 * @param Me mittente	mittente
	 * @throws SQLException eccezione relativa sql
	 */
	public static void send_uninsubria_email(String to, Client Me) throws SQLException {

		me = Me;
		System.out.println("Controllo errore:" + me.toString());
		String host = "smtp.office365.com";
		String from = me.getUSERNAME();

		javax.mail.Authenticator authenticator = new javax.mail.Authenticator() {
			@Override
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				return new javax.mail.PasswordAuthentication(me.getUSERNAME(), me.getPASSWORD());
			}
		};

		Properties props = new Properties();
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.host", host);
		props.setProperty("mail.smtp.port", "587");
		props.setProperty("mail.smtp.starttls.enable", "true");

		System.out.println("Controllo props:" + props + "Controllo authenticator:" + authenticator);
		javax.mail.Session session = javax.mail.Session.getInstance(props, authenticator);

		StringBuilder bodyText = new StringBuilder();
		bodyText.append("<div>").append("Caro Utente<br/><br/>").append("  Grazie per la Registrazione. <br/>")
				.append("Il codice di attivazione temporaneo è").append("  <br/>").append(MQ_Read.ReadPassTemp())
				.append("  <br/>").append("  Grazie <br/>").append("</div>");
		try {

			javax.mail.Message msg = new javax.mail.internet.MimeMessage(session);
			msg.setFrom(new javax.mail.internet.InternetAddress(from));
			msg.setRecipients(javax.mail.Message.RecipientType.TO, javax.mail.internet.InternetAddress.parse(to));
			msg.setSubject("Registrazione Email");
			msg.setContent(bodyText.toString(), "text/html; charset=utf-8");

			System.out.println("Controllo msg:" + msg + "Controllo user:" + me.getUSERNAME() + "Controllo password:"
					+ me.getPASSWORD());
			javax.mail.Transport.send(msg, me.getUSERNAME(), me.getPASSWORD());

			// Transport.send(msg);

			System.out.println("\nMail was sent successfully.");

		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public static int randomGenerator1() {
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(100);
		return randomInt;
	}

	/**
	 * Questo metodo invia una email di recupero password all'utente(verrà inserita
	 * una password casuale)
	 * 
	 * @param to destinatario
	 * @param Me mittente
	 * @param newpassword nuova password
	 * @throws SQLException eccezione relativa sql eccezione relativa sql
	 */
	public static void send_uninsubria_recoverypassword(String to, Client Me, String newpassword) throws SQLException {

		String[] userdata = MQ_Read.readSettingTable();
		String tox = userdata[4];
		String pwx = newpassword;

		String host = "smtp.office365.com";
		String from = tox;

		javax.mail.Authenticator authenticator = new javax.mail.Authenticator() {
			@Override
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				return new javax.mail.PasswordAuthentication(tox, userdata[5]);
			}
		};
		Properties props = new Properties();
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.host", host);
		props.setProperty("mail.smtp.port", "587");
		props.setProperty("mail.smtp.starttls.enable", "true");
		System.out.println("Controllo props:" + props + "Controllo authenticator:" + authenticator);
		javax.mail.Session session = javax.mail.Session.getInstance(props, authenticator);
		StringBuilder bodyText = new StringBuilder();
		bodyText.append("<div>").append("Caro Utente<br/><br/>").append(", <br/>")
				.append("la tua password temporanea è").append("  <br/>").append(pwx).append("  <br/>")
				.append("  Grazie <br/>").append("</div>");
		try {
			javax.mail.Message msg = new javax.mail.internet.MimeMessage(session);
			msg.setFrom(new javax.mail.internet.InternetAddress(from));
			msg.setRecipients(javax.mail.Message.RecipientType.TO, javax.mail.internet.InternetAddress.parse(to));
			msg.setSubject("Recovery Password");
			msg.setContent(bodyText.toString(), "text/html; charset=utf-8");
			System.out.println("Controllo msg:" + msg + "Controllo user:" + tox + "Controllo password:" + pwx);
			javax.mail.Transport.send(msg, tox, pwx);
			// Transport.send(msg);
			System.out.println("\nMail was sent successfully.");
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * Questo metodo invia una email all'utente che ha un libro scaduto o in
	 * scadenza di uno o più prestiti
	 * 
	 * @param idlibro			identificativo libro
	 * @param utnome			identificativo utente
	 * @param utcognome			utente cognome
	 * @param email				utente email
	 * @param nome_autore		libro autore
	 * @param cognome_autore	libro autore
	 * @param titolo			libro titolo
	 * @param UN				user name da dati SETTING
	 * @param PW				password da dati SETTING
	 * @throws SQLException eccezione relativa sql
	 */
	public static void send_LoansExpired(String UN, String PW,

			String email,

			String idlibro, String utnome, String utcognome,

			String nome_autore, String cognome_autore, String titolo

	) throws SQLException {
		String host = "smtp.office365.com";
		String from = UN;
		javax.mail.Authenticator authenticator = new javax.mail.Authenticator() {
			@Override
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				return new javax.mail.PasswordAuthentication(UN, PW);
			}
		};
		Properties props = new Properties();
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.host", host);
		props.setProperty("mail.smtp.port", "587");
		props.setProperty("mail.smtp.starttls.enable", "true");
		System.out.println("Controllo props:" + props + "Controllo authenticator:" + authenticator);
		javax.mail.Session session = javax.mail.Session.getInstance(props, authenticator);

		StringBuilder bodyText = new StringBuilder();
		bodyText.append("<div>").append("Caro " + utnome + " " + utcognome + "<br/><br/>")
				.append("  La contattiamo per informarla della scadenza del prestito. <br/>")
				.append("Il prestito in oggetto : libro ID: " + idlibro).append("  <br/>").append(" TITOLO: " + titolo)
				.append("  <br/>").append(" Autore: " + nome_autore + " " + cognome_autore).append("  <br/>")
				.append("  <br/>")
				.append(" Nell'invitarla alla restituzione il prima possibile, la ringraziamo in anticipo <br/>")
				.append("</div>");
		try {

			javax.mail.Message msg = new javax.mail.internet.MimeMessage(session);
			msg.setFrom(new javax.mail.internet.InternetAddress(from));
			msg.setRecipients(javax.mail.Message.RecipientType.TO, javax.mail.internet.InternetAddress.parse(email));
			msg.setSubject("Avviso Scadenza Prestito");
			msg.setContent(bodyText.toString(), "text/html; charset=utf-8");

			System.out.println("Controllo msg:" + msg + "Controllo user:" + UN + "Controllo password:" + PW);

			javax.mail.Transport.send(msg, UN, PW);
			System.out.println("\nMail was sent successfully.");
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * Questo metodo invia una email all'utente che ha preso in prestito un libro
	 * 
	 * @param idlibro			identificativo libro
	 * @param utnome			identificativo utente
	 * @param utcognome			utente cognome
	 * @param email				utente email
	 * @param nome_autore		libro autore
	 * @param cognome_autore	libro autore
	 * @param titolo			libro titolo
	 * @param data_inizio		inizio prestito
	 * @param UN				user name da dati SETTING
	 * @param PW				password da dati SETTING
	 * @throws SQLException eccezione relativa sql
	 */

	public static void send_email_books_loans(String idlibro, String utnome, String utcognome, String email,
			String nome_autore, String cognome_autore, String titolo, Date data_inizio,
			// Date data_fine,
			String UN, String PW

	) throws SQLException {

		System.out.println("ottengo idlibro: [" + idlibro + "]");
		System.out.println("ottengo utnome: [" + utnome + "]");
		System.out.println("ottengo utcognome: [" + utcognome + "]");
		System.out.println("ottengo email: [" + email + "]");
		System.out.println("ottengo nome_autore: [" + nome_autore + "]");
		System.out.println("ottengo cognome_autore: [" + cognome_autore + "]");
		System.out.println("ottengo titolo: [" + titolo + "]");
		System.out.println("ottengo data_inizio: [" + data_inizio + "]");
		System.out.println("ottengo UN: [" + UN + "]");
		System.out.println("ottengo PW: [" + PW + "]");

		// String[] all = MQ_Read.sendEmailLoans();
		String host = "smtp.office365.com";
		String from = UN;
		javax.mail.Authenticator authenticator = new javax.mail.Authenticator() {
			@Override
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				return new javax.mail.PasswordAuthentication(UN, PW);
			}
		};
		Properties props = new Properties();
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.host", host);
		props.setProperty("mail.smtp.port", "587");
		props.setProperty("mail.smtp.starttls.enable", "true");
		System.out.println("Controllo props:" + props + "Controllo authenticator:" + authenticator);
		javax.mail.Session session = javax.mail.Session.getInstance(props, authenticator);

		StringBuilder bodyText = new StringBuilder();
		bodyText.append("<div>").append("Caro " + utnome + " " + utcognome + "<br/><br/>")
				.append("La contattiamo per dirle che ha preso in prestito il seguente libro. <br/>")
				.append("Libro ID: " + idlibro).append("  <br/>").append("TITOLO: " + titolo).append("  <br/>")
				.append("Autore: " + nome_autore + " " + cognome_autore).append("  <br/>")
				.append("La data di inizio prestito è:" + data_inizio).append("  <br/>")
				.append("La data di fine prestito è: " + data_inizio).append("  <br/>").append(" <br/>")
				.append("Grazie per averci scelto, Arrivederci <br/>").append("</div>");
		try {

			javax.mail.Message msg = new javax.mail.internet.MimeMessage(session);
			msg.setFrom(new javax.mail.internet.InternetAddress(from));
			msg.setRecipients(javax.mail.Message.RecipientType.TO, javax.mail.internet.InternetAddress.parse(email));
			msg.setSubject("Avviso Prestito Accordato");
			msg.setContent(bodyText.toString(), "text/html; charset=utf-8");

			System.out.println("Controllo msg:" + msg + "Controllo user:" + UN + "Controllo password:" + PW);

			javax.mail.Transport.send(msg, UN, PW);
			// Transport.send(msg);
			System.out.println("\nMail was sent successfully.");
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

}
