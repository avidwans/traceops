import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = {"controller"})
public class TraceOpsSpringBootApp {

    public static void main(String[] args) {
        SpringApplication.run(TraceOpsSpringBootApp.class, args);
    }

}
