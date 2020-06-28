package org.tec.lambda.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

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
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "mariadbEntityManagerFactory",
        transactionManagerRef = "mariadbTransactionManager",
        basePackages = {"org.tec.lambda.mariadb.repository"})
public class MariaDBDataSourceConfig {
    @Primary
    @Bean(name = "mariadbDataSourceProperties")
    @ConfigurationProperties("datasource.mariadb")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean(name = "mariadbDataSource")
    @ConfigurationProperties(prefix = "datasource.mariadb-hikari")
    public DataSource dataSource(@Qualifier("mariadbDataSourceProperties") final DataSourceProperties dsp) {
        return dsp.
                initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Primary
    @Bean(name = "mariadbTemplate")
    public JdbcTemplate jdbcTemplate(@Qualifier("mariadbDataSource") final DataSource ds){
        return new JdbcTemplate(ds);
    }

    @Primary
    @Bean(name = "mariadbEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            final EntityManagerFactoryBuilder builder,
            @Qualifier("mariadbDataSource") final DataSource ds) {

        final HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MariaDBDialect");

        return builder
                .dataSource(ds)
                .packages("org.tec.lambda.mariadb.entity")
                .properties(properties)
                .persistenceUnit("mariadb")
                .build();
    }

    @Primary
    @Bean(name = "mariadbTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("mariadbEntityManagerFactory") final EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}