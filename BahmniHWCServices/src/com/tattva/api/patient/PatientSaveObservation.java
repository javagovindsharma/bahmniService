package com.tattva.api.patient;

import java.io.DataOutputStream;
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

public class PatientSaveObservation {

	String httpURL="https://13.232.102.174/openmrs/ws/rest/v1/bahmnicore/bahmniencounter";
	
	
	
	
	public JSONObject   addObservationDetails(String DrugsName,String strength) {
	
		JSONObject jsondata=new JSONObject();
		//String DrugsName="Paracetamol & Codeine Phosphate 500mg + 10mg";
		JSONObject data=new PatientSaveObservation().getObservationUuid(DrugsName);
    
		
       System.out.println("DATA-->>"+data);
	  
       JSONArray arrObj = data.getJSONArray("results");
       
       System.out.println(getClass()+"***>>"+arrObj);
       
       for(int i=0;i<arrObj.length();i++) {
    	   JSONObject rec = arrObj.getJSONObject(i);
    	   
    	   System.out.println("Display-->>"+rec.get("display").toString());
    	   
    	   if((rec.get("display").toString().replaceAll("\\s+","")).equalsIgnoreCase((DrugsName+""+strength).replaceAll("\\s+",""))) {
    		  System.out.println("rec"+rec.toString());
    		  System.out.println("DrugsName"+DrugsName);
    		   jsondata.put("DrugName",DrugsName);
    		   jsondata.put("UUID", rec.getString("uuid").toString());
    		   
	    		    JSONObject jsonchild=rec.getJSONObject("dosageForm");
	    		    jsondata.put("display", jsonchild.get("display").toString());
    		   
	    		    JSONObject jsonauditInfo=rec.getJSONObject("auditInfo");
	    		    JSONObject  json_creator=jsonauditInfo.getJSONObject("creator");
	    		    jsondata.put("creator_uuid", json_creator.get("uuid").toString());
	    		    
    		   
    	   }
       }
       
       return jsondata;
	}
	
	public JSONObject  addObservation(String DrugName,String dose,String frequency,String quantity,String duration,String durationUnits,String strength ,String patientIdentifier){
	
		JSONObject jsondata=new JSONObject();
		String doseUnit="";
		
	     
		
		
	        
	        //Here call rest api for get drug Name name UUId and form 
	        JSONObject data=new PatientSaveObservation().addObservationDetails(DrugName,strength);
	       
	       System.out.println("DATA-->>"+data);
		
	       String drug_Uuid=data.getString("UUID");
	       String drug_Name=data.getString("DrugName");
	       String drug_form=data.getString("display");
	       
	       
	       String comments="Govind Sharma Comments";
	       
	       //Here get PatientUUId by Indetifier
	       String patientUuuid=new Commontrust().getPatientUuid(patientIdentifier);
	       
	       
	       
	        String drugsGroup="{ \"careSetting\": \"OUTPATIENT\",\n" + 
	        		"      \"drug\": {\n" + 
	        		"        \"name\": \""+drug_Name+"\",\n" + 
	        		"        \"form\": \""+drug_form+"\",\n" + 
	        		"        \"uuid\": \""+drug_Uuid+"\"\n" + 
	        		"      },\n" + 
	        		"      \"orderType\": \"Drug Order\",\n" + 
	        		"      \"dosingInstructionType\": \"org.openmrs.module.bahmniemrapi.drugorder.dosinginstructions.FlexibleDosingInstructions\",\n" + 
	        		"      \"dosingInstructions\": {\n" + 
	        		"        \"dose\": "+dose+",\n" + 
	        		"        \"doseUnits\": \""+doseUnit+"\",\n" + 
	        		"        \"route\": \"Oral\",\n" + 
	        		"        \"frequency\": \""+frequency+"\",\n" + 
	        		"        \"asNeeded\": false ,\n" + 
	        		"\"administrationInstructions\":\"{\\\"instructions\\\":\\\"As directed\\\",\\\"additionalInstructions\\\":\\\""+comments+"\\\"}\""+ 
	        		"  ,      \"quantity\": "+quantity+",\n" + 
	        		"        \"quantityUnits\": \""+doseUnit+"\",\n" + 
	        		"        \"numberOfRefills\": 0 \n" + 
	        		"      },\n" + 
	        		"      \"duration\": "+duration+",\n" + 
	        		"      \"durationUnits\": \""+durationUnits+"\",\n" + 
	        		"      \"scheduledDate\": null,\n" + 
	        		"      \"autoExpireDate\": null,\n" + 
	        		"      \"dateStopped\": null,\n" + 
	        		"      \"orderGroup\": {\n" + 
	        		"        \"orderSet\": {}\n" + 
	        		"      }\n" + 
	        		"    }";
	        
	        
	        
		String jsonStr="{\"locationUuid\":\"37a22a24-f6d9-4d80-af2f-2bd120362625\","
				+ "\"patientUuid\":\""+patientUuuid+"\","
				+ "\"encounterUuid\":null,\"visitUuid\":null,"
				+ "\"providers\":[{\"uuid\":\"c1c26908-3f10-11e4-adec-0800271c1b75\"}],"
				+ "\"encounterDateTime\":null,\"extensions\":{\"mdrtbSpecimen\":[]},\"context\":{},\"visitType\":\"OPD\","
				+ "\"bahmniDiagnoses\":[],"
				+ "\"orders\":[],"
				+ "\"drugOrders\":["+drugsGroup+"],"
				+ "\"disposition\":null,"
				+ "\"observations\":[],"
				+ "\"encounterTypeUuid\":\"81852aee-3f10-11e4-adec-0800271c1b75\"}";
				
				
		
		
		
		
		System.out.println("Param-->>"+jsonStr);	
				
		URL url;

		try {

			// Create a context that doesn't check certificates.

			SSLContext ssl_ctx = SSLContext.getInstance("TLS");

			TrustManager[] trust_mgr = new Commontrust().get_trust_mgr();

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

			
		
                new Commontrust().print_https_cert(conn);

			// dump all the content

                
              
              jsondata.put("DATA",jsonStr)  ;
                
			 jsondata= new Commontrust().print_content(conn);

			 
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
	
	
	
	
	
	//get Drugs UUId via Rest api
	
      public JSONObject getObservationUuid(String DrugName) {
    	 
		JSONObject jsondata=null;
		
	//	String https_url="https://13.232.102.174/openmrs/ws/rest/v1/drug?q=Para&s=ordered&v=full";
		  String https_url = "https://"+PersonApi.IP+"/openmrs/ws/rest/v1/drug?q="+DrugName.replaceAll("\\s+","")+"&s=ordered&v=full";
		
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

	
    
}
