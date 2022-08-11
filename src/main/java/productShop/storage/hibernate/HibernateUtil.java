package productShop.storage.hibernate;

import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import productShop.entity.Action;
import productShop.entity.Product;
import productShop.storage.Init.DataBaseInit;

import java.util.List;

import static productShop.storage.Storage.connectionURL;

public class HibernateUtil {
    private static final HibernateUtil INSTANCE;

    static {
        INSTANCE = new HibernateUtil();
    }

    @Getter
    SessionFactory sessionFactory;

    public HibernateUtil() {
        sessionFactory = new Configuration()
                .addAnnotatedClass(Action.class)
                .addAnnotatedClass(Product.class)
                .buildSessionFactory();

    }

    public static HibernateUtil getINSTANCE() {
        return INSTANCE;
    }

    public void close() {
        sessionFactory.close();
    }

    public static void main(String[] args) {
        new DataBaseInit().initDB(connectionURL);
        HibernateUtil util = HibernateUtil.getINSTANCE();

        Session session = util.getSessionFactory().openSession();

        List<Product> from_product = session.createQuery("from Product", Product.class).list();
        System.out.println("from_product = " + from_product);
        session.close();


//        Session session = util.getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();
//        Product newProduct = new Product();
//        newProduct.setName("apple");
//        newProduct.setPrice(1.345f);
//        newProduct.setAction_id(BigInteger.valueOf(1));
//        session.persist(newProduct);
//        transaction.commit();
//
//
//        session.close();

//        Session session = util.sessionFactory.openSession();
//
//        Transaction transaction = session.beginTransaction();
//
//        Product ex = session.get(Product.class, 2L);
//        ex.setName("Mod");
//        session.persist(ex);
//
//
//        transaction.commit();
//        session.close();
    }
}
