package dBService.dao;

import dBService.entities.ReleaseEntity;
import dBService.entities.ReleaseRequirementEntity;
import dBService.entities.RequirementEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

public class ReleaseRequirementDAO {
    private final Session session;

    public ReleaseRequirementDAO(Session session) {
        this.session = session;
    }

    public ReleaseRequirementEntity get(long id) {
        return session.get(ReleaseRequirementEntity.class, id);
    }

    public void addReleaseRequirement(ReleaseRequirementEntity releaseRequirement) {
        session.save(releaseRequirement);
    }

    public void addReleaseRequirement(ReleaseEntity releaseEntity, RequirementEntity requirementEntity) {
        session.save(new ReleaseRequirementEntity(releaseEntity,requirementEntity));
    }

    public List<ReleaseRequirementEntity> getByReleaseId(long releaseId)  throws HibernateException {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ReleaseRequirementEntity> criteria = builder.createQuery(ReleaseRequirementEntity.class);
        Root<ReleaseRequirementEntity> root = criteria.from(ReleaseRequirementEntity.class);
        ParameterExpression<ReleaseEntity> parameter = builder.parameter(ReleaseEntity.class);
        criteria.select(root).where(builder.equal(root.get("release"), parameter));
        Query<ReleaseRequirementEntity> query = session.createQuery(criteria);
        ReleaseDAO releaseDAO = new ReleaseDAO(session);
        query.setParameter(parameter, releaseDAO.get(releaseId));
        return query.getResultList();
    }
}
