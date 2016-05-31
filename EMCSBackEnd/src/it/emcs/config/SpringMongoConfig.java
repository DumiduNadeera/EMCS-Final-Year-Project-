/*
 * package it.emcs.config;
 * 
 * import org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.data.mongodb.MongoDbFactory; import
 * org.springframework.data.mongodb.core.MongoTemplate; import
 * org.springframework.data.mongodb.core.SimpleMongoDbFactory;
 * 
 * import com.mongodb.MongoClient;
 * 
 * @Configuration public class SpringMongoConfig {
 * 
 * public @Bean MongoClient mongoClient() throws Exception { return new
 * MongoClient("127.0.0.1"); }
 * 
 * public @Bean MongoDbFactory mongoDbFactory() throws Exception { return new
 * SimpleMongoDbFactory(mongoClient(), "emcsdb"); }
 * 
 * public @Bean MongoTemplate mongoTemplate() throws Exception {
 * 
 * MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
 * 
 * return mongoTemplate;
 * 
 * }
 * 
 * }
 */