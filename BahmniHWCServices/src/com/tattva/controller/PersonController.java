package com.tattva.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import com.tattva.api.patient.GetAllPatientAPI;
import com.tattva.api.patient.GetPatientByUUID;
import com.tattva.api.patient.GetPatientEncounter;
import com.tattva.api.patient.GetPatientObservation;
import com.tattva.api.patient.GetPatientVisit;
import com.tattva.api.patient.PatientBasicInfo;
import com.tattva.api.patient.PatientSaveBateriology;
import com.tattva.api.patient.PatientSaveConsultant;
import com.tattva.api.patient.PatientSaveDiagnosis;
import com.tattva.api.patient.PatientSaveDisposition;
import com.tattva.api.patient.PatientSaveDrugs;
import com.tattva.api.patient.PatientSaveObservation;
import com.tattva.api.patient.PatientSaveOrders;
import com.tattva.api.patient.StartPatientVisit;
import com.tattva.api.person.PersonApi;
import com.tattva.api.person.getPersonDataByUUID;
import com.tattva.api.person.savePeronAddress;
import com.tattva.api.person.savePeronAttribute;
import com.tattva.api.person.savePeronInfo;
import com.tattva.api.person.savePeronName;

@WebServlet("/person")
public class PersonController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String method = req.getParameter("method");
		if (method.equalsIgnoreCase("GETDATA")) {
			getFullInfoByName(req, resp);
		} else if (method.equalsIgnoreCase("getBasicInfo")) {
			getPersonBasicInfo(req, resp);
		} else if (method.equalsIgnoreCase("getAllPatient")) {
			getAllPatient(req, resp);
		} else if (method.equalsIgnoreCase("getAddDrugs")) {
			getAddDrugs(req, resp);
		} else if (method.equalsIgnoreCase("getPatintObs")) {
			getPatintObs(req, resp);
		} else if (method.equalsIgnoreCase("getPatintEncounter")) {
			getPatintEncounter(req, resp);
		} else if (method.equalsIgnoreCase("getPatintVisits")) {
			getPatintVisits(req, resp);
		}else if (method.equalsIgnoreCase("saveDisposition")) {
			saveDisposition(req, resp);
		}else if (method.equalsIgnoreCase("saveDiagnosis")) {
			saveDiagnosis(req, resp);
		} else if (method.equalsIgnoreCase("saveOrders")) {
			saveOrders(req, resp);
		}else if (method.equalsIgnoreCase("startVisits")) {
			startVisits(req, resp);
		}else {
			resp.getWriter().write("wrong url");
		}
		
	}

	private void getFullInfoByName(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		String name = req.getParameter("name");
		JSONObject data = new PersonApi().testIt(name);
		resp.getWriter().write(data.toString());

	}
//method=getBasicInfo&&givenname=govind&middlename=kumar&familyname=pooja&gender=M&age-30&mobileNo=9087654321&state=UttarPradesh&district=Lucknow&tehsil=Nishatganj&gram=new Hyderbad&village=Tattva
	
	
	private void getPersonBasicInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		String age = req.getParameter("age");
		String givenname = req.getParameter("givenname");
		String familyname = req.getParameter("familyname");
		String gender = req.getParameter("gender");
		String middlename = req.getParameter("middlename");
		String district = req.getParameter("district");
		String tehsil = req.getParameter("tehsil");
		String gram = req.getParameter("gram");
		String houseNo = req.getParameter("houseNo");
		String state = req.getParameter("state");
		String village = req.getParameter("village");
		String mobileNo = req.getParameter("mobileNo");

		/*
		 * Date current = new Date(); // If Date is valid, converting String to date.
		 * Date mydob = getValidDate(dob); //String age=""; if (mydob != null &&
		 * mydob.before(current)) {
		 * 
		 * // Get default Time zone Id. ZoneId defaultZoneId = ZoneId.systemDefault();
		 * 
		 * // Convert Date mydob to LocalDate Instant instant1 = mydob.toInstant();
		 * LocalDate localeDateMyDob = instant1.atZone(defaultZoneId).toLocalDate(); //
		 * Convert Date current to LocalDate Instant instant2 = current.toInstant();
		 * LocalDate localeDateCurrent = instant2.atZone(defaultZoneId).toLocalDate();
		 * 
		 * Period period = Period.between(localeDateMyDob, localeDateCurrent);
		 * age=String.valueOf(period.getYears());
		 * 
		 * } else { System.out.println("DOB is invalid."); }
		 */
		String str = "{", colon = ":";

		str += "\"gender\"" + colon + "\"" + gender + "\"" + ",";
		str += "\"names\"" + colon + "[{" + "\"givenName\"" + colon + "\"" + givenname + "\"" + ",\"familyName\""
				+ colon + "\"" + familyname + "\"}],";
		str += "\"age\"" + colon + "\"" + age + "\"" + "}";

		/*
		 * String str = "{", colon = ":"; str += "\"birthdateEstimated\"" + colon + "\""
		 * + " "+ "\"" + ","; str += "\"deathdateEstimated\"" + colon + "\"" + " "+ "\""
		 * + ","; str += "\"addresses\"" + colon + "\"" + address+ "\"" + ","; str +=
		 * "\"names\"" + colon + "[{"
		 * +"\"givenname\""+colon+givenname+"\""+",\"familyname\""+colon+familyname+
		 * "\"}],";
		 * 
		 * str += "\"birthdate\"" + colon + "\"" + dob + "\"" + ","; str += "\"gender\""
		 * + colon + "\"" + gender + "\"" + ","; str += "\"deathDate\"" + colon + "\"" +
		 * " " + "\"" + ","; str += "\"dead\"" + colon + "\"" + "  "+ "\"" + ","; str +=
		 * "\"attributes\"" + colon + "\"" + " " + "\"" + ","; str += "\"birthtime\"" +
		 * colon + "\"" + " " + "\"" + ","; str += "\"causeOfDeath\"" + colon + "\""
		 * +" " + "\"" + ","; str += "\"age\"" + colon + "\"" + age + "\""+"}";
		 */

		System.out.println(str);

		JSONObject datajson = new savePeronInfo().testIt(str);

		System.out.println("persondata" + datajson);

		String respp = str + "\n" + datajson;

		System.out.println("UUID--->>" + datajson.getString("uuid"));

		String uuid = datajson.getString("uuid");

		// Here call APi to personName for inserting person Name in bhamni

		String paramdata = "  {  \"givenName\": \"" + givenname + "\", \"familyName\": \"" + familyname
				+ "\",\"middleName\": \"" + middlename + "\" }";

		new savePeronName().testIt(uuid, paramdata);

		// Here call APi to personName for inserting person Name in bhamni

		paramdata = "{ \"countyDistrict\": \"" + district + "\", \"address3\": \"" + tehsil + "\",\"address2\": \""
				+ gram + "\",\"address1\": \"" + houseNo + "\",\"stateProvince\": \"" + state
				+ "\", \"cityVillage\": \"" + village + "\"  }";
		System.out.println("addres json param" + paramdata);
		new savePeronAddress().testIt(uuid, paramdata);

		// Here call APi to personName for inserting person Name in bhamni

		paramdata = "{  \"attributeType\": \"e915e482-d943-48b8-a33c-062b602b84d0\",\"value\": \"" + mobileNo + "\"}";
		new savePeronAttribute().testIt(uuid, paramdata);

		paramdata = "{  \"attributeType\": \"82325788-3f10-11e4-adec-0800271c1b75\",\"value\": \"" + givenname + "\"}";
		new savePeronAttribute().testIt(uuid, paramdata);

		paramdata = "{  \"attributeType\": \"8233a58a-3f10-11e4-adec-0800271c1b75\",\"value\": \"" + middlename + "\"}";
		new savePeronAttribute().testIt(uuid, paramdata);

		paramdata = "{  \"attributeType\": \"8233a58a-3f10-11e4-adec-0800271c1b75\",\"value\": \"" + familyname + "\"}";
		new savePeronAttribute().testIt(uuid, paramdata);

		paramdata = "{  \"attributeType\": \"c1f7fd17-3f10-11e4-adec-0800271c1b75\",\"value\": \"" + mobileNo + "\"}";
		new savePeronAttribute().testIt(uuid, paramdata);

		// Here get person data

		JSONObject personjson = new getPersonDataByUUID().testIt(uuid);

		// Here person to patient data convertion

		Random rand = new Random();
		int num = rand.nextInt(90000000) + 10000000;
		
		String jsonIdentifier = "{ \"identifiers\": [{ \"identifier\":\"" + num + "\", "
				+ "\"identifierType\":\"81433852-3f10-11e4-adec-0800271c1b75\", "
				+ "\"location\":\"baf7bd38-d225-11e4-9c67-080027b662ec\", " + "\"preferred\": true }], "
				+ "\"person\": " + personjson + "}";

		System.out.println("jsonIdentifier" + jsonIdentifier);

		new PatientBasicInfo().testIt(jsonIdentifier);

		JSONObject jsonpatientRecord = new JSONObject();
		jsonpatientRecord = new StartPatientVisit().visitStared(String.valueOf(num));

		System.out.println(getClass() + "  jsonpatientRecord " + jsonpatientRecord);
		
		resp.getWriter().write(jsonpatientRecord.toString());

	}

	@SuppressWarnings("unused")
	private static Date getValidDate(String date) {

		Date mydate = null;
		if (isValidDateFormat(date)) {
			/**
			 * d -> Day of month M -> Month of year y -> Year
			 */
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			/**
			 * By default setLenient() is true. We should make it false for strict date
			 * validations.
			 * 
			 * If setLenient() is true - It accepts all dates. If setLenient() is false - It
			 * accepts only valid dates.
			 */
			dateFormat.setLenient(false);
			try {
				mydate = dateFormat.parse(date);
			} catch (ParseException e) {
				mydate = null;
			}
		}
		return mydate;
	}

	private static boolean isValidDateFormat(String date) {

		/**
		 * Regular Expression that matches String with format dd/MM/yyyy. dd -> 01-31 MM
		 * -> 01-12 yyyy -> 4 digit number
		 */
		String pattern = "(0?[1-9]|[12][0-9]|3[01])\\/(0?[1-9]|1[0-2])\\/([0-9]{4})";
		boolean result = false;
		if (date.matches(pattern)) {
			result = true;
		}
		return result;
	}

	public void getAllPatient(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		JSONArray jsonArrayResp=new JSONArray();
		JSONObject jsonpatientRecord = null;
		JSONArray jsondata = new GetAllPatientAPI().getallPatientDetails();

		// resp.getWriter().write(jsondata.toString());

		for (int i = 0; i < jsondata.length(); i++) {
			JSONObject objects = jsondata.getJSONObject(i);
			System.out.println("Object " + objects);
			String uuid = objects.getString("uuid");
			System.out.println(getClass() + "  UUID-->>" + uuid);
			jsonpatientRecord = new GetPatientByUUID().getPatientByUUID(uuid);
			jsonArrayResp.put(jsonpatientRecord);
		}

		resp.getWriter().write(jsonArrayResp.toString());

	}// getAllPatient close

	public void getAddDrugs(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		JSONObject jsonpatientRecord = new JSONObject();

		jsonpatientRecord = new PatientSaveDrugs().addDrugs("Paracetamol", "2", "w", "2", "3", "day", "2ml",
				"GAN203007");

		System.out.println(getClass() + "  jsonpatientRecord " + jsonpatientRecord);

		resp.getWriter().write(jsonpatientRecord.toString());

	}// getAllPatient close

	public void getPatintObs(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		JSONObject jsonpatientRecord = new JSONObject();

		jsonpatientRecord = new GetPatientObservation().getPatientObs("GAN203016");

		System.out.println(getClass() + "  jsonpatientRecord " + jsonpatientRecord);

		resp.getWriter().write(jsonpatientRecord.toString());

	}

	public void getPatintEncounter(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		JSONObject jsonpatientRecord = new JSONObject();

		jsonpatientRecord = new GetPatientEncounter().getPatientEcounter("GAN203016");

		System.out.println(getClass() + "  jsonpatientRecord " + jsonpatientRecord);

		resp.getWriter().write(jsonpatientRecord.toString());

	}

	public void getPatintVisits(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		JSONObject jsonpatientRecord = new JSONObject();

		jsonpatientRecord = new GetPatientVisit().getPatientVisit("GAN203016");

		System.out.println(getClass() + "  jsonpatientRecord " + jsonpatientRecord);

		resp.getWriter().write(jsonpatientRecord.toString());

	}

	public void saveDiagnosis(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		JSONObject jsonpatientRecord = new JSONObject();

		jsonpatientRecord = new PatientSaveDiagnosis().addDiagnosis("Bell's palsy", "primary", "confirmed",	"GAN203007");

		System.out.println(getClass() + "  jsonpatientRecord " + jsonpatientRecord);

		resp.getWriter().write(jsonpatientRecord.toString());

	}// saveDiagnosis close

	public void saveDisposition(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		JSONObject jsonpatientRecord = new JSONObject();

		jsonpatientRecord = new PatientSaveDisposition().addDisposition("","GAN203008");
				

		System.out.println(getClass() + "Disposition Record---->> " + jsonpatientRecord);

		resp.getWriter().write(jsonpatientRecord.toString());

	}// saveDiagnosis close

	public void saveOrders(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		JSONObject jsonpatientRecord = new JSONObject();

		jsonpatientRecord = new PatientSaveOrders().addOrders("Differential Count", "GAN203016");
		System.out.println(getClass() + "  jsonpatientRecord " + jsonpatientRecord);

		resp.getWriter().write(jsonpatientRecord.toString());

	}// saveOrders close

	public void saveObservation(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		JSONObject jsonpatientRecord = new JSONObject();

		jsonpatientRecord = new PatientSaveObservation().addObservation("Paracetamol", "2", "w", "2", "3", "day", "2ml","GAN203007");

		System.out.println(getClass() + "  jsonpatientRecord " + jsonpatientRecord);

		resp.getWriter().write(jsonpatientRecord.toString());

	}// saveObservation close
	
	
	
	public void saveBateriology(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		JSONObject jsonpatientRecord = new JSONObject();

		jsonpatientRecord = new PatientSaveBateriology().addBateriology("Paracetamol", "2", "w", "2", "3", "day", "2ml","GAN203007");
				

		System.out.println(getClass() + "  jsonpatientRecord " + jsonpatientRecord);

		resp.getWriter().write(jsonpatientRecord.toString());

	}// saveDiagnosis close
	
	
	
	public void saveConsultant(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		JSONObject jsonpatientRecord = new JSONObject();

		jsonpatientRecord = new PatientSaveConsultant().addConsultant("Paracetamol", "2", "w", "2", "3", "day", "2ml","GAN203007");

		System.out.println(getClass() + "  jsonpatientRecord " + jsonpatientRecord);

		resp.getWriter().write(jsonpatientRecord.toString());

	}// saveDiagnosis close
	
	
	public void startVisits(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		JSONObject jsonpatientRecord = new JSONObject();

		jsonpatientRecord = new StartPatientVisit().visitStared("GAN203014");

		System.out.println(getClass() + "  jsonpatientRecord " + jsonpatientRecord);

		resp.getWriter().write(jsonpatientRecord.toString());

	}// startVisits close
	
	

}
