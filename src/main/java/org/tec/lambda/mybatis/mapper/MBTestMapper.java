package org.tec.lambda.mybatis.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.tec.lambda.mybatis.entity.MBTest;

@Mapper
public interface MBTestMapper {

    @Insert("Insert into my_test(name, value) values (#{name}, #{value})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    void save(MBTest test);

    @Select("SELECT * FROM my_test WHERE name = #{name}")
    MBTest findByName(@Param("name") String name);

}
