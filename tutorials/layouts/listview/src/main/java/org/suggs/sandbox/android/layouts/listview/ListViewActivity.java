package org.suggs.sandbox.android.layouts.listview;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class ListViewActivity extends ListActivity {

    private static final String[] COUNTRIES = new String[]{ "Afghanistan", "Albania", "Algeria", "Andorra",
                                                            "Angola", "Anguilla", "Antartica" };

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setListAdapter( new ArrayAdapter<String>( this, R.layout.list_item, COUNTRIES ) );

        ListView listView = getListView();
        listView.setTextFilterEnabled( true );

        listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick( AdapterView<?> aAdapterView, View aView, int i, long l ) {
                Toast.makeText( getApplicationContext(), ( ( TextView ) aView ).getText(), Toast.LENGTH_SHORT ).show();
            }
        } );
    }

}


