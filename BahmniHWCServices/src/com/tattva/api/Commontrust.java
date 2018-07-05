package com.tattva.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
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

import com.tattva.api.person.PersonApi;

public class Commontrust {

	
	
	public TrustManager[] get_trust_mgr() {

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
	
	
	
	
	
	
	
	public void print_https_cert(HttpsURLConnection con) {

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

	
	
	public JSONArray print_contentbyArary(HttpsURLConnection con) {
		JSONArray jsonObj =null;
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
					
					System.out.println("STR-->>>"+str);
				
					 jsonObj = new JSONArray(str);
				
					
					System.out.println("json Response --->>"+jsonObj);
				System.out.println("****** print_content  method end ********");

				br.close();

			} catch (IOException e) {

				e.printStackTrace();

			}

		}
		
		return jsonObj;

	}

	
	
	
	
	
	 public String getPatientUuid(String Identifier) {
    	 
			JSONObject jsondata=null;
			
			String patient_uuid="";
			
			  String https_url = "https://"+PersonApi.IP+"/openmrs/ws/rest/v1/patient?identifier="+Identifier;
			
			  System.out.println("URl->>>"+https_url);
			URL url;

			try {

				// Create a context that doesn't check certificates.

				SSLContext ssl_ctx = SSLContext.getInstance("TLS");

				TrustManager[] trust_mgr =  new Commontrust().get_trust_mgr();

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

				
			
				 new Commontrust().print_https_cert(conn);

				// dump all the content

				 jsondata= new Commontrust().print_content(conn);

				 System.out.println(getClass()+"Array-->>"+jsondata);
				 JSONArray arrObj = jsondata.getJSONArray("results");
			       
			       System.out.println("getPatientUuid  array-->>>>"+arrObj);
			       
			       for(int i=0;i<arrObj.length();i++) {
			    	   JSONObject rec = arrObj.getJSONObject(i);
			       
			    	   patient_uuid=rec.getString("uuid");
			       }
				 
				
				
			} catch (MalformedURLException e) {

				e.printStackTrace();

			} catch (IOException e) {

				e.printStackTrace();

			} catch (NoSuchAlgorithmException e) {

				e.printStackTrace();

			} catch (KeyManagementException e) {

				e.printStackTrace();

			}
			
			
			return patient_uuid;

		}
		
	
	
	
	
}
