package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    private final SessionFactory sessionFactory;

    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory
                .getCurrentSession()
                .createQuery("from User", User.class);
        return query.getResultList();
    }

    @Override
    public User findUserByCar(String model, int series) {
        Session session = sessionFactory.getCurrentSession();

        String hql = "FROM User u WHERE u.car.model = :model AND u.car.series = :series";

        TypedQuery<User> query = session.createQuery(hql, User.class);
        query.setParameter("model", model);
        query.setParameter("series", series);

        List<User> list = query.getResultList();

        return list.isEmpty() ? null : list.get(0);
    }
}