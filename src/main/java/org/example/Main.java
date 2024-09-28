
package org.example;

import models.Gender;
import models.Passport;
import models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();
        Metadata metadata = new MetadataSources(serviceRegistry)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Passport.class)
                .getMetadataBuilder()
                .build();

        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        User user = new User("Petya", Gender.MALE, Arrays.asList("java", "js", "html"), new Passport("123456"));
        User user2 = new User("Vasya", Gender.MALE, Arrays.asList("css", "sql", "mongo"), new Passport("456789"));
        User user3 = new User("Olya", Gender.FEMALE, Arrays.asList("java", "js", "html"), new Passport("789546"));
        session.save(user);
        session.save(user2);
        session.save(user3);

        session.getTransaction().commit();
        session.close();
        sessionFactory.close();

    }
}