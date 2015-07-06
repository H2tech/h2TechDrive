package com.example.driver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;


import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Result extends Activity implements OnClickListener {

	TextView tdistance;
	TextView tspeed;
	TextView tcounter;
	String 	 distance;
	String	 speed;
	Button end;
	
	SharedPreferences sp;
	SharedPreferences.Editor prefEditor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		sp = getSharedPreferences("file",MODE_PRIVATE);
		tdistance = (TextView)findViewById(R.id.distance);
		tspeed = (TextView)findViewById(R.id.speed);
		tcounter = (TextView)findViewById(R.id.count);
		end = (Button)findViewById(R.id.save);
		distance = getIntent().getExtras().getCharSequence("distance").toString();
		speed = getIntent().getExtras().getCharSequence("speed").toString();
		tdistance.setText(distance);
		tspeed.setText(speed);
		tcounter.setText(getIntent().getExtras().getCharSequence("counter"));
		end.setOnClickListener(this);
		new data().execute();
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		finish();
	}
	private class data extends AsyncTask<String, String, String> {
		 
	     @Override
	     protected String doInBackground(String... params) {
	    	 
	        HttpClient httpclient = new DefaultHttpClient();
	        HttpPost httppost = new HttpPost("http://h2api.herokuapp.com/login");
	        

	        try {
	            // Add your data
	            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	            nameValuePairs.add(new BasicNameValuePair("email", sp.getString("user", "")));
	            nameValuePairs.add(new BasicNameValuePair("password", sp.getString("pass", "")));
	      
	            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	            // Execute HTTP Post Request
	            HttpResponse response = httpclient.execute(httppost);
	          //  return inputStreamToString(response.getEntity().getContent()).toString();
	            
	        } catch (ClientProtocolException e) {
	            // TODO Auto-generated catch block
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	        }
	        HttpPost httppost1 = new HttpPost("http://h2api.herokuapp.com/trip");
	        

	        try {
	            // Add your data
	            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	            nameValuePairs.add(new BasicNameValuePair("distance", distance));
	            nameValuePairs.add(new BasicNameValuePair("averageSpeed", speed));
	      
	            httppost1.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	            // Execute HTTP Post Request
	            HttpResponse response = httpclient.execute(httppost1);
	            return inputStreamToString(response.getEntity().getContent()).toString();
	            
	        } catch (ClientProtocolException e) {
	            // TODO Auto-generated catch block
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	        }
			return "null";
	     }
	     protected void onPostExecute(String result) {
	    	  
	    	
	    	  if(result.contains("true"))
	    		  Toast.makeText(getApplicationContext(), "Saved", 5000).show();
	    	  else
	    		//  Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
	    		  tcounter.setText(result);
	    	}

	}
	
	private StringBuilder inputStreamToString(InputStream is) {
	    String line = "";
	    StringBuilder total = new StringBuilder();
	    
	    // Wrap a BufferedReader around the InputStream
	    BufferedReader rd = new BufferedReader(new InputStreamReader(is));

	    // Read response until the end
	    try {
			while ((line = rd.readLine()) != null) { 
			    total.append(line); 
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    // Return full string
	    return total;
	}


}
