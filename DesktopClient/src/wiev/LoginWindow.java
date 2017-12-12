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
import java.io.IOException;
import java.sql.Date;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Font;

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
  private JLabel lblNewLabel;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(() -> {
      try {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        LoginWindow window = new LoginWindow();
        final JDialog dialog = new JDialog();
        dialog.setAlwaysOnTop(true);
        String ip = (String) JOptionPane.showInputDialog(dialog, "Server ip:", "Enter",
            JOptionPane.PLAIN_MESSAGE);
        if ((ip != null) && (ip.length() > 0)) {
          window.client = Client.getInstance(ip);
        } else {
          System.exit(1);
        }
        window.frmLogin.setVisible(true);
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
  }

  /**
   * Create the application.
   * 
   * @wbp.parser.entryPoint
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
    frmLogin.setBounds(100, 100, 350, 530);
    frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    GridBagLayout gridBagLayout = new GridBagLayout();
    gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
    gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
    gridBagLayout.columnWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
    gridBagLayout.rowWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
    frmLogin.getContentPane().setLayout(gridBagLayout);

    lblNewLabel = new JLabel("Nagra Bank Inc.");
    lblNewLabel.setFont(new Font("Vladimir Script", Font.PLAIN, 30));
    GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
    gbc_lblNewLabel.gridwidth = 2;
    gbc_lblNewLabel.gridx = 0;
    gbc_lblNewLabel.gridy = 0;
    frmLogin.getContentPane().add(lblNewLabel, gbc_lblNewLabel);

    imageLabel = new JLabel(new ImageIcon("img/central_bank_dollar.png"));
    imageLabel.setBounds(100, 100, 240, 240);
    GridBagConstraints gbc_imageLabel = new GridBagConstraints();
    gbc_imageLabel.gridwidth = 2;
    gbc_imageLabel.fill = GridBagConstraints.BOTH;
    gbc_imageLabel.gridx = 0;
    gbc_imageLabel.gridy = 1;
    frmLogin.getContentPane().add(imageLabel, gbc_imageLabel);

    JLabel lblYourLogin = new JLabel("Your login");
    lblYourLogin.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
    GridBagConstraints gbc_lblYourLogin = new GridBagConstraints();
    gbc_lblYourLogin.gridwidth = 2;
    gbc_lblYourLogin.insets = new Insets(10, 50, 10, 50);
    gbc_lblYourLogin.gridx = 0;
    gbc_lblYourLogin.gridy = 2;
    frmLogin.getContentPane().add(lblYourLogin, gbc_lblYourLogin);

    loginInputField = new JTextField();
    loginInputField.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
    GridBagConstraints gbc_loginInputField = new GridBagConstraints();
    gbc_loginInputField.fill = GridBagConstraints.HORIZONTAL;
    gbc_loginInputField.gridwidth = 2;
    gbc_loginInputField.insets = new Insets(10, 50, 10, 50);
    gbc_loginInputField.gridx = 0;
    gbc_loginInputField.gridy = 3;
    frmLogin.getContentPane().add(loginInputField, gbc_loginInputField);
    loginInputField.setColumns(10);

    lblYourPincode = new JLabel("Your PIN-code:");
    lblYourPincode.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
    GridBagConstraints gbc_lblYourPincode = new GridBagConstraints();
    gbc_lblYourPincode.gridwidth = 2;
    gbc_lblYourPincode.insets = new Insets(10, 50, 10, 50);
    gbc_lblYourPincode.gridx = 0;
    gbc_lblYourPincode.gridy = 4;
    frmLogin.getContentPane().add(lblYourPincode, gbc_lblYourPincode);

    passwordInputField = new JPasswordField();
    passwordInputField.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));

    GridBagConstraints gbc_passwordInputField = new GridBagConstraints();
    gbc_passwordInputField.fill = GridBagConstraints.HORIZONTAL;
    gbc_passwordInputField.insets = new Insets(10, 50, 10, 5);
    gbc_passwordInputField.gridx = 0;
    gbc_passwordInputField.gridy = 5;
    frmLogin.getContentPane().add(passwordInputField, gbc_passwordInputField);

    btnForgot = new JButton("Forgot Password");
    btnForgot.setFont(new Font("Arial", Font.PLAIN, 14));
    btnForgot.addActionListener((arg0) -> {
      ForgotWindow window = new ForgotWindow();
      window.frame.setVisible(true);
      frmLogin.setVisible(false);
    });
    GridBagConstraints gbc_btnForgot = new GridBagConstraints();
    gbc_btnForgot.fill = GridBagConstraints.HORIZONTAL;
    gbc_btnForgot.insets = new Insets(10, 5, 10, 50);
    gbc_btnForgot.gridx = 1;
    gbc_btnForgot.gridy = 5;
    frmLogin.getContentPane().add(btnForgot, gbc_btnForgot);

    btnLogIn = new JButton("Log In");
    btnLogIn.setFont(new Font("Arial", Font.PLAIN, 14));
    btnLogIn.addActionListener((arg0) -> {
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
      String hashedPin = getMd5Hash(pinToSend);
      client.sendMessage("login", loginToSend, hashedPin);
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
        newUser.pin = hashedPin;
        MainWindow window = new MainWindow(newUser);
        window.frmNagrabank.setVisible(true);
        frmLogin.setVisible(false);
      } else {
        showErrorMessage("error while loggining in", answer[1]);
        loginInputField.setText("");
        passwordInputField.setText("");
      }
    });
    GridBagConstraints gbc_btnLogIn = new GridBagConstraints();
    gbc_btnLogIn.fill = GridBagConstraints.HORIZONTAL;
    gbc_btnLogIn.insets = new Insets(10, 50, 10, 5);
    gbc_btnLogIn.gridx = 0;
    gbc_btnLogIn.gridy = 6;
    frmLogin.getContentPane().add(btnLogIn, gbc_btnLogIn);

    btnRegistration = new JButton("Registration");
    btnRegistration.setFont(new Font("Arial", Font.PLAIN, 14));
    btnRegistration.addActionListener((arg0) -> {
      RegistrationWindow window = new RegistrationWindow();
      window.frmRegistration.setVisible(true);
      frmLogin.setVisible(false);
    });
    GridBagConstraints gbc_btnRegistration = new GridBagConstraints();
    gbc_btnRegistration.insets = new Insets(10, 5, 10, 50);
    gbc_btnRegistration.gridx = 1;
    gbc_btnRegistration.gridy = 6;
    frmLogin.getContentPane().add(btnRegistration, gbc_btnRegistration);
  }

}
