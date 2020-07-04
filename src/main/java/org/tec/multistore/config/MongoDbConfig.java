package org.tec.multistore.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.tec.multistore.mongo.converter.LocalDateTimeReadConverter;
import org.tec.multistore.mongo.converter.LocalDateTimeWriteConverter;

import java.util.Arrays;

/**
 * https://www.baeldung.com/spring-data-mongodb-zoneddatetime
 * https://stackoverflow.com/questions/23972002/java-8-date-time-jsr-310-types-mapping-with-spring-data-mongodb?rq=1
 */
@Configuration
@EnableMongoRepositories(basePackages = {"org.tec.multistore.mongo.repository"})
public class MongoDbConfig extends AbstractMongoClientConfiguration {

    //TODO add value...

    @Override
    protected String getDatabaseName() {
        return "multi";
    }

    @Override
    public MongoCustomConversions customConversions() {
        return new MongoCustomConversions(
                Arrays.asList(
                        new LocalDateTimeReadConverter(),
                        new LocalDateTimeWriteConverter()));
    }
}
