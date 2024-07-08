package id.my.hendisantika.springboottestcontainersmysql.reuse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MySQLContainer;

import javax.sql.DataSource;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-testcontainers-mysql
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 7/8/24
 * Time: 16:30
 * To change this template use File | Settings | File Templates.
 */
@SpringBootTest
public class MySQLContainerBaseTest {

    @ServiceConnection
    protected static final MySQLContainer mySQLContainer = new MySQLContainer<>("mysql:8.4.0")
            .withDatabaseName("testcontainer")
            .withUsername("yuji")
            .withPassword("S3cret")
            .withTmpFs(Map.of("/var/lib/mysql", "rw"))
            .withReuse(true); //to use this, enable testcontainers.reuse.enable in testcontainers properties

    static {
        mySQLContainer.start();
    }
    // see https://www.testcontainers.org/features/reuse/

    @Autowired
    protected DataSource dataSource;

    //As of Spring Boot 3.1, with @ServiceConnection you don't need the following method to set up the connection
   /*
    @DynamicPropertySource
    private static void setupProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mySQLContainer::getUsername);
        registry.add("spring.datasource.password", mySQLContainer::getPassword);
    }
    */
}
