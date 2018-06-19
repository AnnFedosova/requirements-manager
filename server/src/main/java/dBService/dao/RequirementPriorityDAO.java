package dBService.dao;

import dBService.entities.RequirementPriorityEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class RequirementPriorityDAO {
    private final Session session;

    public RequirementPriorityDAO(Session session) {
        this.session = session;
    }

    public RequirementPriorityEntity get(long id) {
        return session.get(RequirementPriorityEntity.class, id);
    }

    public void update(RequirementPriorityEntity requirementPriorityEntity) throws HibernateException {
        session.update(requirementPriorityEntity);
    }

    public long addRequirementPriority(RequirementPriorityEntity requirementPriority) {
        session.save(requirementPriority);
        return requirementPriority.getId();
    }
    public long addRequirementPriority(String name) {
        return (long) session.save(new RequirementPriorityEntity(name));
    }

    public List<RequirementPriorityEntity> selectAll() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<RequirementPriorityEntity> criteria = builder.createQuery(RequirementPriorityEntity.class);
        Root<RequirementPriorityEntity> root = criteria.from(RequirementPriorityEntity.class);
        criteria.select(root);
        Query<RequirementPriorityEntity> query = session.createQuery(criteria);
        return query.getResultList();
    }
}
