package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import java.util.List;

/**
 * @author luca
 *
 */
public class MQ_Update {

	/**
	 * Questo metodo setta libro come LIBERO
	 * @param idbook	identificativo libro	identificativo libro
	 * @throws SQLException eccezione relativa comandi sql eccezione relativa comandi sqleccezione relativa comandi sql
	 */
	public static void updateBookFree(int idbook) throws SQLException
	{					
		
		String q = "UPDATE libro SET disponibilità = 'Libero' WHERE codice = '" + idbook + "';";
    	
		DBmanager.openConnection();
		DBmanager.executeUpdate(q);
		DBmanager.closeConnection();
	}
	
	
	

	/**
	 * Questo metodo aggiorna i campi del libro di un determinato codice
	 * @param cd	codice libro	
	 * @param input dati da inserire
	 * @param col	colonna
	 * @throws SQLException eccezione relativa comandi sql
	 */
	public static void updateTableCliente(String cd,String input, int col) throws SQLException
	{	
		
		String query = "UPDATE libro ";
		
		if(col == 1)
		{
			query += "SET nome_autore = '" + input + "'";
		}
		else if(col == 2)
		{
			query += "SET cognome_autore = '" + input + "'";
		}
		else if(col == 3)
		{
			query += "SET categoria = '" + input + "'";
		}
		else if(col == 4)
		{
			query += "SET titolo = '" + input + "'";
		}
		query += " WHERE codice = '" + cd + "';";
		
		DBmanager.openConnection();
		DBmanager.executeUpdate(query);
		DBmanager.closeConnection();
	}
	
	/**
	 * Questo metodo aggiorna i campi della tabella prestiti, quando un utente vuole prendere in prestito un libro
	 * @throws SQLException eccezione relativa comandi sql
	 * @param idut 		identificativo utente
	 * @param idbook 	identificativo libro
	 * @return 			ritorna 1 con procedimento ok
	 */
	public static int updateLoansRetired(int idut, int idbook) throws SQLException
	{	int prestitiaggiornati=0;
	System.out.println(90);
	System.err.println("idbook: " + idbook);
	String qctll = "select count (codice) from prestiti WHERE codice='"+idbook+"'AND rientrato = false AND ritirato= false AND " +
	"data_fine is null AND data_inizio is not null";
	
	
	try {
		DBmanager.openConnection();
		ResultSet rs = DBmanager.executeQuery(qctll);
		DBmanager.closeConnection();
		
		System.out.println(91);	
		if (!rs.isBeforeFirst()) {
			prestitiaggiornati=0;			
			System.out.println("NON HO TROVATO TUPLE DA AGGIORNARE");	
			
			return 0;
		}else {		
			rs.next();
			prestitiaggiornati=rs.getInt(1);
			
			System.err.println("getInt(1) "+rs.getInt(1));
			
			System.out.println("HO TROVATO TUPLE DA AGGIORNARE: "+prestitiaggiornati);

			System.err.println("ho contato tuple da aggiornare : "+prestitiaggiornati);	
			
			String qup = "update prestiti set ritirato = true WHERE codice='"+idbook+"'AND rientrato = false AND " +
					"ritirato= false AND data_fine is null AND data_inizio is not null";
		
		DBmanager.openConnection();
		DBmanager.executeUpdate(qup);
		DBmanager.closeConnection();			
			return 1;
		}
		
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println(92);
	}

	
		
		
	
		
	

		
		
		return prestitiaggiornati;
		
	}
	
	/**
	 * Questo metodo aggiorna tutti i campi di un utente dopo averli modificati
	 * @param input		dati da inserire
	 * @param col		colonna
	 * @param cd		codice libro
	 * @throws SQLException eccezione relativa comandi sql
	 */
	

	public static void updateTableLoans(String cd,String input, int col) throws SQLException
	{	
		
		String query = "UPDATE prestiti ";
		
		if(col == 0)
		{
			query += "SET codice = '" + input + "'";
		}
		else if(col == 1)
		{
			query += "SET id = '" + input + "'";
		}
		else if(col == 2)
		{
			query += "SET data_inizio = '" + input + "'";
		}
		else if(col == 3)
		{
			query += "SET data_fine = '" + input + "'";
		}
		query += " WHERE id = '" + cd + "';";
		
		DBmanager.openConnection();
		DBmanager.executeUpdate(query);
		DBmanager.closeConnection();
	}
	
	/**
	 * Questo metodo aggiorna tutti i campi di un utente dopo averli modificati
	 * @param r			lista parametri 
	 * @param input		dati da inserire 
	 * @param col		colonna
	 * @throws SQLException eccezione relativa comandi sql
	 */
	public static void updateModUser1(List<String> r,String input, int col) throws SQLException
	{	
		
		String query = "UPDATE utente ";
		
		if(col == 2)
		{
			query += "SET nome = '" + input + "'";
		}
		else if(col == 3)
		{
			query += "SET cognome = '" + input + "'";
		}
		else if(col == 4)
		{
			query += "SET email = '" + input + "'";
		}
		else if(col == 6)
		{
			query += "SET inquadramento = '" + input + "'";
		}
		else if(col == 7)
		{
			query += "SET password = '" + input + "'";
		}
		else if(col == 9)
		{
			query += "SET ntel = '" + input + "'";
		}
		else if(col == 10)
		{
			query += "SET tipo_utente = '" + input + "'";
		}
		query += " WHERE nome = '" + r.get(1) + "' cognome = '" + r.get(2) + "' email = '" + r.get(3) + "' inquadramento = '" + r.get(5) + "' password = '" + r.get(6) + "' ntel = '" + r.get(8) + "' tipo_utente = '" + r.get(9) + "';";
		
		DBmanager.openConnection();
		DBmanager.executeUpdate(query);
		DBmanager.closeConnection();
	}

	public static void updateModUser(String nome,  String cognome, String email,  String inq, String pass, String ntel,String tipo_utente) throws SQLException
	{	

		String query1 = "UPDATE utente SET nome = '" + nome + "', cognome = '" + cognome + "' , email = '" + email + "' , inquadramento = '" + inq +
				"' , password = '" + pass + "' , ntel = '" + ntel +"' , tipo_utente = '" + tipo_utente +"' WHERE email = '" + email +"';";
    	
		DBmanager.openConnection();
		DBmanager.executeUpdate(query1);
		DBmanager.closeConnection();
	}
	
	/**
	 * Questo metodo aggiorna la password di un utente (Panel di modifica dati)
	 * @param idus identificativo utente
	 * @param pass password
	 * @throws SQLException eccezione relativa comandi sql
	 */
	public static void updatePassMod(int idus,String pass) throws SQLException
	{	
		String q=null;
	    q = "UPDATE utente SET password = '" + pass + "' WHERE id  = '" + idus + "';";
    	
		DBmanager.openConnection();
		DBmanager.executeUpdate(q);
		DBmanager.closeConnection();
		
		
	}
	/**
	 * Questo metodo aggiorna la password di un utente se ha dimenticato la password(Password dimenticata)
	 * @param email	email
	 * @param pass	password
	 * @param i	contatore tentativi
	 * @throws SQLException eccezione relativa comandi sql
	 */
	public static void updatePassForgot(String email,String pass, int i) throws SQLException
	{	
		String query1 = "UPDATE utente SET email = '" + email + "', password = '" + pass + "' , password_temp = '" + i + "';";
    	
		DBmanager.openConnection();
		DBmanager.executeUpdate(query1);
		DBmanager.closeConnection();
	}
	/**
	 * Questo metodo aggiona la password guardando l'email inserita dall'utente
	 * @param email	email
	 * @param pass	password
	 * @throws SQLException eccezione relativa comandi sql
	 */
	public static void updatePassForgotDirect(String email,String pass) throws SQLException
	{	
		String query1 = "UPDATE utente SET  password = '" + pass + "' WHERE email = '" + email + "'';";
    	
		DBmanager.openConnection();
		DBmanager.executeUpdate(query1);
		DBmanager.closeConnection();
	}
	
	/**
	 * Questo metodo aggiona la password guardando l'email inserita dall'utente
	 * @param email	email
	 * @param pass	password
	 * @return q	query ottenuta
	 */
	public static String updateNewPassForgotGETQUERY (String email,String pass) {
		String q = "UPDATE utente SET password = '" + pass +"' WHERE email = '" + email +"';";
		return q;
	}
	
	public static void updateNewPassForgot(String q) throws SQLException
	{	
		//String query1 = "UPDATE utente SET password = '" + pass +"' WHERE email = '" + email +"';";
		DBmanager.openConnection();
		DBmanager.executeUpdate(q);
		DBmanager.closeConnection();
	}
	
	
	/**
	 * Questo metodo aggiorna i tentativi di password temp di un utente(se arriva a 5 password_temp_tentativi falliti)
	 * @param email	email
	 * @param tentativi tentativi
	 * @throws SQLException eccezione relativa comandi sql
	 */
	public static void updateLoginTry(String email, int tentativi) throws SQLException
	{	

		String tent= String.valueOf(tentativi);
		String query1 = "UPDATE utente SET password_temp_tentativi = '" + tent + "' WHERE email = '" + email + "';";
		
    	
		DBmanager.openConnection();
		DBmanager.executeUpdate(query1);
		DBmanager.closeConnection();
	}

	/**
	 * Questo metodo aggiorna i campi dei prestiti al ritorno di un libro 
	 * @param idbook	identificativo libro
	 * @param idus identificativo utente		identificativo utente
	 * @param datacorrente data corrente	data corrente
	 * @return q		query da riproporre al comando successivo 
	 * @throws SQLException eccezione relativa comandi sql
	 */
	public static String updateLoansReturnedGetQuery(int idbook,int idus, String datacorrente) throws SQLException
	{
		String q=null;
		//adatta data prima di passarla come stringa
		
		
		q = "UPDATE prestiti "
				+ "SET " 						+ 
				"data_fine = '"+datacorrente+"'," 	+ 
				"rientrato=true " 				+ 
				"where codice='"+idbook+"'and data_fine is null and ritirato = true and rientrato = false;";
		return q;
	}
	public static void updateLoansReturned(String q) throws SQLException
	{	
		DBmanager.openConnection();
		DBmanager.executeUpdate(q);
		DBmanager.closeConnection();
	}
	//UPDATE LOANS RETURNED	******************************************************************
	
	/**
	 * Questo metodo aggiorna i campi dell'utente dopo averli modificati dal pannello di modifica dati
	 * @param idus identificativo utente
	 * @param nome		nome
	 * @param cognome	cognome
	 * @param email		email
	 * @param inq		inquadramento
	 * @param ntel		telefonico
	 * @param tipo_utente	tipo utente
	 * @return q		query di ritorno
	 * @throws SQLException eccezione relativa comandi sql
	 */
	public static String updateModUserIdGetQuery(int idus, String nome,  String cognome, String email,  String inq, String ntel,String tipo_utente) throws SQLException
	{
		String q=null;
		
		
		q = "UPDATE utente SET nome = '" + nome + "', cognome = '" + cognome + "' , email = '" + email + "'"
				+ ", inquadramento = '" + inq + "' , ntel = '" + ntel +"' , tipo_utente = '" + tipo_utente +"' WHERE id = '" + idus +"';";
		return q;
	}
	

	
	public static void updateModUserIdbyQuery(String q)throws SQLException
	{		
		DBmanager.openConnection();
		DBmanager.executeUpdate(q);
		DBmanager.closeConnection();
		

	}
	
	/**
	 * Questo metodo aggiorna i campi dell'utente dopo averli modificati dal pannello di modifica dati 
	 * @param id		identificativo utente
	 * @param nome		nome
	 * @param cognome	cognome
	 * @param email		email
	 * @param inq		inquadramento
	 * @param ntel		telefonico
	 * @param pass		password
	 * @param tipo_utente	tipo utente
	 * @return user		dati utente
	 * @throws SQLException eccezione relativa comandi sql
	 */
	public static String[] updateModUserId(String id, String nome,  String cognome, String email,  String inq, String pass, String ntel,String tipo_utente) throws SQLException
	{		

		String query1 = "UPDATE utente SET nome = '" + nome + "', cognome = '" + cognome + "' , email = '" + email + "' , password = '" + pass +
				"' , inquadramento = '" + inq + "' , ntel = '" + ntel +"' , tipo_utente = '" + tipo_utente +"' WHERE id = '" + id +"';";
		
		
		DBmanager.openConnection();
		ResultSet rs = DBmanager.executeQuery(query1);
		
		List<String> results = new ArrayList<String>();
		String[] user = new String[8]; // nome,cognome,email,password,inquadramento,ntel,tipo_utente,numpren(mancante)
		
		if (!rs.isBeforeFirst()) 
		{
			results.add("Nessun Dato");
		}
		else
		{
			while(rs.next()) 
			{
				results.add(rs.getString("id"));
				results.add(rs.getString("nome"));
				results.add(rs.getString("cognome"));
				results.add(rs.getString("email"));
				results.add(rs.getString("password"));
				results.add(rs.getString("inquadramento"));
				results.add(rs.getString("ntel"));
				results.add(rs.getString("tipo_utente"));
			}
		}
		for(int i = 0; i<results.size(); i++)
		{
			user[i]=results.get(i);
		}
		
		rs.close();
		DBmanager.closeConnection();
		
		return user;
	}
	/**
	 * Questo metodo aggiorna tutti i campi della tabella setting
	 * @param localhost			indirizzo ip localhost
	 * @param lan				indirizzo ip lavoro in lan
	 * @param www				indirizzo ip lavoro su www
	 * @param srvtype			tipo server
	 * @param emailUSER			email utente
	 * @param emailPW			password utente
	 * @throws SQLException eccezione relativa comandi sql
	 */
	public static void updateSetting(	String localhost,  
										String lan, 
										String www,  
										String srvtype, 
										String emailUSER, 
										String emailPW) 	throws SQLException
	{	

	System.out.println(" 	alla q arrivano...  ");
	
	System.out.println(localhost+"\n"+lan+"\n"+www+"\n"+srvtype+"\n"+emailUSER+"\n"+emailPW);
	
	
	
	
	
		
		String query1 = "UPDATE setting SET "
						+ "local_host 		= '" + localhost + "', "
						+ "lan 				= '" + lan + "' , "
						+ "www 				= '" + www + "' , "
						+ "srvtype 			= '" + srvtype + "' , "
						+ "email 			= '" + emailUSER + "' , "
						+ "password 		= '" + emailPW +"'";
    	try {
			DBmanager.openConnection();
		DBmanager.executeUpdate(query1);
		DBmanager.closeConnection();	
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
		
	/**
	 * Questo metodo serve per dire che è stata inviata una email di richiesta di un prestito da parte di un utente
	 * @param idut		identificativo utente
	 * @param idlib		identificativo libro
	 * @throws SQLException eccezione relativa comandi sql
	 */
	public static void updateLoansEmailSent(	String idut,   
												String idlib) 	throws SQLException
							{	

							String query1 = "UPDATE prestiti set email_inviata = true where id='"+idut+"' and codice='"+idlib+"';";
							
							DBmanager.openConnection();
							DBmanager.executeUpdate(query1);
							DBmanager.closeConnection();
	}	
	
		/**
		 * Questo metodo aggiorna la disponibilità di un determinato libro(aggiorna lo stato da libero/in prestito/in coda)
		 * @param idlib			identificativo utente
		 * @param newStato		nuovo stato prestito
		 * @throws SQLException eccezione relativa comandi sql
		 */
		public static void updateLoansStato(String idlib,String newStato) 	throws SQLException
	{	
	
	String query1 = "UPDATE libro set disponibilità =  '"+newStato+"' where codice='"+idlib+"';";
	
	DBmanager.openConnection();
	DBmanager.executeUpdate(query1);
	DBmanager.closeConnection();
	}		
	
	
}
