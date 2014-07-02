package applets;

import java.io.IOException;

/**
 * 
 * @author hazielzuniga
 * 
 */
public class QueryApplet extends ObjectApplet {

	@Override
	public Object returnRequestObject() throws ClassNotFoundException,
			IOException {
		return model.postRequest(
				"https://aw.cs.arizona.edu/AZDBLAB/response.jsp",
				"dataTarget=applet&id=visualize");
	}
}
