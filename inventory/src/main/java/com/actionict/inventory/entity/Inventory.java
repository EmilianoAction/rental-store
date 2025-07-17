package com.actionict.inventory.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "inventory")
public class Inventory {

    @Id
    @Column(name = "inventory_id", nullable = false)
    private Integer id;

    @JoinColumn(name = "film_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Film film;

    //@JoinColumn(name = "store.store_id")
    //@ManyToOne(fetch = FetchType.EAGER)
    private Integer storeId;
}