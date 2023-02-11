package com.example.demo.repository;

import com.example.demo.entity.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface QuoteRepository extends JpaRepository<Quote,Long> {

    List<Quote> findQuotesByUserId(Long userId);

    @Query(nativeQuery = true, value = "select * from Quote order by vote desc limit 10")
    List<Quote> findAllQuotesOnTop10();

    @Query(nativeQuery = true, value = "select * from Quote order by vote asc limit 10")
    List<Quote> findAllQuotesOnFlop10();
}
