package connections;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import Core.Guardian;
import Core.Requests;
import gui.SystemServer;

public class Server {						
	private	static 			Server 			me;
	private static 			SystemServer 	meG		=null;
	
	private static 			String 			msg		=null;
	private static 			int 			srvconn = 0;
	
	private 				LinkedList<ServerReal>operatori;
	
	private 				Guardian		G;		
	private 				Requests 		R;

	private boolean			dbOK=false;
	
	
	
	public Server(){

		
	}
	public static void main(String[] args) throws Exception {
		me = new Server();
		me.setOperatori(new LinkedList<>());
		
		setMeG(new SystemServer(me));
		getMeG().getFrame().setVisible(true);
		
		while(!me.isDbOK()) {
			Thread.sleep(1000);
			getMeG().checkdb();
			getMeG().addMsg("SRV :> controllo db in corso...");
		}
		
		getMeG().addMsg("SRV :> controllo db OK ");
		
				
		
		ServerSocket serverSocket = new ServerSocket(IServer.PORT);
		System.out.println(msg = "xxx schoolLib Server Started: \n" + serverSocket);
		getMeG().addMsg(msg);

		me.setR(new Requests(10));
		me.setG(new Guardian(me,me.getR()));
		try {
			new Thread(me.getG()).start();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("problemi con creazione Guardian");
		}
		
		
	    while(true){
	    	try {
	    			System.out.println(msg="Connessioni attive : " + getSrvconn() + "\n Waiting a connection... ");
	    			getMeG().addMsg(msg);
	    			Socket socket = serverSocket.accept();
	    			
	    			ServerReal x = new ServerReal(socket,me);
	    			me.getOperatori().add(x);
	    			new Thread(x).start();
	    			
			} catch (Exception e) {
				
					e.printStackTrace();	
			} 

	    }
	}
	public static int getSrvconn() {
		return srvconn;
	}
	public static void setSrvconn(int srvconn) {
		Server.srvconn = srvconn;
	}
	public static void setSrvconnINC() {
		Server.srvconn++;
	}
	public static void setSrvconnDEC() {
		Server.srvconn--;
	}
	public static SystemServer getMeG() {
		return meG;
	}
	public static void setMeG(SystemServer meG) {
		Server.meG = meG;
	}
	public Guardian getG() {
		return G;
	}
	public void setG(Guardian g) {
		G = g;
	}
	public Requests getR() {
		return R;
	}
	public void setR(Requests r) {
		R = r;
	}

	public LinkedList<ServerReal> getOperatori() {
		return operatori;
	}

	public void setOperatori(LinkedList<ServerReal> operatori) {
		this.operatori = operatori;
	}

	public void removeOp(ServerReal x) {
		this.operatori.remove(x);
		setSrvconnDEC();
		
		System.out.println(msg="Connessioni attive : " + getSrvconn() + "\n Waiting a connection... ");
		getMeG().addMsg(msg);
		
		
	}
	public boolean isDbOK() {
		return dbOK;
	}
	public void setDbOK(boolean dbOK) {
		this.dbOK = dbOK;
	}

	
	
}
