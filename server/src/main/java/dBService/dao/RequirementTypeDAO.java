package dBService.dao;

import dBService.entities.RequirementTypeEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;

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
}
