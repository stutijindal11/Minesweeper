package com.example.minesweeper;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.telephony.SmsManager;


public class Signup extends Activity {

	SQLiteDatabase db;
	EditText e1,e2,e3;
	ImageButton i,b;
	
	int checking;
	String time,name,pass;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);
		
		e1=(EditText)findViewById(R.id.editText1);
		e2=(EditText)findViewById(R.id.editText2);
		e3=(EditText)findViewById(R.id.editText3);
		i=(ImageButton)findViewById(R.id.imageButton1);
		b=(ImageButton)findViewById(R.id.imageButton2);
		db=openOrCreateDatabase("PlayerDB", Context.MODE_PRIVATE, null);
	    db.execSQL("CREATE TABLE IF NOT EXISTS player5(name VARCHAR,password VARCHAR);");
		
		i.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				name=e1.getText().toString();
				
				pass=e2.getText().toString();
				String phone=e3.getText().toString();
				if(name.matches("") || pass.matches("") || phone.matches("") ){
					Toast.makeText(getApplicationContext(),"PLEASE FILL IN THE DETAILS FIRST",Toast.LENGTH_LONG).show();
				   }
				else{
					
					 checking =isValidPhone();
					if(checking==1){
					Cursor c=db.rawQuery("SELECT * FROM player5 WHERE name='"+e1.getText().toString()+"'",null);
					   if(c.getCount()>0)
					   {
						   Toast.makeText(getApplicationContext(),"USERNAME ALREADY EXISTS",Toast.LENGTH_LONG).show();
					      
					   }
					   else{
					db.execSQL("INSERT INTO player5 VALUES('"+name+"','"+pass+"');");
					Toast.makeText(getApplicationContext(),"REGISTERED",Toast.LENGTH_LONG).show();
					
					
					
				sendSMS(name,pass);
				Intent in=new Intent(Signup.this,MainActivity.class);
				startActivity(in);
				//invokeSMSApp(name,pass);
					   }
				}
				else{
					 Toast.makeText(getApplicationContext(),"ENTER VALID NUMBER",Toast.LENGTH_LONG).show();
					
					
				}
			}
				
			}
			
		});
		b.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				/*Cursor c1=db.rawQuery("SELECT * FROM player5", null);
				   if(c1.getCount()==0)
				   {
					   Toast.makeText(getApplicationContext(),"NO DATA EXISTS",Toast.LENGTH_LONG).show();
				       return;
				   }
				   StringBuffer buffer=new StringBuffer();
				   while(c1.moveToNext())
				   {
				       
				       buffer.append("\n"+"Name: "+c1.getString(0)+"\n");
				       buffer.append("Password: "+c1.getString(1)+"\n");
				       buffer.append("Time: "+c1.getString(2)+"\n\n");
				       
				   }
				  Toast.makeText(getApplicationContext(),"DETAILS ARE"+ buffer.toString(),Toast.LENGTH_LONG).show();
				*/
				  
				Intent in=new Intent(Signup.this,MainActivity.class);
				startActivity(in);
			}
			
		});
	}
	public void sendSMS(String name,String pass) {
	    String phoneNumber = e3.getText().toString();
	    String message ="Welcome to Minesweeper Decoded!!"+"\n"+"Your UserName is: "+name+"\n"+"and Password is: "+pass ;

	    SmsManager smsManager = SmsManager.getDefault();
	    smsManager.sendTextMessage(phoneNumber, null, message, null, null);
	    Toast.makeText(getApplicationContext(),"MESSAGE SENT",Toast.LENGTH_LONG).show();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.signup, menu);
		return true;
	}
	public void invokeSMSApp(String name,String pass) {
        Intent smsIntent = new Intent(Intent.ACTION_VIEW);

        smsIntent.putExtra("sms_body", "Your UserName is: "+name+"\n"+"Your Password is: "+pass); 
        smsIntent.putExtra("address", e3.getText().toString());
        smsIntent.setType("vnd.android-dir/mms-sms");

        startActivity(smsIntent);
        Toast.makeText(getApplicationContext(),"MESSAGE SENT",Toast.LENGTH_LONG).show();
}
	
	public int isValidPhone() 
	{
		int check;
		if(e3.length() < 6 || e3.length() > 13)
	    {
	        check = 0;
	       
	    }
	    else
	    {
	        check = 1;
	    }
		return check;
	}
}
