package com.project.tsordat.rss_feed_aggregator.Model.Articles;


public class ArticleRequest {
    private String viewedArticles;

    public ArticleRequest(String viewedArticles) {
        this.viewedArticles = viewedArticles;
    }

    public String getViewedArticles() {
        return viewedArticles;
    }

    public void setViewedArticles(String viewedArticles) {
        this.viewedArticles = viewedArticles;
    }
}
