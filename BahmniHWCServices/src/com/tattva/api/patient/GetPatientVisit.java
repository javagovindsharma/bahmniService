package com.tattva.api.patient;

import java.io.IOException;
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

import com.tattva.api.Commontrust;
import com.tattva.api.person.PersonApi;

public class GetPatientVisit {

	
	
	 public JSONObject getPatientVisit(String identifierPatient) {
		  
		  
		  JSONObject jsonresp=new JSONObject();
		  
		  //here get Patient UUID via patient Identifier id (GAN203016)
		  String patient_Uuid=new Commontrust().getPatientUuid(identifierPatient);
		  
		  
		  //Here get Patient Observation via patient UUID
		  jsonresp=new GetPatientVisit().getVisits(patient_Uuid);
		 
		  
		  return jsonresp;
		  
		  
		  
	  }
	 
	 
	 
	 public JSONObject getVisits(String uuid) {
		  
		  
		  JSONObject jsondata=new JSONObject();
		  	
			
			  String https_url = "https://"+PersonApi.IP+"/openmrs/ws/rest/v1/visit?patient="+uuid+"&v=full";
			
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
	 
	 
	  
}
