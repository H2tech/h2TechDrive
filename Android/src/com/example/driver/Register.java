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
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends Activity implements OnClickListener {

	EditText fname;
	EditText lname;
	EditText email;
	EditText pass;
	Button register;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		fname = (EditText)findViewById(R.id.fname);
		lname = (EditText)findViewById(R.id.lName);
		email = (EditText)findViewById(R.id.email);
		pass = 	(EditText)findViewById(R.id.password);
		register = (Button)findViewById(R.id.register);
		register.setOnClickListener(this);
	}
	String mail;
	String firstname;
	String lastname;
	String password;
	@Override
	
	public void onClick(View v)
	{
		// TODO Auto-generated method stub
		
		firstname = fname.getText().toString();
		lastname = lname.getText().toString();
		mail = email.getText().toString();
		password = pass.getText().toString();
		if(firstname.toString().isEmpty() || lastname.isEmpty() || mail.toString().isEmpty()|| password.isEmpty())
			Toast.makeText(this, "Please enter the required Fields", Toast.LENGTH_SHORT).show();
		else
			new register().execute();
		
			
		
	}
	private class register extends AsyncTask<String, String, String> {
		 
	     @Override
	     protected String doInBackground(String... params) {
	    	 
	        HttpClient httpclient = new DefaultHttpClient();
	        HttpPost httppost = new HttpPost("http://h2api.herokuapp.com/register");

	        try {
	            // Add your data
	            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(4);
	            nameValuePairs.add(new BasicNameValuePair("email", mail));
	            nameValuePairs.add(new BasicNameValuePair("password", password));
	            nameValuePairs.add(new BasicNameValuePair("firstName",firstname));
	            nameValuePairs.add(new BasicNameValuePair("lastName",lastname));
	            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	            // Execute HTTP Post Request
	            HttpResponse response = httpclient.execute(httppost);
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
	    	  {	  Toast.makeText(getApplicationContext(), "You are offically Registerd", Toast.LENGTH_SHORT).show();
	    		  finish();
	    	  }
	    	  else if(result.contains("already"))
	    		  Toast.makeText(getApplicationContext(), "Email Already Exists", 5000).show();
	    	  else if(result.contains("null"))
	    		  Toast.makeText(getApplicationContext(), "Problem in your Network", Toast.LENGTH_SHORT).show();
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

	     
	     



