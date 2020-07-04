package org.tec.multistore.postgres.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.tec.multistore.postgres.entity.PsTest;

import java.util.Optional;

@Repository
public interface PsTestRepository extends CrudRepository<PsTest, Long> {
    Optional<PsTest> findByName(String name);
}
