package wiev;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JTextField;

import javafx.scene.input.KeyCode;

import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ForgotWindow {

  public JFrame frame;
  private JTextField textField;
  private JTextField textField_1;
  private JTextField textField_2;
  private JPasswordField textField_3;
  private JPasswordField textField_4;

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
    gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
    gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
    
    textField_2 = new JTextField();
    GridBagConstraints gbc_textField_2 = new GridBagConstraints();
    gbc_textField_2.insets = new Insets(0, 0, 5, 5);
    gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
    gbc_textField_2.gridx = 1;
    gbc_textField_2.gridy = 2;
    frame.getContentPane().add(textField_2, gbc_textField_2);
    textField_2.setColumns(10);
    
    JLabel lblNewLabel = new JLabel("Enter your secret question");
    lblNewLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
    GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
    gbc_lblNewLabel.gridwidth = 3;
    gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
    gbc_lblNewLabel.gridx = 0;
    gbc_lblNewLabel.gridy = 3;
    frame.getContentPane().add(lblNewLabel, gbc_lblNewLabel);
    
    textField = new JTextField();
    GridBagConstraints gbc_textField = new GridBagConstraints();
    gbc_textField.insets = new Insets(0, 0, 5, 5);
    gbc_textField.fill = GridBagConstraints.HORIZONTAL;
    gbc_textField.gridx = 1;
    gbc_textField.gridy = 4;
    frame.getContentPane().add(textField, gbc_textField);
    textField.setColumns(10);
    
    JLabel lblEnterSecretAnswer = new JLabel("Enter secret answer");
    lblEnterSecretAnswer.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
    GridBagConstraints gbc_lblEnterSecretAnswer = new GridBagConstraints();
    gbc_lblEnterSecretAnswer.gridwidth = 3;
    gbc_lblEnterSecretAnswer.insets = new Insets(0, 0, 5, 0);
    gbc_lblEnterSecretAnswer.gridx = 0;
    gbc_lblEnterSecretAnswer.gridy = 5;
    frame.getContentPane().add(lblEnterSecretAnswer, gbc_lblEnterSecretAnswer);
    
    textField_1 = new JTextField();
    textField_1.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent arg0) {
        if () {
          
        }
      }
    });
    GridBagConstraints gbc_textField_1 = new GridBagConstraints();
    gbc_textField_1.insets = new Insets(0, 0, 5, 5);
    gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
    gbc_textField_1.gridx = 1;
    gbc_textField_1.gridy = 6;
    frame.getContentPane().add(textField_1, gbc_textField_1);
    textField_1.setColumns(10);
    
    JButton btnNewButton = new JButton("Back");
    btnNewButton.addActionListener((arg0) -> {
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
    gbc_lblEnterNewPincode.gridy = 7;
    frame.getContentPane().add(lblEnterNewPincode, gbc_lblEnterNewPincode);
    
    textField_3 = new JPasswordField();
    textField_3.setEnabled(false);
    textField_3.setColumns(10);
    GridBagConstraints gbc_textField_3 = new GridBagConstraints();
    gbc_textField_3.insets = new Insets(0, 0, 5, 5);
    gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
    gbc_textField_3.gridx = 1;
    gbc_textField_3.gridy = 8;
    frame.getContentPane().add(textField_3, gbc_textField_3);
    
    JLabel lblRepeatNewPincode = new JLabel("Repeat new pin-code");
    lblRepeatNewPincode.setEnabled(false);
    lblRepeatNewPincode.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
    GridBagConstraints gbc_lblRepeatNewPincode = new GridBagConstraints();
    gbc_lblRepeatNewPincode.insets = new Insets(0, 0, 5, 5);
    gbc_lblRepeatNewPincode.gridx = 1;
    gbc_lblRepeatNewPincode.gridy = 9;
    frame.getContentPane().add(lblRepeatNewPincode, gbc_lblRepeatNewPincode);
    
    textField_4 = new JPasswordField();
    textField_4.setEnabled(false);
    textField_4.setColumns(10);
    GridBagConstraints gbc_textField_4 = new GridBagConstraints();
    gbc_textField_4.insets = new Insets(0, 0, 5, 5);
    gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
    gbc_textField_4.gridx = 1;
    gbc_textField_4.gridy = 10;
    frame.getContentPane().add(textField_4, gbc_textField_4);
    btnNewButton.setFont(new Font("Arial", Font.PLAIN, 14));
    GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
    gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
    gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
    gbc_btnNewButton.anchor = GridBagConstraints.SOUTH;
    gbc_btnNewButton.gridx = 0;
    gbc_btnNewButton.gridy = 11;
    frame.getContentPane().add(btnNewButton, gbc_btnNewButton);
    
    JButton btnNewButton_1 = new JButton("Continue");
    btnNewButton_1.setFont(new Font("Arial", Font.PLAIN, 14));
    GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
    gbc_btnNewButton_1.gridx = 2;
    gbc_btnNewButton_1.gridy = 11;
    frame.getContentPane().add(btnNewButton_1, gbc_btnNewButton_1);
  }

}
