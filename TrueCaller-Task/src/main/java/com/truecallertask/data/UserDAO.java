package com.truecallertask.data;

import com.google.common.base.Optional;
import com.truecallertask.core.User;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDAO extends AbstractDAO<User> {
    public UserDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Optional<User> findById(Long id) {
        return Optional.fromNullable(get(id));
    }
    public User create(User user) {
        return persist(user);
    }
    public List<User> findAll() {
        return list(namedQuery("com.truecallertask.core.User.findAll"));
    }
}
