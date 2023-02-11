package com.example.demo.service.imp;

import com.example.demo.entity.Quote;
import com.example.demo.repository.QuoteRepository;
import com.example.demo.service.QuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Random;

@Service
@Transactional
@RequiredArgsConstructor
public class QuoteServiceImpl  extends BaseServiceImpl<Quote, Long> implements QuoteService {

    private final QuoteRepository quoteRepository;

    @PostConstruct
    public void init() {
        repository = quoteRepository;
    }

    @Override
    public Quote findRandomQuote() {
        Random random = new Random();
        int max = quoteRepository.findAll().size();
        Long rn = Long.valueOf(random.nextInt(max - 1) + 1);
        return quoteRepository.findById(rn).orElse(null);
    }

    @Override
    public List<Quote> findAllQuotesByUserId(Long userId) {
        return quoteRepository.findQuotesByUserId(userId);
    }

    @Override
    public List<Quote> findAllQuotesOnTop10() {
        return quoteRepository.findAllQuotesOnTop10();
    }

    @Override
    public List<Quote> findAllQuotesOnFlop10() {
        return quoteRepository.findAllQuotesOnFlop10();
    }

    @Override
    public void upVote(Long id) {
        Quote quote = quoteRepository.findById(id).orElse(null);
        quote.setVote(quote.getVote()+1);
        repository.save(quote);
    }

    @Override
    public void downVote(Long id) {
        Quote quote = quoteRepository.findById(id).orElse(null);
        quote.setVote(quote.getVote()-1);
        repository.save(quote);
    }
}
