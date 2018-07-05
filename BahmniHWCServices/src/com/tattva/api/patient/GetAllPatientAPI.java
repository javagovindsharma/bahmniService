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
import org.json.JSONArray;

import com.tattva.api.Commontrust;
import com.tattva.api.person.PersonApi;


public class GetAllPatientAPI {
	
    
	
	public static String IP="13.232.102.174";
	
	public String locUUID="baf7bd38-d225-11e4-9c67-080027b662ec";//"37a22a24-f6d9-4d80-af2f-2bd120362625";    //
	
	public String Prod_UUID="c1c26908-3f10-11e4-adec-0800271c1b75";//"740f25e3-b250-4b7b-9553-0c93831d36e1";   //
	
	public  String GET_ALL_PATIENT_HTTPSURL="https://"+IP+"/openmrs/ws/rest/v1/bahmnicore/sql?location_uuid="+locUUID+"&provider_uuid="+Prod_UUID+"&q=emrapi.sqlSearch.activePatients&v=full";


	public JSONArray getallPatientDetails() {
		JSONArray jsondata=null;
		URL url;

		try {

			// Create a context that doesn't check certificates.

			SSLContext ssl_ctx = SSLContext.getInstance("TLS");

			TrustManager[] trust_mgr = new Commontrust().get_trust_mgr();

			ssl_ctx.init(null, // key manager

					trust_mgr, // trust manager

					new SecureRandom()); // random number generator

			HttpsURLConnection.setDefaultSSLSocketFactory(ssl_ctx.getSocketFactory());

			url = new URL(GET_ALL_PATIENT_HTTPSURL);
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

			 jsondata=new Commontrust().print_contentbyArary(conn);
				
			 
			 System.out.println(getClass()+"JSONDATA"+jsondata);
			
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
