package com.ksubaka;

/**
 * Created by Hari Rao on 06/01/17.
 */
public enum  PlaceHolder {
    QUERY("query"),
    TERM("term");

    private String value;

    PlaceHolder(String query){
        this.value = query;
    }

    public String getValue() {
        return value;
    }
}
