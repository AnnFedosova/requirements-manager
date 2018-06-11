package dBService;

import dBService.dao.SystemRoleDAO;
import dBService.dao.UserDAO;
import dBService.dao.UserSystemRoleDAO;
import dBService.entities.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.jboss.crypto.CryptoUtil;


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
                    fillDB(instance);
                }
            }
        }
        return instance;
    }

    private Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        addTables(configuration);
        return  configuration;
    }

    private void addTables(Configuration configuration) {
        configuration.addAnnotatedClass(ProjectEntity.class);
        configuration.addAnnotatedClass(ProjectRoleEntity.class);
        configuration.addAnnotatedClass(ReleaseEntity.class);
        configuration.addAnnotatedClass(ReleaseRequirementEntity.class);
        configuration.addAnnotatedClass(RequirementEntity.class);
        configuration.addAnnotatedClass(RequirementPositionEntity.class);
        configuration.addAnnotatedClass(RequirementPriorityEntity.class);
        configuration.addAnnotatedClass(RequirementTypeEntity.class);
        configuration.addAnnotatedClass(SpecificationEntity.class);
        configuration.addAnnotatedClass(SpecificationRequirementEntity.class);
        configuration.addAnnotatedClass(SystemRoleEntity.class);
        configuration.addAnnotatedClass(UserEntity.class);
        configuration.addAnnotatedClass(UserProjectRoleEntity.class);
        configuration.addAnnotatedClass(UserSystemRoleEntity.class);
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    private static void fillDB(DBService dbService) {
        try {
            dbService.addSystemRole("admin");
            dbService.addSystemRole("user");
            dbService.addAdmin("admin", "admin", "admin", "admin", "admin");
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    // Work with DAOs


    public UserEntity getUser(long id) {
        Session session = sessionFactory.openSession();
        UserDAO userDAO = new UserDAO(session);
        UserEntity user = userDAO.get(id);
        session.close();
        return user;
    }

    public long addSystemRole(String roleName) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        SystemRoleDAO systemRoleDAO = new SystemRoleDAO(session);
        long id = systemRoleDAO.addSystemRole(roleName);
        transaction.commit();
        session.close();
        return id;
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
            SystemRoleDAO systemRoleDAO = new SystemRoleDAO(session);
            UserSystemRoleDAO userSystemRoleDAO = new UserSystemRoleDAO(session);

            UserEntity user = new UserEntity(
                    firstName,
                    lastName,
                    middleName,
                    login,
                    CryptoUtil.createPasswordHash(
                            "MD5",
                            CryptoUtil.BASE64_ENCODING,
                            null,
                            null,
                            password));

            long userId = userDAO.addUser(user);
            SystemRoleEntity role = systemRoleDAO.get(roleName);
            userSystemRoleDAO.addUserSystemRole(user, role);
            transaction.commit();
            session.close();
            return userId;
        }
        catch (HibernateException e) {
            throw new DBException(e);
        }
    }

}
