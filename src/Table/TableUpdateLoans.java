package Table;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import Core.Commands;
import connections.Client;
import database.MQ_Delete;
import java.sql.SQLException;
import javax.swing.JTable;


public class TableUpdateLoans {
	private static String input;
	private static List<String> rowData;
	private static int column;
	private static boolean notOk = false;

	 /**
	  * Questo metodo elimina una riga da prestiti utente
	 * @param r lista di prestiti
	 * @param t tabella prestiti
	 * @throws SQLException � l'eccezione di sql che avviene se ci sono problemi di comunicazione con il database
	 */
	public static void deleteRow(List<String> r, JTable t) throws SQLException
		{
		 	MQ_Delete.deleteRowLoans(r);
		}
	
	 /**
	  * Questo metodo elimina una riga da prestiti utente
	  * @param r lista di prestiti
	  * @param t tabella prestiti
	  * @param me Client
	  * @throws SQLException � l'eccezione di sql che avviene se ci sono problemi di comunicazione con il database
	 */
	public static void deleteRow(List<String> r, JTable t,Client me) throws SQLException
		{
		 	String q = MQ_Delete.deleteRowLoansGetQuery(r);
			me.setActTable(t);
			me.setSql(q);
			try {
				
				me.getCmdLIST().put(Commands.LoanDELETE);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}	 

	 
	public static int getColumn() {
		return column;
	}

	public static boolean isNotOk() {
		return notOk;
	}

	public static void setNotOk(boolean notOk) {
		TableUpdateLoans.notOk = notOk;
	}
	
	public static String getInput() {
		return input;
	}
	public static void setInput(String input) {
		TableUpdateLoans.input = input;
	}
	public static List<String> getRowData() {
		return rowData;
	}
	public static void setRowData(List<String> rowData) {
		TableUpdateLoans.rowData = rowData;
	}
	public int getColumn(DefaultTableModel model) {
		return column;
	}
	public static void setColumn(int column) {
		TableUpdateLoans.column = column;
	} 
}