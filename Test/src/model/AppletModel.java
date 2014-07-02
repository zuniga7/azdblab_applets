package model;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JPanel;

public class AppletModel {

	/**
	 * From <a href=
	 * 'http://stackoverflow.com/questions/4205980/java-sending-http-parameters-via-post-method-easily'>StackOverflow</
	 * a >
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

		// send parameters
		DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		// get response
		InputStream response = connection.getInputStream();
		ObjectInputStream inputStream = new ObjectInputStream(response);
		Object object = inputStream.readObject();
		
		inputStream.close();
		connection.disconnect();

		return object;
	}

}
