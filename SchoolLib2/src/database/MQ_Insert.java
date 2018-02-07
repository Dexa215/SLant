package database;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.sql.SQLException;


public class MQ_Insert {

	public static void insertUtente(String query) throws SQLException
	{
		DBmanager.openConnection();
		DBmanager.executeUpdate(query);
		DBmanager.closeConnection();
	}
	
	/**
	 * Questo metodo inserisce i dati utente nella tabella utente
	 * @param name parametro che identifica il nome di un utente
	 * @param surname parametro che identifica il cognome di un utente
	 * @param inq parametro che identifica il inquadramento di un utente
	 * @param mail parametro che identifica il mail di un utente
	 * @param cf parametro che identifica il codice fiscale di un utente
	 * @param tel parametro che identifica il telefono di un utente
	 * @param pass parametro che identifica il password di un utente
	 * @param i parametro che identifica i tentativi falliti della passsword temporanea di un utente
	 * @throws SQLException è l'eccezione di sql che avviene se ci sono problemi di comunicazione con il database
	 */
	public static void insertUtente(
			String name, 
			String surname, 
			String inq, 
			String mail,  
			String cf, 
			String tel, 
			String pass, 
			int i) throws SQLException
	{
		String query = 		"INSERT INTO utente(nome,"
				+ "cognome,"
				+ "email,"
				+ "codice_fiscale,"
				+ "inquadramento,"
				+ "password,"
				+ "password_temp,"
				+ "ntel,) "
				+ "password_temp_tentativi) "
				+ "VALUES('" + name + "' , '" + surname + "' , '" + mail + "' , '" + cf + "' , '" + inq + "' , '" + pass + "' , '" + i + "' , '" + tel + "', '" + 0 + "')";
		DBmanager.openConnection();
		DBmanager.executeUpdate(query);
		DBmanager.closeConnection();
	}

	
		
		public static String insertUtenteGetQuery(
				int idus,
				String name, 
				String surname, 
				String inq, 
				String mail,  
				String cf, 
				String tel, 
				String pass,	
				int i, 
				int tentativi,
				String typePerson
				) throws SQLException
		{

		
			String query = 		"INSERT INTO utente("
					+ "nome,"
					+ "cognome,"
					+ "email,"
					+ "codice_fiscale,"
					+ "inquadramento,"
					+ "password,"
					+ "password_temp,"
					+ "password_temp_tentativi,"
					+ "ntel,"
					+ "tipo_utente ) "
					+ "VALUES('" 	+ name 
									+ "' , '" + surname 
									+ "' , '" + mail + "' , '" 
									+ cf + "' , '" 
									+ inq + "' , '" 
									+ pass + "' , '" 
									+ i + "' , '" 
									+ tentativi + "','"
									+ tel + "' , '"
									+ typePerson +"')";


			return 	query;
		}	

		
		public static void insertBooks(String q) throws SQLException
		{
			DBmanager.openConnection();
			DBmanager.executeUpdate(q);
			DBmanager.closeConnection();
		}
		
		public static void insertBooks(String name, String surname, String cat,String title,String disp,int pren_cod) throws SQLException
		{
			String query = "INSERT INTO libro(nome_autore, cognome_autore, categoria, titolo,disponibilità,prenotazioni_in_coda) "
					       + "VALUES('" + name + "' , '" + surname + "' , '" + cat + "' , '" + title + "', '" + disp + "' , '" + pren_cod + "')";
			DBmanager.openConnection();
			DBmanager.executeUpdate(query);
			DBmanager.closeConnection();
		}
		
		/**
		 * Questo metodo inserisce i dati dei libri nella tabella libro
		 * @param codice parametro che identifica il codice di un libro
		 * @param name  parametro che identifica il nome di un libro
		 * @param surname parametro che identifica il cognome di un libro
		 * @param cat parametro che identifica la categoria di un libro
		 * @param title parametro che identifica il titolo di un libro
		 * @param disp parametro che identifica la disponibilià di un libro
		 * @param pren_cod parametro che identifica la prenotazione in coda di un libro
		 * @return query inserisce i libri nella tabella libro
		 * @throws SQLException è l'eccezione di sql che avviene se ci sono problemi di comunicazione con il database
		 */
		public static String insertBooksGetQuery(
				int codice,
				String name, 
				String surname, 
				String cat, 
				String title,
				String disp,
				int pren_cod) throws SQLException
		{
			String query = 		"INSERT INTO libro("
					+ "codice, "
					+ "nome_autore, "
					+ "cognome_autore, "
					+ "categoria, "
					+ "titolo, "
					+ "disponibilità, "
					+ "prenotazioni_in_coda) "
					+ "VALUES('" 		+ codice	+ "' , '"
										+ name		+ "' , '" 
										+ surname	+ "' , '" 
										+ cat 		+ "' , '" 
										+ title 	+ "', '"
									    + disp 	    + "', '"          
									    + pren_cod 	+ "')";
			return 	query;
		}

		public static void insertBooking(String query) throws SQLException
		{
			DBmanager.openConnection();
			DBmanager.executeUpdate(query);
			DBmanager.closeConnection();
		}
		
		/**
		 * Questo metodo inserisce i dati prenotazioni nella tabella prenotazioni
		 * @param codice  parametro che identifica la prenotazione di un libro
		 * @param id  parametro che identifica 'l id della prenotazione di un libro
		 * @param priorita  parametro che identifica la priorità della prenotazione di un libro
		 * @param data_inizio parametro che identifica la data inizio della prenotazione di un libro
		 * @return query inserisce le prenotazioni nella tabella prenotazioni
		 * @throws SQLException è l'eccezione di sql che avviene se ci sono problemi di comunicazione con il database
		 */
		public static String insertBookingGetQuery(
				int codice,
				int id,
				int priorita,
				Date data_inizio
				) throws SQLException
		{
			String data_F="";
			String query = 		"INSERT INTO prenotazioni("
					+ "codice, "
					+ "id, "
					+ "priorità,"
					+ "data_inizio )"
					+ "VALUES('" 		+ codice	            + "' , '"
										+ id		            + "' , '" 
										+ priorita				+ "' , '" 
			                            + data_inizio 	    + "')";
			return 	query;
		}		
		
		
		public static void insertLoans(String query) throws SQLException
		{
			DBmanager.openConnection();
			DBmanager.executeUpdate(query);
			DBmanager.closeConnection();
		}
		
		/**
         * @param codice parametro che identifica il codice della prentozioni di un libro
         * @param id parametro che identifica l'id della la prenotazione di un libro
         * @param data_inizio parametro che identifica la data inizio della prenotazione di un libro
         * @return query inserisce i libri nella tabella libro 
         * @throws SQLException è l'eccezione di sql che avviene se ci sono problemi di comunicazione con il database
		 */
		public static String insertLoansGetQuery(
				int codice,
				int id,
				Date data_inizio
				) throws SQLException
		{
			String data_F="";
			String falsita		= "false";
			
			String query = 		"INSERT INTO prestiti("
					+ "codice, "
					+ "id, "
					+ "data_inizio, "
					+ "rientrato, "
					+ "ritirato, "
					+ "scaduto, "
					+ "email_inviata)"
					+ "VALUES('" 		+ codice	            + "' , '"
										+ id		            + "' , '" 
										+ data_inizio        	+ "' , '" 
										+ falsita	          	+ "' , '"
										+ falsita           	+ "' , '"
			                            + falsita 	            + "' , '" 
			                            + falsita		 	    + "')";
			return 	query;
		}

/**
 * Questo metodo inserisce i dati dei prestiti
 * @param codice parametro che identifica il codice della prentozioni di un libro
 * @param id parametro che identifica l'id della la prenotazione di un libro
 * @param priorita parametro che identifica la priorità la prenotazione di un libro
 * @param data_inizio parametro che identifica la data inizio della prenotazione di un libro
 * @return query inserisce i libri nella tabella libro 
 * @throws SQLException è l'eccezione di sql che avviene se ci sono problemi di comunicazione con il database
 */
public static String insertLoansCodaGetQuery(		
				int codice,
				int id,
				int priorita,
				Date data_inizio 
				) throws SQLException
{			
			Calendar c = new GregorianCalendar();
			Date datacorrente = c.getTime();
			int pr = 10;

			String query = 	"INSERT INTO prenotazioni("
					+ "codice, "
					+ "id, "
					+ "priorità, "
					+ "data_inizio )"
					+ "VALUES('" 		+ codice	            + "' , '"
										+ id		            + "' , '" 
										+ priorita		        + "' , '" 
			                            + data_inizio	 	    + "')";
			return 	query;
}				
	


		
public static void insertLoansCoda(String q) throws SQLException
		{
			DBmanager.openConnection();
			DBmanager.executeUpdate(q);
			DBmanager.closeConnection();
}
		
}
