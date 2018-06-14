package dBService.dao;

import dBService.entities.RequirementEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class RequirementDAO {
    private final Session session;

    public RequirementDAO(Session session) {
        this.session = session;
    }

    public RequirementEntity get(long id) {
        return session.get(RequirementEntity.class, id);
    }

    public void update(RequirementEntity requirementEntity) throws HibernateException {
        session.update(requirementEntity);
    }

    public long addRequirement(RequirementEntity requirement) {
        session.save(requirement);
        return requirement.getId();
    }
}
