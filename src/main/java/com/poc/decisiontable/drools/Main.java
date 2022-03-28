package com.poc.decisiontable.drools;

import com.poc.decisiontable.drools.model.Employee;
import com.poc.decisiontable.drools.model.RelationshipType;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.*;
import org.drools.decisiontable.InputType;
import org.drools.decisiontable.SpreadsheetCompiler;
import org.drools.io.Resource;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatelessKnowledgeSession;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws Exception {

        KnowledgeBase knowledgeBase = createKnowledgeBaseFromSpreadsheet();
        StatelessKnowledgeSession session = knowledgeBase.newStatelessKnowledgeSession();

        Employee employee = new Employee(RelationshipType.CONTRACTOR, 3, false);
        session.execute(employee);
        System.out.println(employee.isAnnualBonus() ? "Provide an Annual Bonus" : "No bonus this year");

        employee = new Employee(RelationshipType.EMPLOYEE, 3, false);
        session.execute(employee);
        System.out.println(employee.isAnnualBonus() ? "Provide an Annual Bonus" : "No bonus this year");
    }

    private static KnowledgeBase createKnowledgeBaseFromSpreadsheet()
            throws Exception {

        final Resource classPathResource = ResourceFactory.newClassPathResource("drools/rules/AnnualBonus.csv");
        /* for the trace purpose only */
        traceTheRule(classPathResource);

        DecisionTableConfiguration dtConf = KnowledgeBuilderFactory.newDecisionTableConfiguration();
        dtConf.setInputType(DecisionTableInputType.CSV);

        KnowledgeBuilder knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        knowledgeBuilder.add(classPathResource, ResourceType.DTABLE, dtConf);

        if (knowledgeBuilder.hasErrors()) {
            throw new RuntimeException(knowledgeBuilder.getErrors().toString());
        }

        KnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
        knowledgeBase.addKnowledgePackages(knowledgeBuilder.getKnowledgePackages());

        return knowledgeBase;
    }

    private static void traceTheRule(Resource classPathResource) throws IOException {

        SpreadsheetCompiler compiler = new SpreadsheetCompiler();
        String source = compiler.compile(classPathResource.getInputStream(), InputType.CSV);
        System.out.println(source);
    }
}
