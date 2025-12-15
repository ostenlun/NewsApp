package com.codermages.NewsApp.domain;

import java.util.ArrayList;

@lombok.Data
public class Article {
    public String article_id;
    public String link;
    public String title;
    public String description;
    public String content;
    public ArrayList<String> keywords;
    public ArrayList<String> creator;
    public String language;
    public ArrayList<String> country;
    public ArrayList<String> category;
    public String datatype;
    public String pubDate;
    public String pubDateTZ;
    public String image_url;
    public Object video_url;
    public String source_id;
    public String source_name;
    public int source_priority;
    public String source_url;
    public String source_icon;
    public String sentiment;
    public String sentiment_stats;
    public String ai_tag;
    public String ai_region;
    public String ai_org;
    public String ai_summary;
    public boolean duplicate;

    public Article(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
