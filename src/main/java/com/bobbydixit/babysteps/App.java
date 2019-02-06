package com.bobbydixit.babysteps;


import com.bobbydixit.babysteps.entities.BaseEntity;
import com.bobbydixit.babysteps.resources.BaseResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.zapodot.hystrix.bundle.HystrixBundle;

public class App extends Application<AppConfig> {


  private final HibernateBundle<AppConfig> hibernateBundle = new HibernateBundle<AppConfig>(
      BaseEntity.class
  ) {
    public DataSourceFactory getDataSourceFactory(AppConfig configuration) {
      return configuration.getDataSourceFactory();
    }
  };
//
  @Override
  public void initialize(Bootstrap<AppConfig> bootstrap) {

    bootstrap.addBundle(hibernateBundle);
//
    bootstrap.addBundle(new MigrationsBundle<AppConfig>() {
      public DataSourceFactory getDataSourceFactory(AppConfig configuration) {
        return configuration.getDataSourceFactory();
      }
    });
//
    bootstrap.addBundle(HystrixBundle.withDefaultSettings());
  }

  @Override
  public void run(AppConfig appConfig, Environment environment) {
    environment.jersey().register(new BaseResource());

  }

  @Override
  public String getName() {
    return "app";
  }

  public static void main(String[] args) throws Exception {
    new App().run(args);
  }
}
