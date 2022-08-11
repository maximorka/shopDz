package productShop.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import productShop.entity.Product;
import productShop.storage.hibernate.HibernateUtil;

import java.util.List;

public class ProductDao {
    HibernateUtil util = HibernateUtil.getINSTANCE();

    public List<Product> getAllProduct() {
        Session session = util.getSessionFactory().openSession();
        return session.createQuery("from Product", Product.class).list();
    }

    public String create(Product product) {
        Session session = util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(product);
        transaction.commit();
        session.close();
        return product.getName();
    }

    public Product getProductByName(String name) {
        Session session = util.getSessionFactory().openSession();
        Query<Product> query = session.createQuery("from Product t WHERE t.name = :name", Product.class);
        query.setParameter("name", name);
        Product result = query.getSingleResult();
        session.close();
        return result;
    }

    public void deleteProductByName(String name) {
        Session session = util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("delete from Product where name= :name");
        query.setParameter("name", name);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }
}
