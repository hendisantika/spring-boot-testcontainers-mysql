package id.my.hendisantika.springboottestcontainersmysql;

import org.springframework.boot.SpringApplication;

public class TestSpringBootTestcontainersMysqlApplication {

    public static void main(String[] args) {
        SpringApplication.from(SpringBootTestcontainersMysqlApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
