package org.tec.lambda.postgres.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.tec.lambda.postgres.entity.PsTest;

@Repository
public interface PsTestRepository extends CrudRepository<PsTest, Long> {
}
