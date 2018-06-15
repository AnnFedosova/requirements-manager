package dBService.dao;

import dBService.entities.ReleaseEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

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

    public long addRelease(ReleaseEntity release) {
        session.save(release);
        return release.getId();
    }

    public long addRelease(String name, String description, Date releaseDate)
            throws HibernateException {
        return (long) session.save(new ReleaseEntity(name, description, releaseDate));
    }

    public List<ReleaseEntity> selectAll() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ReleaseEntity> criteria = builder.createQuery(ReleaseEntity.class);
        Root<ReleaseEntity> root = criteria.from(ReleaseEntity.class);
        criteria.select(root);
        Query<ReleaseEntity> query = session.createQuery(criteria);
        return query.getResultList();
    }
}
