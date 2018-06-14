package dBService.dao;

import dBService.entities.SpecificationRequirementEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class SpecificationRequirementDAO {
    private final Session session;

    public SpecificationRequirementDAO(Session session) {
        this.session = session;
    }

    public SpecificationRequirementEntity get(long id) {
        return session.get(SpecificationRequirementEntity.class, id);
    }

    public void update(SpecificationRequirementEntity specificationRequirementEntity) throws HibernateException {
        session.update(specificationRequirementEntity);
    }

    public void addProject(SpecificationRequirementEntity specificationRequirement) {
        session.save(specificationRequirement);
    }
}
