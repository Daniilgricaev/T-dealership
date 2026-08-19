package com.orangemask.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;


import java.time.LocalDateTime;

@Entity
@Table(name = "cars")
public class CarOrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "car_name",nullable = false)
    private String carName;

    @Column(name = "car_price")
    private int carPrice;

    @Column(name = "total_debt")
    private double totalDebt;

    @Column(name = "monthly_payment")
    private double monthlyPayment;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public CarOrderEntity(){}

    public CarOrderEntity(String carName, int carPrice, double totalDebt, double monthlyPayment){
        this.carName = carName;
        this.carPrice = carPrice;
        this.totalDebt = totalDebt;
        this.monthlyPayment = monthlyPayment;
    }
    public String getCarName(){
        return carName;
    }
    public void setCarName(String carName){
        this.carName = carName;
    }
    public int getCarPrice(){
        return carPrice;
    }
    public void setCarPrice(int carPrice){
        this.carPrice = carPrice;
    }
    public double getTotalDebt(){
        return totalDebt;
    }
    public void setTotalDebt(double totalDebt){
        this.totalDebt = totalDebt;
    }
    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }
}
