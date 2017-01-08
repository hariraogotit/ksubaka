package com.handlers;

import com.ksubaka.API;
import com.ksubaka.PlaceHolder;
import com.sun.jersey.api.client.ClientResponse;

/**
 * Created by Hari Rao on 06/01/17.
 */

/**
 *
 * Methods in this interface should be implemented by the handlers
 * that will handle the request from user
 */
public interface InputQueryHandler {

    public ClientResponse parse(String movieMusic, PlaceHolder placeHolder, API api) throws Exception;
    public void handle(String movieMusic, API api) throws Exception;
}
