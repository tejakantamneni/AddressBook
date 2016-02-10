package com.jags.validator;

import com.jags.model.Address;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by JParvathaneni on 2/1/16.
 */
public class RuleParser {

    static final Log LOG = LogFactory.getLog(RuleParser.class);

    Map<String, List<Rule>> ruleSet;

    public RuleParser(String... ruleFiles) throws Exception{
        if(ruleFiles != null && ruleFiles.length > 0){
            ruleSet = new HashMap<>();
            for(String file : ruleFiles){
                parseRuleFile(ruleSet, file);
            }
        }
    }

    public List<Rule> getRules(String className){
        if(ruleSet == null || !ruleSet.containsKey(className)) return  null;
        return ruleSet.get(className);
    }

    public List<Rule> getRules(Class className){
        if(ruleSet == null || !ruleSet.containsKey(className.getCanonicalName())) return  null;
        return ruleSet.get(className.getCanonicalName());
    }

    private void parseRuleFile(Map<String, List<Rule>> ruleSet, String file) throws Exception {
        File inputFile = new File(file);
        List<Rule> validationRules = new ArrayList<>();
        LOG.info("parsing file - " + inputFile.getAbsolutePath());
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document validationDocument = dBuilder.parse(inputFile);
        validationDocument.getDocumentElement().normalize();
        String valiationClassName = validationDocument.getDocumentElement().getAttribute("class");
        LOG.debug("parsing rules for validation class [" + valiationClassName + "]");

        NodeList ruleNodes = validationDocument.getElementsByTagName("rule");
        for(int i = 0; i < ruleNodes.getLength(); i ++){
            Rule rule = new Rule();
            Element ruleElement = (Element) ruleNodes.item(i);
            rule.setName(ruleElement.getAttribute("name"));
            Element fieldNode = (Element) ruleElement.getElementsByTagName("field").item(0);
            rule.setFieldName((fieldNode.getElementsByTagName("name").item(0)).getTextContent());
            rule.setRuleType((fieldNode.getElementsByTagName("rule-type").item(0)).getTextContent());
            rule.setErrorMessage((fieldNode.getElementsByTagName("error-message").item(0)).getTextContent());
            validationRules.add(rule);
        }

        ruleSet.put(valiationClassName, validationRules);
    }

}

