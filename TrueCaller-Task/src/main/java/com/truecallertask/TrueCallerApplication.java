package com.truecallertask;

import com.truecallertask.core.User;
import com.truecallertask.core.UserView;
import com.truecallertask.data.UserDAO;
import com.truecallertask.data.UserViewDAO;
import com.truecallertask.resources.UserViewResource;
import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

public class TrueCallerApplication extends Application<TrueCallerApplicationConfiguration> {
    public static void main(String[] args) throws Exception {
        new TrueCallerApplication().run(args);
    }

    private final HibernateBundle<TrueCallerApplicationConfiguration> hibernateUserBundle =
            new HibernateBundle<TrueCallerApplicationConfiguration>(User.class) {
                @Override
                public DataSourceFactory getDataSourceFactory(TrueCallerApplicationConfiguration configuration) {
                    return configuration.getDataSourceFactory();
                }
            };

    private final HibernateBundle<TrueCallerApplicationConfiguration> hibernateUserViewBundle =
            new HibernateBundle<TrueCallerApplicationConfiguration>(UserView.class) {
                @Override
                public DataSourceFactory getDataSourceFactory(TrueCallerApplicationConfiguration configuration) {
                    return configuration.getDataSourceFactory();
                }
            };

    @Override
    public String getName() {
        return "TrueCaller Java-Task";
    }

    @Override
    public void initialize(Bootstrap<TrueCallerApplicationConfiguration> bootstrap) {
// Enable variable substitution with environment variables

        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(
                        bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(false)
                )
        );

        bootstrap.addBundle(hibernateUserBundle);
        bootstrap.addBundle(hibernateUserViewBundle);
    }

    @Override
    public void run(TrueCallerApplicationConfiguration trueCallerApplicationConfiguration, Environment environment) throws Exception {

        //final DBIFactory factory = new DBIFactory();
        //final DBI jdbi = factory.build(environment, trueCallerApplicationConfiguration.getDataSourceFactory(), "sqlite");
        final UserDAO userDAO = new UserDAO(hibernateUserBundle.getSessionFactory()); //jdbi.onDemand(UserDAO.class);
        final UserViewDAO userViewDAO = new UserViewDAO(hibernateUserViewBundle.getSessionFactory()); //jdbi.onDemand(UserViewDAO.class);
        environment.jersey().register(new UserViewResource(userDAO, userViewDAO));
    }
}
