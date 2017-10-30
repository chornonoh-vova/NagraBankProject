package wiev;

import javax.swing.JFrame;

import client.Client;
import client.Md5Hasher;
import client.UserInfo;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.Date;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow implements ShowMessage, Md5Hasher {

	private UserInfo user;
	private Client client = Client.getInstance();
	public JFrame frmNagrabank;
	private JTextField moneyToRefill;
	private JTextField moneyToWidthdraw;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the application.
	 */
	public MainWindow(UserInfo other) {
		user = new UserInfo(other);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNagrabank = new JFrame();
		frmNagrabank.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				if (showConfirmDialog("Exit", "Do you really wanna quit?") == 0) {
					client.sendMessage("logout");
					arg0.getWindow().dispose();
				} else {
					return;
				}
			}
		});
		frmNagrabank.setTitle("NagraBank");
		frmNagrabank.setBounds(100, 100, 526, 461);
		frmNagrabank.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		frmNagrabank.getContentPane().setLayout(gridBagLayout);

		JPanel userInfoPanel = new JPanel();
		userInfoPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Your account info", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_userInfoPanel = new GridBagConstraints();
		gbc_userInfoPanel.fill = GridBagConstraints.BOTH;
		gbc_userInfoPanel.insets = new Insets(5, 25, 5, 25);
		gbc_userInfoPanel.gridx = 0;
		gbc_userInfoPanel.gridy = 0;
		frmNagrabank.getContentPane().add(userInfoPanel, gbc_userInfoPanel);
		GridBagLayout gbl_userInfoPanel = new GridBagLayout();
		gbl_userInfoPanel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_userInfoPanel.rowHeights = new int[] { 0, 0, 0 };
		gbl_userInfoPanel.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
				Double.MIN_VALUE };
		gbl_userInfoPanel.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		userInfoPanel.setLayout(gbl_userInfoPanel);

		JLabel lblLogin = new JLabel("Login");
		GridBagConstraints gbc_lblLogin = new GridBagConstraints();
		gbc_lblLogin.insets = new Insets(5, 10, 5, 5);
		gbc_lblLogin.gridx = 0;
		gbc_lblLogin.gridy = 0;
		userInfoPanel.add(lblLogin, gbc_lblLogin);

		JLabel lblBirthdate = new JLabel("Birthdate");
		GridBagConstraints gbc_lblBirthdate = new GridBagConstraints();
		gbc_lblBirthdate.insets = new Insets(5, 5, 5, 5);
		gbc_lblBirthdate.gridx = 1;
		gbc_lblBirthdate.gridy = 0;
		userInfoPanel.add(lblBirthdate, gbc_lblBirthdate);

		JLabel lblBalance = new JLabel("Balance");
		GridBagConstraints gbc_lblBalance = new GridBagConstraints();
		gbc_lblBalance.insets = new Insets(5, 5, 5, 5);
		gbc_lblBalance.gridx = 2;
		gbc_lblBalance.gridy = 0;
		userInfoPanel.add(lblBalance, gbc_lblBalance);

		JLabel lblAccountId = new JLabel("Account ID");
		GridBagConstraints gbc_lblAccountId = new GridBagConstraints();
		gbc_lblAccountId.insets = new Insets(5, 5, 5, 5);
		gbc_lblAccountId.gridx = 3;
		gbc_lblAccountId.gridy = 0;
		userInfoPanel.add(lblAccountId, gbc_lblAccountId);

		JLabel lblSecretQuestion = new JLabel("Secret question");
		GridBagConstraints gbc_lblSecretQuestion = new GridBagConstraints();
		gbc_lblSecretQuestion.insets = new Insets(5, 5, 5, 5);
		gbc_lblSecretQuestion.gridx = 4;
		gbc_lblSecretQuestion.gridy = 0;
		userInfoPanel.add(lblSecretQuestion, gbc_lblSecretQuestion);

		JButton btnUpdate = new JButton("Update");
		
		GridBagConstraints gbc_btnUpdate = new GridBagConstraints();
		gbc_btnUpdate.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnUpdate.insets = new Insets(5, 5, 5, 10);
		gbc_btnUpdate.gridx = 5;
		gbc_btnUpdate.gridy = 0;
		userInfoPanel.add(btnUpdate, gbc_btnUpdate);

		JLabel labelForLogin = new JLabel(user.userLogin);
		GridBagConstraints gbc_labelForLogin = new GridBagConstraints();
		gbc_labelForLogin.insets = new Insets(5, 10, 5, 5);
		gbc_labelForLogin.gridx = 0;
		gbc_labelForLogin.gridy = 1;
		userInfoPanel.add(labelForLogin, gbc_labelForLogin);

		JLabel labelForBirthdate = new JLabel(user.birthDate.toString());
		GridBagConstraints gbc_labelForBirthdate = new GridBagConstraints();
		gbc_labelForBirthdate.insets = new Insets(5, 5, 5, 5);
		gbc_labelForBirthdate.gridx = 1;
		gbc_labelForBirthdate.gridy = 1;
		userInfoPanel.add(labelForBirthdate, gbc_labelForBirthdate);

		JLabel labelForBalance = new JLabel(String.valueOf(user.balance));
		GridBagConstraints gbc_labelForBalance = new GridBagConstraints();
		gbc_labelForBalance.insets = new Insets(5, 5, 5, 5);
		gbc_labelForBalance.gridx = 2;
		gbc_labelForBalance.gridy = 1;
		userInfoPanel.add(labelForBalance, gbc_labelForBalance);

		JLabel labelForId = new JLabel(String.valueOf(user.userId));
		GridBagConstraints gbc_labelForId = new GridBagConstraints();
		gbc_labelForId.insets = new Insets(5, 5, 5, 5);
		gbc_labelForId.gridx = 3;
		gbc_labelForId.gridy = 1;
		userInfoPanel.add(labelForId, gbc_labelForId);

		JLabel labelForSecrQuestion = new JLabel(user.secretQuestion);
		GridBagConstraints gbc_labelForSecrQuestion = new GridBagConstraints();
		gbc_labelForSecrQuestion.insets = new Insets(5, 5, 5, 5);
		gbc_labelForSecrQuestion.gridx = 4;
		gbc_labelForSecrQuestion.gridy = 1;
		userInfoPanel.add(labelForSecrQuestion, gbc_labelForSecrQuestion);
		
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				client.sendMessage("update", user.userLogin);
				String[] upInfo = null;
				
				try {
					upInfo = client.getArrayFromMessage();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				user.userId = Integer.valueOf(upInfo[1]);
				user.balance = Double.valueOf(upInfo[2]);
				user.secretQuestion = upInfo[3];
				user.birthDate = Date.valueOf(upInfo[4]);
				
				labelForLogin.setText(user.userLogin);
				labelForBirthdate.setText(user.birthDate.toString());
				labelForBalance.setText(String.valueOf(user.balance));			
				labelForId.setText(String.valueOf(user.userId));
				labelForSecrQuestion.setText(user.secretQuestion);
			}
				
		});

		JPanel moneyOperations = new JPanel();
		moneyOperations.setBorder(
				new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Operations with your balance",
						TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_moneyOperations = new GridBagConstraints();
		gbc_moneyOperations.insets = new Insets(5, 25, 5, 25);
		gbc_moneyOperations.fill = GridBagConstraints.BOTH;
		gbc_moneyOperations.gridx = 0;
		gbc_moneyOperations.gridy = 1;
		frmNagrabank.getContentPane().add(moneyOperations, gbc_moneyOperations);
		GridBagLayout gbl_moneyOperations = new GridBagLayout();
		gbl_moneyOperations.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_moneyOperations.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_moneyOperations.columnWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_moneyOperations.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		moneyOperations.setLayout(gbl_moneyOperations);

		JLabel lblAmountOfMoney = new JLabel("Amount of money");
		GridBagConstraints gbc_lblAmountOfMoney = new GridBagConstraints();
		gbc_lblAmountOfMoney.insets = new Insets(5, 10, 5, 5);
		gbc_lblAmountOfMoney.gridx = 0;
		gbc_lblAmountOfMoney.gridy = 0;
		moneyOperations.add(lblAmountOfMoney, gbc_lblAmountOfMoney);

		moneyToRefill = new JTextField();
		GridBagConstraints gbc_moneyToRefill = new GridBagConstraints();
		gbc_moneyToRefill.fill = GridBagConstraints.HORIZONTAL;
		gbc_moneyToRefill.insets = new Insets(5, 5, 5, 5);
		gbc_moneyToRefill.gridx = 1;
		gbc_moneyToRefill.gridy = 0;
		moneyOperations.add(moneyToRefill, gbc_moneyToRefill);
		moneyToRefill.setColumns(10);

		JButton btnRefill = new JButton("Refill");
		btnRefill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(moneyToRefill.getText().isEmpty()) {
					showErrorMessage("error", "Fill amount of money first");
					return ;
				}
				client.sendMessage("refill", String.valueOf(user.userId), moneyToRefill.getText());
				String[] answer = null;
				
				try {
					answer = client.getArrayFromMessage();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(answer[0].equals("success")) {
					showPlainMessage("successfully", "Money refilled");
				}
				else{
					showErrorMessage(answer[0], answer[1]);
				}
			}			
		});
		GridBagConstraints gbc_btnRefill = new GridBagConstraints();
		gbc_btnRefill.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRefill.insets = new Insets(5, 5, 5, 10);
		gbc_btnRefill.gridx = 2;
		gbc_btnRefill.gridy = 0;
		moneyOperations.add(btnRefill, gbc_btnRefill);

		moneyToWidthdraw = new JTextField();
		GridBagConstraints gbc_moneyToWidthdraw = new GridBagConstraints();
		gbc_moneyToWidthdraw.fill = GridBagConstraints.HORIZONTAL;
		gbc_moneyToWidthdraw.insets = new Insets(5, 5, 5, 5);
		gbc_moneyToWidthdraw.gridx = 1;
		gbc_moneyToWidthdraw.gridy = 1;
		moneyOperations.add(moneyToWidthdraw, gbc_moneyToWidthdraw);
		moneyToWidthdraw.setColumns(10);

		JButton btnWidthdraw = new JButton("Widthdraw");
		btnWidthdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(moneyToWidthdraw.getText().isEmpty()) {
					showErrorMessage("error", "Fill amount of money first");
					return ;
				}
				client.sendMessage("withdrawal", String.valueOf(user.userId), moneyToWidthdraw.getText());
				String[] answer = null;
				
				try {
					answer = client.getArrayFromMessage();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(answer[0].equals("success")) {
					showPlainMessage("successfully", "Your money delivered");
				}
				else{
					showErrorMessage(answer[0], answer[1]);
				}
			}
		});
		GridBagConstraints gbc_btnWidthdraw = new GridBagConstraints();
		gbc_btnWidthdraw.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnWidthdraw.insets = new Insets(5, 5, 5, 10);
		gbc_btnWidthdraw.gridx = 2;
		gbc_btnWidthdraw.gridy = 1;
		moneyOperations.add(btnWidthdraw, gbc_btnWidthdraw);

		JLabel lblTransferTo = new JLabel("Transfer to");
		GridBagConstraints gbc_lblTransferTo = new GridBagConstraints();
		gbc_lblTransferTo.insets = new Insets(5, 10, 5, 5);
		gbc_lblTransferTo.gridx = 0;
		gbc_lblTransferTo.gridy = 2;
		moneyOperations.add(lblTransferTo, gbc_lblTransferTo);

		JLabel lblAmountOfMoney_1 = new JLabel("Amount of money");
		GridBagConstraints gbc_lblAmountOfMoney_1 = new GridBagConstraints();
		gbc_lblAmountOfMoney_1.insets = new Insets(5, 0, 5, 5);
		gbc_lblAmountOfMoney_1.gridx = 1;
		gbc_lblAmountOfMoney_1.gridy = 2;
		moneyOperations.add(lblAmountOfMoney_1, gbc_lblAmountOfMoney_1);

		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(5, 10, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 3;
		moneyOperations.add(textField, gbc_textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(5, 5, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 3;
		moneyOperations.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);

		JButton btnTransfer = new JButton("Transfer");
		btnTransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().isEmpty() ||textField_1.getText().isEmpty()) {
					showErrorMessage("error", "fill fields first");
					return;
				}
				client.sendMessage("transfer", String.valueOf(user.userId), textField.getText(), textField_1.getText());
				String[] answer = null;
				
				try {
					answer = client.getArrayFromMessage();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(answer[0].equals("success")) {
					showPlainMessage(answer[0], answer[1]);
				}
				else{
					showErrorMessage(answer[0], answer[1]);
				}
			}
		});
		GridBagConstraints gbc_btnTransfer = new GridBagConstraints();
		gbc_btnTransfer.insets = new Insets(5, 5, 5, 10);
		gbc_btnTransfer.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnTransfer.gridx = 2;
		gbc_btnTransfer.gridy = 3;
		moneyOperations.add(btnTransfer, gbc_btnTransfer);
	}

}
