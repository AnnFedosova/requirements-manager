package dBService.dao;

import dBService.entities.RequirementStateEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

public class RequirementStateDAO {
    private final Session session;

    public RequirementStateDAO(Session session) {
        this.session = session;
    }

    public RequirementStateEntity get(long id) {
        return session.get(RequirementStateEntity.class, id);
    }

    public void update(RequirementStateEntity requirementState) throws HibernateException{
        session.update(requirementState);
    }

    public long addRequirementState(RequirementStateEntity requirementState) {
        session.save(requirementState);
        return requirementState.getId();
    }

    public long addRequirementState(String name) {
        return (long) session.save(new RequirementStateEntity(name));
    }

    public RequirementStateEntity get(String name)  throws HibernateException {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<RequirementStateEntity> criteria = builder.createQuery(RequirementStateEntity.class);
        Root<RequirementStateEntity> root = criteria.from(RequirementStateEntity.class);
        ParameterExpression<String> parameter = builder.parameter(String.class);
        criteria.select(root).where(builder.equal(root.get("name"), parameter));
        Query<RequirementStateEntity> query = session.createQuery(criteria);
        query.setParameter(parameter, name);
        return query.uniqueResult();
    }
}
