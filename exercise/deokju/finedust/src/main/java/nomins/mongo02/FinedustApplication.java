package nomins.mongo02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FinedustApplication implements CommandLineRunner {

    @Autowired
    private WritingFineDustService dustService;

	public static void main(String[] args) {
		SpringApplication.run(FinedustApplication.class, args);
	}


    @Override
    public void run(String... args) throws Exception {
        dustService.writeAll();
    }
}
