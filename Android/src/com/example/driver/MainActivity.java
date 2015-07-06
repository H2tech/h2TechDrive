package com.example.driver;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	
	Button register;
	Button login;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		login = (Button)findViewById(R.id.login);
		register = (Button)findViewById(R.id.register);
		login.setOnClickListener(this);
		register.setOnClickListener(this);
	}
	
	@Override
	
	public void onClick(View v)
	{
		// TODO Auto-generated method stub
		Intent i;
		if(R.id.register == v.getId())
		{
			i = new Intent(this,Register.class);
			startActivity(i);
		}
		else
		{
			i = new Intent(this,Login.class);
			startActivity(i);
		}
		
	}
	


}

	     
	     



