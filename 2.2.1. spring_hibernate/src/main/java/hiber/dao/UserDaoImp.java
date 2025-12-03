package hiber.dao;

import hiber.model.User;
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
                .createQuery("SELECT u FROM User u JOIN FETCH u.car", User.class);
        return query.getResultList();
    }

    @Override
    public User findUserByCar(String model, int series) {
        TypedQuery<User> query = sessionFactory
                .getCurrentSession()
                .createQuery(
                        "SELECT u FROM User u JOIN FETCH u.car " +
                                "WHERE u.car.model = :model AND u.car.series = :series",
                        User.class
                );
        query.setParameter("model", model);
        query.setParameter("series", series);

        List<User> list = query.getResultList();
        return list.isEmpty() ? null : list.get(0);
    }
}