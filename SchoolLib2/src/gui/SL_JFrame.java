package gui;
import java.io.Serializable;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import connections.Client;


public class SL_JFrame extends JFrame implements Serializable  {		
	private static final long serialVersionUID = 1L;
//	School Lib JFrame	
	private String			Model;
	
	AppType					SL_Type;	
	Client					SL_Client;
	SL_JFrame  				SL_JF;
	
	public 	JTextField		text;
	public 	JPanel 			ac 			= new JPanel();
	
	public SL_JFrame (){		
	}
	
	public void addMsg(String msg){
		if (msg!=null) {
		System.out.println("ERRORE... "+msg);
			
			text.setText(msg);
		}else {
			text.setText(null);
		}
		
	}

	// SET GET Metod
	public void ClientAggiornaActW(){			
		getSL_Client().setActW((SL_JFrame)SL_JF);
	}
	
	public SL_JFrame getSL_JF() {
	return this;
	}	
	public void setSL_JF(SL_JFrame sLJF) {
		SL_JF = sLJF;
	}
	public Client getSL_Client() {
		return SL_Client;
	}
	public void setSL_Client(Client sLClient) {
		SL_Client = sLClient;
	}


	public AppType getSL_Type() {
		return SL_Type;
	}


	public void setSL_Type(AppType sL_Type) {
		SL_Type = sL_Type;
	}

	public String getModel() {
		return Model;
	}

	public void setModel(String model) {
		Model = model;
	}
}
