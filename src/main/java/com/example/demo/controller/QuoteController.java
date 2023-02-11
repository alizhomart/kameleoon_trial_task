package com.example.demo.controller;

import com.example.demo.entity.Quote;
import com.example.demo.service.QuoteService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/quote")
@RequiredArgsConstructor
public class QuoteController {

    private final QuoteService quoteService;
    private final UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<Quote>> findAll(){
        return ResponseEntity.ok(quoteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quote> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(quoteService.findById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Quote> create(@RequestBody Quote quote) {
            return ResponseEntity.ok(quoteService.saveOrUpdate(quote));
    }

    @PutMapping("/update")
    public ResponseEntity<Quote> update(@RequestBody Quote quote) {
        return ResponseEntity.ok(quoteService.saveOrUpdate(quote));
    }

    @DeleteMapping("/id")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        quoteService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/top10")
    public ResponseEntity<List<Quote>> findAllQuotesOnTop10(){
        return ResponseEntity.ok(quoteService.findAllQuotesOnTop10());
    }

    @GetMapping("/flop10")
    public ResponseEntity<List<Quote>> findAllQuotesOnFlop10(){
        return ResponseEntity.ok(quoteService.findAllQuotesOnFlop10());
    }

    @PutMapping("/upVote/{id}")
    public ResponseEntity<?> upVoteById(@PathVariable("id") Long id){
        quoteService.upVote(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/downVote/{id}")
    public ResponseEntity<?> downVoteById(@PathVariable("id") Long id){
        quoteService.downVote(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/randomQuote")
    public ResponseEntity<Quote> findRandomQuote(){
        return ResponseEntity.ok(quoteService.findRandomQuote());
    }

    @GetMapping("/findAllByUserId/{id}")
    public ResponseEntity<List<Quote>> findAllByUserId(@PathVariable Long id){
        if(userService.findById(id)==null){
            log.info("User with this ID not found" + id);
            return null;
        }

        return ResponseEntity.ok(quoteService.findAllQuotesByUserId(id));
    }
}
