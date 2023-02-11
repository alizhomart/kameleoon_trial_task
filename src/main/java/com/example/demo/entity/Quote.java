package com.example.demo.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "quote")
@Data
@AllArgsConstructor
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

    @CreationTimestamp
    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "vote")
    private int vote;

    @Column(name = "user_id")
    private Long userId ;

    public Quote(){
        userId = 1L;
    }
}
