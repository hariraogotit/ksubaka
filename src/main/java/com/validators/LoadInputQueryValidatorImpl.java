package com.validators;

import com.ksubaka.API;
import com.services.APIException;


/**
 * Created by Hari Rao on 05/01/17.
 */
public class LoadInputQueryValidatorImpl implements LoadInputQueryValidator {

    /**
     *
     * @param apiName
     * @throws APIException if the api name is not in ${@link API}
     */
    public void validate(String apiName) throws APIException  {
         if(!validateApiName(apiName)){
             throw new APIException("api Name is not valid");
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
}
