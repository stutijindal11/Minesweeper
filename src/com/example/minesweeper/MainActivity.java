package com.example.minesweeper;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class MainActivity extends Activity {
	SQLiteDatabase db;
	ImageButton i,j,k;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		i=(ImageButton)findViewById(R.id.imageButton1);
		
		k=(ImageButton)findViewById(R.id.imageButton2);
		i.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Intent in=new Intent(MainActivity.this,Login.class);
				
				startActivity(in);
			}
			
		});
		
		k.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Intent in=new Intent(MainActivity.this,Signup.class);
				startActivity(in);
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
