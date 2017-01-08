package com.ksubaka;

import com.handlers.AbstractQueryHandler;
import com.validators.LoadInputQueryValidator;
import com.validators.LoadInputQueryValidatorImpl;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by Hari Rao on 04/01/17.
 */
public class LoadInputQuery {

    private static Logger logger = Logger.getLogger(LoadInputQuery.class);

    public static void main(String args[]) throws Exception{

       try {

           populateUsage();

           LoadInputQueryValidator loadInputQueryValidator = new LoadInputQueryValidatorImpl();

           String apiName = System.getProperty("api");
           String movieMusic = System.getProperty("movie");


           loadInputQueryValidator.validate(apiName);

           processInput(apiName, movieMusic);

           System.exit(0);

       }catch(Exception ex){
           logger.error("Exception occurred ",ex);
           throw ex;
       }
    }

    /**
     * This method prints out the usage of this app which will give a clear picture to the user
     * when it comes to what input to give
     */

    private static void populateUsage(){
        StringBuilder usageMessage = new StringBuilder();
        usageMessage.append("Usage :-")
                    .append("-Dapi=")
                    .append(Arrays.stream(API.values()).map(api -> api.getApi()).collect(Collectors.toList()))
                    .append("-Dmovie=[name Of the Movie, Music]")
                    .append("\n")
                    .append("...Example....")
                    .append("\n")
                    .append("java -jar -Dapi=themoviedbapi -Dmovie=\"Live At Wimbley\" ksubaka-1.0-SNAPSHOT.jar")
                    .append("\n");
        System.out.println(usageMessage);
    }

    private static void processInput(String apiName, String movieMusic) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml");

        AbstractQueryHandler abstractQueryHandler =
                (AbstractQueryHandler) context.getBean("inputQueryMovieHandler");

        API api = API.getApi(apiName);

        abstractQueryHandler.getHandler(api.getType(),apiName)
                            .handle(movieMusic, api);
    }

}
