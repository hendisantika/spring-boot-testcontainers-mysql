package id.my.hendisantika.springboottestcontainersmysql;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-testcontainers-mysql
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 7/8/24
 * Time: 16:34
 * To change this template use File | Settings | File Templates.
 */
@SpringBootTest
@Testcontainers
class MySQLContainerTest {

    @Container
    @ServiceConnection
    private static final MySQLContainer mySQLContainer = new MySQLContainer<>("mysql:8.4.0")
            .withDatabaseName("testcontainer")
            .withUsername("user")
            .withPassword("pass")
            .withInitScript("init.sql");
    @Autowired
    private DataSource dataSource;

    //As of Spring Boot 3.1, with @ServiceConnection
    // you don't need the following method to set up the connection
    /*@DynamicPropertySource
    private static void setupProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mySQLContainer::getUsername);
        registry.add("spring.datasource.password", mySQLContainer::getPassword);
    }*/

    @Test
    void testMySQLContainerIsRunning() {
        assertThat(mySQLContainer.isRunning()).isTrue();
    }

    @Test
    void testTableExists() throws SQLException {
        TableTestAssertion.assertTableExists(dataSource);
    }
}
