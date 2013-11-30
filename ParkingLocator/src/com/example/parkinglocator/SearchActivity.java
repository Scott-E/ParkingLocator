package com.example.parkinglocator;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.os.Build;

public class SearchActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		// Show the Up button in the action bar.
//		double costArray[] = new double[6];
//		costArray = getIntent().getDoubleArrayExtra("key");
		double costArray[] = getIntent().getDoubleArrayExtra("key");
		int sortedCosts[] = new int[7];
		
		sortedCosts = sortResults(costArray, sortedCosts);
		buildResults(R.id.resultsField, costArray, sortedCosts);
		setupActionBar();
	}
	
	public int[] sortResults(double costArray[], int indexArray[])
	{
		int lesserIndex = 0, greaterIndex = 0;
		for (int i = 0; i < 6; i++)
		{
			lesserIndex = i;
			for(int j = i + 1; j < 7; j++)
			{
				greaterIndex = j;
				if(costArray[j] < costArray[i])
				{
					lesserIndex = j;
					greaterIndex = i;
				}
			}
			indexArray[i] = lesserIndex;
			indexArray[i + 1] = greaterIndex;
		}
		return indexArray;
	}
	
	public void buildResults(int fieldId, double costs[], int costsIndex[])
	{
		LinearLayout field = (LinearLayout)this.findViewById(fieldId);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, 
                LinearLayout.LayoutParams.WRAP_CONTENT);
		
		for(int i = 0; i < 6; i++)
		{
			TextView result = new TextView(this.getApplicationContext());
			switch(costsIndex[i])
			{
			//Blue Meters
			case 0:
				result.setText("$" + costs[costsIndex[i]] + " - Blue Meters");
				break;
			//Gray Meters
			case 1:
				result.setText("$" + costs[costsIndex[i]] + " - Gray Meters");
				break;
			//Brown Meters
			case 2:
				result.setText("$" + costs[costsIndex[i]] + " - Brown Meters");
				break;
			//Town Center
			case 3:
				result.setText("$" + costs[costsIndex[i]] + " - Town Center located at Cherry Street, Bank Street, and Pine Street");
				break;
			//Municipal Garages
			case 4:
				result.setText("$" + costs[costsIndex[i]] + " - Municipal Garages located at Marketplace, Lakeview, Macy's, College Street, and Hilton");
				break;
			//Corporate Place
			case 5:
				result.setText("$" + costs[costsIndex[i]] + " - Corporate Place located at Saint Paul Street");
				break;
			//Courthouse Place
			case 6:
				result.setText("$" + costs[costsIndex[i]] + " - Courthouse Place located at South Winooski Avenue");
				break;
			}
			result.setId(costsIndex[i]);
			field.addView(result, params);
		}
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
