package Core;

import connections.Message;
import connections.MessageRealServer;
import connections.ServerReal;

/**
 * @author Mauro De Salvatore
 *
 */
public class GuardianTimeOut extends Thread {
ServerReal	SrvR;
Guardian 	GpG;
int 		TOMillisenconds ;
Message 	Mess;		
	
/**creato dal Guardiano, monitorizza lo stato del messaggio con cui viene inizializzato, 
 * se dopo TOMmilliseconds di attesa il messaggio servito é lo stesso , sblocca il BUSY del Guardiano
 * ritenendolo bloccato da un eccezione imprevista
 * @param Gpg	Guardian
 * @param Mes	MessageRealServer
 * @param TOM	int, indica millisecondi di timeout oltre i quali il Guardian deve essere ricreato
 */
public GuardianTimeOut(Guardian Gpg,MessageRealServer Mes,int TOM) {
	GpG				=Gpg;
	TOMillisenconds	=TOM;
	Mess			=Mes.getMsg();
}
	
	@Override
	public void run() {
		
		try {
			Thread.sleep(TOMillisenconds);		
			
			if (GpG.getMesServing()!=null){// se sta servendo qualcosa...
				
				if (Mess.equalTo(GpG.getMesServing())){		//Medesimo Message (Richiesta) in lavorazione
					// Time Out
					GpG.setBusy(false);
				}				
			}else{
				
				// non sta servendo nulla...
				
			}
			

		
		} 	catch (InterruptedException e) {
			e.printStackTrace();
			}
	}


}
