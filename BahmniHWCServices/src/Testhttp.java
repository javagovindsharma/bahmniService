import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class Testhttp {

	public static void main(String[] args) throws NoSuchAlgorithmException, KeyManagementException {

		String https_url ="https://13.232.102.174/openmrs/ws/rest/v1/person"; 

		String JsonResponse = null;
		String JsonDATA = "{\"gender\":\"M\",\"names\":[{\"givenName\":\"ff\",\"familyName\":\"sharma\"}],\"age\":\"28\"}";
		
		
		SSLContext ssl_ctx = SSLContext.getInstance("TLS");

		TrustManager[] trust_mgr = get_trust_mgr();

		ssl_ctx.init(null, // key manager

				trust_mgr, // trust manager

				new SecureRandom()); // random number generator

		HttpsURLConnection.setDefaultSSLSocketFactory(ssl_ctx.getSocketFactory());
		
		
	
		

		
		
		
		HttpsURLConnection urlConnection = null;
		BufferedReader reader = null;
		try {
			URL url = new URL(https_url);
			urlConnection = (HttpsURLConnection) url.openConnection();
			urlConnection.setDoOutput(true);
			urlConnection.setRequestMethod("POST");
			urlConnection.setRequestProperty("Content-Type", "application/json");
			urlConnection.setRequestProperty("Accept", "application/json");
			Writer writer = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream(), "UTF-8"));

			writer.write(JsonDATA);
//writer.write(JsonDATA.replace("\"",""));
			writer.close();
		
			((HttpsURLConnection) urlConnection).setHostnameVerifier(new HostnameVerifier() {

				public boolean verify(String host, SSLSession sess) {

					if (host.equals("13.232.102.174"))

						return true;

					else

						return false;

				}

			});
			
			InputStream inputStream = urlConnection.getInputStream();
			StringBuffer buffer = new StringBuffer();
			if (inputStream == null) {
// Nothing to do.
			
			}
			reader = new BufferedReader(new InputStreamReader(inputStream));

			String inputLine;
			while ((inputLine = reader.readLine()) != null)
				buffer.append(inputLine + "\n");
			if (buffer.length() == 0) {
// Stream was empty. No point in parsing.
			
			}
			JsonResponse = buffer.toString();
			System.out.println("RESPONSE "+ JsonResponse);
//send to post execute
			

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (urlConnection != null) {
				urlConnection.disconnect();
			}
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					System.out.println("Error"+ "Error closing stream"+ e);
				}
			}
		}
	

	}

	
	
	private static TrustManager[] get_trust_mgr() {

		TrustManager[] certs = new TrustManager[] { new X509TrustManager() {

			public X509Certificate[] getAcceptedIssuers() {

				return null;

			}

			public void checkClientTrusted(X509Certificate[] certs, String t) {

			}

			public void checkServerTrusted(X509Certificate[] certs, String t) {

			}

		} };

		return certs;

	}
}
