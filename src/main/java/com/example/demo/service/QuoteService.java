package com.example.demo.service;

import com.example.demo.entity.Quote;

import java.util.List;

public interface QuoteService extends BaseService<Quote, Long> {

    Quote findRandomQuote();

    List<Quote> findAllQuotesByUserId(Long id);

    List<Quote> findAllQuotesOnTop10();

    List<Quote> findAllQuotesOnFlop10();

    void upVote(Long id);

    void downVote(Long id);
}
