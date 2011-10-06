package org.suggs.android.personalkanban;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Manages the creation and update of the
 * <p/>
 * User: suggitpe Date: 05/10/11 Time: 19:43
 */

public class StoryDbOpenHelper extends SQLiteOpenHelper {

    private static final String DB_CREATE = "create table " + StoryDAO.STORY_TABLE + "(id integer primary key autoincrement, asa text not null, ineed text not null, sothat text not null);";
    private static final String DB_DROP = "drop table if exists" + StoryDAO.STORY_TABLE;
    private Context context;

    public StoryDbOpenHelper( Context aContext ) {
        super( aContext, aContext.getString( R.string.database_name ), null, ( new Integer( aContext.getString( R.string.database_version ) ) ).intValue() );
        context = aContext;
    }

    @Override
    public void onCreate( SQLiteDatabase aDatabase ) {
        Toast.makeText( context, "Creating database", Toast.LENGTH_SHORT ).show();
        aDatabase.execSQL( DB_CREATE );
    }

    @Override
    public void onUpgrade( SQLiteDatabase aDatabase, int i, int i1 ) {
        Toast.makeText( context, "Upgrading database", Toast.LENGTH_SHORT ).show();
        aDatabase.execSQL( DB_DROP );

    }
}
