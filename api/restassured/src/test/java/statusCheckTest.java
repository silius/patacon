import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.core.IsEqual.equalTo;

public class statusCheckTest extends BaseTest {
    private String URI = "/v1/examen/status";

    @Test
    public void checkStatus() {
        when()
                .get(String.format("%s",URI))
        .then()
            .body("status", equalTo("Listos para el examen"));
    }
}
