package org.tec.multistore.mongo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.tec.multistore.mongo.entity.MoTest;

import java.util.Optional;

@Repository
public interface MoRepository extends CrudRepository<MoTest, Long> {
    Optional<MoTest> findByName(String name);
}
