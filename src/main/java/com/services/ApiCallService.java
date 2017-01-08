package com.services;

import com.models.ImdbMovie;
import com.models.TheMovieDbMovie;
import com.sun.jersey.api.client.ClientResponse;
import java.util.List;
import java.util.Map;


/**
 * Created by Hari Rao on 05/01/17.
 */

/**
 *
 * Methods in this interface should be implemented by the api call services
 * that will call the api and parse the response
 */
public interface ApiCallService {
       public ClientResponse callApi(String url, Map<String,String> parameter);
       public void parseApiResponse(ClientResponse clientResponse) throws APIException;
       public <T> T extractApiResponse(ClientResponse clientResponse,Class<T> t) throws Exception;
       public List<ImdbMovie> callHelperApi(List<TheMovieDbMovie>theMovieDbMovies, String url, String parameter) throws Exception;

}
