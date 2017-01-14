package com.handlers;

import com.Utilities.Constant;
import com.ksubaka.API;

/**
 * Created by Hari Rao on 11/01/17.
 */


public class InputQueryHandlerFactory {

    private InputQueryMovieHandlerImpl inputQueryMovieHandler;

    private InputQueryMusicHandlerImpl inputQueryMusicHandler;

    /**
     *
     * @param api
     * @return Based on the type this class returns either ${@link InputQueryMovieHandlerImpl}
     * or ${@link InputQueryMusicHandlerImpl} to handle the input from the user.
     */

     public InputQueryHandler getHandler(API api){

         if(api==null){
             return null;
         }

         if(Constant.MUSIC_TYPE.equals(api.getType())){
             return inputQueryMusicHandler;
         }

         if(Constant.MOVIE_TYPE.equals(api.getType())){
             return inputQueryMovieHandler;
         }

         return null;
     }

    public void setInputQueryMovieHandler(InputQueryMovieHandlerImpl inputQueryMovieHandler) {
        this.inputQueryMovieHandler = inputQueryMovieHandler;
    }


    public void setInputQueryMusicHandler(InputQueryMusicHandlerImpl inputQueryMusicHandler) {
        this.inputQueryMusicHandler = inputQueryMusicHandler;
    }
}
