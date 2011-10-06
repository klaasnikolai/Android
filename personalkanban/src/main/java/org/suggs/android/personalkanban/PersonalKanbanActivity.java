package org.suggs.android.personalkanban;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class PersonalKanbanActivity extends Activity {

    private static final String TAG = PersonalKanbanActivity.class.getSimpleName();
    private static final int NONE = 0;
    private static final int ADD_ID = Menu.FIRST;
    private static final int DELETE_ID = ADD_ID + 1;
    private static final int STORY_ADD = 0;

    private StoryDAO storyDao;
    private Cursor dataCursor;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.main );

        storyDao = new StoryDAO( this );
        storyDao.open();
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

    public void onActivityResult( int aRequestCode, int aResultCode, Intent aIntent ) {
        super.onActivityResult( aRequestCode, aResultCode, aIntent );
        if ( aResultCode == RESULT_CANCELED ) {
            Toast.makeText( this, "Cancelled ...", Toast.LENGTH_SHORT ).show();
            return;
        }
        Bundle extras = aIntent.getExtras();
        switch ( aRequestCode ) {
            case STORY_ADD:
                Story story = ( Story ) extras.getSerializable( StoryDAO.STORY_ITEM );
                Toast.makeText( this, "Creating story: " + story.getHeadline(), Toast.LENGTH_SHORT ).show();
                break;
        }
    }

    @Override
    public void onCreateContextMenu( ContextMenu aContextMenu, View aView, ContextMenu.ContextMenuInfo aContextMenuInfo ) {
        super.onCreateContextMenu( aContextMenu, aView, aContextMenuInfo );
        aContextMenu.add( 0, DELETE_ID, 0, R.string.menu_delete );
    }

}
