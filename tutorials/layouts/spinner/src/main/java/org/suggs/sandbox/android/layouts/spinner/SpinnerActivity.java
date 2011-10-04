package org.suggs.sandbox.android.layouts.spinner;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class SpinnerActivity extends Activity {

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.main );

        Spinner spinner = ( Spinner ) findViewById( R.id.spinner );
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource( this, R.array.planets, android.R.layout.simple_spinner_item );
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        spinner.setAdapter( adapter );
        spinner.setOnItemSelectedListener( new SpinnerOnItemSelectedListener() );

    }


    private class SpinnerOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected( AdapterView<?> aParent, View aView, int aPosition, long aId ) {
            Toast.makeText( aParent.getContext(), "The planet is " + aParent.getItemAtPosition( aPosition ).toString(), Toast.LENGTH_LONG ).show();
        }

        @Override
        public void onNothingSelected( AdapterView<?> aAdapterView ) {
            // nadda
        }
    }
}
