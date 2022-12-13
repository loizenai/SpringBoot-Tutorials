# SpringBoot-Tutorials
This Repository is built for SpringBoot Tutorials

SpringBoot Article Lists:
------------------------

Spring Boot
springboot-tutorial
Spring Boot helps us build stand-alone, production Spring Applications easily, less configuration then rapidly start new projects.

Features:

Stand-alone Spring applications
Embedded Serverlet Container: Tomcat, Jetty or Undertow.
Support ‘Starter’ POMs to make your Maven configuration easily ways.
Automatically configure Spring.
Provide production-ready features such as metrics, health checks and externalized configuration
No code generation, No requirement for XML configuration
< Spring Framework


SQL Databases
1. Configure DataSource
Spring Boot supports auto-configured DataSource if you use spring-boot-starter-jdbc or spring-boot-starter-data-jpa ‘starters’. Details configuration at application.properties file:

spring.datasource.url=jdbc:postgresql://ozenero.com/test
spring.datasource.username=user
spring.datasource.password=pass
But How to configure 2 or Multi-Datasource?

Steps to do:
– Open application.properties, configure datasource’s info:

#db1 datasource
db1.datasource.url=jdbc:postgresql://url1
db1.datasource.username=user1
db1.datasource.password=password1

#db2 datasource
db2.datasource.url=jdbc:postgresql://url2
db2.datasource.username=user2
db2.datasource.password=password2
– Mark one of the DataSource with @Primary:

@Configuration
public class DataSourceBeans {
   
    @Primary
    @Bean(name="db1.datasource")
    @ConfigurationProperties(prefix="db1.datasource")
    public DataSource db1DataSource(){
        return DataSourceBuilder.create().build();
    }
   
    @Bean(name="db2.datasource")
    @ConfigurationProperties(prefix="db2.datasource")
    public DataSource db2DataSource(){
        return DataSourceBuilder.create().build();
    } 
}
– Now they are ready to be injected:

...
// datasource 1
@Autowired
@Qualifier("db1.datasource")
DataSource dataSource1;

// datasource 2
@Autowired
@Qualifier("db2.datasource")
DataSource dataSource2;
>>> More details at: How to configure multi Postgres DataSources with Springboot

2. Using JdbcTemplate
Spring’s Jdbc Template is used to access and manipulate databases. Spring Boot provides auto-configuration JdbcTemplate bean. So we can easily inject it via @Autowire. Or using supported function: getJdbcTemplate() of Spring’s JdbcDaoSupport class:

...
@Repository
public class CustomerDaoImpl extends JdbcDaoSupport implements CustomerDao{
  
    @Autowired
    DataSource dataSource;
  
    @PostConstruct
    private void initialize(){
        setDataSource(dataSource);
    }
    
    ...
    @Override
    public void insert(Customer cus) {
        String sql = "INSERT INTO customer " + "(CUST_ID, NAME, AGE) VALUES (?, ?, ?)" 
        getJdbcTemplate().update(sql, new Object[]{
            cus.getCustId(), cus.getName(), cus.getAge()
        });
    }
    ...
}
>>> More details at: How to use Spring JDBC Template with Spring Boot for Postgres DataBase

3. Spring JPA and Spring Data
For persistence, SpringBoot provides @Entity anonation for scanning entities.

...
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
  
@Entity
@Table(name="company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    ...     
}
>>> Example to practice:
– Spring JPA – One to Many Relationship
– Spring JPA – Many to Many relationship

With RestAPIs ->
– SpringBoot + Hibernate Spring JPA One-to-One Association + PostgreSQL | CRUD RestAPIs Post/Get/Put/Delete
– Spring JPA/Hibernate One-to-Many Association + PostgreSQL | SpringBoot CRUD RestAPIs Post/Get/Put/Delete example

4. H2 Database
H2 database has small footprint (smaller than 1.5 MB) with low memory requirements. It supports for multiple schemas and standard SQL, JDBC API. We can use H2 with disk based or in-memory databases.

H2 can be built by following mode:
– Embedded mode (local connections)
– Server mode (remote connections)
– Mixed mode (local and remote connections)

With Embedded Mode, an application uses JDBC to create a H2 database within the same JVM so it’s very fast to exchange data.

How to configure it with SpringBoot?
-> Spring Boot has a built in H2 database, so We just add below dependency:

<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
</dependency>
>>> More details at: Integrate H2 database with SpringBoot & Spring JPA in Embedded mode

If your project uses H2 database to develop and also enable Spring Security, then when accessing to H2 console path: /h2_console, an error Access Denied Page will be thrown.

Why?
-> By default, Spring Security will block /h2_console path of H2 database.
How to resolve it?
-> Solution is a simple configuration with Spring Security as below segment code:

@Override
protected void configure(HttpSecurity http) throws Exception {
	http.authorizeRequests()
		...
		
	http.csrf().disable();
	http.headers().frameOptions().disable();
}
http.csrf().disable(): disable CRSF.
http.headers().frameOptions().disable(): H2 database console runs inside a frame, So we need to disable X-Frame-Options in Spring Security.

>>> More details at: How to configure Spring Security to access H2 database console in Spring Boot project

NoSQL Technologies
1. MongoDB
MongoDB is an open-source NoSQL document database, written using C++. Spring Boot provides an spring-boot-starter-data-mongodb to work with MongoDB.

To define MongoDb configured beans, use:
– MongoDbFactory: an interface for factories to create DB instances.
– MongoOperations: an interface that specifies a basic set of MongoDB operations.

@Configuration
public class AppConfig {
    @Bean
    public MongoDbFactory mongoDbFactory() throws UnknownHostException{
        return new SimpleMongoDbFactory(new MongoClient("localhost", 27017), "test");
    }
   
    @Bean
    public MongoOperations mongoOperations() throws UnknownHostException{
        return new MongoTemplate(mongoDbFactory());
    }
}
To define Document model, use @Document

>>> Example for details at: Spring MongoOperations to access MongoDB

2. Neo4j
Neo4j is a highly scalable, native graph database. Spring Boot supports Spring Neo4J configuration with module: spring-boot-starter-data-neo4j.

– Configuration for connecting to Neo4J:

spring.data.neo4j.uri=http://my-server:7474
spring.data.neo4j.username=neo4j
spring.data.neo4j.password=admin
– Create a Neo4J repository by extends GraphRepository, sample:

...
import org.springframework.data.neo4j.repository.GraphRepository;

public interface StudentRepository extends GraphRepository<student> {
...
}
</student>
– Some annotations for Neo4j database:
@NodeEntity: Identifies a domain entity as being backed by a node in the graph.
@GraphId: Identifies the field in the domain entity which is to be mapped to the id property of its backing node in the graph.
@Relationship: purpose for creating relationship of entities in Neo4j.

>>> Example for more details at: Spring Neo4J

3. Gemfire
Spring Data REST provides a mechanics for creating and retrieving an Object from Gemfire storage.

Configuration steps:
– Use needed dependencies: spring-boot-starter-data-rest, spring-boot-starter-data-gemfire:

<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-gemfire</artifactId>
</dependency>
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-rest</artifactId>
</dependency>
– Use annotation @Region for mapping data:

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.gemfire.mapping.Region;
  
@Region("customer")
public class Customer {
     
    private static AtomicLong COUNTER = new AtomicLong(0L);
     
    @Id
    private Long id;
    private String firstname;
    private String lastname;
    private int age;
      
    @PersistenceConstructor
    public Customer() {
        this.id = COUNTER.incrementAndGet();
    }
    ...
}
– Config CacheFactoryBean and LocalRegionFactoryBean:

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.gemfire.CacheFactoryBean;
import org.springframework.data.gemfire.LocalRegionFactoryBean;
 
import com.gemstone.gemfire.cache.GemFireCache;
import com.javasampleapproach.gemfirerestapi.model.Customer;
 
@Configuration
public class GemfireConfig {
    @Bean
    Properties gemfireProperties() {
        Properties gemfireProperties = new Properties();
        gemfireProperties.setProperty("name", "EmbeddedGemfireApplication");
        gemfireProperties.setProperty("mcast-port", "0");
        return gemfireProperties;
    }
      
    @Bean
    CacheFactoryBean gemfireCache() {
        CacheFactoryBean gemfireCache = new CacheFactoryBean();
        gemfireCache.setProperties(gemfireProperties());
        return gemfireCache;
    }
      
    @Bean
    LocalRegionFactoryBean customerRegion(final GemFireCache cache) {
        LocalRegionFactoryBean customerRegion = new LocalRegionFactoryBean<>();
        customerRegion.setCache(cache);
        customerRegion.setName("customer");
        customerRegion.setPersistent(false);
        return customerRegion;
    }
}
– Create a Gemfire repository by extends CrudRepository interface:

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
 
@RepositoryRestResource(collectionResourceRel = "customer", path = "customer")
public interface CustomerRepository extends CrudRepository<customer, long=""> {
    Customer findByFirstname(@Param("firstname") String firstname);
    Customer findByLastname(@Param("lastname") String lastname);
    Iterable<customer> findByAgeGreaterThan(@Param("age") int age);
    Iterable<customer> findByAgeLessThan(@Param("age") int age);
}
</customer></customer></customer,>
>>> Example for more details at: How to create a SpringBoot Gemfire RestfulApi
