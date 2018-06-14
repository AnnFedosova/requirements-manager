package dBService.dao;

import dBService.entities.ProjectEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class ProjectDAO {
    private final Session session;

    public ProjectDAO(Session session) {
        this.session = session;
    }

    public ProjectEntity get(long id) {
        return session.get(ProjectEntity.class, id);
    }

    public void update(ProjectEntity project) throws HibernateException{
        session.update(project);
    }

    public long addProject(ProjectEntity project) {
        session.save(project);
        return project.getId();
    }

    public long addProject(String name, String description)
            throws HibernateException {
        return (long) session.save(new ProjectEntity(name, description));
    }

    public List<ProjectEntity> selectAll() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ProjectEntity> criteria = builder.createQuery(ProjectEntity.class);
        Root<ProjectEntity> root = criteria.from(ProjectEntity.class);
        criteria.select(root);
        Query<ProjectEntity> query = session.createQuery(criteria);
        return query.getResultList();
    }
}
