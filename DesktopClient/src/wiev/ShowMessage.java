package wiev;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * Interface to show different types of messages: ERROR_MESSAGE, PLAIN_MESSAGE
 * or dialog with YES_NO_OPTION
 * 
 * @see javax.swing.JDialog
 * @see javax.swing.JOptionPane
 */
public interface ShowMessage {
	/**
	 * Method-wraper at JOptionPane.showMessageDialog(parent, String, String,
	 * JOptionPane.ERROR_MESSAGE)
	 * 
	 * @param header
	 *          header of a dialog
	 * @param message
	 *          info in dialog
	 */
	default void showErrorMessage(String header, String message) {
		final JDialog dialog = new JDialog();
		dialog.setAlwaysOnTop(true);
		JOptionPane.showMessageDialog(dialog, message, header, JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Method-wrapper at JOptionPane.showMessageDialog(parent, String, String,
	 * JOptionPane.PLAIN_MESSAGE)
	 * 
	 * @param header
	 *          header of a dialog
	 * @param message
	 *          info in dialog
	 */
	default void showPlainMessage(String header, String message) {
		final JDialog dialog = new JDialog();
		dialog.setAlwaysOnTop(true);
		JOptionPane.showMessageDialog(dialog, message, header, JOptionPane.PLAIN_MESSAGE);
	}

	/**
	 * Method-wrapper at JOptionPane.showConfirmDialog(parent, String, String,
	 * JOptionPane.YES_NO_OPTION)
	 * 
	 * @param header
	 *          header of a dialog
	 * @param message
	 *          info in dialog
	 * @return int-parameter chosen
	 */
	default int showConfirmDialog(String header, String message) {
		final JDialog dialog = new JDialog();
		dialog.setAlwaysOnTop(true);
		return JOptionPane.showConfirmDialog(dialog, message, header, JOptionPane.YES_NO_OPTION);
	}
}
