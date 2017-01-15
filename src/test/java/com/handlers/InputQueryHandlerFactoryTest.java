package com.handlers;

import com.ksubaka.API;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;

/**
 * Created by Hari Rao on 14/01/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext.xml" })
public class InputQueryHandlerFactoryTest {

    @Autowired
    private InputQueryHandlerFactory inputQueryHandlerFactory;

    @Test
    public void testGetHandlerForNull(){
          assertNull(inputQueryHandlerFactory.getHandler(null));
    }

    @Test
    public void testGetHandlerForMovieApi(){
        InputQueryHandler inputQueryHandler = inputQueryHandlerFactory.getHandler(API.THEMOVIEDBAPI);
        assertTrue(inputQueryHandler instanceof InputQueryMovieHandlerImpl);
    }

    @Test
    public void testGetHandlerForMusciApi(){
        InputQueryHandler inputQueryHandler = inputQueryHandlerFactory.getHandler(API.ITUNES);
        assertTrue(inputQueryHandler instanceof InputQueryMusicHandlerImpl);
    }

}