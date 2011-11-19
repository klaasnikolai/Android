package org.suggs.android.personalkanban.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import org.suggs.android.personalkanban.Story;

/**
 * Database access object for story objects.
 * <p/>
 * User: suggitpe Date: 05/10/11 Time: 19:25
 */

public class StoryDAO {

    private static final String TAG = StoryDAO.class.getSimpleName();
    public static final String STORY_ITEM = "story";
    private Context context;

    private SQLiteOpenHelper dbHelper;
    private SQLiteDatabase database;
    public static final String STORY_TABLE = "STORY";

    static final String STORY_ID = "ID";
    static final String STORY_ASA = "AS_A";
    static final String STORY_INEED = "I_NEED";
    static final String STORY_SOTHAT = "SO_THAT";
    static final String STORY_STATE = "STATE";


    public StoryDAO( Context aContext ) {
        context = aContext;
        dbHelper = new StoryDbOpenHelper( context );
    }

    public StoryDAO open() {
        Log.i( TAG, "Opening database" );
        try {
            database = dbHelper.getWritableDatabase();
        }
        catch ( SQLiteException eqlEx ) {
            Toast.makeText( context, "Problem encountered opening the database, getting read-only database instead", Toast.LENGTH_LONG ).show();
            database = dbHelper.getReadableDatabase();
        }
        return this;
    }

    public void close() {
        database.close();
    }

    public long createStory( Story aStory ) {
        Log.i( TAG, "Writing story to database" );
        ContentValues content = new ContentValues();
        content.put( STORY_ASA, aStory.getAsA() );
        content.put( STORY_INEED, aStory.getINeed() );
        content.put( STORY_SOTHAT, aStory.getSoThat() );
        content.put( STORY_STATE, aStory.getState() );
        return database.insert( STORY_TABLE, null, content );
    }

    public void updateStory( long aId, Story aStory ) {
    }

    public boolean deleteStory( long aId ) {
        return database.delete( STORY_TABLE, STORY_ID + "=" + aId, null ) > 0;
    }

    public Cursor fetchAllStories() {
        return database.query( STORY_TABLE, new String[]{ STORY_ID, STORY_ASA, STORY_INEED, STORY_SOTHAT }, null, null, null, null, null );
    }

    public Cursor fetchAllForState( String aState ) {
        return database.query( STORY_TABLE, new String[]{ STORY_ID, STORY_ASA, STORY_INEED, STORY_SOTHAT }, STORY_STATE + "=" + aState, null, null, null, null );
    }

    public void persist( Story aStory ) {
        throw new RuntimeException();
    }
}
