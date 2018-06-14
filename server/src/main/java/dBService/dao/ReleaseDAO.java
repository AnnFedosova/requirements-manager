package dBService.dao;

import dBService.entities.ReleaseEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class ReleaseDAO {
    private final Session session;

    public ReleaseDAO(Session session) {
        this.session = session;
    }

    public ReleaseEntity get(long id) {
        return session.get(ReleaseEntity.class, id);
    }

    public void update(ReleaseEntity release) throws HibernateException {
        session.update(release);
    }

    public long addProject(ReleaseEntity release) {
        session.save(release);
        return release.getId();
    }

}
