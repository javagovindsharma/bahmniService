package com.tattva.api.patient;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import org.json.JSONObject;

import com.sun.istack.internal.logging.Logger;
import com.tattva.api.Commontrust;
import com.tattva.api.person.PersonApi;

public class StartPatientVisit {

	
	   public JSONObject getPatientFullDetails(String Patient_Identifier){
			JSONObject jsonresp=new JSONObject();
			URL url;
	
			//here get person UUID 
            
            String patient_Uuid=new Commontrust().getPatientUuid(Patient_Identifier);
            
            String http_url="https://"+PersonApi.IP+"/openmrs/ws/rest/v1/bahmnicore/patientprofile/"+patient_Uuid;
        
			
			try {

				// Create a context that doesn't check certificates.

				SSLContext ssl_ctx = SSLContext.getInstance("TLS");

				TrustManager[] trust_mgr = new Commontrust().get_trust_mgr();

				ssl_ctx.init(null, // key manager

						trust_mgr, // trust manager

						new SecureRandom()); // random number generator

				HttpsURLConnection.setDefaultSSLSocketFactory(ssl_ctx.getSocketFactory());

				url = new URL(http_url);
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
				new Commontrust().print_https_cert(conn);
					// dump all the content
					
				jsonresp=new Commontrust().print_content(conn);

				 
				 System.out.println(getClass()+"JSONDATA"+jsonresp.toString());
				
			} catch (MalformedURLException e) {

				e.printStackTrace();

			} catch (IOException e) {

				e.printStackTrace();

			} catch (NoSuchAlgorithmException e) {

				e.printStackTrace();

			} catch (KeyManagementException e) {

				e.printStackTrace();

			}
			return jsonresp;

		}
	   
	   
	   public JSONObject visitStared(String Patient_Identifier) {
		  
		 
		   System.out.println("patient Identiders"+Patient_Identifier );
		   
		   JSONObject jsonresp=new JSONObject();
		   new StartPatientVisit().startVisits(Patient_Identifier);
		   jsonresp=new StartPatientVisit().startEncounter(Patient_Identifier);
		   
		   return jsonresp;
		   
	   }
	   
	   
	   
	   public JSONObject startVisits(String Patient_Identifier){
			JSONObject jsonresp=new JSONObject();
			URL url;
		
			
			  String patient_Uuid=new Commontrust().getPatientUuid(Patient_Identifier);
           String http_url="https://"+PersonApi.IP+"/openmrs/ws/rest/v1/visit";
       
           System.out.println("url of start vists-->>"+http_url);
           
           String Params="{\"patient\":\""+patient_Uuid+"\","
           		+ "\"visitType\":\"c22a5000-3f10-11e4-adec-0800271c1b75\","
           		+ "\"location\":\"37a22a24-f6d9-4d80-af2f-2bd120362625\"}";
			
			try {

				// Create a context that doesn't check certificates.

				SSLContext ssl_ctx = SSLContext.getInstance("TLS");

				TrustManager[] trust_mgr = new Commontrust().get_trust_mgr();

				ssl_ctx.init(null, // key manager

						trust_mgr, // trust manager

						new SecureRandom()); // random number generator

				HttpsURLConnection.setDefaultSSLSocketFactory(ssl_ctx.getSocketFactory());

				url = new URL(http_url);
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
		
				DataOutputStream wr = new DataOutputStream(
	                    conn.getOutputStream());
	                wr.writeBytes(Params);
	                wr.flush();
	                wr.close();
				
					// dumpl all cert info
				new Commontrust().print_https_cert(conn);
					// dump all the content
					
				jsonresp=print_content(conn);

				 
				 System.out.println(getClass()+"JSONDATA"+jsonresp.toString());
				
			} catch (MalformedURLException e) {

				e.printStackTrace();

			} catch (IOException e) {

				e.printStackTrace();

			} catch (NoSuchAlgorithmException e) {

				e.printStackTrace();

			} catch (KeyManagementException e) {

				e.printStackTrace();

			}
			return jsonresp;

		}
	   
	   
	 
	   public JSONObject startEncounter(String Patient_Identifier){
			JSONObject jsonresp=new JSONObject();
			URL url;
		
			
			  String patient_Uuid=new Commontrust().getPatientUuid(Patient_Identifier);
          String http_url="https://"+PersonApi.IP+"/openmrs/ws/rest/v1/bahmnicore/bahmniencounter";
          System.out.println("url of startEncounter-->>"+http_url);
          
          String Params="{\"patientUuid\":\""+patient_Uuid+"\","
          		+ "\"locationUuid\":\"37a22a24-f6d9-4d80-af2f-2bd120362625\","
          		+ "\"encounterTypeUuid\":\"81888515-3f10-11e4-adec-0800271c1b75\","
          		+ "\"orders\":[],\"drugOrders\":[],\"extensions\":{},"
          		+ "\"observations\":[{\"concept\":{\"uuid\":\"c446af3d-3f10-11e4-adec-0800271c1b75\","
          		+ "\"name\":\"Fee Information\",\"dataType\":\"N/A\"},\"units\":null,\"label\":"
          		+ "\"Fee Information\",\"possibleAnswers\":[],\"groupMembers\":[{\"concept\":{\"uuid\":"
          		+ "\"c366184c-3f10-11e4-adec-0800271c1b75\",\"name\":\"REGISTRATION FEES\","
          		+ "\"dataType\":\"Numeric\"},\"units\":null,\"label\":\"REGISTRATION FEES\","
          		+ "\"possibleAnswers\":[],\"groupMembers\":[],\"comment\":null,\"isObservation\":true,"
          		+ "\"conceptUIConfig\":{\"required\":true,\"label\":\"Fee\"},\"uniqueId\":\"observation_4\","
          		+ "\"erroneousValue\":false,\"value\":20,\"autocompleteValue\":20,\"showComment\":false,"
          		+ "\"__prevValue\":2,\"_value\":20,\"disabled\":false,\"voided\":false}],\"comment\":null,"
          		+ "\"isObservation\":true,\"conceptUIConfig\":[],\"uniqueId\":\"observation_6\","
          		+ "\"erroneousValue\":null,\"conceptSetName\":\"Fee Information\",\"voided\":false}],"
          		+ "\"providers\":[{\"uuid\":\"c1c26908-3f10-11e4-adec-0800271c1b75\"}]}";
			
			try {

				// Create a context that doesn't check certificates.

				SSLContext ssl_ctx = SSLContext.getInstance("TLS");

				TrustManager[] trust_mgr = new Commontrust().get_trust_mgr();

				ssl_ctx.init(null, // key manager

						trust_mgr, // trust manager

						new SecureRandom()); // random number generator

				HttpsURLConnection.setDefaultSSLSocketFactory(ssl_ctx.getSocketFactory());

				url = new URL(http_url);
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
		
				DataOutputStream wr = new DataOutputStream(
	                    conn.getOutputStream());
	                wr.writeBytes(Params);
	                wr.flush();
	                wr.close();
				
					// dumpl all cert info
				new Commontrust().print_https_cert(conn);
					// dump all the content
					
				jsonresp=print_content(conn);

				 
				 System.out.println(getClass()+"JSONDATA"+jsonresp.toString());
				
			} catch (MalformedURLException e) {

				e.printStackTrace();

			} catch (IOException e) {

				e.printStackTrace();

			} catch (NoSuchAlgorithmException e) {

				e.printStackTrace();

			} catch (KeyManagementException e) {

				e.printStackTrace();

			}
			return jsonresp;

		}
	   
	   
	   
	   public JSONObject print_content(HttpsURLConnection con) {
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
				reader = new BufferedReader(new	InputStreamReader(con.getInputStream()));
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
