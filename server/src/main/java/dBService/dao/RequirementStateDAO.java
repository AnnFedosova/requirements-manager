package dBService.dao;

import dBService.entities.RequirementStateEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;

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
}
