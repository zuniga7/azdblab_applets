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

/**
 * 
 * @author hazielzuniga
 * 
 */
public abstract class ObjectApplet extends JApplet {
	protected AppletModel model;

	public void init() {
		setup();
		addToApplet(nodeSetup());
	}

	private void setup() {
		model = new AppletModel();
	}

	/**
	 * add a JPanel to this applet
	 * 
	 * @param panel
	 */
	private void addToApplet(JPanel panel) {
		Container pane = getContentPane();
		pane.add(panel);
	}

	/**
	 * calls returnRequestObject() and adds the Object (Component) retrieved
	 * into a panel that will be displayed on the applet
	 * 
	 * @return
	 */
	private JPanel nodeSetup() {
		JPanel panel = new JPanel();

		try {
			panel.add((Component) returnRequestObject());

		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			return getErrorPanel(e.getMessage());
		}

		return panel;
	}

	/**
	 * Will call model.postRequest(URL, Parameters) to make a request to
	 * response.jsp, which will return the Object (Component) to be displayed by
	 * the applet. <br>
	 * <br>
	 * 
	 * It should be implemented similarly to this: <br>
	 * return model.postRequest(
	 * "https://aw.cs.arizona.edu/AZDBLAB/response.jsp",
	 * "dataTarget=applet&id=YOURIDTHATCALLSAMETHOD");
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public abstract Object returnRequestObject() throws ClassNotFoundException,
			IOException;

	/**
	 * Returns a JPanel containing the error message provided
	 * 
	 * @param message
	 * @return
	 */
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
