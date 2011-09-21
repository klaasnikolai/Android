package org.suggs.sandbox.android.list;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Adapter to the database for the list class.
 * <p/>
 * User: suggitpe
 * Date: 26/08/11
 * Time: 07:11
 */

public final class ListDbAdapter {

    public static final String DB_ITEM = "item";
    public static final String DB_ID = "_id";

    private static final String DB_TABLE = "LIST";
    private static final String DB_NAME = "LIST_DATA";
    private static final int DB_VERSION = 1;

    private static final String DB_CREATE = "create table " + DB_TABLE + "(_id integer primary key autoincrement, item text not null);";
    private static final String DB_DROP = "drop table if exists " + DB_TABLE;

    private SQLiteDatabase database;
    private final Context listContext;
    private ListDbHelper dbHelper;

    public ListDbAdapter( Context aContext ) {
        listContext = aContext;
    }

    public ListDbAdapter open() {
        Log.i( "List", "Opening the database" );
        dbHelper = new ListDbHelper( listContext );
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public Cursor fetchAllItemsFromPersistentStore() {
        Log.i( "List", "Fetching all items" );
        return database.query( DB_TABLE, new String[]{ DB_ID, DB_ITEM }, null, null, null, null, null );
    }

    public long createItemForString( String aItem ) {
        Log.i( "List", "Creating items" );
        ContentValues content = new ContentValues();
        content.put( DB_ITEM, aItem );
        return database.insert( DB_TABLE, null, content );
    }

    public boolean deleteItem( long aRowId ) {
        Log.i( "List", "Deleting ID [" + aRowId + "]" );
        return database.delete( DB_TABLE, DB_ID + "=" + aRowId, null ) > 0;
    }

    /** Helper class to interact with the database for creates and drops etc. */
    private static final class ListDbHelper extends SQLiteOpenHelper {

        public ListDbHelper( Context context ) {
            super( context, DB_NAME, null, DB_VERSION );
        }

        @Override
        public void onCreate( SQLiteDatabase aDatabase ) {
            Log.i( "List", "Creating a new database" );
            aDatabase.execSQL( DB_CREATE );
        }

        @Override
        public void onUpgrade( SQLiteDatabase aDatabase, int aOldVersion, int aNewVersion ) {
            Log.w( "List", "Upgrading database from old version [" + aOldVersion
                    + "] to new version [" + aNewVersion + "].  All existing data will be lost in the process" );
            aDatabase.execSQL( DB_DROP );
            onCreate( aDatabase );
        }
    }
}
