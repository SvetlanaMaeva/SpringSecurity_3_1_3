package ru.kata.spring.boot_security.demo.dao;


import ru.kata.spring.boot_security.demo.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void add(User user) {
        entityManager.persist(user);
    }

    public void delete(Long id) {
        List<User> users = listUsers();
        for (User user : users) {
            if (user.getId().equals(id)){
                entityManager.remove(user);
            }
        }
    }

    public void update(Long id, User user) {
        User newUser = getUser(id);
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
    }

    public User getUser(Long id) {
        return entityManager.createQuery("FROM User user WHERE user.id = :id", User.class)
                .setParameter("id", id).getSingleResult();
    }

    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        Query query = entityManager.createQuery("from User", User.class);
        return query.getResultList();

    }
}
