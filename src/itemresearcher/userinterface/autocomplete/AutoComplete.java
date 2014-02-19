package itemresearcher.userinterface.autocomplete;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

public class AutoComplete implements DocumentListener {

	private static enum Mode {
		INSERT,
		COMPLETION
	};

	private JTextField textField;
	private List<String> keywords;
	private Mode mode = Mode.INSERT;

	public AutoComplete(JTextField textField) {
		this.textField = textField;
		this.loadKeywords();
		Collections.sort(keywords);
	}

	private void loadKeywords() {
		keywords = new ArrayList<>();
		final BufferedReader in = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/itemresearcher/userinterface/autocomplete/keywords/Keywords.txt")));
		String input;
		try {
			while ((input = in.readLine()) != null) {
				keywords.add(input);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void changedUpdate(DocumentEvent ev) { }

	@Override
	public void removeUpdate(DocumentEvent ev) { }

	@Override
	public void insertUpdate(DocumentEvent ev) {
		if (ev.getLength() != 1)return;
		final int pos = ev.getOffset();
		String content = null;
		try {
			content = textField.getText(0, pos + 1);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		int w;
		for (w = pos; w >= 0; w--) {
			if (!Character.isLetter(content.charAt(w))) {
				break;
			}
		}
		if (pos - w < 2)return;
		final String prefix = content.substring(w + 1).toLowerCase();
		final int n = Collections.binarySearch(keywords, prefix);
		if (n < 0 && -n <= keywords.size()) {
			String match = keywords.get(-n - 1);
			if (match.startsWith(prefix)) {
				final String completion = match.substring(pos - w);
				SwingUtilities.invokeLater(new CompletionTask(completion, pos + 1));
			}
		} else {
			mode = Mode.INSERT;
		}
	}

	public class CommitAction extends AbstractAction {

		private static final long serialVersionUID = 5794543109646743416L;

		@Override
		public void actionPerformed(ActionEvent ev) {
			if (mode == Mode.COMPLETION) {
				int pos = textField.getSelectionEnd();
				StringBuffer sb = new StringBuffer(textField.getText());
				sb.insert(pos, " ");
				textField.setText(sb.toString());
				textField.setCaretPosition(pos + 1);
				mode = Mode.INSERT;
			} else {
				textField.replaceSelection("\t");
			}
		}
		
	}

	private class CompletionTask implements Runnable {
		
		private String completion;
		private int position;

		public CompletionTask(String completion, int position) {
			this.completion = completion;
			this.position = position;
		}

		public void run() {
			StringBuffer sb = new StringBuffer(textField.getText());
			sb.insert(position, completion);
			textField.setText(sb.toString());
			textField.setCaretPosition(position + completion.length());
			textField.moveCaretPosition(position);
			mode = Mode.COMPLETION;
		}
	}

}
