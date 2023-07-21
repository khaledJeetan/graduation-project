package com.project.nextstep.entity.enums;

public enum HouseFinishingTask {
    ELECTRICAL_INSTALLATION(1),
    PLUMBING_INSTALLATION(1),
    DRYWALL_INSTALLATION(2),
    FLOORING(3),
    PAINTING(4),
    TRIM_AND_MOLDING(5),
    CABINETRY_AND_BUILT_INS(6),
    INTERIOR_DOORS_AND_HARDWARE(6),
    LIGHTING_FIXTURES(6),
    PLUMBING_FIXTURES(6);

    private int order;

    HouseFinishingTask(int order) {
        this.order = order;
    }

    public int getOrder() {
        return order;
    }
}

