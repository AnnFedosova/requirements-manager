package dBService;

import dBService.dao.*;
import dBService.entities.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.jboss.crypto.CryptoUtil;

import java.util.*;


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
        configuration.addAnnotatedClass(RequirementStateEntity.class);
        configuration.addAnnotatedClass(RequirementPriorityEntity.class);
        configuration.addAnnotatedClass(RequirementTypeEntity.class);
        configuration.addAnnotatedClass(SpecificationEntity.class);
        configuration.addAnnotatedClass(SpecificationRequirementEntity.class);
        configuration.addAnnotatedClass(SystemRoleEntity.class);
        configuration.addAnnotatedClass(UserEntity.class);
        configuration.addAnnotatedClass(UserProjectRoleEntity.class);
        configuration.addAnnotatedClass(UserSystemRoleEntity.class);
        configuration.addAnnotatedClass(ConnectionTypeEntity.class);
        configuration.addAnnotatedClass(RequirementDependenceEntity.class);
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    private static void fillDB(DBService dbService) {
        try {
            //заполнение таблицы ProjectRole
            dbService.addProjectRole("Руководитель проекта");
            dbService.addProjectRole("Аналитик");
            dbService.addProjectRole("Разработчик");
            dbService.addProjectRole("Тестировщик");

            //заполнение таблицы RequirementPriority
            dbService.addRequirementPriority("Низкий");
            dbService.addRequirementPriority("Средний");
            dbService.addRequirementPriority("Высокий");

            //dbService.addRequirementPriority("Иерархическая зависимость");
            //dbService.addConnectionType("Взаимная зависимость");

            //Заполнение таблицы RequirementState
            dbService.addRequirementState("Новое");
            dbService.addRequirementState("Уточнено");
            dbService.addRequirementState("Разработано");
            dbService.addRequirementState("Протестировано");
            dbService.addRequirementState("Вошло в релиз");
            dbService.addRequirementState("Отклонено");
            dbService.addRequirementState("Отожено");


            //Заполнение таблицы RequirementType
            dbService.addRequirementType("Бизнес-требование");
            dbService.addRequirementType("Требование пользователей");
            dbService.addRequirementType("Функциональное требование");
            dbService.addRequirementType("Нефункциональное требование");

            //Заполнение таблицы SystemRole
            dbService.addSystemRole("admin");
            dbService.addSystemRole("user");

            //Заполнение тестовыми данными для остальной системы

            dbService.addAdmin("admin", "admin", "admin", "admin", "admin");

            dbService.addProject("Проект «Газпром»", "В этом проекте наша компания реализует ПО для работы с ценами на топливо для компании Газпром. ПАО «Газпром» — российская (ранее советская) транснациональная энергетическая корпорация");
            dbService.addProject("Проект «Роскосмос»", "Реализуется ПО, которое служит для выбора кандидатов на работу в космической отрасли.");
            dbService.addProject("Проект «Сбербанк России»", "«Сбербанк» — российский финансовый конгломерат, крупнейший транснациональный и универсальный банк России, Центральной и Восточной Европы. Контролируется Центральным банком Российской Федерации, которому принадлежат более 52 % акций.");

            dbService.addRelease("release1", "test1",new Date(), 1);
            dbService.addRelease("release2", "test2",new Date(), 2);

            dbService.addSpecification("specification1","test1", "Какая-то дата", 1);
            dbService.addSpecification("specification2","test2", "Какая-то дата", 1);

            dbService.addUser("user1","123","Sergey","Kuznetcov","Andreevich");
            dbService.addUser("user2","321","Anna","Fedosova","Mihailovna");
            dbService.addUser("user3","12","Boris","Lebedev","Ivanov");
            dbService.addUser("user4","21","Ivan","Lenin"," Borisovich");

            dbService.addUserToProject(1,1,1);
            dbService.addUserToProject(2,1,4);
            dbService.addUserToProject(3,2,1);
            dbService.addUserToProject(4,2,2);

            dbService.addRequirement(1,"Требование 1","Необходимо обновить функциональность ПО",1,1,1,0);
            dbService.addRequirement(1,"Требование 2","Требование к обновлению справочных данных",3,1,2,1);
            dbService.addRequirement(2,"Требование 3","hjg",2,2,4,0);

            dbService.addRequirementToSpecification(1,1);
            dbService.addRequirementToSpecification(2,2);
            //dbService.addRequirementToSpecification(2,3);

            dbService.addRequirementToRelease(1,2);
            dbService.addRequirementToRelease(2,3);

        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    // Work with DAOs

    //////////////////////////////
    /////////    Users    ////////
    //////////////////////////////

    public UserEntity getUser(long id) {
        Session session = sessionFactory.openSession();
        UserDAO userDAO = new UserDAO(session);
        UserEntity user = userDAO.get(id);
        session.close();
        return user;
    }

    public UserEntity getUser(String login) {
        Session session = sessionFactory.openSession();
        UserDAO userDAO = new UserDAO(session);
        UserEntity user = userDAO.get(login);
        session.close();
        return user;
    }

    public List<UserEntity> getAllUsers() {
        Session session = sessionFactory.openSession();
        UserDAO userDAO = new UserDAO(session);
        List<UserEntity> users = userDAO.selectAll();
        session.close();
        return users;
    }

    public List<UserEntity> getUsersByProjectId(long projectId) {
        Session session = sessionFactory.openSession();
        //Set<UserEntity> users = new TreeSet<>();
        ArrayList<UserEntity> users = new ArrayList<>();
        List<UserProjectRoleEntity> userProjectRoleList = getUserProjectRoleList(projectId);
        for(UserProjectRoleEntity userProjectRoleEntity : userProjectRoleList) {
            users.add(userProjectRoleEntity.getUser());
        }
        session.close();
        return new ArrayList<>(users);
    }

    public long addUser(String login, String password, String firstName, String lastName, String middleName) throws DBException {
        return addPerson(login, password, firstName, lastName, middleName, "user");
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

    /////////////////////////////////////
    ////////       SystemRole   /////////
    /////////////////////////////////////

    public long addSystemRole(String roleName) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        SystemRoleDAO systemRoleDAO = new SystemRoleDAO(session);
        long id = systemRoleDAO.addSystemRole(roleName);
        transaction.commit();
        session.close();
        return id;
    }

    public long addAdmin(String login, String password, String firstName, String lastName, String middleName) throws DBException {
        return addPerson(login, password, firstName, lastName, middleName, "admin");
    }

    public boolean isAdmin(String userLogin) {
        Session session = sessionFactory.openSession();

        UserSystemRoleDAO userSystemRoleDAO = new UserSystemRoleDAO(session);
        long adminRoleId = new SystemRoleDAO(session).get("admin").getId();
        List<UserSystemRoleEntity> roles = userSystemRoleDAO.getRolesByUser(userLogin);
        for (UserSystemRoleEntity role: roles) {
            if (role.getRole().getId() == adminRoleId) {
                session.close();
                return true;
            }
        }
        session.close();
        return false;
    }

    /////////////////////////////
    //////    Requirement   /////
    /////////////////////////////

    public long addRequirement(long projectId, String name, String description, long priorityId,
                               long typeId, long creatorId, long lastVersionId) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            RequirementDAO requirementDAO = new RequirementDAO(session);
            UserDAO userDAO = new UserDAO(session);
            RequirementPriorityDAO requirementPriorityDAO = new RequirementPriorityDAO(session);
            RequirementStateDAO requirementStateDAO = new RequirementStateDAO(session);
            ProjectDAO projectDAO = new ProjectDAO(session);
            RequirementTypeDAO requirementTypeDAO = new RequirementTypeDAO(session);

            ProjectEntity project = projectDAO.get(projectId);
            UserEntity creator = userDAO.get(creatorId);
            RequirementTypeEntity type = requirementTypeDAO.get(typeId);
            RequirementPriorityEntity priority = requirementPriorityDAO.get(priorityId);
            RequirementStateEntity state = requirementStateDAO.get("Новое");
            RequirementEntity lastVersion=null;
            if(lastVersionId!=0){
                lastVersion = requirementDAO.get(lastVersionId);
            }
            RequirementEntity requirementEntity = new RequirementEntity(project, name, description,
                        priority, type, state, "Текущая дата", creator, lastVersion ,true);

            long requirementId = requirementDAO.addRequirement(requirementEntity);

            transaction.commit();
            session.close();
            return requirementId;
        }
        catch (HibernateException e) {
            throw new DBException(e);
        }
    }

    public RequirementEntity getRequirement(long id) {
        Session session = sessionFactory.openSession();
        RequirementDAO requirementDAO = new RequirementDAO(session);
        RequirementEntity requirementEntity = requirementDAO.get(id);
        session.close();
        return requirementEntity;
    }

    public List<RequirementEntity> getRequirementsByProject(long projectId) {
        Session session = sessionFactory.openSession();
        RequirementDAO requirementDAO = new RequirementDAO(session);
        List <RequirementEntity> list = requirementDAO.getRequirementsByProjectId(projectId);
        session.close();
        return list;
    }

    public List<RequirementEntity> getRequirementsBySpecification(long specificationId) {
        Session session = sessionFactory.openSession();
        //Set<RequirementEntity> requirementEntities = new TreeSet<>();
        ArrayList<RequirementEntity> requirementEntities = new ArrayList<>();
        List<SpecificationRequirementEntity> specificationRequirementEntities = getSpecificationRequirementsList(specificationId);
        for(SpecificationRequirementEntity specificationRequirementEntity : specificationRequirementEntities) {
            requirementEntities.add(specificationRequirementEntity.getRequirement());
        }
        session.close();
        return new ArrayList<>(requirementEntities);
    }

    public List<SpecificationRequirementEntity> getSpecificationRequirementsList(long specificationId) {
        Session session = sessionFactory.openSession();
        SpecificationRequirementDAO specificationRequirementDAO = new SpecificationRequirementDAO(session);
        List <SpecificationRequirementEntity> list = specificationRequirementDAO.getBySpecificationId(specificationId);
        session.close();
        return list;
    }

    public List<RequirementEntity> getRequirementsByRelease(long releaseId) {
        Session session = sessionFactory.openSession();
        //Set<RequirementEntity> requirementEntities = new TreeSet<>();
        ArrayList<RequirementEntity> requirementEntities = new ArrayList<>();
        List<ReleaseRequirementEntity> releaseRequirementEntities = getReleaseRequirementsList(releaseId);
        for(ReleaseRequirementEntity releaseRequirementEntity : releaseRequirementEntities) {
            requirementEntities.add(releaseRequirementEntity.getRequirement());
        }
        session.close();
        return new ArrayList<>(requirementEntities);
    }

    public List<ReleaseRequirementEntity> getReleaseRequirementsList(long releaseId) {
        Session session = sessionFactory.openSession();
        ReleaseRequirementDAO releaseRequirementDAO = new ReleaseRequirementDAO(session);
        List <ReleaseRequirementEntity> list = releaseRequirementDAO.getByReleaseId(releaseId);
        session.close();
        return list;
    }

    public List<RequirementEntity> getAllRequirements() {
        Session session = sessionFactory.openSession();
        RequirementDAO requirementDAO = new RequirementDAO(session);
        List<RequirementEntity> requirements = requirementDAO.selectAll();
        session.close();
        return requirements;
    }

    public void updateRequirement(RequirementEntity requirementEntity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        RequirementDAO requirementDAO = new RequirementDAO(session);
        requirementDAO.update(requirementEntity);

        transaction.commit();
        session.close();
    }

    /////////////////////////////
    ////   ProjectRole    ///////
    /////////////////////////////

    public long addProjectRole(String roleName){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ProjectRoleDAO projectRoleDAO = new ProjectRoleDAO(session);
        long id = projectRoleDAO.addProjectRole(roleName);
        transaction.commit();
        session.close();
        return id;
    }

    //////////////////////////////
    ///  RequirementPriority  ////
    //////////////////////////////

    public List<RequirementPriorityEntity>  getAllPriorities() {
        Session session = sessionFactory.openSession();
        RequirementPriorityDAO requirementPriorityDAO = new RequirementPriorityDAO(session);
        List<RequirementPriorityEntity> requirementPriorityEntities = requirementPriorityDAO.selectAll();
        session.close();
        return requirementPriorityEntities;
    }

    public RequirementPriorityEntity getRequirementPriority(long id){
        Session session = sessionFactory.openSession();
        RequirementPriorityDAO requirementPriorityDAO = new RequirementPriorityDAO(session);
        RequirementPriorityEntity requirementPriorityEntity =  requirementPriorityDAO.get(id);
        session.close();
        return requirementPriorityEntity;
    }

    public long addRequirementPriority(String priorityName){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        RequirementPriorityDAO requirementPriorityDAO = new RequirementPriorityDAO(session);
        long id = requirementPriorityDAO.addRequirementPriority(priorityName);
        transaction.commit();
        session.close();
        return id;
    }

    //////////////////////////////
    ///  RequirementState  ////
    //////////////////////////////

    public List<RequirementStateEntity>  getAllStates() {
        Session session = sessionFactory.openSession();
        RequirementStateDAO requirementStateDAO = new RequirementStateDAO(session);
        List<RequirementStateEntity> requirementStateEntities = requirementStateDAO.selectAll();
        session.close();
        return requirementStateEntities;
    }

    public RequirementStateEntity getRequirementState(long id){
        Session session = sessionFactory.openSession();
        RequirementStateDAO requirementStateDAO = new RequirementStateDAO(session);
        RequirementStateEntity requirementStateEntity =  requirementStateDAO.get(id);
        session.close();
        return requirementStateEntity;
    }

    public long addRequirementState(String stateName){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        RequirementStateDAO requirementStateDAO = new RequirementStateDAO(session);
        long id = requirementStateDAO.addRequirementState(stateName);
        transaction.commit();
        session.close();
        return id;
    }

    //////////////////////////////
    ////  RequirementType   //////
    //////////////////////////////

    public List<RequirementTypeEntity>  getAllTypes() {
        Session session = sessionFactory.openSession();
        RequirementTypeDAO requirementTypeDAO = new RequirementTypeDAO(session);
        List<RequirementTypeEntity> requirementTypeEntities = requirementTypeDAO.selectAll();
        session.close();
        return requirementTypeEntities;
    }

    public RequirementTypeEntity getRequirementType(long id){
        Session session = sessionFactory.openSession();
        RequirementTypeDAO requirementTypeDAO = new RequirementTypeDAO(session);
        RequirementTypeEntity requirementTypeEntity =  requirementTypeDAO.get(id);
        session.close();
        return requirementTypeEntity;
    }

    public long addRequirementType(String typeName){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        RequirementTypeDAO requirementTypeDAO = new RequirementTypeDAO(session);
        long id = requirementTypeDAO.addRequirementType(typeName);
        transaction.commit();
        session.close();
        return id;
    }

    //////////////////////////////
    //////     Project     ///////
    //////////////////////////////

    public ProjectEntity getProject(long id) {
        Session session = sessionFactory.openSession();
        ProjectDAO projectDAO = new ProjectDAO(session);
        ProjectEntity project =  projectDAO.get(id);
        session.close();
        return project;
    }

    public void updateProject(ProjectEntity project) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        ProjectDAO projectDAO = new ProjectDAO(session);
        projectDAO.update(project);

        transaction.commit();
        session.close();
    }

    public List<ProjectEntity>  getProjectsList() {
        Session session = sessionFactory.openSession();
        ProjectDAO projectDAO = new ProjectDAO(session);
        List<ProjectEntity> projects = projectDAO.selectAll();
        session.close();
        return projects;
    }

    public long addProject(String name, String description) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            ProjectDAO projectDAO = new ProjectDAO(session);
            long projectId = projectDAO.addProject(name, description);
            transaction.commit();
            session.close();
            return projectId;
        }
        catch (HibernateException e) {
            throw new DBException(e);
        }
    }

    public List<UserProjectRoleEntity> getUserProjectRoleList(long projectId) {
        Session session = sessionFactory.openSession();

        UserProjectRoleDAO userProjectRoleDAO = new UserProjectRoleDAO(session);
        List <UserProjectRoleEntity> list = userProjectRoleDAO.getByProjectId(projectId);

        session.close();
        return list;
    }

    public void addUserToProject(long userId, long projectId, long projectRoleId){
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            UserProjectRoleDAO userProjectRoleDAO = new UserProjectRoleDAO(session);

            UserDAO userDAO = new UserDAO(session);
            ProjectDAO projectDAO = new ProjectDAO(session);
            ProjectRoleDAO projectRoleDAO = new ProjectRoleDAO(session);

            userProjectRoleDAO.addUserProjectRole(userDAO.get(userId),projectDAO.get(projectId),projectRoleDAO.get(projectRoleId));
            transaction.commit();
            session.close();
    }

    ////////////////////////////
    ///////   Releases   ///////
    ////////////////////////////

    public ReleaseEntity getRelease(long id) {
        Session session = sessionFactory.openSession();
        ReleaseDAO releaseDAO = new ReleaseDAO(session);
        ReleaseEntity releaseEntity = releaseDAO.get(id);
        session.close();
        return releaseEntity;
    }

    public List<ReleaseEntity>  getAllReleases() {
        Session session = sessionFactory.openSession();
        ReleaseDAO releaseDAO = new ReleaseDAO(session);
        List<ReleaseEntity> releaseEntities = releaseDAO.selectAll();
        session.close();
        return releaseEntities;
    }

    public long addRelease(String name, String description, Date releaseDate, long projectId) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            ReleaseDAO releaseDAO = new ReleaseDAO(session);
            ProjectDAO projectDAO = new ProjectDAO(session);
            ProjectEntity projectEntity = projectDAO.get(projectId);
            long releaseId = releaseDAO.addRelease(name, description, releaseDate, projectEntity);
            transaction.commit();
            session.close();
            return releaseId;
        }
        catch (HibernateException e) {
            throw new DBException(e);
        }
    }

    ////////////////////////////
    /////  Specification   /////
    ////////////////////////////

    public SpecificationEntity getSpecification(long id) {
        Session session = sessionFactory.openSession();
        SpecificationDAO specificationDAO = new SpecificationDAO(session);
        SpecificationEntity specificationEntity =  specificationDAO.get(id);
        session.close();
        return specificationEntity;
    }

    public List<SpecificationEntity>  getAllSpecifications() {
        Session session = sessionFactory.openSession();
        SpecificationDAO specificationDAO = new SpecificationDAO(session);
        List<SpecificationEntity> specificationEntities = specificationDAO.selectAll();
        session.close();
        return specificationEntities;
    }

     public long addSpecification(String name, String description, String plannedDate, long projectId) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            SpecificationDAO specificationDAO = new SpecificationDAO(session);
            ProjectDAO projectDAO = new ProjectDAO(session);
            ProjectEntity projectEntity = projectDAO.get(projectId);
            long specificationId = specificationDAO.addSpecification(name, description, plannedDate, projectEntity);
            transaction.commit();
            session.close();
            return specificationId;
        }
        catch (HibernateException e) {
            throw new DBException(e);
        }
    }
//78
    public void addRequirementToSpecification(long specificationId, long requirementId){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        SpecificationRequirementDAO specificationRequirementDAO= new SpecificationRequirementDAO(session);

        SpecificationDAO specificationDAO = new SpecificationDAO(session);
        RequirementDAO requirementDAO = new RequirementDAO(session);

        specificationRequirementDAO.addSpecificationRequirement(specificationDAO.get(specificationId),requirementDAO.get(requirementId));
        transaction.commit();
        session.close();
    }

    public void addRequirementToRelease(long releaseId, long requirementId){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ReleaseRequirementDAO releaseRequirementDAO= new ReleaseRequirementDAO(session);

        ReleaseDAO releaseDAO = new ReleaseDAO(session);
        RequirementDAO requirementDAO = new RequirementDAO(session);

        releaseRequirementDAO.addReleaseRequirement(releaseDAO.get(releaseId),requirementDAO.get(requirementId));
        transaction.commit();
        session.close();
    }

    public List<SpecificationEntity> getSpecByProjectId(long projectId) {
        Session session = sessionFactory.openSession();
        SpecificationDAO specificationDAO = new SpecificationDAO(session);
        List <SpecificationEntity> list = specificationDAO.getSpecificationsByProjectId(projectId);
        session.close();
        return list;
    }

    public List<ReleaseEntity> getReleasesByProjectId(long projectId) {
        Session session = sessionFactory.openSession();
        ReleaseDAO releaseDAO = new ReleaseDAO(session);
        List <ReleaseEntity> list = releaseDAO.getReleasesByProjectId(projectId);
        session.close();
        return list;
    }
}
