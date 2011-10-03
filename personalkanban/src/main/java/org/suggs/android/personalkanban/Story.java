package org.suggs.android.personalkanban;

import java.io.Serializable;

/**
 * Class to represent a story.
 * <p/>
 * User: suggitpe Date: 03/10/11 Time: 07:19
 */

public class Story implements Serializable{

    private String headline;
    public Story( String aHeadline ) {
        headline = aHeadline;
    }
}
