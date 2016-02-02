package com.jags.validator;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import refutils.ReflectionHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JParvathaneni on 2/1/16.
 */
public class ValidationFactory {
    static final Log LOG = LogFactory.getLog(RuleParser.class);

    static ValidationFactory validationFactory;
    RuleParser ruleParser;

    private ValidationFactory() {

    }

    private ValidationFactory(RuleParser ruleParser) {
        this.ruleParser = ruleParser;
    }

    public static ValidationFactory getInstance(RuleParser ruleParser) {
        if (validationFactory == null) {
            validationFactory = new ValidationFactory(ruleParser);
        }
        return validationFactory;
    }

    public List<String> validate(Object obj){
        List<String> errors = new ArrayList<>();
        LOG.debug("validation object of class [" + obj.getClass() + "]");
        List<Rule> ruleList = ruleParser.getRules(obj.getClass());
        for (Rule rule : ruleList) {
            String error = applyRule(rule, obj);
            if(StringUtils.isNotBlank(error)){
                errors.add(error);
            }
        }
        return errors;
    }

    private String applyRule(Rule rule, Object obj) {
        if(StringUtils.equalsIgnoreCase("requiredAndShouldBeAlphaCharactersOnly", rule.getRuleType())){
            if(StringUtils.isEmpty((String) new ReflectionHelper(obj).getField(rule.getFieldName()))){
                return rule.getErrorMessage();
            }
        }
        return null;
    }

}
