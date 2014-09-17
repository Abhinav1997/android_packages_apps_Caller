package com.example.caller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Search extends Activity{
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.search);
	 }
	
        
       public void find(View view)
       {
    	   EditText num=(EditText) findViewById(R.id.number);
    	  String number= num.getText().toString();
    	   Intent i=new Intent(this,Detail.class);
    	   i.putExtra("number",number);
    	   startActivity(i);
    	   
       }
}
