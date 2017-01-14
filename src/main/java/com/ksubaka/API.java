package com.ksubaka;


import com.Utilities.Constant;

/**
 * Created by Hari Rao on 05/01/17.
 */
public enum API {

    THEMOVIEDBAPI("themoviedbapi","http://api.themoviedb.org/3/search/movie", Constant.MOVIE_TYPE),
    ITUNES("itunes","https://itunes.apple.com/search",Constant.MUSIC_TYPE);

    private String name;

    private String url;

    private String type;

    API(String name, String url,String type){

        this.name = name;
        this.url = url;
        this.type= type;

    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getType() {
        return type;
    }

    public static API getApi(String apiName){
        for(API api: API.values()){
            if(api.getName().equals(apiName)){
                return api;
            }
        }
        return null;
    }
}
