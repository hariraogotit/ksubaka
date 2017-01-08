package com.services;

import com.handlers.InputQueryMovieHandlerImpl;
import com.ksubaka.API;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by Hari Rao on 07/01/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext.xml" })
public class ApiCallServiceImplTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private InputQueryMovieHandlerImpl inputQueryMovieHandler;

    @Autowired
    private ApiCallServiceImpl apiCallService;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testBuildQueryParm(){
        WebResource webResource= Client.create().resource(API.THEMOVIEDBAPI.getUrl());
            assertEquals("http://api.themoviedb.org/3/search/movie?api_key=random_key",
                          apiCallService.buildQueryParm(webResource,"api_key","random_key").toString());
    }

    @Test
    public void testParseApiResponse() throws Exception{

        Map<String,String> theMovieDbApiParameters = inputQueryMovieHandler.getApiParameters()
                                                                           .get(API.THEMOVIEDBAPI.getApi());
        for(Map.Entry<String,String> entry: theMovieDbApiParameters.entrySet()){
              if("api_key".equals(entry.getKey())){
                  entry.setValue("INSERT_CORRUPTED_KEY");
              }
        }
        ClientResponse clientResponse = inputQueryMovieHandler.getApiCallService()
                                                       .callApi(API.THEMOVIEDBAPI.getUrl(),theMovieDbApiParameters);
        thrown.expect(APIException.class);
        thrown.expectMessage("Error calling the api " + clientResponse.getStatus());
        inputQueryMovieHandler.getApiCallService().parseApiResponse(clientResponse);

    }



}