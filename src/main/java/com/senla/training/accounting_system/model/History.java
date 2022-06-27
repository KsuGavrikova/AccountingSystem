package com.senla.training.accountingSystem.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "history")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Long id;

    @Column(name = "date_start")
    private LocalDate dateStart;

    @Column(name = "date_to_return")
    private LocalDate dateToReturn;

    @Column(name = "date_end")
    private LocalDate dateEnd;

    @ManyToOne()
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
