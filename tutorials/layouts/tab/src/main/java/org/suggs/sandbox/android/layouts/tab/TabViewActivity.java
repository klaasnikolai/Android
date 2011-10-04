package org.suggs.sandbox.android.layouts.tab;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

/**
 * <p/>
 * User: suggitpe Date: 04/10/11 Time: 18:02
 */

public class TabViewActivity extends TabActivity {

    @Override
    public void onCreate( Bundle aSavedInstanceState ) {
        super.onCreate( aSavedInstanceState );
        setContentView( R.layout.main );

        Resources resources = getResources();
        TabHost tabHost = getTabHost();
        TabHost.TabSpec spec;
        Intent intent;

        // artists
        intent = new Intent().setClass( this, ArtistsActivity.class );
        spec = tabHost.newTabSpec( "artists" ).setIndicator( "Artists", resources.getDrawable( R.drawable.ic_tab_artists ) ).setContent( intent );
        tabHost.addTab( spec );

        // albums
        intent = new Intent().setClass( this, AlbumsActivity.class );
        spec = tabHost.newTabSpec( "albums" ).setIndicator( "Albums", resources.getDrawable( R.drawable.ic_tab_artists ) ).setContent( intent );
        tabHost.addTab( spec );

        // songs
        intent = new Intent().setClass( this, SongsActivity.class );
        spec = tabHost.newTabSpec( "songs" ).setIndicator( "Songs", resources.getDrawable( R.drawable.ic_tab_artists ) ).setContent( intent );
        tabHost.addTab( spec );

        tabHost.setCurrentTab( 2 );
    }

}
