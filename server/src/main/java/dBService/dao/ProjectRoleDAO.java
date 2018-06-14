package dBService.dao;

import dBService.entities.ProjectRoleEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

public class ProjectRoleDAO {
    private final Session session;

    public ProjectRoleDAO(Session session) {
        this.session = session;
    }

    public ProjectRoleEntity get(long id) throws HibernateException {
        return session.get(ProjectRoleEntity.class, id);
    }

    public ProjectRoleEntity get(String roleName) throws HibernateException {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ProjectRoleEntity> criteria = builder.createQuery(ProjectRoleEntity.class);
        Root<ProjectRoleEntity> root = criteria.from(ProjectRoleEntity.class);
        ParameterExpression<String> parameter = builder.parameter(String.class);
        criteria.select(root).where(builder.equal(root.get("name"), parameter));
        Query<ProjectRoleEntity> query = session.createQuery(criteria);
        query.setParameter(parameter, roleName);
        return query.uniqueResult();
    }

    public long addRole(String roleName) throws HibernateException {
        return (long) session.save(new ProjectRoleEntity(roleName));
    }
}
