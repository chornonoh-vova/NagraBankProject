package wiev;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class MainWindow {

	public JFrame frmNagrabank;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frmNagrabank.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNagrabank = new JFrame();
		frmNagrabank.setTitle("NagraBank");
		frmNagrabank.setBounds(100, 100, 450, 300);
		frmNagrabank.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
