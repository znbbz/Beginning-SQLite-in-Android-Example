package com.examplesqlproject.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by apple on 19/07/2014.
 */
public class AddingVegetable_Activity extends ActionBarActivity
{
    EditText gettingUsersInput;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addingvegetable_activity);

        gettingUsersInput = (EditText) findViewById(R.id.userEnteredText);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.done_button, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if (id == R.id.done)
        {
            //getting the text which the user has entered. Before anything else is done with it.
            // I am going to check to make sure it is not empty.
            String input = String.valueOf(gettingUsersInput.getText());

            if(input.equals(""))
            {
                Toast.makeText(this, "You haven't wrote anything in the textbox.", Toast.LENGTH_LONG).show();
            }

            else
            {
                //adding the name into the database.
                DatabaseConnection db = new DatabaseConnection(this);
                db.addVegetable(new GettingVegetableData(input));

                //now returning to the main page
                startActivity(new Intent(this, DisplayingContent_Activity.class));
            }

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
