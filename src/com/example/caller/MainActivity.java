package com.example.caller;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.os.Handler;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Handler handler=new Handler();
	String n;
	 String name;
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        String m;
		   	m="";
		   	Log.w("comment","Calling contact act");
			 try {
		        URL url = new URL("http://www.chhattisgarhpurkhauti.com/android/app/register1.php?number="+m);
		        HttpURLConnection urlConnection = 
		            (HttpURLConnection) url.openConnection();
		        urlConnection.setRequestMethod("GET");
		        urlConnection.connect();
		                    // gets the server json data
		        BufferedReader bufferedReader = 
		            new BufferedReader(new InputStreamReader(
		                    urlConnection.getInputStream()));
		   Cursor cursor=getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

		      while (cursor.moveToNext()) { 
		      String contactId = cursor.getString(cursor.getColumnIndex( ContactsContract.Contacts._ID)); 
		      String hasPhone = cursor.getString(cursor.getColumnIndex( ContactsContract.Contacts.HAS_PHONE_NUMBER)); 
		      if (!(hasPhone.equals("0"))) { 
		                   // You know have the number so now query it like this
		   Cursor phones = getContentResolver().query( ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null, 
		     ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ contactId, 
		         null, null); 
		       while (phones.moveToNext()) { n="";
		        n=phones.getString( 
		          phones.getColumnIndex( 
		            ContactsContract.CommonDataKinds.Phone.NUMBER));
		        	//n=n.substring(n.length()-10, n.length());
		        n=n.substring(0, 3)+n.substring(4, 7)+n.substring(8, 12);
		      
		        m=m+n+",";
		       }
		    	int l;
		    	l=m.length();
		    	m=m.substring(0, l-1);
		       
		       phones.close(); 
		      } 
		      }
		    }catch(Exception e){}
			 
		   
		       
      final Intent t=new Intent(this,Search.class);
		    	handler.postDelayed(new Runnable()
		    	{
		    		@Override
		    		public void run()
		    		     {finish();
		    			startActivity(t);
		    	}
		    	          },1500);
				}	 


}

