package models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Passport implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    private String series;

    public Passport(String series) {
        this.series = series;
    }
}
