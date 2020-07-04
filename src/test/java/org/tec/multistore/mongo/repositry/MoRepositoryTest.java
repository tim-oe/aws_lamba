package org.tec.multistore.mongo.repositry;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.tec.multistore.Application;
import org.tec.multistore.mongo.entity.MoTest;
import org.tec.multistore.mongo.repository.MoRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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
        //mongo not storing precision TODO fix in converter?
        t.setCreatedOn(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));

        moRepository.save(t);

        Optional<MoTest> actual = moRepository.findByName(t.getName());

        assertTrue(actual.isPresent());

        assertEquals(t, actual.get());
    }
}
