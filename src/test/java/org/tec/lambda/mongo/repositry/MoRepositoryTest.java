package org.tec.lambda.mongo.repositry;

import com.mongodb.BasicDBObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.tec.lambda.Application;
import org.tec.lambda.mariadb.entity.MyTest;
import org.tec.lambda.mongo.entity.MoTest;
import org.tec.lambda.mongo.repository.MoRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes={Application.class})
public class MoRepositoryTest {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    MoRepository moRepository;

    @Test
    public void test() {
        moRepository.deleteAll();

        MoTest t = new MoTest();
        t.setName("name");
        t.setValue("value");

        moRepository.save(t);

        Optional<MoTest> actual = moRepository.findByName(t.getName());

        assertTrue(actual.isPresent());

        assertEquals(t, actual.get());
    }
}
