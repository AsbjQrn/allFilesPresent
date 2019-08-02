package dk.asbjoern.foto.allfilespresent;


import dk.asbjoern.foto.allfilespresent.runner.Runner;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements ApplicationRunner {


    private Runner runner;

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }


    public Application(Runner runner) {
        this.runner = runner;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        this.runner.run();

    }


}
