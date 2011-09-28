package org.suggs.sandbox.android.maze;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.view.View;

/**
 * Class to represent the maze ball.
 * <p/>
 * User: suggitpe Date: 27/09/11 Time: 07:43
 */

public class MazeBall extends View {


    private int cellWidth;
    private int diameter;
    private ShapeDrawable ball;
    private int topX = 0;
    private int topY = 1;

    public MazeBall( Context aContext, AttributeSet aAttributeSet ) {
        super( aContext );
        initVars( aAttributeSet );
        init();
    }

    private void init() {
        ball = new ShapeDrawable( new OvalShape() );
        ball.getPaint().setColor( Color.GREEN );
        ball.setBounds( topX, topY, diameter + topX, diameter + topY );
    }

    private void initVars( AttributeSet aAttributeSet ) {
        TypedArray style = getContext().obtainStyledAttributes( aAttributeSet, R.styleable.Maze );

        cellWidth = ( int ) style.getDimension( R.styleable.Maze_cellWidth, 0 );
        diameter = ( int ) style.getDimension( R.styleable.Maze_ballDiameter, 0 );
        style.recycle();

        if ( cellWidth <= 0 || diameter <= 0 ) {
            throw new RuntimeException( "valid integers for the maze dimensions must be passed into the class via XML parameters" );
        }
    }

    @Override
    protected void onDraw( Canvas aCanvas ) {
        ball.draw( aCanvas );
    }

    protected void moveBall( float x, float y ) {
        int newX = topX - ( int ) x;
        int newY = topY + ( int ) y;
        if ( canBallMove( newX, newY ) ) {
            topX = newX;
            topY = newY;
        }
        ball.setBounds( topX, topY, diameter + topX, diameter + topY );
    }

    private boolean canBallMove( int x, int y ) {
        int newMidX = x + diameter / 2;
        int newMidY = y + diameter / 2;

        // where is balls middle?
        int oldCellX = ( topX + diameter / 2 ) / cellWidth + 1;
        int oldCellY = ( topY + diameter / 2 ) / cellWidth + 1;

        // which cell would middle be in now?
        int newCellX = newMidX / cellWidth + 1;
        int newCellY = newMidY / cellWidth + 1;

        if ( ( newMidX % cellWidth ) + diameter / 2 >= cellWidth ) {
            newCellX++;
        }
        if ( ( newMidX % cellWidth ) - diameter / 2 <= 0 ) {
            newCellX--;
        }
        if ( ( newMidY % cellWidth ) + diameter / 2 >= cellWidth ) {
            newCellY++;
        }
        if ( ( newMidY % cellWidth ) - diameter / 2 <= 0 ) {
            newCellY--;
        }

        return !( MazeActivity.maze.isWall( oldCellX, oldCellY, newCellX, newCellY ) );
    }
}
