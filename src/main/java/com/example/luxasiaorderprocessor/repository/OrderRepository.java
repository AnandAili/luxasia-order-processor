package com.example.luxasiaorderprocessor.repository;

import com.example.luxasiaorderprocessor.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderDetail, Long> {

}
