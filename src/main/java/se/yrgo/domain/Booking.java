package se.yrgo.domain;

import javax.persistence.*;
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
}
