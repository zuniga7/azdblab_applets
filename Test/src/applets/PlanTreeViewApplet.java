package applets;

import java.io.IOException;

public class PlanTreeViewApplet extends ObjectApplet {

	@Override
	public Object returnRequestObject() throws ClassNotFoundException,
			IOException {
		return model.postRequest(
				"https://aw.cs.arizona.edu/AZDBLAB/response.jsp",
				"dataTarget=applet&id=planTreeView&runID=" + getParameter("runID")
						+ "&iQueryNum=" + getParameter("iQueryNum")
						+ "&queryExecutionNum" + getParameter("queryExecutionNum"));

	}
}
