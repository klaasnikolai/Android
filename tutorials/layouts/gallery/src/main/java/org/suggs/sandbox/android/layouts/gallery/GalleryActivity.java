package org.suggs.sandbox.android.layouts.gallery;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.Toast;

public class GalleryActivity extends Activity {

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.main );

        Gallery gallery = ( Gallery ) findViewById( R.id.gallery );
        gallery.setAdapter( new ImageAdapter( this ) );

        gallery.setOnItemClickListener( new GalleryItemClickListener() );
    }

    private class GalleryItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick( AdapterView<?> aParent, View aView, int aPosition, long aId ) {
            Toast.makeText( GalleryActivity.this, "" + aPosition, Toast.LENGTH_SHORT ).show();
        }
    }
}
