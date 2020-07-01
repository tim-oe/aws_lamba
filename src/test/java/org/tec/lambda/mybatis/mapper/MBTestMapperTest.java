package org.tec.lambda.mybatis.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.tec.lambda.Application;
import org.tec.lambda.mybatis.entity.MBTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes={Application.class})
public class MBTestMapperTest {

    @Autowired
    MBTestMapper mbTestMapper;

    @Test
    @Transactional
    public void test () {
        MBTest t = new MBTest();
        t.setName("name");
        t.setValue("value");

        mbTestMapper.save(t);

        assertTrue(t.getId() > 0);

        MBTest actual = mbTestMapper.findByName(t.getName());

        assertNotNull(actual);

        assertEquals(t, actual);
    }
}
