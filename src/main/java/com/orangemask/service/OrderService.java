package com.orangemask.service;

import com.orangemask.dto.OrderDTO;
import com.orangemask.entity.CarOrderEntity;
import com.orangemask.repository.CarOrderRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    private static final double INTEREST_RATE =  1.2;
    private static final int LOAN_TERM_MONTHS = 36;
    private final CarOrderRepo carOrderRepo;
    public OrderService(CarOrderRepo carOrderRepo){
        this.carOrderRepo = carOrderRepo;
    }
    @Transactional
    public void processOrder(OrderDTO orderDTO){
        if(orderDTO.getDownPayment() > orderDTO.getCarPrice()){
            System.out.println("Reject");
            return;
        }
        if(orderDTO.getDownPayment() == orderDTO.getCarPrice()){
            System.out.println("With out credit");
            CarOrderEntity carOrderEntity = new CarOrderEntity(
                    orderDTO.getCarName(),
                    orderDTO.getCarPrice(),
                    0.0,
                    0.0
            );
            carOrderRepo.save(carOrderEntity);
        }else{
            int creditBody = orderDTO.getCarPrice() - orderDTO.getDownPayment();
            double totalDebt = creditBody * INTEREST_RATE;
            double monthlyPayment  = totalDebt / LOAN_TERM_MONTHS;
            CarOrderEntity carOrderEntity = new CarOrderEntity();
            carOrderEntity.setCarName(orderDTO.getCarName());
            carOrderEntity.setCarPrice(orderDTO.getCarPrice());
            carOrderEntity.setTotalDebt(totalDebt);
            carOrderEntity.setMonthlyPayment(monthlyPayment);
            carOrderRepo.save(carOrderEntity);

        }
    }
}
