package dBService.dao;

import dBService.entities.SystemRoleEntity;
import dBService.entities.UserEntity;
import dBService.entities.UserSystemRoleEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserSystemRoleDAO {
    private final Session session;

    public UserSystemRoleDAO(Session session) {
        this.session = session;
    }

    public void addUserSystemRole(UserEntity user, SystemRoleEntity role) throws HibernateException {
        session.save(new UserSystemRoleEntity(user, role));
    }

    public List<UserSystemRoleEntity> getRolesByUser(String userLogin) throws HibernateException {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserSystemRoleEntity> criteria = builder.createQuery(UserSystemRoleEntity.class);
        Root<UserSystemRoleEntity> root = criteria.from(UserSystemRoleEntity.class);
        ParameterExpression<UserEntity> parameter = builder.parameter(UserEntity.class);
        criteria.select(root).where(builder.equal(root.get("user"), parameter));
        Query<UserSystemRoleEntity> query = session.createQuery(criteria);
        UserDAO userDAO = new UserDAO(session);
        query.setParameter(parameter, userDAO.get(userLogin));
        return query.getResultList();
    }
}