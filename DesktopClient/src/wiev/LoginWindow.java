package wiev;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.UIManager;

import client.Checker;
import client.Client;
import client.Md5Hasher;
import client.UserInfo;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Date;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginWindow implements ShowMessage, Md5Hasher {
	private Client client = Client.getInstance();
	public JFrame frmLogin;
	private JTextField loginInputField;
	private JPasswordField passwordInputField;
	private JButton btnForgot;
	private JButton btnLogIn;
	private JLabel lblYourPincode;
	private JButton btnRegistration;
	private JLabel imageLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					LoginWindow window = new LoginWindow();
					final JDialog dialog = new JDialog();
					dialog.setAlwaysOnTop(true);
					String ip = (String) JOptionPane.showInputDialog(dialog, "Server ip:", "Enter",
							JOptionPane.PLAIN_MESSAGE);
					if ((ip != null) && (ip.length() > 0)) {
						window.client = new Client(ip);
					} else {
						System.exit(0);
					}
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
	public LoginWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frmLogin = new JFrame();

		frmLogin.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				client.sendMessage("close");
				System.exit(0);
			}
		});
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 350, 420);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		frmLogin.getContentPane().setLayout(gridBagLayout);

		imageLabel = new JLabel(new ImageIcon("img/central_bank_dollar.png"));
		GridBagConstraints gbc_imageLabel = new GridBagConstraints();
		gbc_imageLabel.gridwidth = 2;
		gbc_imageLabel.fill = GridBagConstraints.BOTH;
		gbc_imageLabel.insets = new Insets(0, 50, 5, 50);
		gbc_imageLabel.gridx = 0;
		gbc_imageLabel.gridy = 0;
		frmLogin.getContentPane().add(imageLabel, gbc_imageLabel);

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
		passwordInputField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					String loginToSend = loginInputField.getText();
					if (!Checker.verifyLogin(loginToSend)) {
						showErrorMessage("error", "Incorrect login\ntry again");
						loginInputField.setText("");
						return;
					}
					String pinToSend = String.valueOf(passwordInputField.getPassword());
					if (!Checker.verifyPinCode(pinToSend)) {
						showErrorMessage("error", "Incorrect password\ntry again");
						passwordInputField.setText("");
						return;
					}
					client.sendMessage("login", loginToSend, pinToSend);
					String[] answer = null;
					try {
						answer = client.getArrayFromMessage();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (answer[0].equals("success")) {
						UserInfo newUser = new UserInfo();
						newUser.userId = Integer.valueOf(answer[1]);
						newUser.userLogin = loginToSend;
						newUser.balance = Double.valueOf(answer[2]);
						newUser.secretQuestion = answer[3];
						newUser.birthDate = Date.valueOf(answer[4]);
						newUser.status = Boolean.valueOf(answer[5]);
						newUser.pin = Integer.valueOf(pinToSend);
						MainWindow window = new MainWindow(newUser);
						window.frmNagrabank.setVisible(true);
						frmLogin.setVisible(false);
					} else {
						showErrorMessage("error while loggining in", answer[1]);
						loginInputField.setText("");
						passwordInputField.setText("");
					}
				}
			}
		});
		GridBagConstraints gbc_passwordInputField = new GridBagConstraints();
		gbc_passwordInputField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordInputField.insets = new Insets(10, 50, 10, 5);
		gbc_passwordInputField.gridx = 0;
		gbc_passwordInputField.gridy = 4;
		frmLogin.getContentPane().add(passwordInputField, gbc_passwordInputField);

		btnForgot = new JButton("Forgot?");
		btnForgot.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				final JDialog dialog = new JDialog();
				dialog.setAlwaysOnTop(true);
				JOptionPane.showMessageDialog(dialog, "IDI VSPOMINAY", "VSPOMINALKA",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		GridBagConstraints gbc_btnForgot = new GridBagConstraints();
		gbc_btnForgot.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnForgot.insets = new Insets(10, 5, 10, 50);
		gbc_btnForgot.gridx = 1;
		gbc_btnForgot.gridy = 4;
		frmLogin.getContentPane().add(btnForgot, gbc_btnForgot);

		btnLogIn = new JButton("Log In");
		btnLogIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String loginToSend = loginInputField.getText();
				if (!Checker.verifyLogin(loginToSend)) {
					showErrorMessage("error", "Incorrect login\ntry again");
					loginInputField.setText("");
					return;
				}
				String pinToSend = String.valueOf(passwordInputField.getPassword());
				if (!Checker.verifyPinCode(pinToSend)) {
					showErrorMessage("error", "Incorrect password\ntry again");
					passwordInputField.setText("");
					return;
				}
				client.sendMessage("login", loginToSend, pinToSend);
				String[] answer = null;
				try {
					answer = client.getArrayFromMessage();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (answer[0].equals("success")) {
					UserInfo newUser = new UserInfo();
					newUser.userId = Integer.valueOf(answer[1]);
					newUser.userLogin = loginToSend;
					newUser.balance = Double.valueOf(answer[2]);
					newUser.secretQuestion = answer[3];
					newUser.birthDate = Date.valueOf(answer[4]);
					newUser.status = Boolean.valueOf(answer[5]);
					newUser.pin = Integer.valueOf(pinToSend);
					MainWindow window = new MainWindow(newUser);
					window.frmNagrabank.setVisible(true);
					frmLogin.setVisible(false);
				} else {
					showErrorMessage("error while loggining in", answer[1]);
					loginInputField.setText("");
					passwordInputField.setText("");
				}
			}
		});
		GridBagConstraints gbc_btnLogIn = new GridBagConstraints();
		gbc_btnLogIn.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnLogIn.insets = new Insets(10, 50, 10, 5);
		gbc_btnLogIn.gridx = 0;
		gbc_btnLogIn.gridy = 5;
		frmLogin.getContentPane().add(btnLogIn, gbc_btnLogIn);

		btnRegistration = new JButton("Registration");
		btnRegistration.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				RegistrationWindow window = new RegistrationWindow();
				window.frmRegistration.setVisible(true);
				frmLogin.setVisible(false);
			}
		});
		GridBagConstraints gbc_btnRegistration = new GridBagConstraints();
		gbc_btnRegistration.insets = new Insets(10, 5, 10, 50);
		gbc_btnRegistration.gridx = 1;
		gbc_btnRegistration.gridy = 5;
		frmLogin.getContentPane().add(btnRegistration, gbc_btnRegistration);
	}

}
