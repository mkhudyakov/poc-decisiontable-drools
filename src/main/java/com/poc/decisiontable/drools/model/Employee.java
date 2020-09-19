package com.poc.decisiontable.drools.model;

public class Employee {

    private RelationshipType relationshipType;
    private int years;
    private boolean topPerformer;

    private boolean annualBonus;

    public Employee() {
    }

    public Employee(RelationshipType relationshipType, int years, boolean topPerformer) {
        this.relationshipType = relationshipType;
        this.years = years;
        this.topPerformer = topPerformer;
    }

    public RelationshipType getRelationshipType() {
        return relationshipType;
    }

    public void setRelationshipType(RelationshipType relationshipType) {
        this.relationshipType = relationshipType;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public boolean isAnnualBonus() {
        return annualBonus;
    }

    public void setAnnualBonus(boolean annualBonus) {
        this.annualBonus = annualBonus;
    }

    public boolean isTopPerformer() {
        return topPerformer;
    }

    public void setTopPerformer(boolean topPerformer) {
        this.topPerformer = topPerformer;
    }
}
