package org.tec.multistore.postgres.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.OffsetDateTimeTypeHandler;
import org.springframework.stereotype.Repository;
import org.tec.multistore.postgres.entity.PsTest;

import java.time.OffsetDateTime;

@Repository
public interface PsTestRepository {
    @Insert("Insert into ps_test(name, value, created_on) values (#{name}, #{value}, #{createdOn})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    void save(final PsTest test);

    @Select("SELECT * FROM ps_test WHERE name = #{name}")
    @Results({
            @Result(property = "createdOn", column = "created_on", jdbcType = JdbcType.TIMESTAMP_WITH_TIMEZONE, javaType = OffsetDateTime.class, typeHandler = OffsetDateTimeTypeHandler.class)
    })
    PsTest findByName(@Param("name") String name);
}
