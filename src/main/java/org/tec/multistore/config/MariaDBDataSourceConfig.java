package org.tec.multistore.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * https://stackoverflow.com/questions/44373186/how-to-use-hikaricp-in-spring-boot-with-two-datasources-in-conjunction-with-flyw
 * https://www.baeldung.com/the-persistence-layer-with-spring-and-jpa
 */
@Configuration
@EnableTransactionManagement
@MapperScan(
        basePackages = {"org.tec.multistore.mariadb.repository"},
        sqlSessionTemplateRef  = "mariadbSessionTemplate")
public class MariaDBDataSourceConfig {
    private static final String DATASOURCE_BEAN_NAME = "mariadbDataSource";

    @Primary
    @Bean(name = "mariadbDataSourceProperties")
    @ConfigurationProperties("datasource.mariadb")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean(name = DATASOURCE_BEAN_NAME)
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

    @Bean(name = "mariadbSessionFactory")
    @Primary
    public SqlSessionFactory mariadbSessionFactory(@Qualifier(DATASOURCE_BEAN_NAME) final DataSource ds) throws Exception {
        final SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(ds);
        return bean.getObject();
    }

    @Bean(name = "mariadbTransactionManager")
    @Primary
    public DataSourceTransactionManager mariadbTransactionManager(@Qualifier(DATASOURCE_BEAN_NAME) final DataSource ds) {
        return new DataSourceTransactionManager(ds);
    }

    @Bean(name = "mariadbSessionTemplate")
    @Primary
    public SqlSessionTemplate mariadbSessionTemplate(@Qualifier("mariadbSessionFactory") final SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}