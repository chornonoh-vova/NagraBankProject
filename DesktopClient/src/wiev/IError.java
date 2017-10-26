package wiev;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public interface IError {
	default void showErrorMessage(String header, String message) {
		final JDialog dialog = new JDialog();
		dialog.setAlwaysOnTop(true);
		JOptionPane.showMessageDialog(dialog, message, header, JOptionPane.ERROR_MESSAGE);
	}

	default void showPlainMessage(String header, String message) {
		final JDialog dialog = new JDialog();
		dialog.setAlwaysOnTop(true);
		JOptionPane.showMessageDialog(dialog, message, header, JOptionPane.PLAIN_MESSAGE);
	}

	default int showConfirmDialog(String header, String message) {
		final JDialog dialog = new JDialog();
		dialog.setAlwaysOnTop(true);
		return JOptionPane.showConfirmDialog(dialog, message, header, JOptionPane.YES_NO_OPTION);
	}
}
