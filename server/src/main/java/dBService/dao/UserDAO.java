package dBService.dao;

import dBService.entities.UserEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserDAO {
    private final Session session;

    public UserDAO(Session session) {
        this.session = session;
    }

    public UserEntity get(long id) {
        return session.get(UserEntity.class, id);
    }

    public UserEntity get(String login) throws HibernateException {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserEntity> criteria = builder.createQuery(UserEntity.class);
        Root<UserEntity> root = criteria.from(UserEntity.class);
        ParameterExpression<String> parameter = builder.parameter(String.class);
        criteria.select(root).where(builder.equal(root.get("login"), parameter));
        Query<UserEntity> query = session.createQuery(criteria);
        query.setParameter(parameter, login);
        return query.uniqueResult();
    }


    public long addUser(UserEntity user) {
        session.save(user);
        return user.getId();
    }

    public long addUser(String login, String password, String firstName, String lastName, String middleName)
            throws HibernateException {
        return (long) session.save(new UserEntity(login, password, firstName, lastName, middleName));
    }

    public List<UserEntity> selectAll() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserEntity> criteria = builder.createQuery(UserEntity.class);
        Root<UserEntity> root = criteria.from(UserEntity.class);
        criteria.select(root);
        Query<UserEntity> query = session.createQuery(criteria);
        return query.getResultList();
    }
}
