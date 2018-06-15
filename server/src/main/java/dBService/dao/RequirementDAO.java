package dBService.dao;

import dBService.entities.ProjectEntity;
import dBService.entities.RequirementEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

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

    public List<RequirementEntity> getRequirementsByProjectId(long projectId) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<RequirementEntity> criteria = builder.createQuery(RequirementEntity.class);

        Root<RequirementEntity> root = criteria.from(RequirementEntity.class);
        ParameterExpression<ProjectEntity> parameter = builder.parameter(ProjectEntity.class);
        criteria.select(root).where(builder.equal(root.get("project"), parameter));
        Query<RequirementEntity> query = session.createQuery(criteria);
        ProjectDAO projectDAO = new ProjectDAO(session);
        query.setParameter(parameter, projectDAO.get(projectId));
        return query.getResultList();
    }

    public List<RequirementEntity> selectAll() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<RequirementEntity> criteria = builder.createQuery(RequirementEntity.class);
        Root<RequirementEntity> root = criteria.from(RequirementEntity.class);
        criteria.select(root);
        Query<RequirementEntity> query = session.createQuery(criteria);
        return query.getResultList();
    }
}
