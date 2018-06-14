package dBService.dao;

import dBService.entities.RequirementPriorityEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;

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

}
