package com.jags;

import com.jags.model.Address;
import com.jags.validator.Rule;
import com.jags.validator.RuleParser;
import com.jags.validator.ValidationFactory;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by JParvathaneni on 2/1/16.
 */
public class RuleParserTest {

    RuleParser ruleParser;
    ValidationFactory validationFactory;

    @Before
    public void setUp() throws Exception {
        ruleParser = new RuleParser("/Users/JParvathaneni/Development/AddressBook/src/test/resources/address-validation-rules.xml");
        validationFactory = ValidationFactory.getInstance(ruleParser);
    }

    @Test
    public void testLoadAddressRules(){
        List<Rule> rules = ruleParser.getRules("com.jags.model.Address");
        Assert.assertEquals("Should load all rules for address", 4, rules.size());
        Address a = new Address();
        List<String> errors = validationFactory.validate(a);
        System.out.println(errors);
    }

    @Test
    public void testAddAddressValues(){
        List<Rule> addresses = ruleParser.getRules("com.jags.model.Address");
        Assert.assertEquals("Address value", 9, addresses.size());
        Address address = new Address();
        System.out.println(address.toString());

    }
}
