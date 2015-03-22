package com.truecallertask.data;

import com.google.common.base.Optional;
import com.truecallertask.core.UserView;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserViewDAO extends AbstractDAO<UserView> {
    public UserViewDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Optional<UserView> findById(Long id) {
        return Optional.fromNullable(get(id));
    }
    public UserView create(UserView userView) {
        return persist(userView);
    }
    public List<UserView> findAll() {
        return list(namedQuery("com.truecallertask.core.UserView.findAll"));
    }
}
