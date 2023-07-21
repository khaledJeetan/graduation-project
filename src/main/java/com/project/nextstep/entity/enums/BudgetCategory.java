package com.project.nextstep.entity.enums;


public enum BudgetCategory {
    HOUSING("Housing"),
    TRANSPORTATION("Transportation"),
    FOOD("Food"),
    DEBT_PAYMENTS("Debt Payments"),
    ENTERTAINMENT("Entertainment"),
    HEALTH_CARE("Health Care"),
    EDUCATION("Education"),
    SAVINGS("Savings"),
    PERSONAL_CARE("Personal Care"),
    UTILITIES("Utilities"),
    CONSTRUCTION("Construction"),
    FINISHING("Finishing"),
    LANDSCAPING("Landscaping"),
    FURNITURE("Furniture"),
    MOVING("Moving"),
    MISCELLANEOUS("Miscellaneous");

    private final String category;

    BudgetCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}
