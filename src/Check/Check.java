package Check;

import java.sql.SQLException;
import java.util.Random;

import database.MQ_Check;
import database.MQ_Read;

/**
 * @author Luca,Mauro,Matteo
 *
 */

public class Check {

	
	// METODI	

	/**
	 * @param s stringa della verifica del nome
	 * @return ritorna la stringa corretta del nome (pattern)
	 */
	public static boolean checkName(String s)
    {
	    s = s.trim();
	    
	    String nomePattern = new String("[a-zA-Z\\s]*");

	    if(s == null || s.equals(""))
	    {
	    	return false;
	    }

	    if(!s.matches(nomePattern))
	    {
	    	return false;
	    }

	    return true;
    }
	
	/**
	 * @param c stringa della verifica dell'inquadramento
	 * @return ritorna la stringa corretta dell'inquadramento (pattern)
	 */
	public static boolean checkInqu(String c)
    {
	    c = c.trim();
	    
	    String[] catPattern = new String[] {"Studente-1A",      "studente-1a",
	    		                            "Studente-2A",      "studente-2a",
	    		                            "Studente-3A",      "studente-3a",
	    		                            "Studente-4A",      "studente-4a", 
	    		                            "Studente-5A",      "studente-5a",
	    		                            "Studente-1B",      "studente-1b",
	    		                            "Studente-2B",      "studente-2b",
	    		                            "Studente-3B",      "studente-3b",
	    		                            "Studente-4B",      "studente-4b", 
	    		                            "Studente-5B",      "studente-5b",
	    		                            "Studente-1C",      "studente-1c",
	    		                            "Studente-2C",      "studente-2c",
	    		                            "Studente-3C",      "studente-3c",
	    		                            "Studente-4C",      "studente-4c", 
	    		                            "Studente-5C",      "studente-5c",
	    		                            "Insegnante",       "insegnante",
	    		                            "Tecnico",          "tecnico",                          
	    		                            "Amministrativo",   "amministrativo"  };

	    if(c == null || c.equals(""))
	    {
	    	return false;
	    }
	    
	    for(int i = 0; i < catPattern.length; i++)
    	{
    		if(catPattern[i].equals(c))
    		{
    			return true;
    		}
    	}
    	
    	return false;
    }
	/**
	 * @param c stringa della verifica della categoria
	 * @return ritorna la stringa corretta della categoria (pattern)
	 */
	public static boolean checkCat(String c)
    {
	    c = c.trim();
	    
	    String[] catPattern = new String[] {"romanzo","Romanzo","Giallo","giallo","Commedia","commedia","Fiaba","fiaba",
	    		                             "Fumetto","fumetto","Narrativo","narrativo","Poesia","poesia","Racconto","racconto",
	    		                             "Fantasy","fantasy","Azione","azione","Drammatico","drammatico","Favola","favola",
	    		                             "Fantascienza","fantascienza","Novella","novella","Thriller","thriller","Umoristico",
	    		                             "umoristico","Avventura","avventura","Western","western","Psicologico","psicologico",
	    		                             "Storico","storico"};

	    if(c == null || c.equals(""))
	    {
	    	return false;
	    }
	    
	    for(int i = 0; i < catPattern.length; i++)
    	{
    		if(catPattern[i].equals(c))
    		{
    			return true;
    		}
    	}
    	
    	return false;
    }

	
	/**
	 * @param a stringa della verifica del telefono
	 * @return ritorna la stringa corretta del telefono (pattern)
	 */
	public static boolean checkTel(String a)
    {		
		a = a.trim();
		
		String Telpattern = "[0-9]{10}$";
		
		if (a==null || a.equals(""))
			
		{
			
	    return false;
		}
		
		
		if(!a.matches(Telpattern))
		{
			
		return false;
		
		}
		return true;
    }

    /**
     * @param m  stringa della verifica dell'email
     * @return ritorna la stringa corretta dell'email (pattern)
     */
    public static boolean checkMail(String m)
    {
	    m = m.trim();
	    
	    String emailPattern = new String("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	
	    if(m == null || m.equals(""))
	    {
	    	return false;
	    }
	    
	    if(!m.matches(emailPattern))
	    {	
	        return false;
	    }    
	
	    return true;
    }
    
 
    /**
     * @param m controlla che esiste una email nel database
     * @return email dell'utente 
     */
    public static boolean checkMailExist(String m)
    {
		String results = null;
		
		try 
		{
			results = MQ_Check.selectMail(m);
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		if(results.equals("No Data"))
		{
			return false;
		}
		else
		{
			return true;
		}	
    }

    /**
     * Questo metedo verifica che nel database ci sia già una password
     * @param m controlla che esiste una password nel database
     * @return password dell'utente 
     */
    public static boolean checkPassExist(String m)
    {
		String results = null;
		
		try 
		{
			results = MQ_Check.selectPass(m);
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		if(results.equals("No Data"))
		{
			return false;
		}
		else
		{
			return true;
		}	
    }
   
    
	/**
	 * Questo metedo verifica che nel database ci sia già una password
	 * @param c controlla che esiste una password nel database
	 * @return controlla la password
	 */
	public static boolean checkPass(char[] c)
    {
    	String p = new String(c);
    	
	    p = p.trim();
	    
	    String emailPattern = new String("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})");

	    if(p == null || p.equals(""))
	    {
	    	return false;
	    }
	    
	    if(!p.matches(emailPattern))
	    {	
	        return false;
	    }    

	    return true;
    }

	/**
	 * @param c1 controlla la prima password
	 * @param c2 controlla la seconda password
	 * @return se sono uguali
	 */
	public static boolean checkPassEq(char[] c1,char[] c2)
    {
		boolean isUguale = false;
    	String p1 = new String(c1);
    	String p2 = new String(c2);
    	if((checkPass(c1)) && (checkPass(c2)))
    	{
    		isUguale = (p1.equals(p2));
    	}
    	
    	return isUguale;
    }
	/**
	 * @param b controlla il pattern del codice fiscale
	 * @return il codice fiscale corretto
	 */
	public static boolean checkCF(String b)
    {
    	
	    b = b.trim();
	    
	    String CodFisPattern = new String("[A-Z0-9]{16}$");

	    if(b == null || b.equals(""))
	    {
	    	return false;
	    }
	    
	    if(!b.matches(CodFisPattern))
	    {	
	        return false;
	    }    

	    return true;
    }
	
	
	/**
	 * Questo metedo mi restituisce la verifica che nel database ci sia un codice fiscale esistente
	 * @param c nel database se esiste un codice fiscale esistente
	 * @return ritorna un codice fiscale
	 */
	public static boolean checkCodFisExist(String c)
	{
		String results = null;
		
		try 
		{
			results = MQ_Check.selectCFexist(c);
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		if(results.equals("No Data"))
		{
			return false;
		}
		else
		{
			return true;
		}	
	}
	
	/**
	 * @param s controlla se sintatticamente è corretto il titolo di un libro
	 * @return ritorna vero se sintatticamente è corretto
	 */
	public static boolean checkTitle(String s)
    {
	    s = s.trim();
	    
	    String nomePattern = new String("[a-zA-Z0-9\\s]*");

	    if(s == null || s.equals(""))
	    {
	    	return false;
	    }

	    if(!s.matches(nomePattern))
	    {
	    	return false;
	    }

	    return true;
    }
	 
	/**
	 * Questo metodo mi restituisce una password random
	 * @return saltStr ritorna password random
	 */
	public static String s(){

		String SALTCHARS = "abcdefghilmnopqrstuvz";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 6) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append("P" + SALTCHARS.charAt(index) + "20$");
        }
        String saltStr = salt.toString();
        System.out.println(saltStr);
        return saltStr;
		}
	 
	 // esamina password TEMPORANEA
	 /**
	 * Questo metodo controlla, nel database, i dati inseriti per loggarsi la prima volta
	 * @param email di un utente
	 * @param pass di un utetne 
	 * @return stringa Login Corretto o Password Errata
	 * @throws SQLException è l'eccezione di sql che avviene se ci sono problemi di comunicazione con il database
	 */
	public static String checkAdminLogInFIRST(String email, String pass) throws SQLException {
	    	

	    	if(email.equals(null) || email.equals("") || pass.equals(null) || pass.equals("")){
	    			return new String("I Campi Non Possono Essere Vuoti");
	    	}else{
	    			
	    			//**********************************************************
	    			String[] datiUtente= MQ_Read.selectAdminLogInFIRST(email, pass);
	    			//**********************************************************
	    			
	    			if(datiUtente[0].equals("Nessun Dato")||datiUtente[0].equals(""))	{
	   	        			return new String("L'Email Non Esiste");
	   	        	}else{        		
	   	        			if(pass.equals(datiUtente[1])){
	   	        					return new String("Login Corretto");
	   	        			}else{
	    							return new String("Password Errata");
	   	        			}
	   	        	}

	    	}
	    }
	 
	 // esamina password 
     /**
     *  Questo metodo controlla, nel database, i dati inseriti per loggarsi dal bottone principale
     * @param email di un utente
     * @param pass di un utente
     * @return stringa Login Corretto o Password Errata
     * @throws SQLException è l'eccezione di sql che avviene se ci sono problemi di comunicazione con il database
     */
    public static String[] checkAdminLogIn(String email, String pass) throws SQLException
       {

    	 
         String [] res= new String[7];


           
           if(email.equals(null) || email.equals("") || pass.equals(null) || pass.equals(""))
           {
               res[0]=new String("I Campi Non Possono Essere Vuoti");
               return res; 
           }
           else
           {
               String[] datiUtente= MQ_Read.selectAdminLogIn(email, pass);
           
               if(datiUtente[0].equals("Nessun Dato")||datiUtente[0].equals(""))
               {
                   res[0]=new String("L'Email Non Esiste");
                   return res;                     
                   
               }
               else
               {                
                  
                   if(pass.equals(datiUtente[2]))
                   {
                       res[0]=new String("Login Corretto");
                       res[1]=datiUtente[0];//id
                       res[2]=datiUtente[1];//mail
                       res[3]=datiUtente[2];//pw
                       res[4]=datiUtente[3];//nome
                       res[5]=datiUtente[4];//cognome
                       res[6]=datiUtente[5];//tipo

                       return res;     
                       
                       
                       
                   }
                   else
                   {
                       res[0]=new String("Password Errata");
                       return res; 
                       
                       
                   }
               }
           }
       }
	  /**
	 * @param mail di un utente
	 * @param pass di un utente
	 * @param checkPassword controlla se la password è corretta
	 * @return controlla che tutti i metodi sono corretti
	 */
	public static boolean checkAllPassForg(String mail, char[] pass,char[] checkPassword )
	    {
	    
			return checkMail(mail) && checkMailExist(mail) && checkPass(pass) &&  checkPass(checkPassword) && checkPassEq(pass, checkPassword);
	    }
	  
	  /**
	 * @param pass di un utente
	 * @param checkPassword controlla se la password è corretta
	 * @return controlla che tutti i metodi sono corretti
	 */
	public static boolean checkAllPassMod(char[] pass,char[] checkPassword )
	    {
	    
			return checkPass(pass) &&  checkPass(checkPassword) && checkPassEq(pass, checkPassword);
	    }
	 
	  /**
	 * @param nome di utente
	 * @param cognome di utente
	 * @param mail di utente
	 * @param pass di utente
	 * @param checkPassword controlla se la password è corretta
	 * @param inq  di utente
	 * @param tel  di utente
	 * @return controlla che tutti i metodi sono corretti
	 */ 
	public static boolean checkAllRegMod(String nome, String cognome,String mail, char[] pass,char[] checkPassword,String inq,String tel )
	    {
			return checkName(nome) && checkName(cognome) && checkMail(mail) && checkPass(pass) && checkPass(checkPassword) && 
	    			checkPassEq(pass, checkPassword) && checkInqu(inq) && checkTel(tel);
	    }
	 /**
	 * @param nome di utente
	 * @param cognome di utente
	 * @param telefono di utente
	 * @param pass di utente
	 * @param passC di utente
	 * @param inq di utente
	 * @return controlla che tutti i metodi sono corretti
	 */
	public static boolean checkAllReg(
				 String nome, 
				 String cognome, 
				 String telefono, 
				 char[] pass, 
				 char[] passC, 
				 String inq)
	    {
		 
	    	return 	checkName(nome) 					&& 
	    			checkName(cognome) 					&& 
	    			checkTel(telefono) 					&& 
	    			checkPass(pass)	    				&& 
	    			checkPass(passC) 					&& 
	    			checkPassEq(pass, passC) 			&&
	    			checkInqu(inq);
	    
		 
	    }
	 
	 
	 /**
	 * @param nome di un autore
	 * @param cognome di un autore
	 * @param categoria di un libro
	 * @param titolo di un libro
	 * @return controlla che tutti i metodi sono corretti
	 */
	public static boolean checkAllBooks(String nome, String cognome, String categoria, String titolo)
	    {
	    	return checkName(nome) && checkName(cognome) && checkCat(categoria) && checkTitle(titolo);
	    }
	
	 
}