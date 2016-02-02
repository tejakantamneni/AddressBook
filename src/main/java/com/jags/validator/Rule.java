package com.jags.validator;

/**
 * Created by JParvathaneni on 2/1/16.
 */
public class Rule {
    String name, fieldName, ruleType, errorMessage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getRuleType() {
        return ruleType;
    }

    public void setRuleType(String ruleType) {
        this.ruleType = ruleType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "Rule{" +
                "name='" + name + '\'' +
                ", fieldName='" + fieldName + '\'' +
                ", ruleType='" + ruleType + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
