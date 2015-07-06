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
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity implements OnClickListener {

	EditText email;
	EditText pass;
	Button login;
	String mail;
	String password;
	SharedPreferences sp;
	SharedPreferences.Editor prefEditor;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		sp = getSharedPreferences("file",MODE_PRIVATE);
		prefEditor = sp.edit();
		login = (Button)findViewById(R.id.login);
		email = (EditText)findViewById(R.id.lemail);
		pass = (EditText)findViewById(R.id.lpassword);
		login.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		mail = email.getText().toString();
		password = pass.getText().toString();
		if(mail.toString().isEmpty()|| password.isEmpty())
			Toast.makeText(this, "Please enter the required Fields", Toast.LENGTH_SHORT).show();
		else
		{
			new login().execute();
		}
	}
	
	private class login extends AsyncTask<String, String, String> {
		 
	     @Override
	     protected String doInBackground(String... params) {
	    	 
	        HttpClient httpclient = new DefaultHttpClient();
	        HttpPost httppost = new HttpPost("http://h2api.herokuapp.com/login");

	        try {
	            // Add your data
	            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	            nameValuePairs.add(new BasicNameValuePair("email", mail));
	            nameValuePairs.add(new BasicNameValuePair("password", password));
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
	    	  {	  prefEditor.putString("user", mail);
	    	  	  prefEditor.putString("pass", password);
	    	  	  prefEditor.commit();
	    		  Intent i = new Intent(getApplicationContext(),StartActivity.class);
	    		  startActivity(i);
	    	  }
	    	  else
	    		  Toast.makeText(getApplicationContext(), "Wrong username/password", 5000).show();
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
