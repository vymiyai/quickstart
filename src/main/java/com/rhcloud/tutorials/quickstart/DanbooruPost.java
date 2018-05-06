package com.rhcloud.tutorials.quickstart;

public class DanbooruPost {
    private Integer id;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "http://danbooru.donmai.us/posts/" + this.getId();
    }
}
