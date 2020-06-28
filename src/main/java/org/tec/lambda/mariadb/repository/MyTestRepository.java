package org.tec.lambda.mariadb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.tec.lambda.mariadb.entity.MyTest;

import java.util.Optional;

@Repository
public interface MyTestRepository extends CrudRepository<MyTest, Long> {
    Optional<MyTest> findByName(String name);
}
