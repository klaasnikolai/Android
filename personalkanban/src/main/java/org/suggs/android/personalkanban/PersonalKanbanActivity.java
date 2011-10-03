package org.suggs.android.personalkanban;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class PersonalKanbanActivity extends Activity {

    private static final String TAG = PersonalKanbanActivity.class.getSimpleName();
    private static final int NONE = 0;
    private static final int ADD_ID = Menu.FIRST;
    private static final int DELETE_ID = ADD_ID + 1;
    private static final int STORY_ADD = 0;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.main );

    }

    @Override
    public boolean onCreateOptionsMenu( Menu aMenu ) {
        super.onCreateOptionsMenu( aMenu );
        aMenu.add( NONE, ADD_ID, NONE, R.string.menu_add );
        return true;
    }

    @Override
    public boolean onMenuItemSelected( int aId, MenuItem aMenuItem ) {
        switch ( aMenuItem.getItemId() ) {
            case ADD_ID:
                createStory();
                return true;
        }
        return super.onMenuItemSelected( aId, aMenuItem );
    }

    private void createStory() {
        Intent intent = new Intent( this, StoryAddActivity.class );
        startActivityForResult( intent, STORY_ADD );
    }


}
