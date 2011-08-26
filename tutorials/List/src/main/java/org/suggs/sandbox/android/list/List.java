package org.suggs.sandbox.android.list;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SimpleCursorAdapter;

import java.sql.SQLException;

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
    private Cursor dataCursor;
    private ListDbAdapter databaseAdapter;


    /** Called when the activity is first created. */
    @Override
    public void onCreate( Bundle savedInstanceState ) {
        Log.i("List", "onCreate" );
        super.onCreate( savedInstanceState );
        setContentView( R.layout.main );
        databaseAdapter = new ListDbAdapter( this );
        databaseAdapter.open();
        getData();
        registerForContextMenu( getListView() );
    }

    private void getData() {
        Log.i( "List", "Getting data from the database");
        dataCursor = databaseAdapter.fetchAllItemsFromPersistentStore();
        startManagingCursor( dataCursor );
        String[] cols = new String[]{ ListDbAdapter.DB_ITEM };
        int[] views = new int[]{ R.id.text1 };
        SimpleCursorAdapter rowCursor = new SimpleCursorAdapter( this, R.layout.list_row, dataCursor, cols, views );
        setListAdapter( rowCursor );
    }

    @Override
    public boolean onCreateOptionsMenu( Menu aMenu ) {
        Log.i("List", "Creating options menu" );
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

    private void createItem() {
        Log.i("List", "Creating item" );
        // need to get data into this
        databaseAdapter.createItemForString( getString( R.string.new_item ) );
        getData();
    }


}
