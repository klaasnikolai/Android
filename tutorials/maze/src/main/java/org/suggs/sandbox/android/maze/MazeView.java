package org.suggs.sandbox.android.maze;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * View class for the maze.
 * <p/>
 * User: suggitpe Date: 26/09/11 Time: 19:11
 */

public class MazeView extends View {

    private int width;
    private int height;
    private float cellWidth;

    private boolean[][] visited;
    private boolean[][] north;
    private boolean[][] east;
    private boolean[][] south;
    private boolean[][] west;

    private Paint paint = new Paint();

    public MazeView( Context aContext, AttributeSet aAttributeSet ) {
        super( aContext );
        initVars( aAttributeSet );
        initMaze();
        generate( 1, 1 );
    }

    protected void onDraw( Canvas aCanvas ) {
        paint.setColor( Color.YELLOW );
        for ( int x = 1; x <= width; ++x ) {
            for ( int y = 1; y <= height; ++y ) {
                if ( south[x][y] ) {
                    aCanvas.drawLine( cellWidth * ( x - 1 ), cellWidth * y, cellWidth * x, cellWidth * y, paint );
                }
                if ( north[x][y] ) {
                    aCanvas.drawLine( cellWidth * ( x - 1 ), cellWidth * ( y - 1 ), cellWidth * x, cellWidth * ( y - 1 ), paint );
                }
                if ( west[x][y] ) {
                    aCanvas.drawLine( cellWidth * ( x - 1 ), cellWidth * ( y - 1 ), cellWidth * ( x - 1 ), cellWidth * y, paint );
                }
                if ( east[x][y] ) {
                    aCanvas.drawLine( cellWidth * x, cellWidth * ( y - 1 ), cellWidth * x, cellWidth * y, paint );
                }
            }
        }
    }

    private void generate( int x, int y ) {
        visited[x][y] = true;
        while ( !visited[x][y + 1] || !visited[x + 1][y] || !visited[x][y - 1] || !visited[x - 1][y] ) {
            while ( true ) {
                // pick a random number
                double r = Math.random();
                if ( r < 0.25 && !visited[x][y - 1] ) {
                    north[x][y] = south[x][y - 1] = false;
                    generate( x, y - 1 );
                    break;
                }
                else if ( r >= 0.25 && r < 0.5 && !visited[x + 1][y] ) {
                    east[x][y] = west[x + 1][y] = false;
                    generate( x + 1, y );
                    break;
                }
                else if ( r >= 0.5 && r < 0.75 && !visited[x][y + 1] ) {
                    south[x][y] = north[x][y + 1] = false;
                    generate( x, y + 1 );
                    break;
                }
                else if ( r >= 0.75 && r < 1.00 && !visited[x - 1][y] ) {
                    west[x][y] = east[x - 1][y] = false;
                    generate( x - 1, y );
                    break;
                }
            }
        }
    }

    private void initMaze() {
        visited = new boolean[width + 2][height + 2];

        // first visit the border cells
        for ( int x = 0; x < width + 2; x++ ) {
            visited[x][0] = visited[x][height + 1] = true;
        }
        for ( int y = 0; y < height + 2; y++ ) {
            visited[0][y] = visited[width + 1][y] = true;
        }

        north = new boolean[width + 2][height + 2];
        east = new boolean[width + 2][height + 2];
        south = new boolean[width + 2][height + 2];
        west = new boolean[width + 2][height + 2];
        for ( int x = 0; x < width + 2; x++ ) {
            for ( int y = 0; y < height + 2; y++ ) {
                north[x][y] = east[x][y] = south[x][y] = west[x][y] = true;
            }
        }

        west[1][1] = false;
        east[width][height] = false;
    }

    private void initVars( AttributeSet aAttributeSet ) {
        TypedArray array = getContext().obtainStyledAttributes( aAttributeSet, R.styleable.Maze );
        cellWidth = array.getDimension( R.styleable.Maze_cellWidth, 0 );
        int pixelWidth = ( int ) array.getDimension( R.styleable.Maze_pixelWidth, 0 );
        int pixelHeight = ( int ) array.getDimension( R.styleable.Maze_pixelHeight, 0 );
        array.recycle();

        if ( cellWidth <= 0 || pixelWidth <= 0 || pixelHeight <= 0 ) {
            throw new RuntimeException( "Valid integers for the maze dimensions must be passed in via the xml parameters" );
        }

        width = pixelWidth / ( int ) cellWidth;
        height = pixelHeight / ( int ) cellWidth;
    }

    public boolean isWall( int oldX, int oldY, int newX, int newY ) {
        boolean wallPresent;

        if ( ( oldX == newX ) && ( oldY == newY ) ) {
            wallPresent = false;
        }
        else if ( newX == oldX - 1 ) {
            wallPresent = west[oldX][oldY];
        }
        else if ( newX == oldX + 1 ) {
            wallPresent = east[oldX][oldY];
        }
        else if ( newY == oldY - 1 ) {
            wallPresent = north[oldX][oldY];
        }
        else if ( newY == oldY + 1 ) {
            wallPresent = south[oldX][oldY];
        }
        else {
            wallPresent = false;
        }

        if ( ( oldX != newX ) && ( oldY != newY ) ) {
            if ( ( newX == oldX + 1 ) && ( newY == oldY + 1 ) && ( north[newX][newY] || west[newX][newY] ) ) {
                wallPresent = true;
            }
            else if ( ( newX == oldX + 1 ) && ( newY == oldY - 1 ) && ( south[newX][newY] || west[newX][newY] ) ) {
                wallPresent = true;
            }
            else if ( ( newX == oldX - 1 ) && ( newY == oldY + 1 ) && ( north[newX][newY] || east[newX][newY] ) ) {
                wallPresent = true;
            }
            else if ( ( newX == oldX - 1 ) && ( newY == oldY - 1 ) && ( south[newX][newY] || east[newX][newY] ) ) {
                wallPresent = true;
            }
        }

        return wallPresent;
    }
}
