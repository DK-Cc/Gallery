package com.ckt.cc.gallery.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cc on 2017/8/2.
 */

public class JsonParseUtils {

    public static List<GalleryItem> parseMesJson(String json)  {

        List<GalleryItem> galleryItems = new ArrayList<>();

        try {
            JSONObject jsonBody = new JSONObject(json);
            JSONArray companyArray = jsonBody.getJSONArray("companys");

            for (int i = 0;i<companyArray.length();i++) {
                JSONObject jsonObject = companyArray.getJSONObject(i);

                GalleryItem galleryItem = new GalleryItem();
                galleryItem.setId(jsonObject.getString("id"));
                galleryItem.setCompany(jsonObject.getString("company"));
                galleryItem.setLogo(jsonObject.getString("logo"));
                galleryItem.setSummary(jsonObject.getString("summary"));

                galleryItems.add(galleryItem);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return galleryItems;

    }

}
