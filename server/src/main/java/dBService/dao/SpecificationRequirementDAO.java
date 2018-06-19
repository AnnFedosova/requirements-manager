package dBService.dao;

import dBService.entities.RequirementEntity;
import dBService.entities.SpecificationEntity;
import dBService.entities.SpecificationRequirementEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

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

    public void addSpecificationRequirement(SpecificationRequirementEntity specificationRequirement) {
        session.save(specificationRequirement);
    }

    public void addSpecificationRequirement(SpecificationEntity specificationEntity, RequirementEntity requirementEntity) {
        session.save(new SpecificationRequirementEntity(specificationEntity,requirementEntity));
    }

    public List<SpecificationRequirementEntity> getBySpecificationId(long specificationId)  throws HibernateException {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<SpecificationRequirementEntity> criteria = builder.createQuery(SpecificationRequirementEntity.class);
        Root<SpecificationRequirementEntity> root = criteria.from(SpecificationRequirementEntity.class);
        ParameterExpression<SpecificationEntity> parameter = builder.parameter(SpecificationEntity.class);
        criteria.select(root).where(builder.equal(root.get("specification"), parameter));
        Query<SpecificationRequirementEntity> query = session.createQuery(criteria);
        SpecificationDAO specificationDAO = new SpecificationDAO(session);
        query.setParameter(parameter, specificationDAO.get(specificationId));
        return query.getResultList();
    }
}
