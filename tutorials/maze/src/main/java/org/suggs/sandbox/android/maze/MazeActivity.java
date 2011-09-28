package org.suggs.sandbox.android.maze;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

/**
 * Main activity class for the Maze.
 * <p/>
 * User: suggitpe Date: 26/09/11 Time: 07:34
 */
public class MazeActivity extends Activity implements SensorEventListener {

    protected static MazeView maze;
    private MazeBall ball;
    private SensorManager manager;
    private Sensor accelerometer;

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
    }

    @Override
    protected void onResume() {
        super.onResume();
        manager.registerListener( this, accelerometer, SensorManager.SENSOR_DELAY_GAME );
    }

    @Override
    protected void onPause(){
        super.onPause();
        manager.unregisterListener( this);
    }


    @Override
    public void onSensorChanged( SensorEvent aSensorEvent ) {
        ball.moveBall( aSensorEvent.values[0], aSensorEvent.values[1]);
        ball.invalidate();
    }

    @Override
    public void onAccuracyChanged( Sensor aSensor, int i ) {
        ball.invalidate();
    }
}
