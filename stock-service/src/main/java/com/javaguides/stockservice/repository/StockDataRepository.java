package com.javaguides.stockservice.repository;

import com.javaguides.stockservice.entity.StockData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockDataRepository extends JpaRepository<StockData, Long> {
}
