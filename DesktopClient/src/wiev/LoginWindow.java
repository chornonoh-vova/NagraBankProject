package wiev;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;

import client.Client;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class LoginWindow {

	private Client client;
	private JFrame frmLogin;
	private JTextField loginInputField;
	private JPasswordField passwordInputField;
	private JButton btnForgot;
	private JButton btnLogIn;
	private JLabel lblYourPincode;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					Socket kkSocket = null;
					PrintWriter out = null;
					BufferedReader in = null;
					try {
						kkSocket = new Socket("localhost", 4444);
		        out = new PrintWriter(kkSocket.getOutputStream(), true);
		        in = new BufferedReader(
		            new InputStreamReader(kkSocket.getInputStream()));
					} catch (Exception e) {
						System.exit(1);
					}
					LoginWindow window = new LoginWindow(kkSocket, out, in);
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginWindow(Socket socket, PrintWriter out, BufferedReader in) {
		client = new Client(socket, out, in);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 350, 420);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frmLogin.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblYourLogin = new JLabel("Your login");
		GridBagConstraints gbc_lblYourLogin = new GridBagConstraints();
		gbc_lblYourLogin.gridwidth = 2;
		gbc_lblYourLogin.insets = new Insets(10, 50, 10, 50);
		gbc_lblYourLogin.gridx = 0;
		gbc_lblYourLogin.gridy = 1;
		frmLogin.getContentPane().add(lblYourLogin, gbc_lblYourLogin);
		
		loginInputField = new JTextField();
		GridBagConstraints gbc_loginInputField = new GridBagConstraints();
		gbc_loginInputField.fill = GridBagConstraints.HORIZONTAL;
		gbc_loginInputField.gridwidth = 2;
		gbc_loginInputField.insets = new Insets(10, 50, 10, 50);
		gbc_loginInputField.gridx = 0;
		gbc_loginInputField.gridy = 2;
		frmLogin.getContentPane().add(loginInputField, gbc_loginInputField);
		loginInputField.setColumns(10);
		
		lblYourPincode = new JLabel("Your PIN-code:");
		GridBagConstraints gbc_lblYourPincode = new GridBagConstraints();
		gbc_lblYourPincode.gridwidth = 2;
		gbc_lblYourPincode.insets = new Insets(10, 50, 10, 50);
		gbc_lblYourPincode.gridx = 0;
		gbc_lblYourPincode.gridy = 3;
		frmLogin.getContentPane().add(lblYourPincode, gbc_lblYourPincode);
		
		passwordInputField = new JPasswordField();
		GridBagConstraints gbc_passwordInputField = new GridBagConstraints();
		gbc_passwordInputField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordInputField.insets = new Insets(10, 50, 10, 5);
		gbc_passwordInputField.gridx = 0;
		gbc_passwordInputField.gridy = 4;
		frmLogin.getContentPane().add(passwordInputField, gbc_passwordInputField);
		
		btnForgot = new JButton("Forgot?");
		GridBagConstraints gbc_btnForgot = new GridBagConstraints();
		gbc_btnForgot.insets = new Insets(10, 5, 10, 50);
		gbc_btnForgot.gridx = 1;
		gbc_btnForgot.gridy = 4;
		frmLogin.getContentPane().add(btnForgot, gbc_btnForgot);
		
		btnLogIn = new JButton("Log In");
		btnLogIn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				client.sendMessage("login", loginInputField.getText(), String.valueOf(passwordInputField.getPassword()));
				String answer = null;
				try {
					 answer = client.getMessage();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				final JDialog dialog = new JDialog();
				dialog.setAlwaysOnTop(true);
				JOptionPane.showMessageDialog(dialog, answer, "Answer", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		GridBagConstraints gbc_btnLogIn = new GridBagConstraints();
		gbc_btnLogIn.insets = new Insets(10, 0, 10, 0);
		gbc_btnLogIn.gridwidth = 2;
		gbc_btnLogIn.gridx = 0;
		gbc_btnLogIn.gridy = 5;
		frmLogin.getContentPane().add(btnLogIn, gbc_btnLogIn);
	}

}
