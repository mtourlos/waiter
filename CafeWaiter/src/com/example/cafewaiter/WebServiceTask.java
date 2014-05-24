package com.example.cafewaiter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;
import android.app.Activity;


public class WebServiceTask extends AsyncTask<String,Void,String>{

	public static final int GET_TASK=1;
	public static final int POST_TASK=2;
	//connection parameters
	private static final int CONN_TIMEOUT=3000;
	private static final int SOCKET_TIMEOUT=5000;
	
	private int taskType = GET_TASK;
	private Context mContext = null;
	private String processMessage = "Processing...";
	private ArrayList<Order> params = new ArrayList<Order>;
	
	public WebServiceTask(int taskType, Context mContext, String processMessage){
		this.taskType = taskType;
		this.mContext = mContext;
		this.processMessage = processMessage; 
	}
	
	public void Parameters(String name, String value){
		params.add(Order(tableNo,productNo));
	}
	
	@Override
	protected void onPreExecute(){
		
	}

	@Override
	protected String doInBackground(String... urls) {
		String url = urls[0];
		String result="";
		
		HttpResponse response = doResponse(url);
		
		if (response == null){
			return result;
		}else{
			try{
				result = inputStreamToString(response.getEntity().getContent());
			} catch (IllegalStateException e){
				Log.e(TAG, e.getLocalizedMessage(), e);
			}catch (IOException e){
				Log.e(TAG, e.getLocalizedMessage(), e);
			}
		}
		return result;
	}
	
	@Override 
	protected void onPostExecute(String response){
		handleResponse(response);
	}
	
	//Connection Establishment
	private HttpParams getHttpParams(){
		
		HttpParams http = new BasicHttpParams();
		
		 HttpConnectionParams.setConnectionTimeout(http, CONN_TIMEOUT);
         HttpConnectionParams.setSoTimeout(http, SOCKET_TIMEOUT);
          
         return http;
	}
	
	private HttpResponse doResponse(String url) {
        
        // Use our connection and data timeouts as parameters for our
        // DefaultHttpClient
        HttpClient httpclient = new DefaultHttpClient(getHttpParams());

        HttpResponse response = null;

        try {
            switch (taskType) {

            case POST_TASK:
                HttpPost httppost = new HttpPost(url);
                // Add parameters
                httppost.setEntity(new UrlEncodedFormEntity(params));

                response = httpclient.execute(httppost);
                break;
            case GET_TASK:
                HttpGet httpget = new HttpGet(url);
                response = httpclient.execute(httpget);
                break;
            }
        } catch (Exception e) {

            Log.e(TAG, e.getLocalizedMessage(), e);

        }

        return response;
    }
     
    private String inputStreamToString(InputStream is) {

        String line = "";
        StringBuilder total = new StringBuilder();

        // Wrap a BufferedReader around the InputStream
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));

        try {
            // Read response until the end
            while ((line = rd.readLine()) != null) {
                total.append(line);
            }
        } catch (IOException e) {
            Log.e(TAG, e.getLocalizedMessage(), e);
        }

        // Return full string
        return total.toString();
    }
	
	
}
	

