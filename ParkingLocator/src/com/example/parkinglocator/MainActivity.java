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
    
//    private View.OnClickListener onClick = new View.OnClickListener()
//    {
//    	@Override
//    	public void onClick(View v)
//    	{
//    		
//    	}
//    };
    
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
}
