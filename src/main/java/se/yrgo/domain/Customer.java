package se.yrgo.domain;

import javax.persistence.*;
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    
}
