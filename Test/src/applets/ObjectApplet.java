package applets;

import java.awt.Component;
import java.awt.Container;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.AppletModel;

public abstract class ObjectApplet extends JApplet {
	protected AppletModel model;

	public void init() {
		setup();
		addToApplet(nodeSetup());
	}

	private void setup() {
		model = new AppletModel();
	}

	private void addToApplet(JPanel panel) {
		Container pane = getContentPane();
		pane.add(panel);
	}

	public JPanel nodeSetup() {
		JPanel panel = new JPanel();

		try {
			panel.add((Component) returnRequestObject());

		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			return getErrorPanel(e.getMessage());
		}

		return panel;
	}

	public abstract Object returnRequestObject() throws ClassNotFoundException,
			IOException;

	private JPanel getErrorPanel(String message) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(new JLabel("Unable to load object"));

		JTextArea textArea = new JTextArea();
		textArea.append(message);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);

		JScrollPane scroll = new JScrollPane(textArea);
		panel.add(scroll);

		return panel;
	}
}
