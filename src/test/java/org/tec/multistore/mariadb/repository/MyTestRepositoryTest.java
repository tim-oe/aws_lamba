package org.tec.multistore.mariadb.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.tec.multistore.Application;
import org.tec.multistore.mariadb.entity.MyTest;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes={Application.class})
public class MyTestRepositoryTest {
    @Autowired
    MyTestRepository myTestRepository;

    @Test
    @Transactional
    public void test() {
        MyTest t = new MyTest();
        t.setName("name");
        t.setValue("value");
        t.setCreatedOn(OffsetDateTime.now());

        myTestRepository.save(t);

        assertTrue(t.getId() > 0);

        MyTest actual = myTestRepository.findByName(t.getName());

        assertNotNull(actual);

        assertEquals(t, actual);
    }
}
