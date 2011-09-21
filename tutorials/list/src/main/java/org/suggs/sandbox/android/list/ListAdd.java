package org.suggs.sandbox.android.list;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Activity that allows the addition of items to the list.
 * <p/>
 * User: suggitpe
 * Date: 28/08/11
 * Time: 18:51
 */

public final class ListAdd extends Activity {

    private EditText label;

    @Override
    public void onCreate( Bundle savedInstanceState ) {

        super.onCreate( savedInstanceState );
        setContentView( R.layout.new_item );

        label = ( EditText ) findViewById( R.id.newItemLabel );
        Button okButton = ( Button ) findViewById( R.id.okButton );
        Button cancelButton = ( Button ) findViewById( R.id.cancelButton );
        okButton.setOnClickListener( new OkButtonClickListener() );
        cancelButton.setOnClickListener( new CancelButtonClickListener() );

    }

    private class OkButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick( View aView ) {
            Bundle bundle = new Bundle();
            bundle.putString( ListDbAdapter.DB_ITEM, label.getText().toString() );
            Intent intent = new Intent();
            intent.putExtras( bundle );
            setResult( RESULT_OK, intent );
            finish();
        }
    }

    private class CancelButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick( View aView ) {
            Intent intent = new Intent();
            setResult( RESULT_CANCELED, intent );
            finish();
        }
    }
}
