package model;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JPanel;

/**
 * 
 * @author hazielzuniga
 * 
 */
public class AppletModel {

	/**
	 * Makes a POST Request to the URL with the given urlParameters. Then
	 * retrieves an object in response and returns it.
	 * 
	 * @see <a href='http://stackoverflow.com/a/4206094/3435718'> StackOverflow
	 *      -- Java - sending HTTP parameters via POST method easily </a>
	 * 
	 * @param URL
	 * @param urlParameters
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public Object postRequest(String URL, String urlParameters)
			throws IOException, ClassNotFoundException {
		URL url = new URL(URL);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setInstanceFollowRedirects(false);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		connection.setRequestProperty("charset", "utf-8");
		connection.setRequestProperty("Content-Length",
				"" + Integer.toString(urlParameters.getBytes().length));
		connection.setUseCaches(false);
		connection.setDefaultUseCaches(false);

		// send parameters
		DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		// get response
		InputStream response = connection.getInputStream();
		ObjectInputStream inputStream = new CustomInputStream(response);
		Object object = inputStream.readObject();

		inputStream.close(); // close ObjectInputStream
		connection.disconnect(); // disconnect from server

		return object;
	}

}
