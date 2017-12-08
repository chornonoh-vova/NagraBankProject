package wiev;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JTextField;

import client.Checker;
import client.Client;
import client.Md5Hasher;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class ForgotWindow implements ShowMessage, Md5Hasher {
  private Client client = Client.getInstance();
  public JFrame frame;
  private JTextField answerField;
  private JTextField loginField;
  private JPasswordField pinCodeField;
  private JPasswordField pinCodeFiled_1;

   /**
   * Create the application.
   */
  public ForgotWindow() {
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    frame = new JFrame();
    frame.setBounds(100, 100, 450, 460);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    GridBagLayout gridBagLayout = new GridBagLayout();
    gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
    gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
    gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
    frame.getContentPane().setLayout(gridBagLayout);
    
    JLabel lblAccountRecovery = new JLabel("Account recovery");
    lblAccountRecovery.setFont(new Font("Vladimir Script", Font.PLAIN, 42));
    GridBagConstraints gbc_lblAccountRecovery = new GridBagConstraints();
    gbc_lblAccountRecovery.fill = GridBagConstraints.VERTICAL;
    gbc_lblAccountRecovery.gridwidth = 3;
    gbc_lblAccountRecovery.insets = new Insets(0, 0, 5, 0);
    gbc_lblAccountRecovery.gridx = 0;
    gbc_lblAccountRecovery.gridy = 0;
    frame.getContentPane().add(lblAccountRecovery, gbc_lblAccountRecovery);
    
    JLabel lblEnterYourLogin = new JLabel("Enter your login");
    lblEnterYourLogin.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
    GridBagConstraints gbc_lblEnterYourLogin = new GridBagConstraints();
    gbc_lblEnterYourLogin.gridwidth = 3;
    gbc_lblEnterYourLogin.insets = new Insets(0, 0, 5, 0);
    gbc_lblEnterYourLogin.gridx = 0;
    gbc_lblEnterYourLogin.gridy = 1;
    frame.getContentPane().add(lblEnterYourLogin, gbc_lblEnterYourLogin);
    
    loginField = new JTextField();
    
    GridBagConstraints gbc_loginField = new GridBagConstraints();
    gbc_loginField.insets = new Insets(0, 0, 5, 5);
    gbc_loginField.fill = GridBagConstraints.HORIZONTAL;
    gbc_loginField.gridx = 1;
    gbc_loginField.gridy = 2;
    frame.getContentPane().add(loginField, gbc_loginField);
    loginField.setColumns(10);
    
    JLabel lblForQuestion = new JLabel("<secret_question>");
    lblForQuestion.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
    GridBagConstraints gbc_lblForQuestion = new GridBagConstraints();
    gbc_lblForQuestion.gridwidth = 3;
    gbc_lblForQuestion.insets = new Insets(0, 0, 5, 0);
    gbc_lblForQuestion.gridx = 0;
    gbc_lblForQuestion.gridy = 3;
    frame.getContentPane().add(lblForQuestion, gbc_lblForQuestion);
    
    JLabel lblEnterSecretAnswer = new JLabel("Enter secret answer");
    lblEnterSecretAnswer.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
    GridBagConstraints gbc_lblEnterSecretAnswer = new GridBagConstraints();
    gbc_lblEnterSecretAnswer.gridwidth = 3;
    gbc_lblEnterSecretAnswer.insets = new Insets(0, 0, 5, 0);
    gbc_lblEnterSecretAnswer.gridx = 0;
    gbc_lblEnterSecretAnswer.gridy = 4;
    frame.getContentPane().add(lblEnterSecretAnswer, gbc_lblEnterSecretAnswer);
    
    answerField = new JTextField();
    
    GridBagConstraints gbc_answerField = new GridBagConstraints();
    gbc_answerField.insets = new Insets(0, 0, 5, 5);
    gbc_answerField.fill = GridBagConstraints.HORIZONTAL;
    gbc_answerField.gridx = 1;
    gbc_answerField.gridy = 5;
    frame.getContentPane().add(answerField, gbc_answerField);
    answerField.setColumns(10);
    
    JButton backButton = new JButton("Back");
    backButton.addActionListener((arg0) -> {
      LoginWindow window = new LoginWindow();
      window.frmLogin.setVisible(true);
      frame.setVisible(false);
    });
    
    JLabel lblEnterNewPincode = new JLabel("Enter new pin-code");
    lblEnterNewPincode.setEnabled(false);
    lblEnterNewPincode.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
    GridBagConstraints gbc_lblEnterNewPincode = new GridBagConstraints();
    gbc_lblEnterNewPincode.insets = new Insets(0, 0, 5, 5);
    gbc_lblEnterNewPincode.gridx = 1;
    gbc_lblEnterNewPincode.gridy = 6;
    frame.getContentPane().add(lblEnterNewPincode, gbc_lblEnterNewPincode);
    
    pinCodeField = new JPasswordField();
    pinCodeField.setEnabled(false);
    pinCodeField.setColumns(10);
    GridBagConstraints gbc_pinCodeField = new GridBagConstraints();
    gbc_pinCodeField.insets = new Insets(0, 0, 5, 5);
    gbc_pinCodeField.fill = GridBagConstraints.HORIZONTAL;
    gbc_pinCodeField.gridx = 1;
    gbc_pinCodeField.gridy = 7;
    frame.getContentPane().add(pinCodeField, gbc_pinCodeField);
    
    JLabel lblRepeatNewPincode = new JLabel("Repeat new pin-code");
    lblRepeatNewPincode.setEnabled(false);
    lblRepeatNewPincode.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
    GridBagConstraints gbc_lblRepeatNewPincode = new GridBagConstraints();
    gbc_lblRepeatNewPincode.insets = new Insets(0, 0, 5, 5);
    gbc_lblRepeatNewPincode.gridx = 1;
    gbc_lblRepeatNewPincode.gridy = 8;
    frame.getContentPane().add(lblRepeatNewPincode, gbc_lblRepeatNewPincode);
    
    pinCodeFiled_1 = new JPasswordField();
    pinCodeFiled_1.setEnabled(false);
    pinCodeFiled_1.setColumns(10);
    GridBagConstraints gbc_pinCodeFiled_1 = new GridBagConstraints();
    gbc_pinCodeFiled_1.insets = new Insets(0, 0, 5, 5);
    gbc_pinCodeFiled_1.fill = GridBagConstraints.HORIZONTAL;
    gbc_pinCodeFiled_1.gridx = 1;
    gbc_pinCodeFiled_1.gridy = 9;
    frame.getContentPane().add(pinCodeFiled_1, gbc_pinCodeFiled_1);
    backButton.setFont(new Font("Arial", Font.PLAIN, 14));
    GridBagConstraints gbc_backButton = new GridBagConstraints();
    gbc_backButton.insets = new Insets(0, 0, 0, 5);
    gbc_backButton.fill = GridBagConstraints.HORIZONTAL;
    gbc_backButton.anchor = GridBagConstraints.SOUTH;
    gbc_backButton.gridx = 0;
    gbc_backButton.gridy = 10;
    frame.getContentPane().add(backButton, gbc_backButton);
    
    JButton continueButton = new JButton("Continue");
    continueButton.setFont(new Font("Arial", Font.PLAIN, 14));
    GridBagConstraints gbc_continueButton = new GridBagConstraints();
    gbc_continueButton.gridx = 2;
    gbc_continueButton.gridy = 10;
    frame.getContentPane().add(continueButton, gbc_continueButton);
    loginField.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent arg0) {
        if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
          String loginToSend = loginField.getText();
          if (!Checker.verifyLogin(loginToSend)) {
            showErrorMessage("error", "nepravilny login bitch");
            return;
          }
          client.sendMessage("getQuestion", loginToSend);
          String[] answer = null;
          try {
            answer = client.getArrayFromMessage();
          } catch (IOException e) {
            e.printStackTrace();
          }
          if (answer[0].equals("success")) {
            lblForQuestion.setText(answer[1]);
            loginField.setEditable(false);
          } else {
            showErrorMessage("error", answer[1]);
          }
        }
      }
    });
    answerField.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent arg0) {
        if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
          System.out.println("ncdjchd");
        }
      }
    });
    continueButton.addActionListener((arg0)-> {
      
    });
  }

}
