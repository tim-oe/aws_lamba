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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * https://stackoverflow.com/questions/44373186/how-to-use-hikaricp-in-spring-boot-with-two-datasources-in-conjunction-with-flyw
 * https://www.baeldung.com/the-persistence-layer-with-spring-and-jpa
 * https://medium.com/@gokuldas.puthenpurakkal/using-multiple-data-sources-using-spring-boot-2-and-spring-data-d94769383646
 * https://github.com/gokuldas-puthenpurakkal/springboot2-multipledatasources-example
 */
@Configuration
@MapperScan(
        basePackages = {"org.tec.multistore.postgres.repository"},
        sqlSessionTemplateRef  = "postgresSessionTemplate")
public class PostgressDataSourceConfig {
    private static final String DATASOURCE_BEAN_NAME = "postgresDataSource";

    @Bean(name = "postgresDataSourceProperties")
    @ConfigurationProperties("datasource.postgres")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = DATASOURCE_BEAN_NAME)
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
    @Bean(name = "postgresSessionFactory")
    public SqlSessionFactory postgresSessionFactory(@Qualifier(DATASOURCE_BEAN_NAME) final DataSource ds) throws Exception {
        final SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(ds);
        return bean.getObject();
    }

    @Bean(name = "postgresTransactionManager")
    public DataSourceTransactionManager postgresTransactionManager(@Qualifier(DATASOURCE_BEAN_NAME) final DataSource ds) {
        return new DataSourceTransactionManager(ds);
    }

    @Bean(name = "postgresSessionTemplate")
    public SqlSessionTemplate postgresSessionTemplate(@Qualifier("postgresSessionFactory") final SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}