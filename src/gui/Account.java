package gui;


import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Check.Check;
import Check.PasswordBox;
import Check.PopUp;
import Core.Clients;
import Core.Commands;

import connections.Client;

import database.MQ_Update;

import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;
import java.awt.Font;

/**
 * @author luca
 *
 */
public class Account extends SL_JFrame{
	

	private static final long 	serialVersionUID = 1L;
	private static String[] 	UserData = null;
	static int 			userRow = 0;
    static int			rows = 0;
    static int			cols = 0; 		    
		    
	private Account 			w;	
	private AppType				t;
	
	private String				emailOLD;
	
	private JFrame 				frmSchoolib;
	private Client 				me;
	private JPanel 				panelAccount;
	private JPanel 				panelModify;	

	private ImageIcon 			iconLogoT;
	private ImageIcon 			iconLogoC;
	private ImageIcon 			iconLogoRA;
	private ImageIcon 			iconLogoQ;
	
	private JLabel              lblNome;
	private JLabel              lblCognome;
	private JLabel              lblPass;
	private JLabel              lblEmail;
	private JLabel              lblInq;
	private JLabel              lblTipoUte ;
	private JLabel              lblTel ;
	
    private JLabel		 		lblSetNome;
    private JLabel 				lblSetCognome;
    private JLabel 				lblSetEmail;
    private JLabel 				lblSetPass;
    private JLabel 				lblSetInq;
    private JLabel 				lblSetTipoUte;
    private JLabel 				lblSetTel;
    private JLabel 				lblReturnBack;

	private	JLabel 				lblChangeNameCheck; 
	private	JLabel 				lblChangeSurnameCheck; 
    private JLabel 				lblChangeEmailCheck;
	private	JLabel 				lblChangePassCheck;
	private	JLabel 				lblChangePassConfCheck ;
	private	JLabel 				lblChangeInqCheck ;
	private	JLabel 				lblChangePhoneCheck ;
    private JLabel 				lblMAIL;
	private JRadioButton 		rdbtnTypeUserLibMod;
	private JRadioButton 		rdbtnTypeUserLetMod;
	private JPasswordField 		passwordFieldMod;
	private JPasswordField 		passwordFieldConfMod;
	private JTextField 			txtNameMod;
	private JTextField 			txtSurnameMod;
	private JTextField 			txtMailMod;
	private JTextField 			txtInqMod;
	private JTextField 			txtTelMod;
	private JTextField 			passwordFieldMod1;
	private JTextField 			passwordFieldConfMod1;
    String 				r = null;	
	public 	String 				p1,p2,p3,p4,p5,p6,p7 = null; 	
	private String 				mailcheckResult;
	private boolean 			mailcheckinprogress=false;	
	private JLabel lblCheckChangePass;
	private JLabel lblCheckChangePassC;
	private JLabel lblPopUpInq;
	private JLabel lblPopUpTel;
	private JLabel lblPopUpPass;
	private String TypePerson = "Lettore";
	private String 		emailuser;
	private boolean 	cambioemail=false;
	private String [] 	userdata;
	private JPanel panelChangePass;
	public static boolean ModPass = false;
	private JPasswordField passwordField;
    private int 				idUser ;

	
	
	public Account(Component c,Client x)
	{
		super.setSL_Type(AppType.AppAccount);
		setT(AppType.AppAccount);
		
		
		setW(this);
		me = x;
		me.setActW(this);
		me.setActC(c);
		me.setCliType(Clients.Reader);		
		
		Account(c);
		
	}
	
	public void Account(Component c) {
		
		frmSchoolib = new JFrame();
		frmSchoolib.setTitle("Schoolib");
		frmSchoolib.setBounds(100, 100, 893, 545);
		frmSchoolib.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmSchoolib.setLocationRelativeTo(c);
		frmSchoolib.setVisible(true);
		frmSchoolib.getContentPane().setLayout(new CardLayout(0, 0));
	
		ImageIcon iconLogoT = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/Tick.png")));
		setIconLogoT(iconLogoT);
		
		ImageIcon iconLogoC = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/Cross.png")));
		setIconLogoC(iconLogoC);
		
		ImageIcon iconLogoRA = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/RedArrow.png")));
		setIconLogoRA(iconLogoRA);
		
		ImageIcon iconLogoQ = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/question.png")));
		setIconLogoQ(iconLogoQ);
		
		panelAccount = new JPanel();
		frmSchoolib.getContentPane().add(panelAccount, "name_353435345061838");
		panelAccount.setLayout(null);
		
		panelModify = new JPanel();
		frmSchoolib.getContentPane().add(panelModify, "name_454607080642439");
		panelModify.setLayout(null);
		
		panelChangePass = new JPanel();
		frmSchoolib.getContentPane().add(panelChangePass, "name_443629321471336");
		panelChangePass.setLayout(null);
			
		lblNome = new JLabel("Nome: ");
		lblNome.setBounds(10, 31, 42, 30);
		panelAccount.add(lblNome);
		
	    lblSetNome = new JLabel();
		lblSetNome.setBounds(118, 31, 174, 20);
		panelAccount.add(lblSetNome);
		
		lblCognome = new JLabel("Cognome: ");
		lblCognome.setBounds(10, 84, 98, 14);
		panelAccount.add(lblCognome);
		
		lblSetCognome = new JLabel();
		lblSetCognome.setBounds(118, 78, 174, 20);
		panelAccount.add(lblSetCognome);
		
		lblPass = new JLabel("Password:");
		lblPass.setBounds(10, 173, 86, 14);
		panelAccount.add(lblPass);
		
		lblSetPass = new JLabel();
		lblSetPass.setBounds(118, 173, 174, 20);
		panelAccount.add(lblSetPass);
		
		lblEmail = new JLabel("Email: ");
		lblEmail.setBounds(10, 130, 46, 14);
		panelAccount.add(lblEmail);
		
		lblSetEmail = new JLabel();
		lblSetEmail.setBounds(118, 124, 174, 20);
		panelAccount.add(lblSetEmail);
			
		lblInq = new JLabel("Inquadramento: ");
		lblInq.setBounds(10, 217, 137, 14);
		panelAccount.add(lblInq);
		
		lblSetInq = new JLabel();
		lblSetInq.setBounds(140, 211, 174, 20);
		panelAccount.add(lblSetInq);
		
		lblTipoUte = new JLabel("Tipo Utente:");
		lblTipoUte.setBounds(10, 258, 74, 14);
		panelAccount.add(lblTipoUte);
		
		lblSetTipoUte = new JLabel();
		lblSetTipoUte.setBounds(118, 252, 186, 20);
		panelAccount.add(lblSetTipoUte);
		
		lblTel = new JLabel("Telefono:");
		lblTel.setBounds(10, 300, 59, 14);
		panelAccount.add(lblTel);
		
		lblSetTel = new JLabel();
		lblSetTel.setBounds(118, 294, 184, 20);
		panelAccount.add(lblSetTel);
		
// PANEL ACCOUNT // ***************************************************************************************************
		
		lblReturnBack = new JLabel();
		lblReturnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				 WindowEvent close = new WindowEvent(frmSchoolib, WindowEvent.WINDOW_CLOSING);
				 frmSchoolib.dispatchEvent(close);
			}
		});
		lblReturnBack.setBounds(835, 11, 30, 30);
		lblReturnBack.setIcon(getIconLogoRA());
		lblReturnBack.setBorder(null);
		panelAccount.add(lblReturnBack);
		
		JButton btnDelete = new JButton("Cancella Profilo");
		btnDelete.setBackground(Color.RED);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
             PopUp.warningBox(frmSchoolib,"Questa azione cancellerÓ in modo completo e definitivo il profilo utenete attualmente in uso !!!");
				if(PopUp.confirmBox(frmSchoolib))
				{
					int idUs = getIdUser();
					
					me.setIdut(idUs);				
					me.setActW(getW());
					me.setActF(frmSchoolib);
					me.setActC(c);				
					try {
						System.out.println("GUI account:> ottenuti dati user ");
					me.setCliType(Clients.Librarian);	
						me.getCmdLIST().put(Commands.UserDELETE);
					} catch (InterruptedException e2) {
						System.out.println("GUI account:> NON ottenuti dati user ");	
						e2.printStackTrace(); 
					}
					//*************************************************************	
					
			}
			}
		});
		btnDelete.setBounds(192, 381, 193, 54);
		panelAccount.add(btnDelete);
// Panel Account
		
			JButton btnModify = new JButton("Modifica Profilo");
			btnModify.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					panelAccount.setVisible(false);
					panelModify.setVisible(true);				
					//************************************************************
					String email = lblSetEmail.getText();
					me.setSql(email);				
					me.setActW(getW());
					me.setActF(frmSchoolib);
					me.setActC(c);				
					try {
						System.out.println("GUI account:> ottenuti dati user ");
					//me.setCliType(Clients.Librarian);	
						me.getCmdLIST().put(Commands.UserREADbyEmail);
					} catch (InterruptedException e2) {
						System.out.println("GUI account:> NON ottenuti dati user ");	
						e2.printStackTrace(); 
					}
					//*************************************************************
					System.out.println(" gui account comando modifica  ");	
					System.err.println("finesta attiva "+me.getActW().toString());
					
					Account x = (Account)me.getActW();
					x.setEmailOLD(email); // vedere qui
					
					System.err.println("finesta attiva tipo: "+ x.getT());
					
					
				}
			});
			btnModify.setBounds(428, 381, 186, 54);
			panelAccount.add(btnModify);

		
// PANEL MODIFY // ****************************************************************************************************
		
		lblPopUpInq = new JLabel();
		lblPopUpInq.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//Per informazioni cercare la classe PopUp
				PopUp.infoBox(frmSchoolib,"Si deve mettere uno tra questi campi: \n"
						       +          "Studente-1A,Studente-1B,Studente-1C,Studente-2A,Studente-2B,Studente-2C, \n"
						       +          "Studente-3A,Studente-3B,Studente-3C,Studente-4A,Studente-4B,Studente-4C, \n"
						       +          "Studente-5A,Studente-5B,Studente-5C,Insegnante,Tecnico,Amministrativo");
			}
		});
		lblPopUpInq.setIcon(iconLogoQ);
		lblPopUpInq.setBounds(682, 11, 16, 16);
		panelModify.add(lblPopUpInq);
		
		lblPopUpTel = new JLabel();
		lblPopUpTel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//Per informazioni cercare la classe PopUp
				PopUp.infoBox(frmSchoolib,"Immettere 10 numeri");
			}
		});
		lblPopUpTel.setIcon(iconLogoQ);
		lblPopUpTel.setBounds(682, 85, 16, 16);
		panelModify.add(lblPopUpTel);
		
		
		JLabel lblNameMod = new JLabel("Nome");
		lblNameMod.setBounds(10, 29, 127, 23);
		panelModify.add(lblNameMod);
		
		JLabel lblChangeNameCheck = new JLabel();
		setLblChangeNameCheck(lblChangeNameCheck);
		lblChangeNameCheck.setBounds(362, 31, 21, 19);
		panelModify.add(lblChangeNameCheck);
		
		
		JLabel lblSurnameMod = new JLabel("Cognome");
		lblSurnameMod.setBounds(10, 101, 127, 23);
		panelModify.add(lblSurnameMod);
		
		JLabel lblChangeSurnameCheck = new JLabel();
		setLblChangeSurnameCheck(lblChangeSurnameCheck);
		lblChangeSurnameCheck.setBounds(362, 105, 21, 19);
		panelModify.add(lblChangeSurnameCheck);
		
		JLabel lblMailMod = new JLabel("Email");
		lblMailMod.setBounds(10, 172, 127, 23);
		panelModify.add(lblMailMod);
		
		JLabel lblChangeEmailCheck = new JLabel();
	    setLblMAIL(lblChangeEmailCheck);	
		lblChangeEmailCheck.setBounds(362, 176, 21, 19);
		panelModify.add(lblChangeEmailCheck);
		
		JLabel lblInqMod = new JLabel("Inquadramento");
		lblInqMod.setBounds(431, 33, 132, 14);
		panelModify.add(lblInqMod);
		
		JLabel lblChangeInqCheck = new JLabel();
		setLblChangeInqCheck(lblChangeInqCheck);
		lblChangeInqCheck.setBounds(807, 33, 21, 19);
		panelModify.add(lblChangeInqCheck);
		
		JLabel lblTelMod = new JLabel("Telefono");
		lblTelMod.setBounds(431, 105, 132, 14);
		panelModify.add(lblTelMod);
		
		JLabel lblChangePhoneCheck = new JLabel();
		setLblChangePhoneCheck(lblChangePhoneCheck);
		lblChangePhoneCheck.setBounds(807, 105, 21, 19);
		panelModify.add(lblChangePhoneCheck);
		
		JLabel lblTypeUserMod = new JLabel("Tipo Utente");
		lblTypeUserMod.setBounds(379, 226, 157, 19);
		panelModify.add(lblTypeUserMod);
		
		txtNameMod = new JTextField();
		txtNameMod.setEditable(false);
		txtNameMod.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtNameMod.setEditable(true);
				txtNameMod.getCaret().setVisible(true);
			}
		});
		txtNameMod.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				checkname();
			}
		
			
		});
		txtNameMod.setBounds(120, 30, 224, 20);
		panelModify.add(txtNameMod);
		txtNameMod.setColumns(10);
		
		txtSurnameMod = new JTextField();
		txtSurnameMod.setEditable(false);
		//txtSurnameMod.setText(user[2]);
		txtSurnameMod.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtSurnameMod.setEditable(true);
				txtSurnameMod.getCaret().setVisible(true);
			}
		});
		txtSurnameMod.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				checksurname();
			}
		});
		txtSurnameMod.setBounds(120, 102, 224, 20);
		panelModify.add(txtSurnameMod);
		txtSurnameMod.setColumns(10);
		
		setTxtMailMod(new JTextField());
		getTxtMailMod().setEditable(false);
		//txtMailMod.setText(user[3]);
		getTxtMailMod().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getTxtMailMod().setEditable(true);
				getTxtMailMod().getCaret().setVisible(true);
			}
		});
		getTxtMailMod().addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if (!getTxtMailMod().equals(emailOLD)) {			
					
			
					//checkmail();
				
				
				}
			}
		});
		getTxtMailMod().setBounds(120, 173, 224, 20);
		panelModify.add(getTxtMailMod());
		getTxtMailMod().setColumns(10);
	   
		
		txtInqMod = new JTextField();
		txtInqMod.setEditable(false);
		txtInqMod.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtInqMod.setEditable(true);
				txtInqMod.getCaret().setVisible(true);
			}
		});
		txtInqMod.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				
				if(Check.checkInqu(txtInqMod.getText()))		//Controllo sintattico
				{
					lblChangeInqCheck.setIcon(iconLogoT);
				}
				else
				{
					lblChangeInqCheck.setIcon(iconLogoC);
				}
			}
		});
		
		
		txtInqMod.setBounds(573, 30, 224, 20);
		panelModify.add(txtInqMod);
		txtInqMod.setColumns(10);
		
		txtTelMod = new JTextField();
		txtTelMod.setEditable(false);
		txtTelMod.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtTelMod.setEditable(true);
				txtTelMod.getCaret().setVisible(true);
			}
		});
		txtTelMod.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				
				if(Check.checkTel(txtTelMod.getText()))			//Controllo sintattico
				{
					lblChangePhoneCheck.setIcon(iconLogoT);
				}
				else
				{
					lblChangePhoneCheck.setIcon(iconLogoC);
				}
			}
		});
		txtTelMod.setBounds(573, 102, 224, 20);
		panelModify.add(txtTelMod);
		txtTelMod.setColumns(10);
		
		rdbtnTypeUserLibMod = new JRadioButton("Libraio");
		rdbtnTypeUserLibMod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rdbtnTypeUserLibMod.isSelected())
				{
					TypePerson = "Libraio";
				}
			}
		});
		rdbtnTypeUserLibMod.setBounds(284, 277, 109, 23);
		panelModify.add(rdbtnTypeUserLibMod);
		
		rdbtnTypeUserLetMod = new JRadioButton("Lettore");
		rdbtnTypeUserLetMod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rdbtnTypeUserLetMod.isSelected())
				{
					TypePerson = "Lettore";
				}
			}
		});
		rdbtnTypeUserLetMod.setBounds(448, 277, 109, 23);
		panelModify.add(rdbtnTypeUserLetMod);
		
		ButtonGroup bgMod = new ButtonGroup();
		bgMod.add(rdbtnTypeUserLibMod);
		bgMod.add(rdbtnTypeUserLetMod);	
		
		JButton btnModData = new JButton("Modifica Dati");
		btnModData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				//Assegna a delle variabili il contenuto dei text field
				
				String nome 			= txtNameMod.getText();
				String cognome 			= txtSurnameMod.getText();
				String mail 			= getTxtMailMod().getText();
				String inq 				= txtInqMod.getText();
				String tel 				= txtTelMod.getText();
				String stato 			= TypePerson;
				System.out.println("1");				

				
				//************************************************************
				try
				{

				String Q = MQ_Update.updateModUserIdGetQuery(getIdUser(), nome, cognome, mail, inq, tel, stato);
				me.setIdut(getIdUser());
				me.setSql(Q);
				me.setSql2(getTxtMailMod().getText());					
				me.setActW(getW());
				me.setActF(frmSchoolib);
				me.setActC(c);				
				try {
					System.out.println("GUI account:> ottenuti dati user ");
				me.setCliType(Clients.Librarian);	
					me.getCmdLIST().put(Commands.UserUPDATE);
				} catch (InterruptedException e2) {
					System.out.println("GUI account:> NON ottenuti dati user ");	
					e2.printStackTrace(); 
				}
				}
				catch (SQLException e) 
				{
					e.printStackTrace();
				}					
				//*************************************************************	
				
								 
		}		
	});
	
		btnModData.setBounds(301, 391, 175, 67);
		panelModify.add(btnModData);
		
		JButton btnBackData = new JButton("Annulla");
		btnBackData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				PopUp.warningBox(frmSchoolib, "Le modifiche effettuate verranno annullate");
				
				if(PopUp.confirmBox(frmSchoolib))
				{
					
					panelAccount.setVisible(true);
					panelModify.setVisible(false);
					panelChangePass.setVisible(false);
					
					txtNameMod.setText(null);
					txtSurnameMod.setText(null);
					getTxtMailMod().setText(null);
					txtInqMod.setText(null);
					txtTelMod.setText(null);	
		
					txtNameMod.setEditable(false);
					txtSurnameMod.setEditable(false);
					getTxtMailMod().setEditable(false);
					txtInqMod.setEditable(false);
					txtTelMod.setEditable(false);
					
					lblChangeNameCheck.setIcon(null);
					lblChangeSurnameCheck.setIcon(null);
					getLblMAIL().setIcon(null);
					lblChangeInqCheck.setIcon(null);
					lblChangePhoneCheck.setIcon(null);
				}
			}
		});
		btnBackData.setBounds(523, 391, 175, 67);
		panelModify.add(btnBackData);
		
		System.out.println("leggo dal campo email... "+getTxtMailMod());
		
		JButton btnChangePassword = new JButton("Cambia Password");
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
			    PasswordBox p = new PasswordBox();
				p.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				p.setLocationRelativeTo(panelModify);
				
				p.addWindowListener(new WindowAdapter() {

					  public void windowClosed(WindowEvent e)
					  {
							if(ModPass)
							{
				panelModify.setVisible(false);
				panelAccount.setVisible(false);
				panelChangePass.setVisible(true);
				ModPass = false;
			}			
		}
	});;
	p.setSize(300,150);
	p.setVisible(true);
  }
		catch (Exception e1) 
		{
			e1.printStackTrace();
		}
			}
});
		btnChangePassword.setBounds(82, 391, 175, 67);
		panelModify.add(btnChangePassword);
		
// PANEL CHANGE PASS***********************************************************************************************************
		
		lblPopUpPass = new JLabel();
		lblPopUpPass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//Per informazioni cercare la classe PopUp
				PopUp.infoBox(frmSchoolib,"La password deve contere un carattere maiuscolo, uno minuscolo, un numero e un carattere speciale");
			}
		});
		lblPopUpPass.setIcon(iconLogoQ);
		lblPopUpPass.setBounds(301, 64, 16, 16);
		panelChangePass.add(lblPopUpPass);
		
		
		JLabel lblChange = new JLabel("Cambia Password");
		lblChange.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblChange.setBounds(172, 11, 348, 34);
		panelChangePass.add(lblChange);
		
		JLabel lblPassChange = new JLabel("Password");
		lblPassChange.setBounds(59, 79, 100, 23);
		panelChangePass.add(lblPassChange);
		
		JLabel lblPassChangeC = new JLabel("Conferma Password");
		lblPassChangeC.setBounds(59, 187, 140, 23);
		panelChangePass.add(lblPassChangeC);
		
		lblCheckChangePass = new JLabel();
		lblCheckChangePass.setBounds(446, 83, 21, 19);
		panelChangePass.add(lblCheckChangePass);
		
		lblCheckChangePassC = new JLabel();
		lblCheckChangePassC.setBounds(446, 187, 21, 19);
		panelChangePass.add(lblCheckChangePassC);
		
		passwordField = new JPasswordField();
		passwordField.setEditable(false);
		passwordField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				passwordField.setEditable(true);
				passwordFieldConfMod.setEditable(true);
			}
		});
		passwordField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				checkPass1();
			}
		});
		passwordField.setBounds(209, 80, 224, 20);
		panelChangePass.add(passwordField);
		
		passwordFieldConfMod = new JPasswordField();
		
		passwordFieldConfMod.setEditable(false);
		passwordFieldConfMod.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {						//Controllo sintattico
				checkPass2();
				checkPassEq();
			}
		});
		passwordFieldConfMod.setBounds(209, 188, 224, 20);
		panelChangePass.add(passwordFieldConfMod);
		
		JButton btnChangePass = new JButton("Conferma");
		btnChangePass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if	(	Check.checkAllPassMod	(
						passwordField.getPassword(),
						passwordFieldConfMod.getPassword()
						)
)
{
					
					String 	p 	= String.copyValueOf(passwordField.getPassword());
					
					
					try {
						
					    MQ_Update.updatePassMod(getIdUser(), p);	
						PopUp.infoBox(frmSchoolib, "Modifiche password effetuata con successo");
						
						// aggiornare label password con updateAll
						
						
						panelModify.setVisible(false);
						panelAccount.setVisible(true);
						panelChangePass.setVisible(false);
						
						passwordField.setEditable(false);
						passwordFieldConfMod.setEditable(false);
							
						lblCheckChangePass.setIcon(null);
						lblCheckChangePassC.setIcon(null);
									

					} catch (SQLException e2) {
						e2.printStackTrace();
					}
					
                 }
				else
				{
					PopUp.errorBox(frmSchoolib, "Campi Errati");
					

					checkPass1();
					checkPass2();
					checkPassEq();
				}
			}
		});
		btnChangePass.setBounds(92, 295, 158, 82);
		panelChangePass.add(btnChangePass);
		
		JButton btnReturnBack = new JButton("Annulla");
		btnReturnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				PopUp.warningBox(frmSchoolib, "Le modifiche effettuate verranno annullate");
				
				if(PopUp.confirmBox(frmSchoolib))
				{
					
					panelAccount.setVisible(true);
					panelModify.setVisible(false);
					panelChangePass.setVisible(false);
		
					passwordField.setEditable(false);
					passwordFieldConfMod.setEditable(false);

					lblCheckChangePass.setIcon(null);
					lblCheckChangePassC.setIcon(null);
				}
			}
		});
		btnReturnBack.setBounds(330, 295, 158, 82);
		panelChangePass.add(btnReturnBack);
	}
		
	//Fine**********************************************************************************************************	
	
	
	public static String[] getUserData() {
		return UserData;
	}

	public static void setUserData(String[] userData) {
		UserData = userData;
	}

	public JFrame getFrmSchoolib() {
		return frmSchoolib;
	}

	public void setFrmSchoolib(JFrame frmSchoolib) {
		this.frmSchoolib = frmSchoolib;
	}

	public JPasswordField getPasswordFieldMod() {
		return passwordFieldMod;
	}

	public void setPasswordFieldMod(JPasswordField passwordFieldMod) {
		this.passwordFieldMod = passwordFieldMod;
	}

	public JPasswordField getPasswordFieldConfMod() {
		return passwordFieldConfMod;
	}

	public void setPasswordFieldConfMod(JPasswordField passwordFieldConfMod) {
		this.passwordFieldConfMod = passwordFieldConfMod;
	}

	public JTextField getTxtNameMod() {
		return txtNameMod;
	}

	public void setTxtNameMod(JTextField txtNameMod) {
		this.txtNameMod = txtNameMod;
	}

	public JTextField getTxtSurnameMod() {
		return txtSurnameMod;
	}

	public void setTxtSurnameMod(JTextField txtSurnameMod) {
		this.txtSurnameMod = txtSurnameMod;
	}

	public JTextField getTxtInqMod() {
		return txtInqMod;
	}

	public void setTxtInqMod(JTextField txtInqMod) {
		this.txtInqMod = txtInqMod;
	}

	public JTextField getTxtTelMod() {
		return txtTelMod;
	}

	public void setTxtTelMod(JTextField txtTelMod) {
		this.txtTelMod = txtTelMod;
	}

	public JTextField getPasswordFieldMod1() {
		return passwordFieldMod1;
	}

	public void setPasswordFieldMod1(JTextField passwordFieldMod1) {
		this.passwordFieldMod1 = passwordFieldMod1;
	}

	public JTextField getPasswordFieldConfMod1() {
		return passwordFieldConfMod1;
	}

	public void setPasswordFieldConfMod1(JTextField passwordFieldConfMod1) {
		this.passwordFieldConfMod1 = passwordFieldConfMod1;
	}

	public String getTypePerson() {
		return TypePerson;
	}

	public void setTypePerson(String typePerson) {
		TypePerson = typePerson;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public void setPasswordField(JPasswordField passwordField) {
		this.passwordField = passwordField;
	}

	/**
	 * Questo metodo setta i valori trovati da una query di lettura dati (MQ_Read.retrieveUserIdbyemail) nelle jlabel del pannello Account 
	 * @param user setta i campi
	 */
	public void updateall(String[] user )
	{
		setIdUser(Integer.valueOf(user[0]));
		lblSetNome.setText(user[1]);
		lblSetCognome.setText(user[2]);
		lblSetEmail.setText(user[3]);
		lblSetPass.setText(user[4]);
		lblSetInq.setText(user[5]);
		lblSetTel.setText(user[6]);
		lblSetTipoUte.setText(user[7]);
		
		
	}
	/**
	 * Questo metodo legge i dati dell' utente e li setta nei jtext del pannello Modify
	 * @param user setta i campi
	 */
	public void updateallModify(String[] user )
	{
		String idutente = user[0];
		if (idutente.equals("")||idutente.equals("Nessun Dato")) {
			setIdUser(0);	
		}else {
			setIdUser(Integer.valueOf(idutente));
		}
		
		setEmailuser(user[3]);
		txtNameMod.setText(user[1]);
		txtSurnameMod.setText(user[2]);
		txtMailMod.setText(user[3]);
		getTxtMailMod().setText(user[3]);
		passwordField.setText(user[4]);
		passwordFieldConfMod.setText(user[4]);
		txtInqMod.setText(user[5]);
		txtTelMod.setText(user[6]);
		
		if(user[7].equals("Lettore"))
		    {
			  rdbtnTypeUserLetMod.setSelected(true);
		    }
		else
		    {
		      rdbtnTypeUserLibMod.setSelected(true);
		    }
		
	}	
	
	/**
	 * Questo metodo aggiorna tutti i campi modificati dall'utente, dal pannello Modify, e aggiorna le jlabel nel pannello Account 
	 * @param user passa i campi dopo averli modificati
	 */
	public void updateallAfterModify(String[] user )
	{
		
		String idutente = user[0];
		if (idutente.equals("")||idutente.equals("Nessun Dato")) {
			setIdUser(0);	
		}else {
			setIdUser(Integer.valueOf(idutente));
		}
		
		lblSetNome.setText(user[1]);
		lblSetCognome.setText(user[2]);
		lblSetEmail.setText(user[3]);
		lblSetPass.setText(user[4]);
		lblSetInq.setText(user[5]);
		lblSetTel.setText(user[6]);
		lblSetTipoUte.setText(user[7]);

		txtNameMod.setEditable(false);
		txtSurnameMod.setEditable(false);
		getTxtMailMod().setEditable(false);
		passwordField.setEditable(false);
		passwordFieldConfMod.setEditable(false);
		txtInqMod.setEditable(false);
		txtTelMod.setEditable(false);
		
		lblChangeNameCheck.setIcon(null);
		lblChangeSurnameCheck.setIcon(null);
		//lblChangeEmailCheck.setIcon(null);
		lblChangeNameCheck.setIcon(null);
		lblChangeSurnameCheck.setIcon(null);
		getLblMAIL().setIcon(null);

		//lblChangePassCheck.setIcon(null);
		lblChangeInqCheck.setIcon(null);
		lblChangePhoneCheck.setIcon(null);
	
	}
	// verifcare checkall
	/**
	 * Questo metodo verifica la correttezza di tutti i campi inseriti dall'utente
	 * @return checkok
	 */
	public boolean checkall() {
		boolean checkok=true;
		
		System.out.println("check tutti campi tranne email");
		
		if(		checkname()		&&	
				checksurname()	&&
				checkinq()		&&
				checkTel()
										) 
		{
			
		}else {
			checkok=false;
		}
		return checkok;		
	}
	
// testaggio campi *************************************************************************************************************************** 

	public boolean checkname() {
		boolean checkok=true;
			if(Check.checkName(txtNameMod.getText()))
			{
			getLblChangeNameCheck().setIcon(iconLogoT);
			}
			else
			{
			checkok=false;	
			getLblChangeNameCheck().setIcon(iconLogoC);
			}
		return checkok;	
	}
	
	public boolean checksurname() {
		boolean checkok=true;
			if(Check.checkName(txtSurnameMod.getText()))
			{
			getLblChangeSurnameCheck().setIcon(iconLogoT);
			}
			else
			{
			checkok=false;	
			getLblChangeSurnameCheck().setIcon(iconLogoC);
			}
		return checkok;	
	}	
	
	public boolean checkTel() {
		boolean checkok=true;
			if(Check.checkTel(txtTelMod.getText()))
			{
			getLblChangePhoneCheck().setIcon(iconLogoT);
			}
			else
			{
			checkok=false;	
			getLblChangePhoneCheck().setIcon(iconLogoC);
			}
		return checkok;	
	}
	
	
	public boolean checkinq() {
		boolean checkok=true;
		if(Check.checkInqu(txtInqMod.getText()))
		{
			System.out.println("13");	
			lblChangeInqCheck.setIcon(iconLogoT);
		}
		else
		{
			System.out.println("14");
			lblChangeInqCheck.setIcon(iconLogoC);
		}
		return checkok;	
	}
	
	public boolean checkPass1() {
		boolean checkok=true;
		if(Check.checkPass(passwordField.getPassword()))
		{
			lblCheckChangePass.setIcon(iconLogoT);
		}
		else
		{
			lblCheckChangePass.setIcon(iconLogoC);
		}
		return checkok;	
	}	

	public boolean checkPass2() {
		boolean checkok=true;
		if(Check.checkPass(passwordFieldConfMod.getPassword()))
		{
			lblCheckChangePassC.setIcon(iconLogoT);
		}
		else
		{
			lblCheckChangePassC.setIcon(iconLogoC);
		}
		return checkok;	
	}	

	public boolean checkPassEq() {
		boolean checkok=true;
		if(Check.checkPassEq(passwordField.getPassword(),passwordFieldConfMod.getPassword()))
		{
			lblCheckChangePassC.setIcon(iconLogoT);
		}
		else
		{
			lblCheckChangePassC.setIcon(iconLogoC);
		}
		return checkok;	
	}	

	
	/**
	 * Questo metodo controlla l'email inserita dall'utente che sia sintatticamente corretta e che non ci sia nel database una uguale
	 * @return checkok controllo sintattico e database
	 */
	public boolean checkmail(){
		boolean checkok=true;
		
			System.out.println(" ***** sto controllando la email ");
			System.out.println(" ***** sto controllando la email : REGISTRATA : "+getEmailuser());
			System.out.println(" ***** sto controllando la email : NEL CAMPO  : "+getTxtMailMod().getText());

			//******************************************************************
			if(Check.checkMail(getTxtMailMod().getText())){
			System.out.println(" ***** sto controllando la email : SINTATTICAMENTE Corretta");
			//sintatticamente corretta		
							if (!getTxtMailMod().getText().equals(getEmailuser())) {
								// modifica alla email
								System.out.println(" ***** sto controllando la email : email MODIFICATA");
								String email = getTxtMailMod().getText();
								me.setSql(email);				
								me.setSql2("Account");
								me.setActW(getW());
								me.setActF(frmSchoolib);
								//me.setActC(c);									
								try {
									System.out.println("GUI account:> ottenuti dati user ");
								me.setCliType(Clients.Reader);	
									me.getCmdLIST().put(Commands.UserREADcheckEmail);
								} catch (InterruptedException e2) {
									System.out.println("GUI account:> NON ottenuti dati user ");	
									e2.printStackTrace(); 
									setMailcheckResult("problemi con user read check mail");
									this.getLblMAIL().setIcon(getIconLogoC());
								}
								//*************************************************************	
							}else {	//non modificata
								System.out.println(" ***** sto controllando la email : email non modificata");
								//getLblChangeEmailCheck().setIcon(getIconLogoT());
								this.getLblMAIL().setIcon(getIconLogoT());
								setMailcheckResult("non modificata");
								setMailcheckinprogress(false);
							}	
			}else {
			//sintatticamente non corretta
				System.out.println(" ***** sto controllando la email : sintatticamente non corretta");
				//getLblChangeEmailCheck().setIcon(getIconLogoC());
				this.getLblMAIL().setIcon(getIconLogoC());
				checkok=false;
				setMailcheckResult("sintatticamente non corretta");
				setMailcheckinprogress(false);
			}
			//******************************************************************
			return checkok;
	}
	
	
	
	
	
	
	
	public void updatelblSetNome(String[] user )
	{
		lblSetNome.setText(user[0]);	
	}
	public void updatelblSetId(String[] user )
	{
		setIdUser(Integer.valueOf(user[0]));	
	}	
	
	public int getIdUser() {
			return idUser;
	}

	public void setIdUser(int idUser) {
			this.idUser = idUser;
	}
	//TODO E SEGUENTI CAMPI...

	public String getEmailuser() {
		return emailuser;
	}

	public void setEmailuser(String emailuser) {
		this.emailuser = emailuser;
	}

	public boolean isCambioemail() {
		return cambioemail;
	}

	public void setCambioemail(boolean cambioemail) {
		this.cambioemail = cambioemail;
	}

	public String [] getUserdata() {
		return userdata;
	}

	public void setUserdata(String [] userdata) {
		this.userdata = userdata;
	}

	public Account getW() {
		return w;
	}

	public void setW(Account w) {
		this.w = w;
	}


	public JLabel getLblMAIL() {
		return lblMAIL;
	}

	public void setLblMAIL(JLabel lblMAIL) {
		this.lblMAIL = lblMAIL;
	}
	
	public ImageIcon getIconLogoT() {
		return iconLogoT;
	}

	public void setIconLogoT(ImageIcon iconLogoT) {
		this.iconLogoT = iconLogoT;
	}

	public ImageIcon getIconLogoC() {
		return iconLogoC;
	}

	public void setIconLogoC(ImageIcon iconLogoC) {
		this.iconLogoC = iconLogoC;
	}

	public JTextField getTxtMailMod() {
		return txtMailMod;
	}

	public void setTxtMailMod(JTextField txtMailMod) {
		this.txtMailMod = txtMailMod;
	}
	
	public JPanel getPanelAccount() {
		return panelAccount;
	}

	public void setPanelAccount(JPanel panelAccount) {
		this.panelAccount = panelAccount;
	}

	public JPanel getPanelModify() {
		return panelModify;
	}

	public void setPanelModify(JPanel panelModify) {
		this.panelModify = panelModify;
	}	
	
	public JLabel getLblChangeNameCheck() {
		return lblChangeNameCheck;
	}

	public void setLblChangeNameCheck(JLabel lblChangeNameCheck) {
		this.lblChangeNameCheck = lblChangeNameCheck;
	}

	public JLabel getLblChangeSurnameCheck() {
		return lblChangeSurnameCheck;
	}

	public void setLblChangeSurnameCheck(JLabel lblChangeSurnameCheck) {
		this.lblChangeSurnameCheck = lblChangeSurnameCheck;
	}
	   public JLabel getLblChangeEmailCheck() {
			return lblChangeEmailCheck;
		}

		public void setLblChangeEmailCheck(JLabel lblChangeEmailCheck) {
			this.lblChangeEmailCheck = lblChangeEmailCheck;
		}



		public JLabel getLblChangePassCheck() {
			return lblChangePassCheck;
		}

		public void setLblChangePassCheck(JLabel lblChangePassCheck) {
		}



		public JLabel getLblChangePassConfCheck() {
			return lblChangePassConfCheck;
		}

		public void setLblChangePassConfCheck(JLabel lblChangePassConfCheck) {
		}

		public JLabel getLblChangeInqCheck() {
			return lblChangeInqCheck;
		}

		public void setLblChangeInqCheck(JLabel lblChangeInqCheck) {
			this.lblChangeInqCheck = lblChangeInqCheck;
		}

		public JLabel getLblChangePhoneCheck() {
			return lblChangePhoneCheck;
		}

		public void setLblChangePhoneCheck(JLabel lblChangePhoneCheck) {
			this.lblChangePhoneCheck = lblChangePhoneCheck;
		}

		public boolean isMailcheckinprogress() {
			return this.mailcheckinprogress;
		}

		public void setMailcheckinprogress(boolean mailcheckinprogress) {
			this.mailcheckinprogress = mailcheckinprogress;
		}

		public String getMailcheckResult() {
			return mailcheckResult;
		}

		public void setMailcheckResult(String mailcheckResult) {
			this.mailcheckResult = mailcheckResult;
		}

		public ImageIcon getIconLogoRA() {
			return iconLogoRA;
		}

		public void setIconLogoRA(ImageIcon iconLogoRA) {
			this.iconLogoRA = iconLogoRA;
		}

		public ImageIcon getIconLogoQ() {
			return iconLogoQ;
		}

		public void setIconLogoQ(ImageIcon iconLogoQ) {
			this.iconLogoQ = iconLogoQ;
		}

		public AppType getT() {
			return t;
		}

		public void setT(AppType t) {
			this.t = t;
		}	
		
		public JRadioButton getRdbtnTypeUserLibMod() {
			return rdbtnTypeUserLibMod;
		}

		public void setRdbtnTypeUserLibMod(JRadioButton rdbtnTypeUserLibMod) {
			this.rdbtnTypeUserLibMod = rdbtnTypeUserLibMod;
		}

		public JRadioButton getRdbtnTypeUserLetMod() {
			return rdbtnTypeUserLetMod;
		}

		public void setRdbtnTypeUserLetMod(JRadioButton rdbtnTypeUserLetMod) {
			this.rdbtnTypeUserLetMod = rdbtnTypeUserLetMod;
		}

		public String getEmailOLD() {
			return emailOLD;
		}

		public void setEmailOLD(String emailOLD) {
			this.emailOLD = emailOLD;
		}
}
