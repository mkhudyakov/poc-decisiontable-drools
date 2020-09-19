package com.poc.decisiontable.drools.model;

public class Employee {

    private RelationshipType relationshipType;
    private int years;

    private boolean annualBonus;

    public Employee() {
    }

    public Employee(RelationshipType relationshipType, int years) {
        this.relationshipType = relationshipType;
        this.years = years;
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
}
