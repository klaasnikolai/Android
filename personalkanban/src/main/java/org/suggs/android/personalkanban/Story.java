package org.suggs.android.personalkanban;

import java.io.Serializable;

/**
 * Class to represent a story.
 * <p/>
 * User: suggitpe Date: 03/10/11 Time: 07:19
 */

public class Story implements Serializable{

    private String asA;
    private String iNeed;
    private String soThat;

    public Story( String aAsA, String aINeed, String aSoThat ) {
        asA = aAsA;
        iNeed = aINeed;
        soThat = aSoThat;
    }
}
