package Table;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import Core.Commands;
import connections.Client;
import database.MQ_Delete;
import java.sql.SQLException;
import javax.swing.JTable;

public class TableUpdateBooking {
	private static String input;
	private static List<String> rowData;
	private static int column;
	private static boolean notOk = false;

	 /**
	  * Questo metodo elimina una riga da prenotazione utente
	 * @param r lista di prenotazioni
	 * @param t table prenotazioni
	 * @throws SQLException � l'eccezione di sql che avviene se ci sono problemi di comunicazione con il database
	 */
	public static void deleteRow(List<String> r, JTable t) throws SQLException
		{
		 	MQ_Delete.deleteRowBooking(r);
		}
	 
	 /**
	 * Questo metodo elimina una riga da prenotazione utente
	 * @param r lista di prenotazioni
	 * @param t table prenotazioni
	 * @param me Client
	 * @throws  SQLException � l'eccezione di sql che avviene se ci sono problemi di comunicazione con il database
	 */
	public static void deleteRow(List<String> r, JTable t,Client me) throws SQLException
		{
		    int idbook=Integer.valueOf(r.get(0));
		 	String q = MQ_Delete.deleteRowBookingGetQuery(idbook);
			me.setActTable(t);
			me.setSql(q);
			try {
				
				me.getCmdLIST().put(Commands.BookingListREMOVE);
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
		TableUpdateBooking.notOk = notOk;
	}
	
	public static String getInput() {
		return input;
	}
	public static void setInput(String input) {
		TableUpdateBooking.input = input;
	}
	public static List<String> getRowData() {
		return rowData;
	}
	public static void setRowData(List<String> rowData) {
		TableUpdateBooking.rowData = rowData;
	}
	public int getColumn(DefaultTableModel model) {
		return column;
	}
	public static void setColumn(int column) {
		TableUpdateBooking.column = column;
	} 
}