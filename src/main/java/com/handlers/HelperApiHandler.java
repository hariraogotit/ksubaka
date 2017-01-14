package com.handlers;

import com.Utilities.Constant;
import com.models.ImdbMovie;
import com.models.TheMovieDbMovie;
import com.services.ApiCallService;

import java.util.List;

/**
 * Created by Hari Rao on 08/01/17.
 */

/**
 * Since theMovieDbMovie API does not provide director name as on today, Imdb api is used
 * as the helper api to get all the movie details. Since Imdb app is not mature as on today to
 * return sequel etc TheMovieDBMovies app is used to fetch the title and based on the title all the
 * required info are retrieved from the helper api - Imdb
 */
public class HelperApiHandler {

    private ApiCallService apiCallService;


    public List<ImdbMovie> callHelperApi(List<TheMovieDbMovie> theMovieDbMovies) throws Exception{

       return apiCallService.callHelperApi(theMovieDbMovies, Constant.URL,Constant.PARAMETER);


    }

    public void setApiCallService(ApiCallService apiCallService) {
        this.apiCallService = apiCallService;
    }
}
