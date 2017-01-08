package com.handlers;

import com.ksubaka.API;
import com.ksubaka.PlaceHolder;
import com.services.ApiCallService;
import com.sun.jersey.api.client.ClientResponse;
import org.apache.log4j.Logger;

import java.util.Map;

/**
 * Created by Hari Rao on 07/01/17.
 */
public abstract class AbstractQueryHandler implements InputQueryHandler {

    private static Logger logger = Logger.getLogger(AbstractQueryHandler.class);

    private ApiCallService apiCallService;

    private Map<String,Map<String,String>> apiParameters;

    private Map<String,AbstractQueryHandler> handlers;

    /**
     *
     * @param movieMusic
     * @param api
     * @throws Exception
     * Below method is passed on to the individual handlers to implement.
     */

    abstract public void handle(String movieMusic, API api) throws Exception ;

    /**
     *
     * @param movieMusic
     * @param placeHolder
     * @param api
     * @return
     * @throws Exception
     * Below method is called in individual handler implementations. It takes the valid user request and
     * returns ${@link ClientResponse} containing the response from the given valid api name
     */

    public ClientResponse parse(String movieMusic, PlaceHolder placeHolder, API api) throws Exception{

        Map<String,String> apiParametersMap = apiParameters.get(api.getApi());

        if(apiParametersMap!=null){
            replaceThePlaceHolders(apiParametersMap,movieMusic,placeHolder);
        }

         return  apiCallService.callApi(api.getUrl(),apiParametersMap);

    }

    /**
     *
     * @param apiParametersMap
     * @param movieMusic
     * @param placeHolder
     * For a given api paramter map, place holders are populated in applicationConText.xml. The below method
     * replaces them by user input so that a query can be made to the api
     */
    public void replaceThePlaceHolders(Map<String, String> apiParametersMap,  String movieMusic, PlaceHolder placeHolder) {
        for(Map.Entry<String,String> entry: apiParametersMap.entrySet() ){
            if(entry.getKey().equals(placeHolder.getValue())){
                entry.setValue(movieMusic);
            }
        }

    }

    public ApiCallService getApiCallService() {
        return apiCallService;
    }

    public Map<String, Map<String, String>> getApiParameters() {
        return apiParameters;
    }

    /**
     *
     * @param type
     * @return Based on the type this method returns either ${@link InputQueryMovieHandlerImpl}
     * or ${@link InputQueryMusicHandler} to handle the input from the user.
     */

    public AbstractQueryHandler getHandler(String type,String apiName){
        AbstractQueryHandler queryHandler = getHandlers().get(type);
        logger.info("Returned handler " + queryHandler + " for " + "the given api " + apiName);
        return queryHandler;
    }

    public void setApiParameters(Map<String, Map<String, String>> apiParameters) {
        this.apiParameters = apiParameters;
    }

    public void setApiCallService(ApiCallService apiCallService) {
        this.apiCallService = apiCallService;
    }

    public void setHandlers(Map<String, AbstractQueryHandler> handlers) {
        this.handlers = handlers;
    }

    public Map<String, AbstractQueryHandler> getHandlers() {

        return handlers;
    }

}
