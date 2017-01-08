package com.validators;


import com.services.APIException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Created by Hari Rao on 07/01/17.
 */
public class LoadInputQueryValidatorImplTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testValidateApiNameTrue(){
        LoadInputQueryValidatorImpl loadInputQueryValidator = new LoadInputQueryValidatorImpl();
           assertTrue(loadInputQueryValidator.validateApiName("themoviedbapi"));
    }

    @Test
    public void testValidateApiNameFalse(){
        LoadInputQueryValidatorImpl loadInputQueryValidator = new LoadInputQueryValidatorImpl();
        assertFalse(loadInputQueryValidator.validateApiName("Randome"));
    }

    @Test
    public void testValidateForException() throws APIException {
        LoadInputQueryValidatorImpl loadInputQueryValidator = new LoadInputQueryValidatorImpl();
        thrown.expect(APIException.class);
        thrown.expectMessage("api Name is not valid");
        loadInputQueryValidator.validate("Random");

    }

}