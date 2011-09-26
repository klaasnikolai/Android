package org.suggs.sandbox.android.spiritlevel;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.hardware.SensorManager;
import android.view.View;

/**
 * <p/>
 * User: suggitpe Date: 22/09/11 Time: 07:17
 */

public class SpiritLevelDrawableView extends View {

    private LayerDrawable layer;
    private ShapeDrawable bubble;
    private Resources resources;

    private static final String TAG = "SpiritLevelDrawableView";

    private static final double WIDTH_MAGNIFY = 1.5;
    private static final double HEIGHT_MAGNIFY = 3;

    private static final int OUTER_X = 80;
    private static final int OUTER_Y = 50;
    private static final int OUTER_WIDTH = 150;
    private static final int OUTER_HEIGHT = 300;

    private int bubbleOrigX;
    private int bubbleOrigY;
    private int bubbleX;
    private int bubbleY;
    private int bubbleDiameter = 20;

    public SpiritLevelDrawableView( Context aContext ) {
        super( aContext );

        resources = getResources();
        ShapeDrawable outer = createOuter();
        bubble = createBubble();
        layer = new LayerDrawable( new Drawable[]{ outer, createLiquid(), bubble } );
    }

    private ShapeDrawable createBubble() {
        bubbleOrigX = OUTER_X + ( OUTER_WIDTH / 2 ) - ( bubbleDiameter / 2 );
        bubbleOrigY = OUTER_Y + ( OUTER_HEIGHT / 2 ) - ( bubbleDiameter / 2 );
        bubbleX = bubbleOrigX;
        bubbleY = bubbleOrigY;

        ShapeDrawable localBubble = new ShapeDrawable( new OvalShape() );
        localBubble.getPaint().setColor( Color.BLACK );
        localBubble.setBounds( bubbleX, bubbleY, bubbleX + bubbleDiameter, bubbleY + bubbleDiameter );
        return localBubble;
    }

    private ShapeDrawable createLiquid() {
        int liquidWidth = ( int ) ( ( SensorManager.STANDARD_GRAVITY * WIDTH_MAGNIFY * 2 ) + bubbleDiameter + 0.5 );
        int liquidHeight = ( int ) ( ( SensorManager.STANDARD_GRAVITY * HEIGHT_MAGNIFY * 2 ) + bubbleDiameter + 0.5 );
        int liquidX = OUTER_X + ( OUTER_WIDTH - liquidWidth ) / 2;
        int liquidY = OUTER_Y + ( OUTER_HEIGHT - liquidHeight ) / 2;

        ShapeDrawable liquid = new ShapeDrawable();
        liquid.getPaint().setColor( resources.getColor( R.color.brightyellow ) );
        liquid.setBounds( liquidX, liquidY, liquidX + liquidWidth, liquidY + liquidHeight );
        return liquid;
    }

    private ShapeDrawable createOuter() {
        ShapeDrawable outer = new ShapeDrawable();
        outer.getPaint().setColor( resources.getColor( R.color.brightpurple ) );
        outer.setBounds( OUTER_X, OUTER_Y, OUTER_X + OUTER_WIDTH, OUTER_Y + OUTER_HEIGHT );
        return outer;
    }

    @Override
    protected void onDraw( Canvas aCanvas ) {
        bubble.setBounds( bubbleX, bubbleY, bubbleX + bubbleDiameter, bubbleY + bubbleDiameter );
        layer.draw( aCanvas );
    }

    protected void moveBubble( float gravityX, float gravityY ) {
        bubbleX = bubbleOrigX + ( int ) ( gravityX * WIDTH_MAGNIFY );
        bubbleY = bubbleOrigY + ( int ) ( gravityY * HEIGHT_MAGNIFY );
    }

}
