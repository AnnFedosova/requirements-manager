package dBService.dao;

import dBService.entities.RequirementTypeEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class RequirementTypeDAO {
    private final Session session;

    public RequirementTypeDAO(Session session) {
        this.session = session;
    }

    public RequirementTypeEntity get(long id) {
        return session.get(RequirementTypeEntity.class, id);
    }

    public void update(RequirementTypeEntity requirementEntity) throws HibernateException {
        session.update(requirementEntity);
    }

    public long addRequirementType(RequirementTypeEntity requirementType) {
        session.save(requirementType);
        return requirementType.getId();
    }

    public long addRequirementType(String name) {
        return (long) session.save(new RequirementTypeEntity(name));
    }

    public List<RequirementTypeEntity> selectAll() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<RequirementTypeEntity> criteria = builder.createQuery(RequirementTypeEntity.class);
        Root<RequirementTypeEntity> root = criteria.from(RequirementTypeEntity.class);
        criteria.select(root);
        Query<RequirementTypeEntity> query = session.createQuery(criteria);
        return query.getResultList();
    }
}
