package org.tec.multistore.postgres.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.tec.multistore.Application;
import org.tec.multistore.postgres.entity.PsTest;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes={Application.class})
public class PsTestRepositoryTest {

    @Autowired
    @Qualifier("postgresTemplate")
    JdbcTemplate psTemplate;

    @Autowired
    PsTestRepository psTestRepository;

    @Test
    @Transactional
    public void test() {
        psTemplate.execute("delete from ps_test");

        PsTest t = new PsTest();
        t.setName("name");
        t.setValue("value");
        t.setCreatedOn(OffsetDateTime.now().truncatedTo(ChronoUnit.SECONDS));

        psTestRepository.save(t);

        assertTrue(t.getId() > 0);

        PsTest actual = psTestRepository.findByName(t.getName());

        assertNotNull(actual);

        assertEquals(t, actual);
    }
}
