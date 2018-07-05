
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.security.cert.X509Certificate;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import sun.misc.BASE64Encoder;
 
public class JersyGetClient {
 
    public static void main(String a[]){
         
       /* String url = "https://13.232.102.174/openmrs/ws/rest/v1/person?q=govind&v=full&limit=20&startIndex=0";
        String name = "superman";
        String password = "Admin123";
        String authString = name + ":" + password;
        String authStringEnc = new BASE64Encoder().encode(authString.getBytes());
        System.out.println("Base64 encoded auth string: " + authStringEnc);
        Client restClient = Client.create();
        WebResource webResource = restClient.resource(url);
        ClientResponse resp = webResource.accept("application/json")
                                         .header("Authorization", "Basic " + authStringEnc)
                                         .get(ClientResponse.class);
        if(resp.getStatus() != 200){
            System.err.println("Unable to connect to the server");
        }
        String output = resp.getEntity(String.class);*/
    	
    	
    	
    	 Client client = Client.create();
    WebResource webResource = client.resource("https://13.232.102.174/openmrs").path("ws/rest/v1/person").
                queryParam("q", "govind").
                queryParam("v","full").
                queryParam("limit","20").
                queryParam("startIndex","0");
String result =webResource.type(MediaType.APPLICATION_JSON).get(ClientResponse.class).getEntity(String.class);
    	
        System.out.println("response: "+result);
    }
    
    
   
}