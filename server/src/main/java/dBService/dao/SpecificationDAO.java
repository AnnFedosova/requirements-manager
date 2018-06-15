package dBService.dao;

import dBService.entities.SpecificationEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

public class SpecificationDAO {

    private final Session session;

    public SpecificationDAO(Session session) {
        this.session = session;
    }

    public SpecificationEntity get(long id) {
        return session.get(SpecificationEntity.class, id);
    }

    public void update(SpecificationEntity specification) throws HibernateException {
        session.update(specification);
    }

    public long addSpecification(SpecificationEntity specification) {
        session.save(specification);
        return specification.getId();
    }

    public long addSpecification(String name, String description, Date plannedDate)
            throws HibernateException {
        return (long) session.save(new SpecificationEntity(name, description, plannedDate));
    }

    public List<SpecificationEntity> selectAll() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<SpecificationEntity> criteria = builder.createQuery(SpecificationEntity.class);
        Root<SpecificationEntity> root = criteria.from(SpecificationEntity.class);
        criteria.select(root);
        Query<SpecificationEntity> query = session.createQuery(criteria);
        return query.getResultList();
    }
}
