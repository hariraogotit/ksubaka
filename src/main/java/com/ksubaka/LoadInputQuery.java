package com.ksubaka;

import com.Utilities.Constant;
import com.handlers.InputQueryHandler;
import com.handlers.InputQueryHandlerFactory;
import com.handlers.InputQueryMovieHandlerImpl;
import com.handlers.InputQueryMusicHandlerImpl;
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
           String movie = System.getProperty("movie");
           String music = System.getProperty("music");

           loadInputQueryValidator.validate(apiName, movie, music);

           processInput(apiName, movie, music);

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
                    .append(Arrays.stream(API.values()).map(api -> api.getName()).collect(Collectors.toList()))
                    .append("-D")
                    .append("[")
                    .append(Constant.MOVIE_TYPE)
                    .append("||")
                    .append(Constant.MUSIC_TYPE)
                    .append("]")
                    .append("=movie or music name")
                    .append("\n\n")
                    .append("**************************EXAMPLES******************************************")
                    .append("\n")
                    .append("java -jar -Dapi=itunes -Dmusic=\"Live At Wimbley\" ksubaka-1.0-SNAPSHOT.jar")
                    .append("\n")
                    .append("java -jar -Dapi=themoviedbapi -Dmovie=TED ksubaka-1.0-SNAPSHOT.jar")
                    .append("\n")
                    .append("***************************************************************************")
                    .append("\n");
        System.out.println(usageMessage);
    }

    private static void processInput(String apiName, String movie, String music) throws Exception {

        ApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml");

        InputQueryHandlerFactory inputQueryHandlerFactory =
                (InputQueryHandlerFactory) context.getBean("inputQueryHandlerFactory");

        API api = API.getApi(apiName);

        InputQueryHandler inputQueryHandler = inputQueryHandlerFactory.getHandler(api);

           if(movie!=null){
               inputQueryHandler.handle(movie, api);
           }

           if(music!=null){
               inputQueryHandler.handle(music,api);
           }
    }

}
