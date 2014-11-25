package com.oneous.webapp.persistance.dao;

import com.oneous.webapp.persistance.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Abdullah Al Mamun Oronno (www.oneous.com)
 */

@Repository
@Transactional
public class UserDao {
    private static final Logger log = LoggerFactory.getLogger(UserDao.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void save(User user) {
        entityManager.persist(user);
    }

    public void update(User user) {
        entityManager.merge(user);
    }

    public User findUserByUsername(String username) {

        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User AS u WHERE u.username=:username", User.class);
        query.setParameter("username", username);

        return getSingleResultOrNull(query);
    }

    public User findUserByEmail(String email) {

        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User AS u WHERE u.email=:email", User.class);
        query.setParameter("email", email);

        return getSingleResultOrNull(query);
    }

    //reference: http://stackoverflow.com/questions/2002993/jpa-getsingleresult-or-null
    private User getSingleResultOrNull(TypedQuery<User> query) {
        query.setMaxResults(1);
        List<User> list = query.getResultList();
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public List<User> findAllUsers() {
        return entityManager.createQuery("SELECT u FROM User AS u", User.class).getResultList();
    }
}
