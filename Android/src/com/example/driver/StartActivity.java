package com.example.driver;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class StartActivity extends Activity implements OnClickListener, LocationListener
{

	int counter=0;
	Button startButton;
	
	LocationManager locationManager;
	
	float distance=0;
	float speed=0;
	long time;
	long startTime;
	
	TextView showDistance;
	TextView showSpeed;
	
	
	Calendar calender;
	boolean check;
	
	Button temp;
	ArrayList<Location> locationList;
	
	TextView count;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);

		startButton = (Button)findViewById(R.id.start);
		locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		startButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		temp = (Button)v;
		
		if(R.id.start == v.getId())
		{
			check = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
			if(check)
			{
				Intent i = new Intent(this,MapActivity.class);
				startActivity(i);
			}
			else
				Toast.makeText(this,"Turn On your GPS and Internet", Toast.LENGTH_SHORT).show();
		}

	}
		
	

	@Override
	public void onLocationChanged(Location location)
	{
	
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		if(temp.getText().toString().equals("STOP"))
		{
			Toast.makeText(getApplicationContext(), "Process is stopping", Toast.LENGTH_SHORT).show();
			temp.setText("START");
			locationManager.removeUpdates(this);
			distance =0;
			locationList.clear();
			counter=0;
		}
		
	}

	
}
