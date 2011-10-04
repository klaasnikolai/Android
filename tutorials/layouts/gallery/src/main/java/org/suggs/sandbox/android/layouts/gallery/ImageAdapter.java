package org.suggs.sandbox.android.layouts.gallery;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

/**
 * TODO: Justify why you have written this class
 * <p/>
 * User: suggitpe Date: 04/10/11 Time: 08:36
 */

public class ImageAdapter extends BaseAdapter {

    int galleryItemBackround;
    private Context context;

    private Integer[] imageIds = { R.drawable.sample_0, R.drawable.sample_1, R.drawable.sample_2, R.drawable.sample_3 };

    public ImageAdapter( Context aContext ) {
        context = aContext;
    }

    @Override
    public int getCount() {
        return imageIds.length;
    }

    @Override
    public Object getItem( int aPosition ) {
        return aPosition;
    }

    @Override
    public long getItemId( int aPosition ) {
        return aPosition;
    }

    @Override
    public View getView( int aPosition, View aView, ViewGroup aViewGroup ) {
        ImageView imageView = new ImageView( context );

        imageView.setImageResource( imageIds[aPosition] );
        imageView.setLayoutParams( new Gallery.LayoutParams( 150, 100 ) );
        imageView.setScaleType( ImageView.ScaleType.FIT_XY );
        imageView.setBackgroundResource( galleryItemBackround );
        return imageView;
    }
}
