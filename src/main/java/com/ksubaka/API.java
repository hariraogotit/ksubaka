package com.ksubaka;





/**
 * Created by Hari Rao on 05/01/17.
 */
public enum API {

    THEMOVIEDBAPI("themoviedbapi","http://api.themoviedb.org/3/search/movie","1"),
    ITUNES("itunes","https://itunes.apple.com/search","2");

    private String api;

    private String url;

    private String type;

    API(String api, String url,String type){

        this.api = api;
        this.url = url;
        this.type= type;

    }

    public String getApi() {
        return api;
    }

    public String getUrl() {
        return url;
    }

    public String getType() {
        return type;
    }

    public static API getApi(String apiName){
        for(API api: API.values()){
            if(api.getApi().equals(apiName)){
                return api;
            }
        }
        return null;
    }
}
