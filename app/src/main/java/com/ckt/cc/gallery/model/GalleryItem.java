package com.ckt.cc.gallery.model;

/**
 * Created by Cc on 2017/8/2.
 */

public class GalleryItem {

    public static final String URL = "http://218.244.149.129:9010/api/companylist" + "" + "" +
            ".php?industryid=102";

    private String mId;
    private String mCompany;
    private String mSummary;
    private String mLogo;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getCompany() {
        return mCompany;
    }

    public void setCompany(String company) {
        mCompany = company;
    }

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String summary) {
        mSummary = summary;
    }

    public String getLogo() {
        return mLogo;
    }

    public void setLogo(String logo) {
        mLogo = logo;
    }

    public String getPhotoFileName() {
        return "IMG_" + getId() + ".jpg";
    }
}
