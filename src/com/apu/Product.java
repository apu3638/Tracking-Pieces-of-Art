package com.apu;

public class Product {
    private final ProductType productType;
    private final String name;
    private final String startingDate;
    private final String endingDate;
    private final int consumptionTime;
    private final double rating;
    private final int totalConsumptionDays;
    private boolean isDeleted =false;

    public Product(ProductType productType, String name,
                   String startingDate, String endingDate,
                   int consumptionTime, double rating,
                   int totalConsumptionDays
                   ) {
        this.productType = productType;
        this.name = name;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.consumptionTime = consumptionTime;
        this.rating = rating;
        this.totalConsumptionDays = totalConsumptionDays;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public ProductType getProductType() {
        return productType;
    }

    public String getName() {
        return name;
    }

    public String getStartingDate() {
        return startingDate;
    }

    public String getEndingDate() {
        return endingDate;
    }

    public int getConsumptionTime() {
        return consumptionTime;
    }

    public double getRating() {
        return rating;
    }

    public int getTotalConsumptionDays() {
        return totalConsumptionDays;
    }
}
