package HW_1_2;


import HW_2_1.Type;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@Data
@Entity
@NoArgsConstructor

//Створити клас Car з полями:
//id
//        model,
//Type (ENUM)
//power,
//price,
//year.

public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String model;
    @Enumerated(EnumType.STRING)
    private Type type;
    private int power;
    private int price;
    private int year;

    public Car(String model, Type type, int power, int price, int year) {
        this.model = model;
        this.type = type;
        this.power = power;
        this.price = price;
        this.year = year;
    }

}
