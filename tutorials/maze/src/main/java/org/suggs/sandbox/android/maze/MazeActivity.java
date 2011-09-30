package org.suggs.sandbox.android.maze;

import android.app.Activity;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Main activity class for the Maze.
 * <p/>
 * User: suggitpe Date: 26/09/11 Time: 07:34
 */
public class MazeActivity extends Activity implements SensorEventListener {

    private static final int RESTART_MENU_ID = Menu.FIRST;
    private static final int NEWGAME_MENU_ID = RESTART_MENU_ID + 1;
    private static final int NANOSECS_IN_SEC = 1000000000;
    public static final String SHARED_PREFS_NAME = "MazePreferences";
    public static final String HI_SCORE = "hiscore";
    public static final String TIMETAKEN_KEY = "timetaken";
    public static final int NAVIGATE_ID = 0;

    private boolean gameover = false;
    protected static MazeView maze;
    private SensorManager manager;
    private Sensor accelerometer;
    private MazeBall ball;
    private long startNanoSecs;
    private long saveNanoSecs;


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.main );

        ball = ( MazeBall ) findViewById( R.id.mazeball );
        maze = ( MazeView ) findViewById( R.id.maze );
        manager = ( SensorManager ) getSystemService( SENSOR_SERVICE );
        accelerometer = manager.getDefaultSensor( Sensor.TYPE_ACCELEROMETER );
        startGame();
    }

    private void startGame() {
        manager.registerListener( this, accelerometer, SensorManager.SENSOR_DELAY_GAME );
        startTimer();
    }

    private void startTimer() {
        startNanoSecs = System.nanoTime();
    }

    private void newGame() {
        maze.newMaze();
        ball.newBall();
        startGame();
    }

    private void restartThisGame() {
        ball.newBall();
        startGame();
    }

    public void finishGame() {
        long timeNanoSecs = System.nanoTime();
        long timeTaken = ( timeNanoSecs - startNanoSecs ) + saveNanoSecs;

        Toast.makeText( getApplicationContext(), R.string.finished_message, Toast.LENGTH_SHORT ).show();

        int scoreSecs = ( int ) ( timeTaken / NANOSECS_IN_SEC );
        Toast.makeText( getApplicationContext(), "Score: " + scoreSecs + " s", Toast.LENGTH_LONG ).show();

        SharedPreferences prefs = getSharedPreferences( SHARED_PREFS_NAME, 0 );
        long currentHiScore = prefs.getLong( HI_SCORE, 0 );
        if ( currentHiScore == 0 || ( timeTaken < currentHiScore ) ) {
            SharedPreferences.Editor prefEditor = prefs.edit();
            prefEditor.putLong( HI_SCORE, timeTaken );
            prefEditor.commit();
            Toast.makeText( getApplicationContext(), "New high scrore" + scoreSecs + " s", Toast.LENGTH_LONG ).show();
        }
        manager.unregisterListener( this );
    }

    @Override
    public void onAccuracyChanged( Sensor aSensor, int aAccuracy ) {
        ball.invalidate();
    }

    @Override
    public void onSensorChanged( SensorEvent aSensorEvent ) {
        int[] topCornerValues = ball.moveBall( aSensorEvent.values[0], aSensorEvent.values[1] );
        ball.invalidate();
        if ( isBallAtEnd( topCornerValues[0], topCornerValues[1] ) ) {
            finishGame();
        }
    }

    private boolean isBallAtEnd( int x, int y ) {
        int[] midBall = ball.cellBallMiddle( x, y );
        return maze.isLastCell( x, y );

    }

    @Override
    protected void onResume() {
        super.onResume();
        startTimer();
        manager.registerListener( this, accelerometer, SensorManager.SENSOR_DELAY_GAME );
    }

    @Override
    protected void onPause() {
        super.onPause();
        pauseTimer();
        manager.unregisterListener( this );
    }

    private void pauseTimer() {
        long pauseTimeNanos = System.nanoTime();
        saveNanoSecs = pauseTimeNanos - startNanoSecs;
    }

    @Override
    public boolean onCreateOptionsMenu( Menu aMenu ) {
        super.onCreateOptionsMenu( aMenu );
        aMenu.add( Menu.NONE, RESTART_MENU_ID, Menu.NONE, R.string.menu_restart );
        aMenu.add( Menu.NONE, NEWGAME_MENU_ID, Menu.NONE, R.string.menu_newgame );
        return true;
    }

    @Override
    public boolean onMenuItemSelected( int aId, MenuItem aMenuItem ) {
        switch ( aMenuItem.getItemId() ) {
            case RESTART_MENU_ID:
                restartThisGame();
                return true;
            case NEWGAME_MENU_ID:
                newGame();
                return true;
            default:
                // nadda
        }
        return super.onMenuItemSelected( aId, aMenuItem );
    }


}
