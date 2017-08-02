package com.ckt.cc.gallery.model;

/**
 * Created by Cc on 2017/8/2.
 */

public class GalleryItem {

    public static final String URL = "http://218.244.149.129:9010/api/companylist" +
            ".php?industryid=102";

    private String company;
    private String summary;
    private String logo;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
