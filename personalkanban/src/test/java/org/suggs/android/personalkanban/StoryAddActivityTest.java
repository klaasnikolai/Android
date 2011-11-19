package org.suggs.android.personalkanban;

import android.widget.Button;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Test class for the add story activity
 * <p/>
 * User: suggitpe Date: 07/10/11 Time: 18:28
 */
@RunWith(value = RobolectricMavenisedTestRunner.class)
public class StoryAddActivityTest {

    private StoryAddActivity activity;
    private Button okButton;
    private Button cancelButton;

    @Before
    public void setup() {
        activity = new StoryAddActivity();
        activity.onCreate( null );
        okButton = ( Button ) activity.findViewById( R.id.okButton );
        cancelButton = ( Button ) activity.findViewById( R.id.cancelButton );
    }

    @Test
    public void shouldHaveAnOkButton() {
        assertThat( okButton.getText().toString(), equalTo( "OK" ) );
    }

    @Test
    public void shouldReturnWithCancelledIntentWhenCancelled() {
        cancelButton.performClick();
    }

}

