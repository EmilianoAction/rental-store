package com.actionict.inventory.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "picture")
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "picture_seq")
    @SequenceGenerator(name = "picture_seq", sequenceName = "picture_picture_id_seq", allocationSize = 1)
    @Column(name = "picture_id", nullable = false)
    private Integer id;

    @JoinColumn(name = "film_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Film film;

    @Column(name = "order_number")
    private Integer orderNumber;
}