package com.project.nextstep.entity.missellinous;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Dimension {
    private double height;
    private double width;
    private double length;
    private double area;

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area == 0 ? width * length : area;
    }
}
