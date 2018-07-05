import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


import org.apache.tomcat.util.codec.binary.Base64;
 
public class RESTInvoker {
	 String urlpath = "https://13.232.102.174/openmrs/ws/rest/v1/person?q=govind&v=full&limit=20&startIndex=0";
     
	
    public static void main(String[] args) {
		new RESTInvoker().getDataFromServer();
	}
   
 
   public  String getDataFromServer() {
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(urlpath);
            URLConnection urlConnection = setUsernamePassword(url);
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            
            System.out.println(line);
            reader.close();
 
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
 
    private URLConnection setUsernamePassword(URL url) throws IOException {
        URLConnection urlConnection = url.openConnection();
        String authString = "superman"+":"+"Admin123";
        String authStringEnc = new String(Base64.encodeBase64(authString.getBytes()));
        urlConnection.setRequestProperty("Authorization", "Basic " + authStringEnc);
        return urlConnection;
    }
}