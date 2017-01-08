package com.handlers;

import com.ksubaka.API;
import com.ksubaka.PlaceHolder;
import com.models.ImdbMovie;
import com.models.TheMovieDbApiResponse;
import com.sun.jersey.api.client.ClientResponse;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by Hari Rao on 06/01/17.
 */
public class InputQueryMovieHandlerImpl extends AbstractQueryHandler {



    private HelperApiHandler helperApiHandler;


    public void handle(String movie, API api) throws Exception{

        ClientResponse clientResponse =  parse(movie, PlaceHolder.QUERY, api);

        getApiCallService().parseApiResponse(clientResponse);

        TheMovieDbApiResponse theMovieDbApiResponse= getApiCallService()
                                  .extractApiResponse(clientResponse,TheMovieDbApiResponse.class);


        List<ImdbMovie> imdbMovies = helperApiHandler.callHelperApi(theMovieDbApiResponse.getResults());

        printTheResult(imdbMovies, movie);

    }

    private void printTheResult(List<ImdbMovie> imdbMovies, String movie) {

        if(StringUtils.isEmpty(imdbMovies) || imdbMovies.size()==0){
            System.out.println("No details found for " + movie);
        }else {
            for (ImdbMovie imdbMovie : imdbMovies) {
                 StringBuilder finalOutput = new StringBuilder();

                if(!StringUtils.isEmpty(imdbMovie.getTitle())){
                    finalOutput.append("Movie Name :- ");
                    finalOutput.append(imdbMovie.getTitle());
                }

                if(!StringUtils.isEmpty(imdbMovie.getYear())){
                    finalOutput.append(" Year of release :- ");
                    finalOutput.append(imdbMovie.getYear());
                }

                if(!StringUtils.isEmpty(imdbMovie.getDirector())){
                    finalOutput.append(" Directed by ");
                    finalOutput.append(imdbMovie.getDirector());
                }

                 if(!StringUtils.isEmpty(finalOutput.toString())){
                      System.out.println(finalOutput);
                 }
                }
            }
    }



    public void setHelperApiHandler(HelperApiHandler helperApiHandler) {
        this.helperApiHandler = helperApiHandler;
    }
}
