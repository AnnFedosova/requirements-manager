package dBService.dao;

import dBService.entities.UserEntity;
import org.hibernate.Session;

public class UserDAO {
    private final Session session;

    public UserDAO(Session session) {
        this.session = session;
    }

    public UserEntity get(long id) {
        return session.get(UserEntity.class, id);
    }

    public long addUser(UserEntity user) {
        session.save(user);
        return user.getId();
    }
}
