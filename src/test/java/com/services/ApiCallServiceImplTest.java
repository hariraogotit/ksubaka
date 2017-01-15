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

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by Hari Rao on 07/01/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext.xml" })
public class ApiCallServiceImplTest {

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
    public void testParseApiResponseToThrowException() throws Exception{
        ClientResponse clientResponse = mock(ClientResponse.class);
        Response.StatusType  statusType = mock(Response.StatusType.class);
        when(statusType.getFamily()).thenReturn(Response.Status.Family.SERVER_ERROR);
        when(clientResponse.getStatusInfo()).thenReturn(statusType);
        thrown.expect(APIException.class);
        thrown.expectMessage("Error calling the api " + clientResponse.getStatus());
        apiCallService.parseApiResponse(clientResponse);
    }
}