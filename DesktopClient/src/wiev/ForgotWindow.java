package wiev;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import javax.swing.JButton;

public class ForgotWindow {

  private JFrame frame;
  private JTextField textField;
  private JTextField textField_1;
  private JTextField textField_2;

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
    frame.setBounds(100, 100, 450, 300);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    GridBagLayout gridBagLayout = new GridBagLayout();
    gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
    gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
    gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
    frame.getContentPane().setLayout(gridBagLayout);
    
    JLabel lblAccountRecovery = new JLabel("Account recovery");
    GridBagConstraints gbc_lblAccountRecovery = new GridBagConstraints();
    gbc_lblAccountRecovery.gridwidth = 2;
    gbc_lblAccountRecovery.insets = new Insets(0, 0, 5, 5);
    gbc_lblAccountRecovery.gridx = 0;
    gbc_lblAccountRecovery.gridy = 0;
    frame.getContentPane().add(lblAccountRecovery, gbc_lblAccountRecovery);
    
    JLabel lblEnterYourLogin = new JLabel("Enter your login");
    GridBagConstraints gbc_lblEnterYourLogin = new GridBagConstraints();
    gbc_lblEnterYourLogin.gridwidth = 2;
    gbc_lblEnterYourLogin.insets = new Insets(0, 0, 5, 5);
    gbc_lblEnterYourLogin.gridx = 0;
    gbc_lblEnterYourLogin.gridy = 1;
    frame.getContentPane().add(lblEnterYourLogin, gbc_lblEnterYourLogin);
    
    textField_2 = new JTextField();
    GridBagConstraints gbc_textField_2 = new GridBagConstraints();
    gbc_textField_2.gridwidth = 2;
    gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
    gbc_textField_2.insets = new Insets(10, 50, 10, 50);
    gbc_textField_2.gridx = 0;
    gbc_textField_2.gridy = 2;
    frame.getContentPane().add(textField_2, gbc_textField_2);
    textField_2.setColumns(10);
    
    JLabel lblNewLabel = new JLabel("Enter your secret question");
    GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
    gbc_lblNewLabel.gridwidth = 2;
    gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
    gbc_lblNewLabel.gridx = 0;
    gbc_lblNewLabel.gridy = 3;
    frame.getContentPane().add(lblNewLabel, gbc_lblNewLabel);
    
    textField = new JTextField();
    GridBagConstraints gbc_textField = new GridBagConstraints();
    gbc_textField.gridwidth = 2;
    gbc_textField.fill = GridBagConstraints.HORIZONTAL;
    gbc_textField.insets = new Insets(10, 50, 10, 50);
    gbc_textField.gridx = 0;
    gbc_textField.gridy = 4;
    frame.getContentPane().add(textField, gbc_textField);
    textField.setColumns(10);
    
    JLabel lblEnterSecretAnswer = new JLabel("Enter secret answer");
    GridBagConstraints gbc_lblEnterSecretAnswer = new GridBagConstraints();
    gbc_lblEnterSecretAnswer.gridwidth = 2;
    gbc_lblEnterSecretAnswer.insets = new Insets(0, 0, 5, 5);
    gbc_lblEnterSecretAnswer.gridx = 0;
    gbc_lblEnterSecretAnswer.gridy = 5;
    frame.getContentPane().add(lblEnterSecretAnswer, gbc_lblEnterSecretAnswer);
    
    textField_1 = new JTextField();
    GridBagConstraints gbc_textField_1 = new GridBagConstraints();
    gbc_textField_1.gridwidth = 2;
    gbc_textField_1.insets = new Insets(10, 50, 10, 50);
    gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
    gbc_textField_1.gridx = 0;
    gbc_textField_1.gridy = 6;
    frame.getContentPane().add(textField_1, gbc_textField_1);
    textField_1.setColumns(10);
    
    JButton btnNewButton = new JButton("Back");
    GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
    gbc_btnNewButton.insets = new Insets(0, 50, 0, 50);
    gbc_btnNewButton.anchor = GridBagConstraints.NORTHWEST;
    gbc_btnNewButton.gridx = 0;
    gbc_btnNewButton.gridy = 8;
    frame.getContentPane().add(btnNewButton, gbc_btnNewButton);
    
    JButton btnNewButton_1 = new JButton("Continue");
    GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
    gbc_btnNewButton_1.gridx = 2;
    gbc_btnNewButton_1.gridy = 8;
    frame.getContentPane().add(btnNewButton_1, gbc_btnNewButton_1);
  }

}
