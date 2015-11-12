package com.highway2urhell.configuration;

import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories("com.highway2urhell.dao")
public class MongoConfiguration {

    @Value("${mongo.host}")
    private String mongoHost;
    @Value("${mongo.port}")
    private String mongoPort;
    @Value("${mongo.database}")
    private String mongoDatabase;
    @Value("${mongo.username}")
    private String mongoUsername;
    @Value("${mongo.password}")
    private String mongoPassword;

    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {
        String uri = (mongoUsername != null && mongoPassword !=null) ? "mongodb://"+mongoUsername+":"+mongoPassword+"@"+mongoHost+":"+mongoPort+"/"+mongoDatabase : "mongodb://"+mongoHost+":"+mongoPort+"/"+mongoDatabase;
        MongoClientURI mcu = new MongoClientURI(uri);
        return new SimpleMongoDbFactory(mcu);
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoDbFactory());
    }

}