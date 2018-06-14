package dBService.dao;

import dBService.entities.SpecificationEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;

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

    public long addProject(SpecificationEntity specification) {
        session.save(specification);
        return specification.getId();
    }
}
