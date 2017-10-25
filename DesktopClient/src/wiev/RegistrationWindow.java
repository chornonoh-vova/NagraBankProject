package wiev;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JTextField;

import client.Checker;
import client.Client;

import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Insets;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class RegistrationWindow {

	private Client client = Client.getInstance();
	public JFrame frmRegistration;
	private JTextField loginField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JLabel lblBirthdate;
	private JTextField birthdateField;
	private JLabel lblSecretQuestion;
	private JTextField questionField;
	private JLabel lblAnswerToThe;
	private JTextField answerField;
	private JButton btnBack;
	private JButton btnContinue;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrationWindow window = new RegistrationWindow();
					window.frmRegistration.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RegistrationWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRegistration = new JFrame();
		frmRegistration.setTitle("Registration");
		frmRegistration.setBounds(100, 100, 450, 339);
		frmRegistration.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{215, 219, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frmRegistration.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblEnterYourLogin = new JLabel("Enter your login");
		GridBagConstraints gbc_lblEnterYourLogin = new GridBagConstraints();
		gbc_lblEnterYourLogin.gridwidth = 2;
		gbc_lblEnterYourLogin.insets = new Insets(0, 0, 5, 0);
		gbc_lblEnterYourLogin.gridx = 0;
		gbc_lblEnterYourLogin.gridy = 0;
		frmRegistration.getContentPane().add(lblEnterYourLogin, gbc_lblEnterYourLogin);
		
		loginField = new JTextField();
		GridBagConstraints gbc_loginField = new GridBagConstraints();
		gbc_loginField.fill = GridBagConstraints.HORIZONTAL;
		gbc_loginField.gridwidth = 2;
		gbc_loginField.insets = new Insets(0, 50, 5, 50);
		gbc_loginField.gridx = 0;
		gbc_loginField.gridy = 1;
		frmRegistration.getContentPane().add(loginField, gbc_loginField);
		loginField.setColumns(10);
		
		JLabel lblEnterYourPin = new JLabel("Enter your pin");
		GridBagConstraints gbc_lblEnterYourPin = new GridBagConstraints();
		gbc_lblEnterYourPin.gridwidth = 2;
		gbc_lblEnterYourPin.insets = new Insets(0, 0, 5, 0);
		gbc_lblEnterYourPin.gridx = 0;
		gbc_lblEnterYourPin.gridy = 2;
		frmRegistration.getContentPane().add(lblEnterYourPin, gbc_lblEnterYourPin);
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridwidth = 2;
		gbc_passwordField.insets = new Insets(0, 50, 5, 50);
		gbc_passwordField.gridx = 0;
		gbc_passwordField.gridy = 3;
		frmRegistration.getContentPane().add(passwordField, gbc_passwordField);
		
		JLabel lblEnterYourPin_1 = new JLabel("Enter your pin again");
		GridBagConstraints gbc_lblEnterYourPin_1 = new GridBagConstraints();
		gbc_lblEnterYourPin_1.gridwidth = 2;
		gbc_lblEnterYourPin_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblEnterYourPin_1.gridx = 0;
		gbc_lblEnterYourPin_1.gridy = 4;
		frmRegistration.getContentPane().add(lblEnterYourPin_1, gbc_lblEnterYourPin_1);
		
		passwordField_1 = new JPasswordField();
		GridBagConstraints gbc_passwordField_1 = new GridBagConstraints();
		gbc_passwordField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField_1.gridwidth = 2;
		gbc_passwordField_1.insets = new Insets(0, 50, 5, 50);
		gbc_passwordField_1.gridx = 0;
		gbc_passwordField_1.gridy = 5;
		frmRegistration.getContentPane().add(passwordField_1, gbc_passwordField_1);
		
		lblBirthdate = new JLabel("Birthdate");
		GridBagConstraints gbc_lblBirthdate = new GridBagConstraints();
		gbc_lblBirthdate.gridwidth = 2;
		gbc_lblBirthdate.insets = new Insets(0, 0, 5, 0);
		gbc_lblBirthdate.gridx = 0;
		gbc_lblBirthdate.gridy = 6;
		frmRegistration.getContentPane().add(lblBirthdate, gbc_lblBirthdate);
		
		birthdateField = new JTextField();
		GridBagConstraints gbc_birthdateField = new GridBagConstraints();
		gbc_birthdateField.fill = GridBagConstraints.HORIZONTAL;
		gbc_birthdateField.gridwidth = 2;
		gbc_birthdateField.insets = new Insets(0, 50, 5, 50);
		gbc_birthdateField.gridx = 0;
		gbc_birthdateField.gridy = 7;
		frmRegistration.getContentPane().add(birthdateField, gbc_birthdateField);
		birthdateField.setColumns(10);
		
		lblSecretQuestion = new JLabel("Secret question");
		GridBagConstraints gbc_lblSecretQuestion = new GridBagConstraints();
		gbc_lblSecretQuestion.gridwidth = 2;
		gbc_lblSecretQuestion.insets = new Insets(0, 0, 5, 0);
		gbc_lblSecretQuestion.gridx = 0;
		gbc_lblSecretQuestion.gridy = 8;
		frmRegistration.getContentPane().add(lblSecretQuestion, gbc_lblSecretQuestion);
		
		questionField = new JTextField();
		GridBagConstraints gbc_questionField = new GridBagConstraints();
		gbc_questionField.fill = GridBagConstraints.HORIZONTAL;
		gbc_questionField.gridwidth = 2;
		gbc_questionField.insets = new Insets(0, 50, 5, 50);
		gbc_questionField.gridx = 0;
		gbc_questionField.gridy = 9;
		frmRegistration.getContentPane().add(questionField, gbc_questionField);
		questionField.setColumns(10);
		
		lblAnswerToThe = new JLabel("Answer to the secret question");
		GridBagConstraints gbc_lblAnswerToThe = new GridBagConstraints();
		gbc_lblAnswerToThe.gridwidth = 2;
		gbc_lblAnswerToThe.insets = new Insets(0, 0, 5, 0);
		gbc_lblAnswerToThe.gridx = 0;
		gbc_lblAnswerToThe.gridy = 10;
		frmRegistration.getContentPane().add(lblAnswerToThe, gbc_lblAnswerToThe);
		
		answerField = new JTextField();
		GridBagConstraints gbc_answerField = new GridBagConstraints();
		gbc_answerField.fill = GridBagConstraints.HORIZONTAL;
		gbc_answerField.gridwidth = 2;
		gbc_answerField.insets = new Insets(0, 50, 5, 50);
		gbc_answerField.gridx = 0;
		gbc_answerField.gridy = 11;
		frmRegistration.getContentPane().add(answerField, gbc_answerField);
		answerField.setColumns(10);
		
		btnBack = new JButton("< Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginWindow window = new LoginWindow();
				window.frmLogin.setVisible(true);
				frmRegistration.setVisible(false);
			}
		});
		GridBagConstraints gbc_btnBack = new GridBagConstraints();
		gbc_btnBack.anchor = GridBagConstraints.WEST;
		gbc_btnBack.insets = new Insets(0, 0, 0, 5);
		gbc_btnBack.gridx = 0;
		gbc_btnBack.gridy = 12;
		frmRegistration.getContentPane().add(btnBack, gbc_btnBack);
		
		btnContinue = new JButton("Continue ");
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String loginToSend = loginField.getText();
				if (!Checker.verifyLogin(loginToSend)) {
					final JDialog dialog = new JDialog();
					dialog.setAlwaysOnTop(true);
					JOptionPane.showMessageDialog(dialog, "incorrect login\n try again", "error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				String pinToSend = String.valueOf(passwordField.getPassword());
				String confirmPin = String.valueOf(passwordField_1.getPassword());
				if (!Checker.verifyPinCode(pinToSend) && !pinToSend.equals(confirmPin)) {
					final JDialog dialog = new JDialog();
					dialog.setAlwaysOnTop(true);
					JOptionPane.showMessageDialog(dialog, "incorrect password\n try again", "error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				String birthDate = birthdateField.getText();
				if(!Checker.verifyDate(birthDate)) {
					final JDialog dialog = new JDialog();
					dialog.setAlwaysOnTop(true);
					JOptionPane.showMessageDialog(dialog, "incorrect birthdate\n try again", "error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				String secretQuestion = questionField.getText();
				String secretAnswer = answerField.getText();
				client.sendMessage("registry", pinToSend, loginToSend, birthDate, secretQuestion, secretAnswer);
				String[] fromServer = null;
				try {
					fromServer = client.getArrayFromMessage();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(fromServer[0].equals("success")) {
					final JDialog dialog = new JDialog();
					dialog.setAlwaysOnTop(true);
					JOptionPane.showMessageDialog(dialog, "Congratulations\n Welcome to our family", "Success", JOptionPane.PLAIN_MESSAGE);
					LoginWindow window = new LoginWindow();
					window.frmLogin.setVisible(true);
					frmRegistration.setVisible(false);
				} else {
					final JDialog dialog = new JDialog();
					dialog.setAlwaysOnTop(true);
					JOptionPane.showMessageDialog(dialog, fromServer[1], fromServer[0], JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
		GridBagConstraints gbc_btnContinue = new GridBagConstraints();
		gbc_btnContinue.anchor = GridBagConstraints.EAST;
		gbc_btnContinue.gridx = 1;
		gbc_btnContinue.gridy = 12;
		frmRegistration.getContentPane().add(btnContinue, gbc_btnContinue);
	}

}
