package com.example.parkinglocator;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    String address, street, day;
    int startH, endH, deltaH;
    double costArray[][] = new double[7][24];
    
    public void onClick()
    {
    	Spinner spinner = (Spinner)findViewById(R.id.startH);
    	startH = Integer.parseInt(spinner.getSelectedItem().toString());
    	spinner = (Spinner)findViewById(R.id.endH);
    	endH = Integer.parseInt(spinner.getSelectedItem().toString());
    	spinner = (Spinner)findViewById(R.id.start);
    	String timeVar = spinner.getSelectedItem().toString();
    	if(timeVar != "a.m.")
    		startH += 12;
    	spinner = (Spinner)findViewById(R.id.end);
    	timeVar = spinner.getSelectedItem().toString();
    	if(timeVar != "a.m.")
    		endH += 12;
    	spinner = (Spinner)findViewById(R.id.parkDay);
    	day = spinner.getSelectedItem().toString();
    	EditText viewer = (EditText)findViewById(R.id.address);
    	address = viewer.getText().toString();
    }
    
    public void populateArray()
    {
    	int hourCounter = 1;
    	//Blue Meters
    	for (int i = 0; i < 24; i++)
    	{
    		if(hourCounter <= 3)
    		{
    			costArray[0][i] = hourCounter;
    			hourCounter++;
    		}
    		else
    			costArray[0][i] = 999;
    	}
    	hourCounter = 1;
    	
    	//Grey Meters
    	for (int i = 0; i < 24; i++)
    	{
    		if(hourCounter <= 1)
    		{
    			costArray[1][i] = hourCounter;
    			hourCounter++;
    		}
    		else
    			costArray[1][i] = 999;
    	}
    	hourCounter =  1;
    	
    	//Brown Meters
    	for (int i = 0; i < 24; i++)
    	{
    		if(hourCounter <= 10)
    		{
    			costArray[2][i] = hourCounter * 0.1;
    			hourCounter++;
    		}
    		else
    			costArray[2][i] = 999;
    	}
    	hourCounter = 1;
    	
    	//Yellow Meters
//    	for (int i = 0; i < 24; i++)
//    	{
//    		
//    	}
//    	hourCounter = 1;
    	
    	//Municipal Garages
    	for (int i = 0; i < 24; i++)
    	{
    		if(hourCounter <= 2)
    			costArray[3][i] = 0;
    		else if(hourCounter == 3)
    			costArray[3][i] = 3;
    		else if(hourCounter == 4)
    			costArray[3][i] = 5;
    		else if(hourCounter == 5)
    			costArray[3][i] = 7;
    		else if(hourCounter == 6)
    			costArray[3][i] = 8;
    		else if(hourCounter <= 8)
    			costArray[3][i] = 10;
    		else if(hourCounter <= 10)
    			costArray[3][i] = 12;
    		else if(hourCounter <= 12)
    			costArray[3][i] = 15;
    		else
    			costArray[3][i] = 20;
    		hourCounter++;
    	}
    	hourCounter = 1;
    	
    	//Town Center
    	
    	
    	//Corporate Place
    	
    	
    	//Courthouse Place
    	
    }
}
