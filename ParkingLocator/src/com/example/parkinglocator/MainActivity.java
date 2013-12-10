package com.example.parkinglocator;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        onClickListener();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    boolean freeDay = false;
    double costArray[] = new double[7];
    int startH, endH, deltaH;
    String address, street, day, timeFactor;
    
    public void onClickListener()
    {
    	Button button = (Button) findViewById(R.id.submit);
    	button.setOnClickListener(new OnClickListener()
    	{
    		@Override
    		public void onClick(View arg0)
    		{
    			Spinner spinner = (Spinner)findViewById(R.id.startH);
    	    	startH = Integer.parseInt(spinner.getSelectedItem().toString());
    	    	
    	    	spinner = (Spinner)findViewById(R.id.endH);
    	    	endH = Integer.parseInt(spinner.getSelectedItem().toString());
    	    	
    	    	spinner = (Spinner)findViewById(R.id.start);
    	    	if(spinner.getSelectedItem().toString().equals("p.m."))
    	    		startH += 12;
    	    	
    	    	spinner = (Spinner)findViewById(R.id.end);
    	    	if(spinner.getSelectedItem().toString().equals("p.m."))
    	    		endH += 12;
    	    	
    	    	deltaH = endH - startH;
    	    	
    	    	CheckBox check = (CheckBox)findViewById(R.id.freeDay);
    	    	freeDay = check.isChecked();
    	    	
//    	    	spinner = (Spinner)findViewById(R.id.parkDay);
//    	    	day = spinner.getSelectedItem().toString();
//    	    	EditText viewer = (EditText)findViewById(R.id.address);
//    	    	address = viewer.getText().toString();
    	    	
    	    	calculateCosts();
    	    	
    	    	Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
    	    	for(int i = 0; i < 7; i++)
    	    		intent.putExtra("cost" + i + "", costArray[i]);
    	    	startActivity(intent);
    		}
    	});
    }
    
    public void calculateCosts()
    {
    	int meterVar = deltaH;
    	if(startH <= 8)
    		meterVar -= (8 - startH);
    	if(startH >= 18)
    		meterVar -= (startH - 18);
    	if(endH <= 8)
    		meterVar -= (8 - endH);
    	if(endH >= 18)
    		meterVar -= (endH - 18);
    	
    	//All Meters
    	if(meterVar <= 0)
    	{
    		costArray[0] = 0;
    		costArray[1] = 0;
    		costArray[2] = 0;
    	}
    	
    	//Blue Meters
    	if(meterVar <= 3 && meterVar >= 0)
    		costArray[0] = meterVar;
    	else
    		costArray[0] = 999;
    	
    	//Gray Meters
    	if(meterVar <= 1 && meterVar >= 0)
    		costArray[1] = meterVar;
    	else
    		costArray[1] = 999;
    	
    	//Brown Meters
    	if(meterVar <= 10 && meterVar >= 0)
    		costArray[2] = meterVar * 0.4;
    	else
    		costArray[2] = 999;
    	
    	//TownCenter
    	if(deltaH <= 2)
			costArray[3] = 0;
		else if(deltaH == 3)
			costArray[3] = 3;
		else if(deltaH == 4)
			costArray[3] = 5;
		else if(deltaH == 5)
			costArray[3] = 7;
		else if(deltaH == 6)
			costArray[3] = 8;
		else if(deltaH <= 8)
			costArray[3] = 10;
		else if(deltaH <= 10)
			costArray[3] = 12;
		else if(deltaH <= 12)
			costArray[3] = 15;
		else
			costArray[3] = 20;
    	
    	//Municipal Garages
    	if(deltaH <= 2)
			costArray[4] = 0;
		else if(deltaH == 3)
			costArray[4] = 2;
		else if(deltaH == 4)
			costArray[4] = 4;
		else if(deltaH <= 8)
			costArray[4] = deltaH;
		else
			costArray[4] = 999;
    	
    	//Corporate Place
    	if(deltaH <= 4)
			costArray[5] = 2 * deltaH;
		else
			costArray[5] = 999;
    	
    	//Courthouse Place
    	if(deltaH <= 6)
			costArray[6] = 1.5 * deltaH;
		else
			costArray[6] = 999;
    	
    	//Sunday Parking
    	if(freeDay)
    	{
    		costArray[0] = 0;
    		costArray[1] = 0;
    		costArray[2] = 0;
    		costArray[3] = 0;
    		costArray[4] = 0;
    	}
    	
    	//Negative Number
    	if(deltaH <= 0)
    	{
    		for(int i = 0; i < 7; i++)
    		{
    			costArray[i] = 0;
    		}
    	}
    }
}