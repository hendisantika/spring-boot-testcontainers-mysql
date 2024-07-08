package id.my.hendisantika.springboottestcontainersmysql;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-testcontainers-mysql
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 7/8/24
 * Time: 16:35
 * To change this template use File | Settings | File Templates.
 */
@SpringBootTest(properties = "spring.datasource.url=jdbc:tc:mysql:8.4.0:///testcontainer?TC_INITSCRIPT=init.sql&TC_TMPFS=/var/lib/mysql:rw")
class MySQLContainerUrlTest {

    @Test
    void testTableExists(@Autowired DataSource dataSource) throws SQLException {
        TableTestAssertion.assertTableExists(dataSource);
    }
}
