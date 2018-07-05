package com.tattva.api.patient;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.json.JSONArray;
import org.json.JSONObject;
import com.tattva.api.Commontrust;
import com.tattva.api.person.PersonApi;

public class PatientSaveOrders {

	String httpURL="https://13.232.102.174/openmrs/ws/rest/v1/bahmnicore/bahmniencounter";
	
	
	
	
	public JSONObject   addOrdersDetails(String orderName) {
	
		JSONObject jsondata=new JSONObject();
		
		JSONObject data=new PatientSaveOrders().getOrdersUuid(orderName);
    
		
       System.out.println("DATA-->>"+data);
	  
       JSONArray arrObj = data.getJSONArray("results");
       
       System.out.println(getClass()+"***>>"+arrObj);
       
       for(int i=0;i<arrObj.length();i++) {
    	   JSONObject rec = arrObj.getJSONObject(i);
    	   
    	   System.out.println("Display-->>"+rec.get("display").toString());
    	   
    	   if((rec.get("display").toString().replaceAll("\\s+","")).equalsIgnoreCase((orderName).replaceAll("\\s+",""))) {
    		  System.out.println("rec"+rec.toString());
    		  System.out.println("DrugsName"+orderName);
    		   jsondata.put("DrugName",orderName);
    		   jsondata.put("UUID", rec.getString("uuid").toString());
    		   
    	   }
       }
       
       return jsondata;
	}
	
	public JSONObject  addOrders(String orderName,String patientIdentifier){
	
		JSONObject jsondata=new JSONObject();
		   //Here call rest api for get drug Name name UUId and form 
	        JSONObject data=new PatientSaveOrders().addOrdersDetails(orderName);
	       
	       System.out.println("DATA-->>"+data);
		
	       String order_Uuid=data.getString("UUID");
	        
	       //Here get PatientUUId by Indetifier
	       String patientUuuid=new Commontrust().getPatientUuid(patientIdentifier);
	       
	       String jsonChildStr="{\"concept\":{\"uuid\":\""+order_Uuid+"\"}}";
	       
	        
	        
		String jsonStr="{\"locationUuid\":\"37a22a24-f6d9-4d80-af2f-2bd120362625\","
				+ "\"patientUuid\":\""+patientUuuid+"\","
				+ "\"encounterUuid\":null,\"visitUuid\":null,"
				+ "\"providers\":[{\"uuid\":\"c1c26908-3f10-11e4-adec-0800271c1b75\"}],"
				+ "\"encounterDateTime\":null,\"extensions\":{\"mdrtbSpecimen\":[]},\"context\":{},\"visitType\":\"OPD\","
				+ "\"bahmniDiagnoses\":[],"
				+ "\"orders\":["+jsonChildStr+"],"
				+ "\"drugOrders\":[],"
				+ "\"disposition\":null,"
				+ "\"observations\":[],"
				+ "\"encounterTypeUuid\":\"81852aee-3f10-11e4-adec-0800271c1b75\"}";
				
				
		
		
		
		
		System.out.println("Param-->>"+jsonStr);	
				
		URL url;

		try {

			// Create a context that doesn't check certificates.

			SSLContext ssl_ctx = SSLContext.getInstance("TLS");

			TrustManager[] trust_mgr = new PatientSaveOrders().get_trust_mgr();

			ssl_ctx.init(null, // key manager

					trust_mgr, // trust manager

					new SecureRandom()); // random number generator

			HttpsURLConnection.setDefaultSSLSocketFactory(ssl_ctx.getSocketFactory());
			url = new URL(httpURL);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setRequestProperty("Content-Type","application/json");
	        conn.setDoOutput(true);
	        conn.setRequestProperty("Authorization", "Basic c3VwZXJtYW46QWRtaW4xMjM=");
            conn.setRequestMethod("POST");
            
			
		
			conn.setHostnameVerifier(new HostnameVerifier() {

				public boolean verify(String host, SSLSession sess) {

					if (host.equals(PersonApi.IP))

						return true;

					else

						return false;

				}

			});

			
			DataOutputStream wr = new DataOutputStream(
                    conn.getOutputStream());
                wr.writeBytes(jsonStr);
                wr.flush();
                wr.close();
			// dumpl all cert info

			
		
                new PatientSaveOrders().print_https_cert(conn);

			// dump all the content

                
              
              jsondata.put("DATA",jsonStr)  ;
                
			 jsondata= new PatientSaveOrders().print_content(conn);

			 
			 System.out.println("JSOn DATA"+jsondata);
			
			 System.out.println("Param-->>"+jsonStr);	
			 
		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();

		} catch (KeyManagementException e) {

			e.printStackTrace();

		}
			
		return jsondata;

	}
	
	
	
	
	
	//get order UUId via Rest api
	
      public JSONObject getOrdersUuid(String orderTest)  {
    	 
		JSONObject jsondata=null;
		 String https_url="";
		
		  try {
		  https_url = "https://"+PersonApi.IP+"/openmrs/ws/rest/v1/concept?q="+URLEncoder.encode(orderTest, "UTF-8");;
		  }catch(UnsupportedEncodingException e) {
			  e.printStackTrace();
		  }
		  
		  
		  System.out.println("URl->>>"+https_url);
		URL url;

		try {

			// Create a context that doesn't check certificates.

			SSLContext ssl_ctx = SSLContext.getInstance("TLS");

			TrustManager[] trust_mgr =  new PatientSaveOrders().get_trust_mgr();

			ssl_ctx.init(null, // key manager

					trust_mgr, // trust manager

					new SecureRandom()); // random number generator

			HttpsURLConnection.setDefaultSSLSocketFactory(ssl_ctx.getSocketFactory());

			url = new URL(https_url);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setRequestProperty("Content-Type","application/json");
	        conn.setDoOutput(true);
	        conn.setRequestProperty("Authorization", "Basic c3VwZXJtYW46QWRtaW4xMjM=");
			conn.setRequestMethod("GET");


			conn.setHostnameVerifier(new HostnameVerifier() {

				public boolean verify(String host, SSLSession sess) {

					if (host.equals(PersonApi.IP))

						return true;

					else

						return false;

				}

			});

			// dumpl all cert info
			 new PatientSaveOrders().print_https_cert(conn);

			// dump all the content

			 jsondata= new PatientSaveOrders().print_content(conn);
		  
			 System.out.println("Array-->>"+jsondata);
			
		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();

		} catch (KeyManagementException e) {

			e.printStackTrace();

		}
		
		
		return jsondata;

	}
      
      
      private TrustManager[] get_trust_mgr() {

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
      
      
      private void print_https_cert(HttpsURLConnection con) {

  		if (con != null) {

  			try {

  				System.out.println("Response Code : " + con.getResponseCode());

  				System.out.println("Cipher Suite : " + con.getCipherSuite());

  				System.out.println("\n");

  				Certificate[] certs = con.getServerCertificates();

  				for (Certificate cert : certs) {

  					System.out.println("Cert Type : " + cert.getType());

  					System.out.println("Cert Hash Code : " + cert.hashCode());

  					System.out.println("Cert Public Key Algorithm : " + cert.getPublicKey().getAlgorithm());

  					System.out.println("Cert Public Key Format : " + cert.getPublicKey().getFormat());

  					System.out.println("\n");

  				}

  			} catch (SSLPeerUnverifiedException e) {

  				e.printStackTrace();

  			} catch (IOException e) {

  				e.printStackTrace();

  			}

  		}

  	}

  	private JSONObject print_content(HttpsURLConnection con) {
  		JSONObject jsonObj =null;
  		if (con != null) {
  			BufferedReader reader = null;
  			try {

  				System.out.println("****** Content of the URL ********");

  				System.out.println("conenction------>>"+con);
  					    
  			System.out.println("InputStream  --->>>"+con.getResponseCode());
  		
  			
  			if(con.getResponseCode() == 200)
  			{
  			reader = new BufferedReader(new	InputStreamReader(con.getInputStream()));
  			}
  			else
  			{
  			reader = new BufferedReader(new	InputStreamReader(con.getErrorStream()));
  			}
  			
  			 BufferedReader br = new BufferedReader(reader);	
  				
  			 
  					String input,str="";
  					System.out.println("****** print_content  while start ********");
  					while ((input = br.readLine()) != null) {
  	
  						str+=str+input;
  	
  					}
  				
  					if(str.length()>5)
  					   jsonObj = new JSONObject(str);
  					
  					
  					System.out.println("json Response --->>"+jsonObj);
  				System.out.println("****** print_content  method end ********");

  				br.close();

  			} catch (IOException e) {

  				e.printStackTrace();

  			}

  		}
  		
  		return jsonObj;

  	}
      
      
      
      

	
}
