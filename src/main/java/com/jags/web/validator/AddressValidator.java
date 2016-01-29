package com.jags.web.validator;

import com.jags.model.Address;
import org.apache.commons.lang.StringUtils;
import refutils.ReflectionHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JParvathaneni on 1/28/16.
 */
public class AddressValidator {

    public List<String> validate(Address address){
        List<String> errorList = new ArrayList<>();
        checkEmpty(errorList, address, "firstName", "First Name");
        checkEmpty(errorList, address, "lastName", "Last Name");
        checkEmpty(errorList, address, "line1", "Address Line 1");
        checkEmpty(errorList, address, "city", "City");
        checkEmpty(errorList, address, "state", "State");
        checkEmpty(errorList, address, "zip", "zip");

        return errorList;
    }

    public void checkEmpty(List<String> errors, Object object, String path, String fieldName){
        if(StringUtils.isEmpty((String) new ReflectionHelper(object).getField(path))) {
            errors.add(fieldName + " should not be empty");
        }
    }
}
