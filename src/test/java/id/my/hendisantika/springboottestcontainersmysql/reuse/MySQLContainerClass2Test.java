package id.my.hendisantika.springboottestcontainersmysql.reuse;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-testcontainers-mysql
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 7/8/24
 * Time: 16:32
 * To change this template use File | Settings | File Templates.
 */
public class MySQLContainerClass2Test extends MySQLContainerBaseTest {

    @Test
    void testMySQLContainerIsRunning() {
        assertThat(mySQLContainer.isRunning()).isTrue();
    }
}
