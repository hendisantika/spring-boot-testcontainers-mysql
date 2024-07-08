package id.my.hendisantika.springboottestcontainersmysql;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.MountableFile;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-testcontainers-mysql
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 7/8/24
 * Time: 16:36
 * To change this template use File | Settings | File Templates.
 */
@SpringBootTest
@Testcontainers
class MySQLCustomContainerTest {

    @Container
    private static final GenericContainer mySQLContainer = new GenericContainer<>("mysql:8.4.0")
            .withEnv("MYSQL_ROOT_PASSWORD", "pass")
            .withEnv("MYSQL_DATABASE", "testcontainer")
            .withEnv("MYSQL_USER", "user")
            .withEnv("MYSQL_PASSWORD", "pass")
            .withExposedPorts(3306)
            .waitingFor(Wait.forLogMessage(".*mysqld: ready for connections.*", 2))
            .withCopyFileToContainer(MountableFile.forClasspathResource("init.sql"), "/docker-entrypoint-initdb.d/schema.sql");
    @Autowired
    private DataSource dataSource;

    @DynamicPropertySource
    private static void setupProperties(DynamicPropertyRegistry registry) {
        String url = "jdbc:mysql://localhost:" + mySQLContainer.getMappedPort(3306) + "/testcontainer";
        registry.add("spring.datasource.url", () -> url);
        registry.add("spring.datasource.username", () -> "user");
        registry.add("spring.datasource.password", () -> "pass");
    }

    @Test
    void testTableExists() throws SQLException {
        TableTestAssertion.assertTableExists(dataSource);
    }
}
