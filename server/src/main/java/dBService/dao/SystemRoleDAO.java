package dBService.dao;

import dBService.entities.SystemRoleEntity;
import dBService.entities.UserSystemRoleEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;


public class SystemRoleDAO {
    private final Session session;

    public SystemRoleDAO(Session session) {
        this.session = session;
    }

    public SystemRoleEntity get(long id) throws HibernateException {
        return session.get(SystemRoleEntity.class, id);
    }

    public SystemRoleEntity get(String roleName) throws HibernateException {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<SystemRoleEntity> criteria = builder.createQuery(SystemRoleEntity.class);
        Root<SystemRoleEntity> root = criteria.from(SystemRoleEntity.class);
        ParameterExpression<String> parameter = builder.parameter(String.class);
        criteria.select(root).where(builder.equal(root.get("name"), parameter));
        Query<SystemRoleEntity> query = session.createQuery(criteria);
        query.setParameter(parameter, roleName);
        return query.uniqueResult();
    }

    public long addSystemRole(String roleName) throws HibernateException {
        return (long) session.save(new SystemRoleEntity(roleName));
    }
}