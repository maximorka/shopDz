package productShop.storage.hibernate;

import lombok.Getter;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import productShop.entity.Action;
import productShop.entity.Product;


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


}
