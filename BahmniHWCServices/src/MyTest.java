import java.io.IOException;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class MyTest {

	public static void main(String[] args) throws IOException {
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
		  .url("https://13.232.102.174/openmrs/ws/rest/v1/person?q=govind&v=full&limit=20&startIndex=0")
		  .get()
		  .addHeader("authorization", "Basic c3VwZXJtYW46QWRtaW4xMjM=")
		  .addHeader("cache-control", "no-cache")
		  .addHeader("postman-token", "8884d855-36b2-5e28-31a9-c406ac08f746")
		  .build();

		Response response = client.newCall(request).execute();

		System.out.println("response"+response);
		
	}

}
