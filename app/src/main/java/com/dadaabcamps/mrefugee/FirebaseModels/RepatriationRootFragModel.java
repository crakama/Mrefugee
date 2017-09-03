package com.dadaabcamps.mrefugee.FirebaseModels;

/**
 * Created by User on 10/8/2016.
 */

public class RepatriationRootFragModel {




    public RepatriationRootFragModel() {

    }
//
//    public RepatriationRootFragModel(String welcomeMessage,String stagesInvolved,String countryInfo ) {
//        setCountryInfo("COUNTRY INFO");
//        setStagesInvolved("REPAT STAGES");
//        setWelcomeMessage("setWelcomeMessage");
//    }

    private String welcomeMessage;
    private String stagesInvolved;

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

    public String getStagesInvolved() {
        return stagesInvolved;
    }

    public void setStagesInvolved(String stagesInvolved) {
        this.stagesInvolved = stagesInvolved;
    }

    public String getCountryInfo() {
        return countryInfo;
    }

    public void setCountryInfo(String countryInfo) {
        this.countryInfo = countryInfo;
    }

    private String countryInfo;




}
