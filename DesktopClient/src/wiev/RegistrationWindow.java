package wiev;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import javax.swing.JPasswordField;

public class RegistrationWindow {

	public JFrame frmRegistration;
	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

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
		frmRegistration.setBounds(100, 100, 450, 300);
		frmRegistration.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{212, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frmRegistration.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblEnterYourLogin = new JLabel("Enter your login");
		GridBagConstraints gbc_lblEnterYourLogin = new GridBagConstraints();
		gbc_lblEnterYourLogin.gridwidth = 2;
		gbc_lblEnterYourLogin.insets = new Insets(0, 0, 5, 0);
		gbc_lblEnterYourLogin.gridx = 0;
		gbc_lblEnterYourLogin.gridy = 0;
		frmRegistration.getContentPane().add(lblEnterYourLogin, gbc_lblEnterYourLogin);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 2;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 1;
		frmRegistration.getContentPane().add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblEnterYourPin = new JLabel("Enter your pin");
		GridBagConstraints gbc_lblEnterYourPin = new GridBagConstraints();
		gbc_lblEnterYourPin.insets = new Insets(0, 0, 5, 5);
		gbc_lblEnterYourPin.gridx = 0;
		gbc_lblEnterYourPin.gridy = 2;
		frmRegistration.getContentPane().add(lblEnterYourPin, gbc_lblEnterYourPin);
		
		JLabel lblEnterYourPin_1 = new JLabel("Enter your pin again");
		GridBagConstraints gbc_lblEnterYourPin_1 = new GridBagConstraints();
		gbc_lblEnterYourPin_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblEnterYourPin_1.gridx = 1;
		gbc_lblEnterYourPin_1.gridy = 2;
		frmRegistration.getContentPane().add(lblEnterYourPin_1, gbc_lblEnterYourPin_1);
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 0;
		gbc_passwordField.gridy = 3;
		frmRegistration.getContentPane().add(passwordField, gbc_passwordField);
		
		passwordField_1 = new JPasswordField();
		GridBagConstraints gbc_passwordField_1 = new GridBagConstraints();
		gbc_passwordField_1.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField_1.gridx = 1;
		gbc_passwordField_1.gridy = 3;
		frmRegistration.getContentPane().add(passwordField_1, gbc_passwordField_1);
	}

}
