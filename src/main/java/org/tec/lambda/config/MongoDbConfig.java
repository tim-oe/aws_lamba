package org.tec.lambda.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = {
        "org.tec.lambda.mongo.entity",
        "org.tec.lambda.mongo.repository"})
public class MongoDbConfig {
// TODO
}
