import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

public class ReadJSONDATA {

	public static void main(String[] args) {
		String data="{\"auditInfo\":{\"dateChanged\":null,\"creator\":{\"display\":\"superman\",\"links\":[{\"rel\":\"self\",\"uri\":\"http://13.232.102.174/openmrs/ws/rest/v1/user/c1c21e11-3f10-11e4-adec-0800271c1b75\"}],\"uuid\":\"c1c21e11-3f10-11e4-adec-0800271c1b75\"},\"dateCreated\":\"2018-06-12T04:57:14.000+0530\",\"changedBy\":null},\"identifiers\":[{\"identifier\":\"GAN203007\",\"display\":\"Patient Identifier = GAN203007\",\"resourceVersion\":\"1.8\",\"location\":null,\"voided\":false,\"links\":[{\"rel\":\"self\",\"uri\":\"http://13.232.102.174/openmrs/ws/rest/v1/patient/ddf1b15f-b1f5-4e79-995f-4edb055ec53b/identifier/b24f2dcf-65e2-4ed3-8dcb-e80f8edf01c6\"},{\"rel\":\"full\",\"uri\":\"http://13.232.102.174/openmrs/ws/rest/v1/patient/ddf1b15f-b1f5-4e79-995f-4edb055ec53b/identifier/b24f2dcf-65e2-4ed3-8dcb-e80f8edf01c6?v=full\"}],\"identifierType\":{\"display\":\"Patient Identifier\",\"links\":[{\"rel\":\"self\",\"uri\":\"http://13.232.102.174/openmrs/ws/rest/v1/patientidentifiertype/81433852-3f10-11e4-adec-0800271c1b75\"}],\"uuid\":\"81433852-3f10-11e4-adec-0800271c1b75\"},\"uuid\":\"b24f2dcf-65e2-4ed3-8dcb-e80f8edf01c6\",\"preferred\":true},{\"identifier\":\"NAT2805\",\"display\":\"National ID = NAT2805\",\"resourceVersion\":\"1.8\",\"location\":null,\"voided\":false,\"links\":[{\"rel\":\"self\",\"uri\":\"http://13.232.102.174/openmrs/ws/rest/v1/patient/ddf1b15f-b1f5-4e79-995f-4edb055ec53b/identifier/b6a1930b-3781-4fc8-9c63-57db405521d5\"},{\"rel\":\"full\",\"uri\":\"http://13.232.102.174/openmrs/ws/rest/v1/patient/ddf1b15f-b1f5-4e79-995f-4edb055ec53b/identifier/b6a1930b-3781-4fc8-9c63-57db405521d5?v=full\"}],\"identifierType\":{\"display\":\"National ID\",\"links\":[{\"rel\":\"self\",\"uri\":\"http://13.232.102.174/openmrs/ws/rest/v1/patientidentifiertype/0d2ac572-8de3-46c8-9976-1f78899c599f\"}],\"uuid\":\"0d2ac572-8de3-46c8-9976-1f78899c599f\"},\"uuid\":\"b6a1930b-3781-4fc8-9c63-57db405521d5\",\"preferred\":false}],\"person\":{\"addresses\":[{\"country\":null,\"countyDistrict\":null,\"endDate\":null,\"postalCode\":null,\"latitude\":null,\"uuid\":\"c77ec195-0064-48f1-8219-6e8f9bca1df6\",\"address7\":null,\"address6\":null,\"address5\":null,\"voided\":false,\"links\":[{\"rel\":\"self\",\"uri\":\"http://13.232.102.174/openmrs/ws/rest/v1/person/ddf1b15f-b1f5-4e79-995f-4edb055ec53b/address/c77ec195-0064-48f1-8219-6e8f9bca1df6\"},{\"rel\":\"full\",\"uri\":\"http://13.232.102.174/openmrs/ws/rest/v1/person/ddf1b15f-b1f5-4e79-995f-4edb055ec53b/address/c77ec195-0064-48f1-8219-6e8f9bca1df6?v=full\"}],\"address4\":null,\"preferred\":true,\"address9\":null,\"longitude\":null,\"address8\":null,\"address3\":null,\"address2\":null,\"address1\":null,\"display\":null,\"resourceVersion\":\"2.0\",\"stateProvince\":null,\"cityVillage\":\"Thane\",\"address10\":null,\"address11\":null,\"address12\":null,\"address13\":null,\"address14\":null,\"address15\":null,\"startDate\":null}],\"birthdate\":\"1969-06-12T00:00:00.000+0530\",\"gender\":\"M\",\"display\":\"FindBy MobileNumber\",\"resourceVersion\":\"1.11\",\"dead\":false,\"uuid\":\"ddf1b15f-b1f5-4e79-995f-4edb055ec53b\",\"preferredAddress\":{\"country\":null,\"countyDistrict\":null,\"endDate\":null,\"postalCode\":null,\"latitude\":null,\"uuid\":\"c77ec195-0064-48f1-8219-6e8f9bca1df6\",\"address7\":null,\"address6\":null,\"address5\":null,\"voided\":false,\"links\":[{\"rel\":\"self\",\"uri\":\"http://13.232.102.174/openmrs/ws/rest/v1/person/ddf1b15f-b1f5-4e79-995f-4edb055ec53b/address/c77ec195-0064-48f1-8219-6e8f9bca1df6\"},{\"rel\":\"full\",\"uri\":\"http://13.232.102.174/openmrs/ws/rest/v1/person/ddf1b15f-b1f5-4e79-995f-4edb055ec53b/address/c77ec195-0064-48f1-8219-6e8f9bca1df6?v=full\"}],\"address4\":null,\"preferred\":true,\"address9\":null,\"longitude\":null,\"address8\":null,\"address3\":null,\"address2\":null,\"address1\":null,\"display\":null,\"resourceVersion\":\"2.0\",\"stateProvince\":null,\"cityVillage\":\"Thane\",\"address10\":null,\"address11\":null,\"address12\":null,\"address13\":null,\"address14\":null,\"address15\":null,\"startDate\":null},\"auditInfo\":{\"dateChanged\":null,\"creator\":{\"display\":\"superman\",\"links\":[{\"rel\":\"self\",\"uri\":\"http://13.232.102.174/openmrs/ws/rest/v1/user/c1c21e11-3f10-11e4-adec-0800271c1b75\"}],\"uuid\":\"c1c21e11-3f10-11e4-adec-0800271c1b75\"},\"dateCreated\":\"2018-06-12T04:57:14.000+0530\",\"changedBy\":null},\"birthdateEstimated\":false,\"deathdateEstimated\":false,\"names\":[{\"familyName2\":null,\"display\":\"FindBy MobileNumber\",\"givenName\":\"FindBy\",\"familyName\":\"MobileNumber\",\"resourceVersion\":\"1.8\",\"middleName\":null,\"voided\":false,\"links\":[{\"rel\":\"self\",\"uri\":\"http://13.232.102.174/openmrs/ws/rest/v1/person/ddf1b15f-b1f5-4e79-995f-4edb055ec53b/name/5b1689ee-efa5-4d65-b8ff-381a33230c33\"},{\"rel\":\"full\",\"uri\":\"http://13.232.102.174/openmrs/ws/rest/v1/person/ddf1b15f-b1f5-4e79-995f-4edb055ec53b/name/5b1689ee-efa5-4d65-b8ff-381a33230c33?v=full\"}],\"uuid\":\"5b1689ee-efa5-4d65-b8ff-381a33230c33\"}],\"deathDate\":null,\"attributes\":[{\"attributeType\":{\"display\":\"class\",\"links\":[{\"rel\":\"self\",\"uri\":\"http://13.232.102.174/openmrs/ws/rest/v1/personattributetype/c1f455e7-3f10-11e4-adec-0800271c1b75\"}],\"uuid\":\"c1f455e7-3f10-11e4-adec-0800271c1b75\"},\"display\":\"General\",\"resourceVersion\":\"1.8\",\"voided\":false,\"links\":[{\"rel\":\"self\",\"uri\":\"http://13.232.102.174/openmrs/ws/rest/v1/person/ddf1b15f-b1f5-4e79-995f-4edb055ec53b/attribute/2bac9053-34c2-4239-acc7-4de289c15e26\"},{\"rel\":\"full\",\"uri\":\"http://13.232.102.174/openmrs/ws/rest/v1/person/ddf1b15f-b1f5-4e79-995f-4edb055ec53b/attribute/2bac9053-34c2-4239-acc7-4de289c15e26?v=full\"}],\"uuid\":\"2bac9053-34c2-4239-acc7-4de289c15e26\",\"value\":{\"display\":\"General\",\"links\":[{\"rel\":\"self\",\"uri\":\"http://13.232.102.174/openmrs/ws/rest/v1/concept/c1fc20ab-3f10-11e4-adec-0800271c1b75\"}],\"uuid\":\"c1fc20ab-3f10-11e4-adec-0800271c1b75\"}},{\"attributeType\":{\"display\":\"landHolding\",\"links\":[{\"rel\":\"self\",\"uri\":\"http://13.232.102.174/openmrs/ws/rest/v1/personattributetype/3dfdc176-17fd-42b1-b5be-c7e25b78b602\"}],\"uuid\":\"3dfdc176-17fd-42b1-b5be-c7e25b78b602\"},\"display\":\"landHolding = 2\",\"resourceVersion\":\"1.8\",\"voided\":false,\"links\":[{\"rel\":\"self\",\"uri\":\"http://13.232.102.174/openmrs/ws/rest/v1/person/ddf1b15f-b1f5-4e79-995f-4edb055ec53b/attribute/d181958f-7c72-44e9-89ec-19de13c0f64e\"},{\"rel\":\"full\",\"uri\":\"http://13.232.102.174/openmrs/ws/rest/v1/person/ddf1b15f-b1f5-4e79-995f-4edb055ec53b/attribute/d181958f-7c72-44e9-89ec-19de13c0f64e?v=full\"}],\"uuid\":\"d181958f-7c72-44e9-89ec-19de13c0f64e\",\"value\":2},{\"attributeType\":{\"display\":\"Mobile No\",\"links\":[{\"rel\":\"self\",\"uri\":\"http://13.232.102.174/openmrs/ws/rest/v1/personattributetype/e915e482-d943-48b8-a33c-062b602b84d0\"}],\"uuid\":\"e915e482-d943-48b8-a33c-062b602b84d0\"},\"display\":\"Mobile No = 9820449045\",\"resourceVersion\":\"1.8\",\"voided\":false,\"links\":[{\"rel\":\"self\",\"uri\":\"http://13.232.102.174/openmrs/ws/rest/v1/person/ddf1b15f-b1f5-4e79-995f-4edb055ec53b/attribute/195ac283-1361-42a8-b5c3-d40edc957f83\"},{\"rel\":\"full\",\"uri\":\"http://13.232.102.174/openmrs/ws/rest/v1/person/ddf1b15f-b1f5-4e79-995f-4edb055ec53b/attribute/195ac283-1361-42a8-b5c3-d40edc957f83?v=full\"}],\"uuid\":\"195ac283-1361-42a8-b5c3-d40edc957f83\",\"value\":\"9820449045\"}],\"voided\":false,\"birthtime\":null,\"links\":[{\"rel\":\"self\",\"uri\":\"http://13.232.102.174/openmrs/ws/rest/v1/person/ddf1b15f-b1f5-4e79-995f-4edb055ec53b\"}],\"preferredName\":{\"familyName2\":null,\"display\":\"FindBy MobileNumber\",\"givenName\":\"FindBy\",\"familyName\":\"MobileNumber\",\"resourceVersion\":\"1.8\",\"middleName\":null,\"voided\":false,\"links\":[{\"rel\":\"self\",\"uri\":\"http://13.232.102.174/openmrs/ws/rest/v1/person/ddf1b15f-b1f5-4e79-995f-4edb055ec53b/name/5b1689ee-efa5-4d65-b8ff-381a33230c33\"},{\"rel\":\"full\",\"uri\":\"http://13.232.102.174/openmrs/ws/rest/v1/person/ddf1b15f-b1f5-4e79-995f-4edb055ec53b/name/5b1689ee-efa5-4d65-b8ff-381a33230c33?v=full\"}],\"uuid\":\"5b1689ee-efa5-4d65-b8ff-381a33230c33\"},\"causeOfDeath\":null,\"age\":49},\"display\":\"GAN203007 - FindBy MobileNumber\",\"resourceVersion\":\"1.8\",\"voided\":false,\"links\":[{\"rel\":\"self\",\"uri\":\"http://13.232.102.174/openmrs/ws/rest/v1/patient/ddf1b15f-b1f5-4e79-995f-4edb055ec53b\"}],\"uuid\":\"ddf1b15f-b1f5-4e79-995f-4edb055ec53b\"}";

		JSONObject jsondata=new JSONObject(data);
		
		JSONObject jsonresp=new JSONObject();
		
		JSONArray jsonNames=jsondata.names();
		
		
		for(int i=0;i<jsonNames.length();i++) {
			
			String headerName=jsonNames.get(i).toString();
			System.out.println("headers"+headerName);
			
			if(headerName.equalsIgnoreCase("auditInfo")) {
				JSONObject params = jsondata.getJSONObject(headerName);
				System.out.println("data "+params);
			}else if(headerName.equalsIgnoreCase("identifiers")) {
				JSONArray params = jsondata.getJSONArray(headerName);
		
				for(int k=0;k<params.length();k++)
	            {
	                JSONObject jsonObject1 = params.getJSONObject(k);
	               
	                if(k==0)
	                  jsonresp.append("Patient_ID", jsonObject1.optString("identifier"));
	                else if(k==1)
	                	  jsonresp.append("National_ID", jsonObject1.optString("identifier"));
	                
	                System.out.println(jsonObject1.optString("identifier")+"<-->"+k);
	            }
				
			//	jsonresp.put("identifier",String.valueOf(params.getString(headerName)));
			  System.out.println("data "+params);
			}else if(headerName.equalsIgnoreCase("person")) {
				JSONObject params = jsondata.getJSONObject(headerName);
				JSONArray paramsNames=params.names();
				
				System.out.println("ParamNamme"+paramsNames);
				for(int j=0;j<paramsNames.length();j++) {
					  String names=paramsNames.getString(j);     
					  System.out.println("names-->>"+names);
					
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
		
		
		System.out.println(jsonresp.toString());
		 
	}//main method
}
