package com.example.driver;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends Activity implements LocationListener,OnClickListener {


	Button end;

	float distance=0;
	float speed=0;
	long time;
	long startTime;
	
	Calendar calender;
	boolean check;
	
	int count=0;
	
	ArrayList<Location> locationList;
	LocationManager locationManager;
	
	Button temp;
	
	MapFragment mp;
	GoogleMap gm;
	Marker marker=null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		calender = Calendar.getInstance();
		mp = (MapFragment) getFragmentManager().findFragmentById(R.id.showonMap);
		gm = mp.getMap();
		gm.setMapType(GoogleMap.MAP_TYPE_HYBRID);
		locationList = new ArrayList<Location>();
		end = (Button)findViewById(R.id.end);
		startTime = calender.getTimeInMillis();
		locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0, 0, this);
		Criteria c = new Criteria();
		c.setAccuracy(Criteria.ACCURACY_FINE);
	//	c.setHorizontalAccuracy(Criteria.ACCURACY_HIGH);
		locationManager.requestLocationUpdates(2000, 0, c, this, null);
		end.setOnClickListener(this);
		
		
	}
	@Override
	public void onLocationChanged(Location location)
	{
		Double latitude =location.getLatitude();
		Double longitude =location.getLongitude();
		LatLng coordinate = new LatLng(latitude, longitude);
		CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(coordinate, 16);
		gm.animateCamera(yourLocation);
		// TODO Auto-generated method stub
		if(marker != null)
			marker.remove();
		locationList.add(location);
		marker = gm.addMarker(new MarkerOptions().position(new LatLng(location.getLatitude(),location.getLongitude())));
		Toast.makeText(getApplicationContext(), location.getSpeed()+"", 1000).show();
		count++;
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
			locationManager.removeUpdates(this);
			finish();
		}
	}
	@Override
	public void onClick(View v)
	{
		// TODO Auto-generated method stub
		temp = (Button) v;
		calender = Calendar.getInstance();
		if(temp.getText().toString().equals("STOP"))
		{
			time = (calender.getTimeInMillis()-startTime)/1000; // in Seconds
			for(int i=0; i<locationList.size()-1; i++)
				distance = distance + locationList.get(i).distanceTo(locationList.get(i+1));		// Calculating distance
			locationManager.removeUpdates(this);
			speed = distance/time;
			locationList.clear();
			temp.setText("CLOSE");
		}
		else
		{
			Intent i = new Intent(this,Result.class);
			i.putExtra("distance",distance+"");
			i.putExtra("speed",speed+"");
			i.putExtra("counter", count+"");
			startActivity(i);
			finish();
		}

	}


}
