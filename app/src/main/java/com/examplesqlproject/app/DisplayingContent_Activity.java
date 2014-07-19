package com.examplesqlproject.app;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class DisplayingContent_Activity extends ActionBarActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_display);

        //getting the vegetables which are currently in the database
        DatabaseConnection db = new DatabaseConnection(this);
        if (db.getVegetableCount()>0)
        {
            List<GettingVegetableData> list = db.gettingVegetableDataList();
            ArrayList<String> displayingList = new ArrayList<String>();

            //inserting all vegetables into list which can be displayed
            for(GettingVegetableData currentGroup : list)
            {
                displayingList.add(currentGroup.getVegetableName());
            }

            //setting up the listview itself and then passing the content to be displayed to it.
            ListView displayingContentFromDatabase = (ListView) findViewById(R.id.listView);

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, displayingList);

            displayingContentFromDatabase.setAdapter(adapter);
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        startActivity(new Intent(DisplayingContent_Activity.this, AddingVegetable_Activity.class));
        return true;
    }
}
