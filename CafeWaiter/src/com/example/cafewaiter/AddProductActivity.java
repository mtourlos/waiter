package com.example.cafewaiter;

//import java.io.IOException;
//import java.io.InputStream;

//import org.apache.http.HttpEntity;

//import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddProductActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_product);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_product, menu);
		return true;
	}

	public void aProduct (View v){
		final Button button = (Button) findViewById(R.id.add_product_button);
		button.setClickable(false);
		
		EditText table = (EditText) findViewById(R.id.add_product_table);
		int tableNo = Integer.parseInt(table.getText().toString().trim());
		System.out.println("********TableNo:"+tableNo);
		
		EditText product = (EditText) findViewById(R.id.add_product_id);
		int productId = Integer.parseInt(product.getText().toString().trim());
		System.out.println("********Product:"+productId);
		
		//StartTableActivity stb = new StartTableActivity();
		//stb.sTable(v);
		//new AddProduct().execute();
		String response="N/A";
		WebServiceTask wst = new WebServiceTask(WebServiceTask.POST_TASK,this,response);
		wst.execute("http://localhost:8080/CafeServer/service/orderservice/addProduct");
		
    	Context context = getApplicationContext();
    	CharSequence text = "Proceeding...";
    	int duration = Toast.LENGTH_SHORT;
    	Toast toast = Toast.makeText(context, text,duration);
    	toast.show();
		
	}
	
	
}
