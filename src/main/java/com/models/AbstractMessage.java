package com.models;

/**
 * Created by Hari Rao on 06/01/17.
 */
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonInclude(JsonInclude.Include.NON_NULL)

public abstract class AbstractMessage {


    private String releaseDate;

    @JsonProperty("releaseDate")
    public String getReleaseDate() {
        return releaseDate;
    }

    @JsonProperty("releaseDate")
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

}
