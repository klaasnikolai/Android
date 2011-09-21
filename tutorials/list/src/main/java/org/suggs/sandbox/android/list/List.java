package org.suggs.sandbox.android.list;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleCursorAdapter;

/**
 * Main activity class for the android application.
 * <p/>
 * User: suggitpe
 * Date: 26/08/11
 * Time: 07:11
 */
public class List extends ListActivity {


    private static final int ADD_ID = Menu.FIRST;
    private static final int NONE = 0;
    private static final int DELETE_ID = ADD_ID + 1;
    private Cursor dataCursor;
    private ListDbAdapter databaseAdapter;
    private static final int ITEM_ADD = 0;


    /** Called when the activity is first created. */
    @Override
    public void onCreate( Bundle savedInstanceState ) {
        Log.i( "List", "onCreate" );
        super.onCreate( savedInstanceState );
        setContentView( R.layout.main );
        databaseAdapter = new ListDbAdapter( this );
        databaseAdapter.open();
        getData();
        registerForContextMenu( getListView() );
    }

    private void getData() {
        Log.i( "List", "Getting data from the database" );
        dataCursor = databaseAdapter.fetchAllItemsFromPersistentStore();
        startManagingCursor( dataCursor );
        String[] cols = new String[]{ ListDbAdapter.DB_ITEM };
        int[] views = new int[]{ R.id.text1 };
        SimpleCursorAdapter rowCursor = new SimpleCursorAdapter( this, R.layout.list_row, dataCursor, cols, views );
        setListAdapter( rowCursor );
    }

    @Override
    public void onCreateContextMenu( ContextMenu aMenu, View aView, ContextMenu.ContextMenuInfo aContextMenuInfo ) {
        super.onCreateContextMenu( aMenu, aView, aContextMenuInfo );
        aMenu.add( 0, DELETE_ID, 0, R.string.menu_delete );
    }

    @Override
    public boolean onCreateOptionsMenu( Menu aMenu ) {
        Log.i( "List", "Creating options menu" );
        super.onCreateOptionsMenu( aMenu );
        aMenu.add( NONE, ADD_ID, NONE, R.string.menu_add );
        return true;
    }

    @Override
    public boolean onMenuItemSelected( int aId, MenuItem aMenuItem ) {
        switch ( aMenuItem.getItemId() ) {
            case ADD_ID:
                createItem();
                return true;
        }
        return super.onMenuItemSelected( aId, aMenuItem );
    }


    public boolean onContextItemSelected( MenuItem aMenuItem ) {
        switch ( aMenuItem.getItemId() ) {
            case DELETE_ID:
                AdapterView.AdapterContextMenuInfo info = ( AdapterView.AdapterContextMenuInfo ) aMenuItem.getMenuInfo();
                databaseAdapter.deleteItem( info.id );
                getData();
                return true;
        }
        return super.onContextItemSelected( aMenuItem );
    }

    private void createItem() {
        Intent intent = new Intent( this, ListAdd.class );
        startActivityForResult( intent, ITEM_ADD );
    }

    @Override
    protected void onActivityResult( int aRequestCode, int aResultCode, Intent aIntent ) {
        super.onActivityResult( aRequestCode, aResultCode, aIntent );
        if ( aResultCode == RESULT_CANCELED ) {
            return;
        }
        Bundle extras = aIntent.getExtras();
        switch ( aRequestCode ) {
            case ITEM_ADD:
                String label = extras.getString( ListDbAdapter.DB_ITEM );
                databaseAdapter.createItemForString( label );
                getData();
                break;
        }
    }


}
