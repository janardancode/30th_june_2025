package com.ecom.kodewala.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.kodewala.request.OrderRequest;

@Repository
public interface PlaceOrderRepository extends JpaRepository<OrderRequest, Integer> {

}
