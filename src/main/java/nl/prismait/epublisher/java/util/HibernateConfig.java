package nl.prismait.epublisher.java.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.MultiTenancyStrategy;
import org.hibernate.cfg.Environment;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import nl.prismait.hibernate.SimpleMultiTenantConnectionProvider;
import nl.prismait.hibernate.WebSessionCurrentTenantIdentifierResolver;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {

    @Autowired
    private WebSessionCurrentTenantIdentifierResolver tenantIdentifierResolver;
    
    @Autowired
    private DataSource dataSource;

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {

        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("nl.prismait.epublisher.java.model", "nl.prismait.epublisher.java.dao");
        sessionFactory.setHibernateProperties(hibernateProperties());
        sessionFactory.setMultiTenantConnectionProvider(multiTenantConnectionProvider());
        sessionFactory.setCurrentTenantIdentifierResolver(tenantIdentifierResolver);
        return sessionFactory;
    }

    @Bean
    public HibernateTransactionManager transactionManager(LocalSessionFactoryBean sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory.getObject());
        return transactionManager;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "nl.prismait.hibernate.PostgreSQLDialectUuid");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.multiTenancy", "SCHEMA");
//        properties.put("hibernate.tenant_identifier_resolver", "WebSessionTenantIdentifierResolver");
//        properties.put("hibernate.multi_tenant_connection_provider", "SimpleMultiTenantConnectionProvider");
        return properties;
    }
    
    @Bean
    public MultiTenantConnectionProvider multiTenantConnectionProvider() {
        SimpleMultiTenantConnectionProvider connectionProvider = new SimpleMultiTenantConnectionProvider();
        connectionProvider.setDataSource(dataSource);
        return connectionProvider;
    }

    @Bean
    public CurrentTenantIdentifierResolver currentTenantIdentifierResolver() {
        return tenantIdentifierResolver;
    }
}
