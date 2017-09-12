package com.example.minesweeper;

import java.util.ArrayList;

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

public class Login extends Activity {
	SQLiteDatabase db;
	ImageButton i,b;
	EditText e1,e2,e3;
	String name,pass;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		e1=(EditText)findViewById(R.id.editText1);
		e2=(EditText)findViewById(R.id.editText2);
		i=(ImageButton)findViewById(R.id.imageButton1);
		b=(ImageButton)findViewById(R.id.imageButton2);
		b.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Intent in=new Intent(Login.this,MainActivity.class);
				startActivity(in);
			}
			
		});
		i.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				db=openOrCreateDatabase("PlayerDB", Context.MODE_PRIVATE, null);
				name=e1.getText().toString();			
				pass=e2.getText().toString();
				if(name.matches("") || pass.matches("")){
					Toast.makeText(getApplicationContext(),"PLEASE FILL IN THE DETAILS FIRST",Toast.LENGTH_LONG).show();
				}
				   else{
					   
					   Cursor c=db.rawQuery("SELECT * FROM player5 WHERE name='"+name+"' AND password='"+pass+"'",null);
					   if(c.getCount()>0)
						   
					   {   ArrayList<String> s=new ArrayList<String>();
						   Intent in=new Intent(Login.this,Game.class);
						   
						  s.add(name);
						  s.add(pass);
						   in.putStringArrayListExtra("s",s);
							startActivity(in);
					      
					   }
					   else{
					
					Toast.makeText(getApplicationContext(),"PLEASE SIGNUP FIRST",Toast.LENGTH_LONG).show();
					 Intent in=new Intent(Login.this,Signup.class);
						startActivity(in);
					
				
					   }
				   }
			}
			
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
