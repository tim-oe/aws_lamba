package org.tec.lambda.mariadb.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.tec.lambda.Application;
import org.tec.lambda.mariadb.entity.MyTest;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

        myTestRepository.save(t);

        assertTrue(t.getId() > 0);

        Optional<MyTest> actual = myTestRepository.findByName(t.getName());

        assertTrue(actual.isPresent());

        assertEquals(t, actual.get());
    }
}
