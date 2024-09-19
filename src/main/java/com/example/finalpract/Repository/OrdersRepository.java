package com.example.finalpract.Repository;

import com.example.finalpract.Models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
