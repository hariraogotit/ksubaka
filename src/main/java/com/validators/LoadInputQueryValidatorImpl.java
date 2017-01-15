package com.validators;

import com.Utilities.Constant;
import com.ksubaka.API;
import com.services.APIException;
import org.springframework.util.StringUtils;


/**
 * Created by Hari Rao on 05/01/17.
 */
public class LoadInputQueryValidatorImpl implements LoadInputQueryValidator {

    /**
     *
     * @param apiName
     * @throws APIException if the api name is not in ${@link API}
     */
    public void validate(String apiName, String movie, String music) throws APIException  {
         if(!validateApiName(apiName)){
             throw new APIException("api Name is not valid");
         }

         if(!validateApiType(apiName,movie,music)){
             throw new APIException("api Type is not valid");
         }
    }


    /**
     *
     * @param apiName
     * @return boolean indicating whether a given api name is valid or not.
     */

    public boolean validateApiName(String apiName){
          if(API.getApi(apiName)!=null){
               return true;
             }
        return false;
    }


    public boolean validateApiType(String apiType, String movie, String music){
        API api = API.getApi(apiType);

        if( !StringUtils.isEmpty(movie) && !StringUtils.isEmpty(music) ){
            return false;
        }

        if(movie!=null){
            return api.getType().equals(Constant.MOVIE_TYPE);
        }

        if(music!=null){
            return api.getType().equals(Constant.MUSIC_TYPE);
        }

        return false;
    }

}
