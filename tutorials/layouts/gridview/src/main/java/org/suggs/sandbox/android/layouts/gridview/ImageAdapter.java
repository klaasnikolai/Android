package org.suggs.sandbox.android.layouts.gridview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;


/**
 * TODO: Justify why you have written this class
 * <p/>
 * User: suggitpe Date: 04/10/11 Time: 15:33
 */

public class ImageAdapter extends BaseAdapter {

    Context context;

    private Integer[] imageIds = { R.drawable.sample_0, R.drawable.sample_1, R.drawable.sample_2, R.drawable.sample_3 };

    public ImageAdapter( Context aContext ) {
        context = aContext;
    }

    @Override
    public int getCount() {
        return imageIds.length;
    }

    @Override
    public Object getItem( int i ) {
        return null;
    }

    @Override
    public long getItemId( int i ) {
        return 0;
    }

    @Override
    public View getView( int aPosition, View aView, ViewGroup aParent ) {

        ImageView imageView;
        if ( aView == null ) {
            imageView = new ImageView( context );
            imageView.setLayoutParams( new GridView.LayoutParams( 85, 85 ) );
            imageView.setScaleType( ImageView.ScaleType.CENTER_CROP );
            imageView.setPadding( 8, 8, 8, 8 );

        }
        else {
            imageView = ( ImageView ) aView;
        }

        imageView.setImageResource( imageIds[aPosition] );
        return imageView;
    }
}
