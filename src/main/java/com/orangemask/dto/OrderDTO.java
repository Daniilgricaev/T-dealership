package com.orangemask.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public class OrderDTO {
    @NotBlank(message = "Car name cannot be empty")
    private String carName;
    @Positive(message = "Car price must be greater than 0")
    private int carPrice;
    @PositiveOrZero(message = "Down payment cannot be negative")
    private int downPayment;
    public OrderDTO(){}
    public OrderDTO(String carName, int carPrice, int downPayment){
        this.carName = carName;
        this.carPrice = carPrice;
        this.downPayment = downPayment;
    }
    public void setCarName(String carName){
        this.carName = carName;
    }
    public String getCarName(){
        return carName;
    }
    public void setCarPrice(int carPrice){
        this.carPrice = carPrice;
    }
    public int getCarPrice() {
        return carPrice;
    }
    public void setDownPayment(int downPayment){
        this.downPayment = downPayment;
    }
    public int getDownPayment(){
        return downPayment;
    }
}
