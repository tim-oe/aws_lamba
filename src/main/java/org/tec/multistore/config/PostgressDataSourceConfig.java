package org.tec.multistore.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;

/**
 * https://stackoverflow.com/questions/44373186/how-to-use-hikaricp-in-spring-boot-with-two-datasources-in-conjunction-with-flyw
 * https://www.baeldung.com/the-persistence-layer-with-spring-and-jpa
 * https://medium.com/@gokuldas.puthenpurakkal/using-multiple-data-sources-using-spring-boot-2-and-spring-data-d94769383646
 * https://github.com/gokuldas-puthenpurakkal/springboot2-multipledatasources-example
 */
@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "postgresEntityManagerFactory",
        transactionManagerRef = "postgresTransactionManager",
        basePackages = {"org.tec.multistore.postgres.repository"})
public class PostgressDataSourceConfig {
    @Bean(name = "postgresDataSourceProperties")
    @ConfigurationProperties("datasource.postgres")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "postgresDataSource")
    @ConfigurationProperties(prefix = "datasource.postgres-hikari")
    public DataSource dataSource(@Qualifier("postgresDataSourceProperties") final DataSourceProperties dsp) {
        return dsp.
                initializeDataSourceBuilder().
                type(HikariDataSource.class)
                .build();
    }

    @Bean(name = "postgresTemplate")
    public JdbcTemplate jdbcTemplate(@Qualifier("postgresDataSource") final DataSource ds){
        return new JdbcTemplate(ds);
    }

    @Bean(name = "postgresEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            final EntityManagerFactoryBuilder builder,
            @Qualifier("postgresDataSource") final DataSource ds) {

        final HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL82Dialect");

        return builder
                .dataSource(ds)
                .packages("org.tec.multistore.postgres.entity")
                .properties(properties)
                .persistenceUnit("postgres")
                .build();
    }

    @Bean(name = "postgresTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("postgresEntityManagerFactory") final EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}