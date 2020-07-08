package org.tec.multistore.mariadb.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;
import org.tec.multistore.mariadb.entity.MyTest;

import java.time.OffsetDateTime;

@Repository
public interface MyTestRepository {

    @Insert("Insert into my_test(name, value, created_on) values (#{name}, #{value}, #{createdOn})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    void save(final MyTest test);

    @Select("SELECT * FROM my_test WHERE name = #{name}")
    @Results({
            @Result(property = "createdOn", column = "created_on", javaType = OffsetDateTime.class, jdbcType = JdbcType.TIMESTAMP)
    })
    MyTest findByName(@Param("name") String name);
}
