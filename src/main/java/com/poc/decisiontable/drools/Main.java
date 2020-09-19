package com.poc.decisiontable.drools;

import com.poc.decisiontable.drools.model.Employee;
import com.poc.decisiontable.drools.model.RelationshipType;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.*;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatelessKnowledgeSession;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.ReleaseId;
import org.kie.api.io.Resource;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class Main {

    public static void main(String[] args) throws Exception {

        KnowledgeBase knowledgeBase = createKnowledgeBaseFromSpreadsheet();
        StatelessKnowledgeSession session = knowledgeBase.newStatelessKnowledgeSession();

        Employee employee = new Employee(RelationshipType.CONTRACTOR, 3);
        session.execute(employee);
        System.out.println(employee.isAnnualBonus() ? "Provide an Annual Bonus" : "No bonus this year");

        employee = new Employee(RelationshipType.EMPLOYEE, 3);
        session.execute(employee);
        System.out.println(employee.isAnnualBonus() ? "Provide an Annual Bonus" : "No bonus this year");
    }

    private static KnowledgeBase createKnowledgeBaseFromSpreadsheet()
            throws Exception {

        DecisionTableConfiguration dtConf = KnowledgeBuilderFactory
                .newDecisionTableConfiguration();
        dtConf.setInputType(DecisionTableInputType.XLS);

        KnowledgeBuilder knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        knowledgeBuilder.add(ResourceFactory.newClassPathResource("drools/rules/AnnualBonus.xls"), ResourceType.DTABLE, dtConf);

        if (knowledgeBuilder.hasErrors()) {
            throw new RuntimeException(knowledgeBuilder.getErrors().toString());
        }

        KnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
        knowledgeBase.addKnowledgePackages(knowledgeBuilder
                .getKnowledgePackages());
        return knowledgeBase;
    }
}
