package com.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.models.ImdbMovie;
import com.models.TheMovieDbMovie;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.apache.log4j.Logger;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/**
 * Created by Hari Rao on 05/01/17.
 */
public class ApiCallServiceImpl implements ApiCallService{

    private static Logger logger = Logger.getLogger(ApiCallServiceImpl.class);

    /**
     *
     * @param url
     * @param parameter
     * @return
     * Calls the api with the given url and parameters in map
     */
   public ClientResponse callApi(String url, Map<String,String> parameter) {

       logger.info("Called api with the given details " +url+ " Parameters " + parameter  );

      WebResource webResource = Client.create().resource(url);

       for(Map.Entry<String,String> entry : parameter.entrySet()){
           webResource = buildQueryParm(webResource,entry.getKey(),entry.getValue());
       }
         return      webResource.getRequestBuilder()
               .type(MediaType.APPLICATION_JSON)
               .get(ClientResponse.class);
    }

    /**
     *
     * @param webResource
     * @param parameterName
     * @param parameterValue
     * @return Since dynamically the queryParm method is built based on the number of parameters to
     * the api
     */

    public WebResource buildQueryParm(WebResource webResource, String parameterName, String parameterValue){
        return   webResource.queryParam(parameterName,parameterValue);

    }

    /**
     *
     * @param clientResponse
     * @throws APIException
     * This method throws exception if the call to the api did not go well
     */

    public void parseApiResponse(ClientResponse clientResponse) throws APIException{
        logger.info("Response status " + clientResponse.getStatusInfo().getFamily() );

        if (clientResponse.getStatusInfo().getFamily() != Response.Status.Family.SUCCESSFUL) {
            throw new APIException("Error calling the api " + clientResponse.getStatus());
        }
    }

    /**
     *
     * @param clientResponse
     * @param t
     * @param <T>
     * @return
     * @throws Exception
     * This method takes clientResponse which is in JSON format and converts that to the given model
     */

    public <T> T extractApiResponse(ClientResponse clientResponse,Class<T> t) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(clientResponse.getEntity(String.class),t);
    }

    public List<ImdbMovie> callHelperApi(List<TheMovieDbMovie>theMovieDbMovies, String url, String parameter) throws Exception{

        logger.info("Called helper api with the given details " +url+ " Parameters " + parameter  );

        ExecutorService executorService = Executors.newCachedThreadPool();
        Set<Callable<ClientResponse>> callables = submitApiCall(theMovieDbMovies,url,parameter);

        return retrieveResult(executorService, callables);

    }

    /**
     *
     * @param executorService
     * @param callables
     * @return
     * @throws Exception
     * Collates the result of all the api calls made in parallel and returns a List of ${@link ImdbMovie}
     */

    private List<ImdbMovie> retrieveResult(ExecutorService executorService, Set<Callable<ClientResponse>> callables) throws Exception {
        List<Future<ClientResponse>> futures = executorService.invokeAll(callables);
        List<ImdbMovie> imdbMovies = new ArrayList<>();
        for(Future<ClientResponse> future: futures){
            ClientResponse futureResponse = future.get();
            parseApiResponse(futureResponse);
            imdbMovies.add(extractApiResponse(futureResponse,ImdbMovie.class));
        }
        executorService.shutdown();

        return imdbMovies;
    }

    /**
     *
     * @param theMovieDbMovies
     * @param url
     * @param inputParameter
     * @return
     * Parallel API calls are made for quicker response.
     */

    private Set<Callable<ClientResponse>> submitApiCall(List<TheMovieDbMovie> theMovieDbMovies,String url, String inputParameter) {
        Set<Callable<ClientResponse>> callables= new HashSet<>();
        for(TheMovieDbMovie theMovieDbMovie :theMovieDbMovies){
             callables.add(new Callable<ClientResponse>() {
                 @Override
                 public ClientResponse call() throws Exception {
                     Map<String,String> parameter = new LinkedHashMap<>();
                     parameter.put(inputParameter,theMovieDbMovie.getTitle());
                     return callApi(url,parameter);
                 }
             });
        }
        return callables;
    }


}
