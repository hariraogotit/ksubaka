package com.handlers;

import com.ksubaka.API;
import com.ksubaka.PlaceHolder;
import com.models.Itunes;
import com.models.ItunesApiResponse;
import com.sun.jersey.api.client.ClientResponse;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by Hari Rao on 07/01/17.
 */
public class InputQueryMusicHandler extends AbstractQueryHandler {

    public void handle( String music, API api) throws Exception{

        ClientResponse clientResponse =  parse(music, PlaceHolder.TERM, api);

        getApiCallService().parseApiResponse(clientResponse);

        ItunesApiResponse itunesApiResponses= getApiCallService()
                                             .extractApiResponse(clientResponse,ItunesApiResponse.class);

        printTheResult(itunesApiResponses.getResults(),  music);
    }

    private void printTheResult(List<Itunes> itunesApiResponses, String music) {
        if(StringUtils.isEmpty(itunesApiResponses) || itunesApiResponses.size()==0){
            System.out.println("No details found for " + music);
        }else {
            for (Itunes itunes : itunesApiResponses) {

                StringBuilder finalOutput = new StringBuilder();

                if(!StringUtils.isEmpty(itunes.getCollectionName())){
                    finalOutput.append("Music Name :- ");
                    finalOutput.append(itunes.getCollectionName());
                }

                if(!StringUtils.isEmpty(itunes.getReleaseDate())){
                    finalOutput.append(" Year of release :- ");
                    finalOutput.append(itunes.getReleaseDate().substring(0, 4));
                }

                if(!StringUtils.isEmpty(itunes.getArtistName())){
                    finalOutput.append(" Directed by ");
                    finalOutput.append(itunes.getArtistName());
                }

                if(!StringUtils.isEmpty(finalOutput.toString())){
                    System.out.println(finalOutput);
                }
            }
            }
    }



}
