import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;

public class Example {
	public static void main(String[] args) throws Exception {
		// Create a trust manager that does not validate certificate chains
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkClientTrusted(X509Certificate[] certs, String authType) {
			}

			public void checkServerTrusted(X509Certificate[] certs, String authType) {
			}
		} };

		// Install the all-trusting trust manager
		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, trustAllCerts, new java.security.SecureRandom());
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

		// Create all-trusting host name verifier
		HostnameVerifier allHostsValid = new HostnameVerifier() {
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		};

		// Install the all-trusting host verifier
		HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

		String urlpath = "https://13.232.102.174/openmrs/ws/rest/v1/person?q=govind&v=full&limit=20&startIndex=0";
		String name = "superman";
		String password = "Admin123";

		String result = "";
		HttpURLConnection connection = null;
		try {
		URL url = new URL(urlpath);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.connect();
			int response = connection.getResponseCode();
			if (response == HttpURLConnection.HTTP_OK) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				StringBuilder builder = new StringBuilder();
				String line = "";
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
				result = builder.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}


	}
}