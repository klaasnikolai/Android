package org.suggs.android.personalkanban;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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


    public StoryDAO( Context aContext ) {
        context = aContext;
    }

    public StoryDAO open(){
        Log.i(  TAG, "Opening database" );
        dbHelper = new StoryDbOpenHelper( context );
        database = dbHelper.getWritableDatabase();
        return this;
    }


}
