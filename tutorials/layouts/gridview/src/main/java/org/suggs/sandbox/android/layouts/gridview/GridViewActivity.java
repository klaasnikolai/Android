package org.suggs.sandbox.android.layouts.gridview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class GridViewActivity extends Activity {

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.main );

        GridView gridView = ( GridView ) findViewById( R.id.gridview );
        gridView.setAdapter( new ImageAdapter( this ) );

        gridView.setOnItemClickListener( new GridViewItemClickListener() );
    }

    private class GridViewItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick( AdapterView<?> aAdapterView, View aView, int aPosition, long aId ) {
            Toast.makeText( GridViewActivity.this, "" + aPosition, Toast.LENGTH_SHORT ).show();
        }
    }
}
