package dBService;

import dBService.dao.UserDAO;
import dBService.entities.UserEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class DBService {
    private static volatile DBService instance;
    private final SessionFactory sessionFactory;


    private DBService() {
        Configuration configuration = getConfiguration();
        sessionFactory = createSessionFactory(configuration);
    }

    public static DBService getInstance() {
        if (instance == null) {
            synchronized (DBService.class) {
                if (instance == null) {
                    instance = new DBService();
                }
            }
        }
        return instance;
    }

    private Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        return  configuration;
    }

    private void addTables(Configuration configuration) {
        configuration.addAnnotatedClass(UserEntity.class);
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }


    // Work with DAOs

    // Users

    public UserEntity getUser(long id) {
        Session session = sessionFactory.openSession();
        UserDAO userDAO = new UserDAO(session);
        UserEntity user = userDAO.get(id);
        session.close();
        return user;
    }

    public long addUser(String login, String password, String firstName, String lastName, String middleName) throws DBException {
        return addPerson(login, password, firstName, lastName, middleName, "user");
    }

    public long addAdmin(String login, String password, String firstName, String lastName, String middleName) throws DBException {
        return addPerson(login, password, firstName, lastName, middleName, "admin");
    }

    private long addPerson(String login, String password, String firstName, String lastName, String middleName, String roleName) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            UserDAO userDAO = new UserDAO(session);
            //RoleDAO roleDAO = new RoleDAO(session);
            //UserRoleDAO userRoleDAO = new UserRoleDAO(session);


            UserEntity user = new UserEntity(login, password
                    /*CryptoUtil.createPasswordHash("MD5", CryptoUtil.BASE64_ENCODING, null, null, password)*/,
                    firstName, lastName, middleName);

            long userId = userDAO.addUser(user);
           // RoleEntity role = roleDAO.get(roleName);
            //userRoleDAO.addUserRole(user, role);
            transaction.commit();
            session.close();
            return userId;
        }
        catch (HibernateException e) {
            throw new DBException(e);
        }
    }

}
