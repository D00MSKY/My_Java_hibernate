package HW_1_1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

//використовуючи hibernate:
//        - створити табличку Word (id, value)
//- наповнити її
//- дістати всі value слів та запакувати в List .

public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();

        Metadata metadata = new MetadataSources(serviceRegistry)
                .addAnnotatedClass(Word.class)
                .getMetadataBuilder()
                .build();

        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();
        session.beginTransaction();



        session.save(new Word(1, "lorem"));
        session.save(new Word(2, "ipsum"));
        session.save(new Word(3, "dolor"));
        session.save(new Word(4, "sit"));
        session.save(new Word(5, "amet"));


        session.getTransaction().commit();

        List<Word> list = session.createQuery("select w from Word w").list();
        System.out.println(list);

        session.close();
        sessionFactory.close();

    }
}
