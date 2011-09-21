package org.suggs.sandbox.android.countdown;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * Activity to get the countdown time from the user.
 * <p/>
 * User: suggitpe Date: 20/09/11 Time: 07:32
 */

public class CountdownEnterTime extends Activity {

    private static final int MIN = 1;
    private static final int MAX = 60;

    private Context context;
    private int secondsPicked;

    public void onCreate( Bundle aSavedInstanceState ) {
        super.onCreate( aSavedInstanceState );
        setContentView( R.layout.picktime );
        context = this.getApplicationContext();

        setupSpinner();

        createOkButton();
        createCancelButton();

    }

    private void setupSpinner() {
        Spinner spinner = ( Spinner ) findViewById( R.id.spinner );
        ArrayList<Integer> spinnerList = new ArrayList<Integer>();
        for ( int i = MIN; i < MAX; ++i ) {
            spinnerList.add( i );
        }

        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>( context, android.R.layout.simple_spinner_item, spinnerList );
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        spinner.setAdapter( adapter );
        spinner.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected( AdapterView<?> aParentAdapterView, View aView, int position, long id ) {
                secondsPicked = ( Integer ) aParentAdapterView.getItemAtPosition( position );
            }

            @Override
            public void onNothingSelected( AdapterView<?> aAdapterView ) {
                // nadda
            }
        } );

        createOkButton();
        createCancelButton();
    }

    private Button createOkButton() {
        Button okButton = ( Button ) findViewById( R.id.okButton );
        okButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View aView ) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt( CountdownActivity.SECONDS_KEY, secondsPicked );
                intent.putExtras( bundle );
                setResult( RESULT_OK, intent );
                finish();
            }
        } );
        return okButton;
    }

    private Button createCancelButton() {
        Button cancelButton = ( Button ) findViewById( R.id.cancelButton );
        cancelButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View aView ) {
                Intent intent = new Intent();
                setResult( RESULT_CANCELED, intent );
                finish();
            }
        } );

        return cancelButton;
    }


}
