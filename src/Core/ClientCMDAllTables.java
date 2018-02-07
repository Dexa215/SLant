package Core;
import java.sql.SQLException;
import connections.Client;
import connections.Message;
import connections.MessageBack;
import gui.ResearchBooks;


public class ClientCMDAllTables {


	/**accetta Client, spedisce comando getdatafortables al server con il quale ottiene dati per tutte 
	 * e tre le tabelle di researchbook il ritorno viene gestito da ATpopulateRES
	 * @param me	Client
	 * @throws SQLException eccezione  inerente operazioni sql
	 *  @throws InterruptedException eccezione inerente ad interrupted sql 
	 */
	public static void ATpopulate(Client me) throws  SQLException, InterruptedException {
		String mSg;
		Commands cmd = Commands.GetDataForTables;
		MessageBack Mb = new MessageBack();

		System.out.println("CLI :> Request ricevuto da GUI :> "+cmd.toString());
		if (!(me.isStubok())){
			Mb.setText(mSg = "CLI :>  nessuna connessione attiva , riprovare ");			
			System.out.println(mSg);			
			me.getActW().addMsg(new String ("Connection Test result"+mSg));
		}else{	
			System.out.println("CLI :> Stub OK");
			// **** Client crea Message						
			Message MsgSend = new Message(	
					cmd,						// Comando richiesto
					me.getCliType() ,			// tipo di Client , Admin,Librarian,Reader
					me.toString(),				// id Client
					me.getFbook(),					
					me.getFbooking(),
					me.getFloans()
					);
			//MsgSend.setUType(Clients.Librarian);
			// **** Client invia Message
			me.sendM(MsgSend, Mb);	
		}		
	}
	/**con le tabelle ottenute tramite ATpopulate, dopo il rientro dei dati dal server, 
	 * aggiorna dati delle tabelle della gui researchbook
	/**

	 * @param me	Client
	 * @param mes	String messaggio 
	 * @param Mb	MessageBack
	* @throws SQLException eccezione  inerente operazioni sql
	 * @throws InterruptedException eccezione inerente ad interrupted sql
	 */
	public static void ATpopulateRES(Client me,String mes,MessageBack Mb) throws SQLException, InterruptedException {
		
		System.out.println("ritornato per GETdataFORallTABLES RES");
		
		switch (mes){
		case "OK": 
			System.out.println("ritornato AT RES OK");
			me.getMeMain().setReady(true);
			//*****************************************************************
			if (!me.isRefreshData()) {
				//richiesto dalla prima apertura di searchbook...
				me.setActF(null);
				me.setSql(null);				
				me.getMeMain().setReady(true);
			}//apertura finesta reser
			//*****************************************************************
			me.setDatabook(		Mb.getDatabook());
			me.setDatabooking(	Mb.getDatabooking());
			me.setDataloans(	Mb.getDataloans());
			
			
			
			if ( me.getActW().getModel()=="search") {
			//aggiorno tables
			 ResearchBooks x = (ResearchBooks) me.getActW();
			 
			 Clients ClType = me.getCliType(); 
			 
			}

			me.setBusy(false);
			me.setRefreshData(false);
			azzerafiltri(me);
			break;
			
		case "NG": 
			
			if (!me.isRefreshData()) {
				//richiesto dalla prima apertura di searchbook...
				me.getMeMain().setReady(true);	
			}
			me.setBusy(false);
			me.setRefreshData(false);
			azzerafiltri(me);
			break;
		
		default:
			azzerafiltri(me);
			break;
			
		}
	}	
	


		public static void azzerafiltri(Client me) {
			me.setFbook(null);
			me.setFbooking(null);
			me.setFloans(null);	
		}	
}
