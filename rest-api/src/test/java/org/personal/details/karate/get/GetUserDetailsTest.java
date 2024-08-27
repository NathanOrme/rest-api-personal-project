package org.personal.details.karate.get;

import com.intuit.karate.junit5.Karate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GetUserDetailsTest {

    @Value("${local.server.port}")  // Inject the random port
    private int port;

    @Karate.Test
    Karate testGetIssue() {
        return Karate.run("getUserDetails")
                .relativeTo(getClass())
                .systemProperty("karate.server.port", port + "");
    }

}
