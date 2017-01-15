package com.validators;


import com.ksubaka.API;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Created by Hari Rao on 07/01/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext.xml" })
public class LoadInputQueryValidatorImplTest {

    @Autowired
    LoadInputQueryValidatorImpl loadInputQueryValidator;

    public static final String TED = "TED";
    public static final String LIVE_AT_WEMBLEY = "Live At Wembley";
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testValidateApiNameTrue(){
           assertTrue(loadInputQueryValidator.validateApiName("themoviedbapi"));
    }

    @Test
    public void testValidateApiNameFalse(){

        assertFalse(loadInputQueryValidator.validateApiName("Randome"));
    }

   @Test
    public void testValidateApiTypeWithBothTheApiTypeSent(){
        assertFalse(loadInputQueryValidator
                .validateApiType(API.ITUNES.getName(), TED, LIVE_AT_WEMBLEY));
    }

    @Test
    public void testValidateApiTypeForMovieTypeForTrue(){
       assertTrue(loadInputQueryValidator
                .validateApiType(API.THEMOVIEDBAPI.getName(),TED,null));
    }

    @Test
    public void testValidateApiTypeForMovieTypeForFalse(){
       assertFalse(loadInputQueryValidator
                .validateApiType(API.THEMOVIEDBAPI.getName(),null,LIVE_AT_WEMBLEY));
    }

    @Test
    public void testValidateApiTypeForMusicTypeForTrue(){
       assertTrue(loadInputQueryValidator
                .validateApiType(API.ITUNES.getName(),null,LIVE_AT_WEMBLEY));
    }

    @Test
    public void testValidateApiTypeForMusicTypeForFalse(){
       assertFalse(loadInputQueryValidator
                .validateApiType(API.ITUNES.getName(),TED,null));
    }

}