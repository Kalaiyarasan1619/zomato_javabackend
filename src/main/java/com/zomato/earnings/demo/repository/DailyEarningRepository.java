package com.zomato.earnings.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.zomato.earnings.demo.model.DailyEarnings;

public interface DailyEarningRepository extends JpaRepository<DailyEarnings, Integer> {

    @Query("""
        SELECT DISTINCT d.otherType
        FROM DailyEarnings d
        WHERE d.otherType IS NOT NULL
    """)
    List<String> findDistinctOtherTypes();
}

