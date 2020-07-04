package org.tec.multistore;

import org.bson.BsonDocument;
import org.bson.BsonString;
import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes={Application.class})
public class ApplicationTest {

    @Autowired
    @Qualifier("mariadbTemplate")
    JdbcTemplate myTemplate;

    @Autowired
    @Qualifier("postgresTemplate")
    JdbcTemplate psTemplate;

    @Autowired
    @Qualifier("mariadbDataSource")
    DataSource myDataSource;

    @Autowired
    @Qualifier("postgresDataSource")
    DataSource psDataSource;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    Application application;

    @Test
    public void testInit() {
        assertNotNull(application);
        assertNotNull(myTemplate);
        assertNotNull(psTemplate);
        assertNotNull(myDataSource);
        assertNotNull(psDataSource);
        assertNotNull(mongoTemplate);
    }

    @Test
    public void testDatasources() {
        String myVersion = myTemplate.queryForObject("select version();", new Object[]{}, String.class);
        assertNotNull(myVersion);
        assertTrue(myVersion.contains("MariaDB"));
        System.out.println(myVersion);

        String psVersion = psTemplate.queryForObject("select version();", new Object[]{}, String.class);
        assertNotNull(psVersion);
        assertTrue(psVersion.contains("PostgreSQL"));
        System.out.println(psVersion);

        Document doc = mongoTemplate.getDb().runCommand(new BsonDocument("buildinfo", new BsonString("")));

        System.out.println(doc.get("version"));

        for (String collection : mongoTemplate.getCollectionNames()) {
            System.out.println(collection);
        }
    }
}