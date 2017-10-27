package wiev;

import javax.swing.JFrame;

import client.Client;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow implements ShowMessage, Md5Hasher {

	private Client client = Client.getInstance();
	public JFrame frmNagrabank;

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
	}

}
