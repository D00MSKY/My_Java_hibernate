package HW_2_1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Arrays;

//Car
//        id
//model,
//Type (ENUM)
//power,
//price,
//year
//
//        Owner
//id,
//name
//List<Car>
//        DriveLicense
//
//DriveLicense
//        id
//series
//
//За допомоги хібернейту реалізувати наступну структуру
//зв'язо unidirectional!!!

public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();

        Metadata metadata = new MetadataSources(serviceRegistry)
                .addAnnotatedClass(Car.class)
                .addAnnotatedClass(Owner.class)
                .addAnnotatedClass(DriveLicense.class)
                .getMetadataBuilder().build();

        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();
        session.beginTransaction();


        session.save(new Owner("Vasya",
                Arrays.asList(new Car(
                        "BMW",
                        Type.SUV,
                        300,
                        30000,
                        2020),
                        new Car(
                                "Hummer",
                                Type.LIMOUSINE,
                                250,
                                45000,
                                2021
                        )
                ), new DriveLicense("123456789")
                )
        );
        session.save(new Owner("Petya",
                Arrays.asList(new Car(
                        "Mazda",
                        Type.SEDAN,
                        220,
                        20000,
                        2017
                )), new DriveLicense("987654321")
                )
        );
        session.getTransaction().commit();
        session.createQuery("select o from Owner o", Owner.class)
                        .getResultList()
                                .forEach(owner -> System.out.println(owner.getDriveLicense().getSeries()));
        session.createQuery("select o from Owner o", Owner.class)
                        .getResultList()
                                .forEach(owner -> System.out.println(owner.getCars()));
        session.close();
        sessionFactory.close();
    }
}
