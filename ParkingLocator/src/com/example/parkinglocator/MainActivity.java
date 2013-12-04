package com.example.parkinglocator;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
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
    
    String address, street, day, timeFactor;
    int startH, endH, deltaH;
    double costArray[][] = new double[6][24];
    double theArray[] = new double[7];
    
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
    	    	timeFactor = spinner.getSelectedItem().toString();
    	    	if(timeFactor == "p.m.")
    	    		startH += 12;
    	    	
    	    	spinner = (Spinner)findViewById(R.id.end);
    	    	timeFactor = spinner.getSelectedItem().toString();
    	    	if(timeFactor == "p.m.")
    	    		endH += 12;
    	    	deltaH = endH - startH;
    	    	
    	    	spinner = (Spinner)findViewById(R.id.parkDay);
    	    	day = spinner.getSelectedItem().toString();
//    	    	EditText viewer = (EditText)findViewById(R.id.address);
//    	    	address = viewer.getText().toString();
    	    	
    	    	calculateCosts();
    	    	
    	    	Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
    	    	for(int i = 0; i < 7; i++)
    	    		intent.putExtra("cost" + i + "", theArray[i]);
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
    		theArray[0] = 0;
    		theArray[1] = 0;
    		theArray[2] = 0;
    	}
    	
    	//Blue Meters
    	if(meterVar <= 3 && meterVar >= 0)
    		theArray[0] = meterVar;
    	else
    		theArray[0] = 999;
    	
    	//Gray Meters
    	if(meterVar <= 1 && meterVar >= 0)
    		theArray[1] = meterVar;
    	else
    		theArray[1] = 999;
    	
    	//Brown Meters
    	if(meterVar <= 10 && meterVar >= 0)
    		theArray[2] = meterVar * 0.4;
    	else
    		theArray[2] = 999;
    	
    	//TownCenter
    	if(deltaH <= 2)
			theArray[3] = 0;
		else if(deltaH == 3)
			theArray[3] = 3;
		else if(deltaH == 4)
			theArray[3] = 5;
		else if(deltaH == 5)
			theArray[3] = 7;
		else if(deltaH == 6)
			theArray[3] = 8;
		else if(deltaH <= 8)
			theArray[3] = 10;
		else if(deltaH <= 10)
			theArray[3] = 12;
		else if(deltaH <= 12)
			theArray[3] = 15;
		else
			theArray[3] = 20;
    	
    	//Municipal Garages
    	if(deltaH <= 2)
			theArray[4] = 0;
		else if(deltaH == 3)
			theArray[4] = 2;
		else if(deltaH == 4)
			theArray[4] = 4;
		else if(deltaH <= 8)
			theArray[4] = deltaH;
		else
			theArray[4] = 999;
    	
    	//Corporate Place
    	if(deltaH <= 4)
			theArray[5] = 2 * deltaH;
		else
			theArray[5] = 999;
    	
    	//Courthouse Place
    	if(deltaH <= 6)
			theArray[6] = 1.5 * deltaH;
		else
			theArray[6] = 999;
    	
    	//Sunday Parking
    	if(day == "Sunday")
    	{
    		theArray[0] = 0;
    		theArray[1] = 0;
    		theArray[2] = 0;
    		theArray[3] = 0;
    		theArray[4] = 0;
    	}
    }
}