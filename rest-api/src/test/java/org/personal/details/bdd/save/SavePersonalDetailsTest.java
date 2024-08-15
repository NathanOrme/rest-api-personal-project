package org.personal.details.bdd.save;

import com.intuit.karate.junit5.Karate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SavePersonalDetailsTest {

    @Value("${local.server.port}")  // Inject the random port
    private int port;

    @Karate.Test
    Karate testPostIssue() {
        return Karate.run("savePersonalDetails")
                .relativeTo(getClass())
                .systemProperty("karate.server.port", port + "");
    }

}
