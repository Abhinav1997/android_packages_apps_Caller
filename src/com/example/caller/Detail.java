package com.example.caller;




import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.telephony.SmsManager;
import android.util.Log;


public class Detail extends Activity{
	/* TextView nm=(TextView) findViewById(R.id.name);
	 TextView num1=(TextView) findViewById(R.id.num);*/
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display);
      
    	Log.w("comment","Calling contact act");
    	 try {
    		
    		 Bundle extras = getIntent().getExtras();
    			System.out.println("Num is:"+extras.get("number"));
    			String number=extras.get("number").toString();
    			Log.w("comment",number);
            URL url = new URL("http://www.chhattisgarhpurkhauti.com/android/app/retrieve1.php?number="+number);
            HttpURLConnection urlConnection = 
                (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
                        // gets the server json data
            BufferedReader bufferedReader = 
                new BufferedReader(new InputStreamReader(
                        urlConnection.getInputStream()));
            
            Log.w("T","1");
        
        
        String next;
        while ((next = bufferedReader.readLine()) != null){
            JSONArray ja = new JSONArray(next);
            Log.w("comment","json array");

            for (int i = 0; i < ja.length(); i++) {
                JSONObject jo = (JSONObject) ja.get(i);
                
               TextView nm=(TextView) findViewById(R.id.name);
               nm.setText(jo.getString("Name"));
               Log.w("T",jo.getString("Name"));

                TextView num1=(TextView) findViewById(R.id.num);
                num1.setText(jo.getString("Num"));
                Log.w("T",jo.getString("Num"));

               Button call=(Button) findViewById(R.id.call);
                Button msg=(Button) findViewById(R.id.msg);
                call.setOnClickListener(new OnClickListener()
                {

					@Override
					public void onClick(View view) {
						// TODO Auto-generated method stub
						try {
							TextView num1=(TextView)findViewById(R.id.num);
		                    Intent callIntent = new Intent(Intent.ACTION_CALL);
		                    callIntent.setData(Uri.parse("tel:"+num1.getText().toString()));
		                    startActivity(callIntent);
		                } catch (ActivityNotFoundException activityException) {
		                    Log.e("Calling a Phone Number", "Call failed", activityException);
		                }
		        }
		    });
                Log.w("comment","click");
                msg.setOnClickListener(new OnClickListener()
                {

					@Override
					
					public void onClick(View view1) {
						Log.w("comment","clicked");
						// TODO Auto-generated method stub
						try{
							TextView num1=(TextView)findViewById(R.id.num);
							 SmsManager smsManager = SmsManager.getDefault();
							  String smsNumber = num1.getText().toString();
							  //String smsText = num1.getText().toString();
							  smsManager.sendTextMessage(smsNumber, null, "Hello", null, null);
						
						
						}
						catch (ActivityNotFoundException activityException) {
		                    Log.e("Sending message", "failed msg", activityException);
		                }
		        }
		    });
				
						
					}
                	
                }
                 
               
}
        
	  catch (MalformedURLException e) {
	        // TODO Auto-generated catch block
	       e.printStackTrace();
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    } catch (JSONException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }}
    	 
    	/* public void showActivity(View clickedButton){
    			switch(clickedButton.getId())
    			{
    			case R.id.call:
    				 try {
    	                    Intent callIntent = new Intent(Intent.ACTION_CALL);
    	                    callIntent.setData(Uri.parse("tel:"+num1.getText().toString()));
    	                    startActivity(callIntent);
    	                } catch (ActivityNotFoundException activityException) {
    	                    Log.e("Calling a Phone Number", "Call failed", activityException);
    	                }
    				
    				
    			case R.id.msg:
    				
	}
    	 }*/
}
