package com.handlers;

import com.ksubaka.PlaceHolder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

import static org.junit.Assert.assertEquals;


/**
 * Created by Hari Rao on 07/01/17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext.xml" })
public class AbstractQueryHandlerTest {


    @Autowired
    private InputQueryMovieHandlerImpl inputQueryMovieHandler;

    @Test
    public void testReplaceThePlaceHolders(){

        Map<String,String> apiParamters = inputQueryMovieHandler.getApiParameters().get("itunes");

        inputQueryMovieHandler.replaceThePlaceHolders(apiParamters,"Live At Wembley", PlaceHolder.TERM);

        assertEquals("Live At Wembley",apiParamters.get(PlaceHolder.TERM.getValue()));


    }


}