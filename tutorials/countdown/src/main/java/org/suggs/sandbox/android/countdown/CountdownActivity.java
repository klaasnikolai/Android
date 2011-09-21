package org.suggs.sandbox.android.countdown;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CountdownActivity extends Activity {

    private static final int MILLIS_PER_SECOND = 1000;
    private static final int SETTINGS_MENU_ID = Menu.FIRST;
    private static final int PICK_TIME_REQUEST_ID = 0;
    private static final int PICK_RINGTONE_REQUEST_ID = 1;
    public static final String SECONDS_KEY = "seconds";

    private Context context;
    private Ringtone ringtone;
    private RingtoneManager ringToneManager;
    private TextView countdownDisplay;
    private CountDownTimer timer;
    private int countdownSeconds;


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.main );
        context = getApplicationContext();

        ringToneManager = new RingtoneManager( this );
        ringToneManager.setType( RingtoneManager.TYPE_ALL );

        countdownDisplay = ( TextView ) findViewById( R.id.countdownDisplay );
        createPickButton();
        createStartButton();
        createStopButton();
    }

    private Button createPickButton() {
        Button pickButton = ( Button ) findViewById( R.id.pickButton );
        pickButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View aView ) {
                Intent intent = new Intent( context, CountdownEnterTime.class );
                startActivityForResult( intent, PICK_TIME_REQUEST_ID );
            }
        } );

        return pickButton;
    }

    private Button createStartButton() {
        Button startButton = ( Button ) findViewById( R.id.startButton );
        startButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View aView ) {
                showTimer();
            }
        } );
        return startButton;
    }

    private Button createStopButton() {
        Button stopButton = ( Button ) findViewById( R.id.stopButton );
        stopButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View aView ) {
                if ( timer != null ) {
                    timer.cancel();
                    countdownDisplay.setText( Long.toString( countdownSeconds ) );
                }

                if ( ringtone != null ) {
                    ringtone.stop();
                }
            }
        } );

        return stopButton;
    }


    private void showTimer() {
        Log.i( getString( R.string.app_name ), "Starting timer" );
        if ( timer != null ) {
            timer.cancel();
        }
        int countdownInMillis = countdownSeconds * MILLIS_PER_SECOND;

        timer = new CountDownTimer( countdownInMillis, MILLIS_PER_SECOND ) {
            @Override
            public void onTick( long millisUntilFinished ) {
                countdownDisplay.setText( getString( R.string.countdown_during ) + millisUntilFinished / MILLIS_PER_SECOND );
            }

            @Override
            public void onFinish() {
                countdownDisplay.setText( getString( R.string.countdown_end ) );
                if ( ringtone == null ) {
                    Log.i( getString( R.string.app_name ), "Using default ringtone");
                    ringtone = ringToneManager.getRingtone( context, Settings.System.DEFAULT_RINGTONE_URI );
                }
                Log.i( getString( R.string.app_name ), "Playing ringtone [" + ringtone.getTitle( context ) + "]" );
                ringtone.play();
            }
        }.start();

    }

    @Override
    public boolean onCreateOptionsMenu( Menu aMenu ) {
        super.onCreateOptionsMenu( aMenu );
        aMenu.add( Menu.NONE, SETTINGS_MENU_ID, Menu.NONE, R.string.menu_settings );
        return true;
    }

    @Override
    public boolean onMenuItemSelected( int aId, MenuItem aMenuItem ) {
        switch ( aMenuItem.getItemId() ) {
            case SETTINGS_MENU_ID:
                chooseRingtone();
                return true;
            default:
                // no default option
        }
        return super.onMenuItemSelected( aId, aMenuItem );
    }

    private void chooseRingtone() {
        Intent intent = new Intent( RingtoneManager.ACTION_RINGTONE_PICKER );
        startActivityForResult( intent, PICK_RINGTONE_REQUEST_ID );
    }

    @Override
    protected void onActivityResult( int aRequestCode, int aResultCode, Intent aIntent ) {
        super.onActivityResult( aRequestCode, aResultCode, aIntent );
        if ( aResultCode == RESULT_CANCELED ) {
            return;
        }

        assert aResultCode == RESULT_OK;

        switch ( aRequestCode ) {
            case PICK_TIME_REQUEST_ID:
                Bundle extras = aIntent.getExtras();
                countdownSeconds = extras.getInt( SECONDS_KEY );
                countdownDisplay.setText( Long.toString( countdownSeconds ) );
                break;
            case PICK_RINGTONE_REQUEST_ID:
                Uri uri = aIntent.getParcelableExtra( RingtoneManager.EXTRA_RINGTONE_PICKED_URI );
                ringtone = RingtoneManager.getRingtone( context, uri );
                break;
            default:
                // nadda
        }
    }


}
