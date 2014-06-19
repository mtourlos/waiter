package com.example.cafewaiter;

//import java.io.IOException;
//import java.io.InputStream;

//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.protocol.BasicHttpContext;
//import org.apache.http.protocol.HttpContext;

//import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StartTableActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start_table);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.start_table, menu);
		return true;
	}
	
	
	public void sTable (View v){
		final Button button = (Button) findViewById(R.id.start_table_1);
		//button.setClickable(false);
		
		EditText editText = (EditText) findViewById(R.id.table_no);
		int tableNo = Integer.parseInt(editText.getText().toString().trim());
		//System.out.println("********"+tableNo);
		//new StartTable().execute(tableNo);
		if (tableNo>0){
			String response = "N/A";
			WebServiceTask wst = new WebServiceTask(WebServiceTask.GET_TASK,this,response);
			wst.execute("http://192.168.2.11:8080/CafeServer/service/orderservice/"+tableNo);
		}
    	Context context = getApplicationContext();
    	CharSequence text = "Proceeding...";
    	int duration = Toast.LENGTH_SHORT;
    	Toast toast = Toast.makeText(context, text,duration);
    	toast.show();
		
	}
	
	
/*	public class StartTable extends AsyncTask <Integer, Void, String> {
		
		protected String getASCIIContentFromEntity(HttpEntity entity) throws IllegalStateException, IOException {
			InputStream in = entity.getContent();
			StringBuffer out = new StringBuffer();
			int n = 1;
			while (n>0) {
				byte[] b = new byte[4096];
				n =  in.read(b);
				if (n>0) out.append(new String(b, 0, n));
			}
			return out.toString();
		}
		
		@Override
		protected String doInBackground(Integer... tableNo) {
			
			HttpClient httpClient = new DefaultHttpClient();
			HttpContext localContext = new BasicHttpContext();
			int table= tableNo[0];
			System.out.println(table);
			HttpGet httpGet = new HttpGet("http://192.168.1.9:8080/CafeServer/service/orderservice/"+table);
			String text = null;
			try {
				HttpResponse response = httpClient.execute(httpGet, localContext);
				HttpEntity entity = response.getEntity();
				text = getASCIIContentFromEntity(entity);
			} catch (Exception e) {
				System.out.println("***DO IN BACKGROUND***: "+e);
				System.out.println("***table***"+table);
				return e.getLocalizedMessage();
			}
			return text;
		}
		
		protected void onPostExecute(String results) {
			if (results!=null) {
				Context context = getApplicationContext();
				CharSequence text = results;
				int duration = Toast.LENGTH_SHORT;
				Toast toast = Toast.makeText(context, text,duration);
				toast.show();	
			}
			
			Button b = (Button)findViewById(R.id.start_table_1);
			b.setClickable(true);
		}
		
	}*/

}
