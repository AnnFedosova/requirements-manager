package dBService.dao;

import dBService.entities.ReleaseRequirementEntity;
import org.hibernate.Session;

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
}
