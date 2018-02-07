package gui;
import connections.*;
import database.CheckDBSetting;
import database.ChkDBandTab;
import database.DBmanager;
import database.MQ_Insert;
import database.MQ_Update;

import java.awt.Color;
import javax.swing.DropMode;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class SystemServer {

	
	private Server 	me ;
		
	// GRAFICA	
	private JFrame frame;
	private JTextArea text;
	
	public SystemServer(Server mme) throws Exception {
		setMe(mme);
		initialize();
	}
	
	private void initialize() {
		setFrame(new JFrame());
		getFrame().setBounds(100, 100, 510, 486);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().getContentPane().setLayout(null);
		
		JTextArea txtrCiao = new JTextArea();
		txtrCiao.setText("Server gui test ...");
		txtrCiao.setForeground(Color.WHITE);
		text=txtrCiao;
		txtrCiao.setBounds(27, 104, 442, 206);
		txtrCiao.setFocusTraversalPolicyProvider(true);
		txtrCiao.setFocusCycleRoot(true);
		txtrCiao.setDropMode(DropMode.INSERT);
		txtrCiao.setDragEnabled(true);
		txtrCiao.setDoubleBuffered(true);
		txtrCiao.setColumns(2);
		txtrCiao.setBackground(Color.RED);
		txtrCiao.setAutoscrolls(true);
		getFrame().getContentPane().add(txtrCiao);
		
		JLabel lblNewLabel = new JLabel("Finestra Terminale");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(27, 83, 442, 14);
		getFrame().getContentPane().add(lblNewLabel);
		
		JLabel lblSchoollibFinestraControllo = new JLabel("Server");
		lblSchoollibFinestraControllo.setForeground(Color.RED);
		lblSchoollibFinestraControllo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSchoollibFinestraControllo.setFont(new Font("Tahoma", Font.PLAIN, 50));
		lblSchoollibFinestraControllo.setBounds(10, 11, 459, 51);
		getFrame().getContentPane().add(lblSchoollibFinestraControllo);	
		
		JButton btnNewButton = new JButton("LOGIN Administrator");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
			
			}
		});
		btnNewButton.setBounds(27, 334, 442, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("crea batabase schoolib");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int ok = 0;
				
				try {
					DBmanager.openConnectionFirst();
					
					String query = "create database schoolib;";
					DBmanager.executeUpdate(query);
					ok=2;
					
					DBmanager.closeConnection();
					
				}catch (SQLException ee) {
					ok=1;
					System.out.println("err sql"+ee.getErrorCode());
					//ee.printStackTrace();
					try {
						DBmanager.closeConnection();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (Exception e) {						
					//e.printStackTrace();
					System.out.println("err gen");
					try {
						DBmanager.closeConnection();
					} catch (SQLException ea) {
						
						ea.printStackTrace();
					}
				}
				

				
				if(ok!=0) {
					
					try {
					DBmanager.openConnection();
					CheckDBSetting.TableExistSetting();
					} catch (Exception e) {
						try {
						DBmanager.closeConnection();
						} catch (SQLException e1) {
						e1.printStackTrace();
						}
					}
					
					try {
					DBmanager.openConnection();
					ChkDBandTab.tableExistPerson();
					} catch (Exception e) {
						try {
						DBmanager.closeConnection();
						} catch (SQLException e1) {
						e1.printStackTrace();
						}
					}
					
					try {
					DBmanager.openConnection();
					ChkDBandTab.tableExistBook();
					} catch (Exception e) {
						try {
						DBmanager.closeConnection();
						} catch (SQLException e1) {
						e1.printStackTrace();
						}
					}
					
					try {
					DBmanager.openConnection();
					ChkDBandTab.tableExistLoans();
					} catch (Exception e) {
						try {
						DBmanager.closeConnection();
						} catch (SQLException e1) {
						e1.printStackTrace();
						}
					}

					try {
					DBmanager.openConnection();
					ChkDBandTab.tableExistBooking();
					} catch (Exception e) {
						try {
						DBmanager.closeConnection();
						} catch (SQLException e1) {
						e1.printStackTrace();
						}
					}	
					
					
				}else {
					
				} 
					

				
			}
		});
		btnNewButton_1.setBounds(27, 368, 442, 23);
		frame.getContentPane().add(btnNewButton_1);
	}

	
	//-----------------------------------------------------------------------
			
	//-----------------------------------------------------------------------


	public JFrame getFrame() {
		return frame;
	}
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	
	public void addMsg(String msg){
		
		text.setText(msg);
		
	}

	public Server getMe() {
		return me;
	}

	public void setMe(Server me) {
		this.me = me;
	}
	
	
	
	
	
}
