package org.suggs.android.personalkanban.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.suggs.android.personalkanban.R;

/**
 * Manages the creation and update of the
 * <p/>
 * User: suggitpe Date: 05/10/11 Time: 19:43
 */

public class StoryDbOpenHelper extends SQLiteOpenHelper {

    private static final String TAG = StoryDbOpenHelper.class.getSimpleName();
    private static final String DB_CREATE = "create table " + StoryDAO.STORY_TABLE
            + "(" + StoryDAO.STORY_ID + " integer primary key autoincrement, "
            + StoryDAO.STORY_ASA + " text not null, "
            + StoryDAO.STORY_INEED + " text not null, "
            + StoryDAO.STORY_SOTHAT + " text not null, "
            + StoryDAO.STORY_STATE + " text not null);";
    private static final String DB_DROP = "drop table if exists" + StoryDAO.STORY_TABLE;
    private Context context;

    public StoryDbOpenHelper( Context aContext ) {
        super( aContext, aContext.getString( R.string.database_name ), null, ( new Integer( aContext.getString( R.string.database_version ) ) ).intValue() );
        context = aContext;
    }

    @Override
    public void onCreate( SQLiteDatabase aDatabase ) {
        Log.i( TAG, "Creating database" );
        aDatabase.execSQL( DB_CREATE );
    }

    @Override
    public void onUpgrade( SQLiteDatabase aDatabase, int aOldVersion, int aNewVersion ) {
        Log.w( TAG, "Upgrading from version " + aOldVersion + " to version " + aNewVersion
                + ", which will destroy all your old data" );
        aDatabase.execSQL( DB_DROP );
        onCreate( aDatabase );
    }
}
