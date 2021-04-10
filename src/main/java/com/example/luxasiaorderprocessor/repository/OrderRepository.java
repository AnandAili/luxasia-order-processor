package com.example.luxasiaorderprocessor.repository;

import com.example.luxasiaorderprocessor.module.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface OrderRepository extends JpaRepository<OrderDetail, Long> {

}
