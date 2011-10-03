package org.suggs.android.personalkanban;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Activity to manage the construction of new story items.
 * <p/>
 * User: suggitpe Date: 03/10/11 Time: 07:10
 */

public class StoryAddActivity extends Activity {

    private EditText storyHeadline;

    @Override
    public void onCreate( Bundle aSavedInstanceState ) {
        super.onCreate( aSavedInstanceState );
        setContentView( R.layout.new_story );


        storyHeadline = ( EditText ) findViewById( R.id.newStoryHeadline );
        Button okButton = ( Button ) findViewById( R.id.okButton );
        Button cancelButton = ( Button ) findViewById( R.id.cancelButton );
        okButton.setOnClickListener( new OkButtonClickListener() );
        cancelButton.setOnClickListener( new CancelButtonClickListener() );
    }

    private Story buildStory() {
        Story story = new Story(storyHeadline.getText().toString());
        return story;
    }

    private class OkButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick( View aView ) {
            Bundle bundle = new Bundle( );
            Story story =  buildStory();
            bundle.putSerializable("key", story);
            Intent intent = new Intent( );
            setResult( RESULT_OK, intent);
            finish();
        }
    }

    private class CancelButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick( View aView ) {
            Intent intent = new Intent();
            setResult( RESULT_CANCELED, intent );
            finish();
        }
    }
}