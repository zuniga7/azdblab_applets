package applets;

import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class QueryApplet extends ObjectApplet {

	@Override
	public JPanel nodeSetup() {
		JPanel panel = new JPanel();

		try {
			Object object = model.postRequest(
					"https://aw.cs.arizona.edu/AZDBLAB/response.jsp",
					"dataTarget=applet");

			panel.add(new JLabel((String) object));

		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			return getErrorPanel();
		}

		return panel;
	}
}
