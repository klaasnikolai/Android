package org.suggs.sandbox.android.spiritlevel;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;

/**
 * Main activity class.
 * <p/>
 * User: suggitpe Date: 22/09/11 Time: 07:17
 */
public class SpiritLevelActivity extends Activity implements SensorEventListener {

    private static final String TAG = "SpiritLevelActivity";

    private SpiritLevelDrawableView spiritLevelView;
    private SensorManager sensorManger;
    private Sensor gravity;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );

        spiritLevelView = new SpiritLevelDrawableView( this );
        setContentView( spiritLevelView );

        sensorManger = ( SensorManager ) getSystemService( SENSOR_SERVICE );
        gravity = sensorManger.getDefaultSensor( Sensor.TYPE_GRAVITY );
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManger.unregisterListener( this );
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManger.registerListener( this, gravity, SensorManager.SENSOR_DELAY_GAME );
    }

    @Override
    public void onSensorChanged( SensorEvent aSensorEvent ) {
        Log.i( TAG, "onSensorChanged with [" + aSensorEvent.values[0] + "], [" + aSensorEvent.values[1] + "], [" + aSensorEvent.values[2] + "]" );
        spiritLevelView.moveBubble( aSensorEvent.values[0], aSensorEvent.values[1] );
        spiritLevelView.invalidate();
    }

    @Override
    public void onAccuracyChanged( Sensor aSensor, int i ) {
        spiritLevelView.invalidate();
    }
}
