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
    private String headline;
    private String INeed;
    private String state;

    public Story( String aAsA, String aINeed, String aSoThat ) {
        asA = aAsA;
        iNeed = aINeed;
        soThat = aSoThat;
    }

    public String getHeadline() {
        return iNeed;
    }

    public String getAsA() {
        return asA;
    }

    public String getINeed() {
        return INeed;
    }

    public String getSoThat() {
        return soThat;
    }

    public String getState() {
        return state;
    }
}
