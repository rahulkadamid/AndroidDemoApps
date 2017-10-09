package com.example.rahul.assignment2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar (myToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main , menu );
        return true ;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item ) {
        Intent intent;
        switch ( item.getItemId ()) {
            case R.id.action_one :
                return true ;
            case R.id.action_two :
                intent = new Intent( MainActivity.this , Task2Activity.class );
                startActivity( intent );
                return true ;
            case R.id.action_three :
                intent = new Intent( MainActivity.this , Task3Activity.class );
                startActivity( intent );
                return true ;
            case R.id.action_five :
                intent = new Intent( MainActivity.this , Task5Activity.class );
                startActivity( intent );
                return true ;
            default :
                // If we got here , the user 's action was not recognized .
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected( item );
        }
    }
}
