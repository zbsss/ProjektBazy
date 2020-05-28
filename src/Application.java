import entities.CustomersEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.Timestamp;

public class Application {
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void main(final String[] args) throws Exception {
        final Session session = getSession();
        final Transaction transaction = session.beginTransaction();

        try {

            Timestamp startDate = Timestamp.valueOf("2000-12-24 00:00:00");
            Timestamp endDate = Timestamp.valueOf("2020-05-20 00:00:00");

            Invoice invoice = new Invoice();
            CustomersEntity customer = session.get(CustomersEntity.class, 4);
            invoice.create("123456", startDate, endDate, customer, session);

        }
        catch (Exception e){
            throw e;
        }
        finally {
            transaction.commit();
            session.close();
        }
    }
}