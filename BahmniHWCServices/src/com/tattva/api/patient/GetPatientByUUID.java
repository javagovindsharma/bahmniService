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
import org.json.JSONObject;
import com.tattva.api.Commontrust;
import com.tattva.api.person.PersonApi;


public class GetPatientByUUID {

	//public String UUID="740f25e3-b250-4b7b-9553-0c93831d36e1";
	
	
	
	public JSONObject getPatientByUUID(String uuid) {
		JSONObject jsondata=null;
		JSONObject jsonresp=new JSONObject();
		URL url;
		
		
		String GETPATIENTBYUUID_URL="https://13.232.102.174/openmrs/ws/rest/v1/patient/"+uuid+"?v=full";
		
		try {

			// Create a context that doesn't check certificates.

			SSLContext ssl_ctx = SSLContext.getInstance("TLS");

			TrustManager[] trust_mgr = new Commontrust().get_trust_mgr();

			ssl_ctx.init(null, // key manager

					trust_mgr, // trust manager

					new SecureRandom()); // random number generator

			HttpsURLConnection.setDefaultSSLSocketFactory(ssl_ctx.getSocketFactory());

			url = new URL(GETPATIENTBYUUID_URL);
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

			
				
			  jsondata=new Commontrust().print_content(conn);
				
				
				
				JSONArray jsonNames=jsondata.names();
				
				
				for(int i=0;i<jsonNames.length();i++) {
					
					String headerName=jsonNames.get(i).toString();
				
					
					if(headerName.equalsIgnoreCase("auditInfo")) {
					//	JSONObject params = jsondata.getJSONObject(headerName);
						
					}else if(headerName.equalsIgnoreCase("identifiers")) {
						JSONArray params = jsondata.getJSONArray(headerName);
				
						for(int k=0;k<params.length();k++)
			            {
			                JSONObject jsonObject1 = params.getJSONObject(k);
			               
			                if(k==0)
			                  jsonresp.append("Patient_ID", jsonObject1.optString("identifier"));
			                else if(k==1)
			                	  jsonresp.append("National_ID", jsonObject1.optString("identifier"));
			                
			                
			            }
						
					//	jsonresp.put("identifier",String.valueOf(params.getString(headerName)));
					  System.out.println("data "+params);
					}else if(headerName.equalsIgnoreCase("person")) {
						JSONObject params = jsondata.getJSONObject(headerName);
						JSONArray paramsNames=params.names();
						
						System.out.println("ParamNamme"+paramsNames);
						for(int j=0;j<paramsNames.length();j++) {
							  String names=paramsNames.getString(j);     
							 
							
						if(names.equalsIgnoreCase("birthdate")) {
							jsonresp.append("birthdate", params.optString("birthdate"));
						}else if(names.equalsIgnoreCase("gender")) {
							jsonresp.append("gender", params.optString("gender"));
							
						}else if(names.equalsIgnoreCase("display")) {
							jsonresp.append("display", params.optString("display"));
						}else if(names.equalsIgnoreCase("age"))	{
							jsonresp.append("age", params.optString("age"));
						}else if(names.equalsIgnoreCase("addresses"))	{
							JSONArray paramsChild = params.getJSONArray(names);
							

							for(int k=0;k<paramsChild.length();k++)
				            {
				                JSONObject jsonObject1 = paramsChild.getJSONObject(k);
				               
				                jsonresp.append("country", jsonObject1.get("country").toString());
				                jsonresp.append("address1", jsonObject1.get("address1").toString());
				                jsonresp.append("address2", jsonObject1.get("address2").toString());
				                jsonresp.append("cityVillage", jsonObject1.get("cityVillage").toString());
				                jsonresp.append("stateProvince", jsonObject1.get("stateProvince").toString());
				                jsonresp.append("countyDistrict", jsonObject1.get("countyDistrict").toString());
				                jsonresp.append("address3", jsonObject1.get("address3").toString());
				                
				               
				                
				            }
							System.out.println("Addresss-->"+paramsChild);
						
						
						
						}else if(names.equalsIgnoreCase("names")){
							JSONArray paramsChild = params.getJSONArray(names);
							
							for(int k=0;k<paramsChild.length();k++)
				            {
				                JSONObject jsonObject1 = paramsChild.getJSONObject(k);
				               
				                jsonresp.append("display", jsonObject1.get("display").toString());
				                jsonresp.append("givenName", jsonObject1.get("givenName").toString());
				                jsonresp.append("familyName", jsonObject1.get("familyName").toString());
				                jsonresp.append("middleName", jsonObject1.get("middleName").toString());
				               
				                
				            }
									
							
						}else if(names.equalsIgnoreCase("attributes")){	
							JSONArray paramsChild = params.getJSONArray(names);
							System.out.println("Attributes"+paramsChild);
					    }//else 
					}//loop 
						System.out.println("data "+params);
					}
					
				}
				
			 
			 
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

	
}
