package id.my.hendisantika.springboottestcontainersmysql;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
@Disabled
class SpringBootTestcontainersMysqlApplicationTests {

    @Test
    void contextLoads() {
    }

}
