package productShop.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import productShop.entity.Action;
import productShop.entity.Product;
import productShop.storage.hibernate.HibernateUtil;

import java.math.BigInteger;

public class ActionDao {

    HibernateUtil util = HibernateUtil.getINSTANCE();

    public long create(Action action) {
        Session session = util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(action);
        transaction.commit();
        session.close();
        return action.getId();
    }

    public Action getActionById(long id) {
        Session session = util.getSessionFactory().openSession();
        Query<Action> query = session.createQuery("from Action a WHERE a.id = :id", Action.class);
        query.setParameter("id", id);
        Action result = query.getSingleResult();
        session.close();
        return result;
    }

    public void deleteActionById(long id) {
        Session session = util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("delete from Action where id= :id");
        query.setParameter("id", id);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }
}
