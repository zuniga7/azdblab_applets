package applets;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.JPanel;

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

	public abstract JPanel nodeSetup();

	private void addToApplet(JPanel panel) {
		// get viewable panels from node
		// JPanel panel = node.getModifiedDataPanel();
		// JPanel buttons = node.getModifiedButtonPanel();

		// add data panel into the scroll pane that houses it
		// GraphScrollPane myRightGraphPane = new GraphScrollPane();
		// myRightGraphPane.setViewportView(panel);

		// add scroll pane into applet pane
		Container pane = getContentPane();
		pane.add(panel, BorderLayout.NORTH);
		// pane.add(buttons, BorderLayout.SOUTH);
	}

	protected JPanel getErrorPanel() {
		JPanel p = new JPanel();
		p.add(new JLabel("Unable to load object"));
		return p;
	}
}
