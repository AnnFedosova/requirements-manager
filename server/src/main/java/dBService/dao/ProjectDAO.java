package dBService.dao;

import dBService.entities.ProjectEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;

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
}
