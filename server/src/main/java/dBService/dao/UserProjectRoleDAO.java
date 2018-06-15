package dBService.dao;

import dBService.entities.ProjectEntity;
import dBService.entities.ProjectRoleEntity;
import dBService.entities.UserEntity;
import dBService.entities.UserProjectRoleEntity;
import org.hibernate.Session;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserProjectRoleDAO {
    private final Session session;

    public UserProjectRoleDAO(Session session) {
        this.session = session;
    }

    /*public UserProjectRoleEntity get(long id)  throws HibernateException {      ////какая-то дичь
        return session.get(UserProjectRoleEntity.class, id);
    }*/

    public List<UserProjectRoleEntity> getByUser(UserEntity user)  throws HibernateException {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserProjectRoleEntity> criteria = builder.createQuery(UserProjectRoleEntity.class);
        Root<UserProjectRoleEntity> root = criteria.from(UserProjectRoleEntity.class);
        ParameterExpression<UserEntity> parameter = builder.parameter(UserEntity.class);
        criteria.select(root).where(builder.equal(root.get("user"), parameter));
        Query<UserProjectRoleEntity> query = session.createQuery(criteria);
        query.setParameter(parameter, user);
        return query.getResultList();
    }

    public List<UserProjectRoleEntity> getByUser(String login)  throws HibernateException {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserProjectRoleEntity> criteria = builder.createQuery(UserProjectRoleEntity.class);
        Root<UserProjectRoleEntity> root = criteria.from(UserProjectRoleEntity.class);
        ParameterExpression<UserEntity> parameter = builder.parameter(UserEntity.class);
        criteria.select(root).where(builder.equal(root.get("user"), parameter));
        Query<UserProjectRoleEntity> query = session.createQuery(criteria);
        UserDAO userDAO = new UserDAO(session);
        query.setParameter(parameter, userDAO.get(login));
        return query.getResultList();
    }

    public List<UserProjectRoleEntity> getByProjectId(long projectId)  throws HibernateException {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserProjectRoleEntity> criteria = builder.createQuery(UserProjectRoleEntity.class);
        Root<UserProjectRoleEntity> root = criteria.from(UserProjectRoleEntity.class);
        ParameterExpression<ProjectEntity> parameter = builder.parameter(ProjectEntity.class);
        criteria.select(root).where(builder.equal(root.get("project"), parameter));
        Query<UserProjectRoleEntity> query = session.createQuery(criteria);
        ProjectDAO projectDAO = new ProjectDAO(session);
        query.setParameter(parameter, projectDAO.get(projectId));
        return query.getResultList();
    }

    public void addUserProjectRole(long projectId, String projectRoleName, String userLogin) {
        ProjectRoleDAO projectRoleDAO= new ProjectRoleDAO(session);
        ProjectDAO projectDAO = new ProjectDAO(session);
        UserDAO userDAO = new UserDAO(session);
        ProjectRoleEntity role = projectRoleDAO.get(projectRoleName);
        ProjectEntity project = projectDAO.get(projectId);
        UserEntity user = userDAO.get(userLogin);
        session.save(new UserProjectRoleEntity(user,project,role));
    }

    public void addUserProjectRole(UserEntity user,ProjectEntity project, ProjectRoleEntity role) {
        session.save(new UserProjectRoleEntity(user,project,role));
    }

    public void addUserProjectRole(UserProjectRoleEntity userProjectRoleEntity) {
        session.save(userProjectRoleEntity);
    }
}
