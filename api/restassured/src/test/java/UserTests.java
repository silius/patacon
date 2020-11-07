import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.core.IsEqual.equalTo;

public class UserTests extends BaseTest {

    private String name;

    private String TURL = "/v1/examen/randomName";

    @Test
    public void putName() {

        request
                .put(String.format("https://api-coffee-testing.herokuapp.com/v1/examen/updateName"))
                .then()
                .statusCode(406)
                .body("message",equalTo("Name was not provided"));
    }

    @Test
    public void noAuthRandomName() {

        request
                .get(String.format("%s", TURL))
                .then()
                .statusCode(401)
                .body("message",equalTo("Please login first"));

        System.out.println(name);
    }

    @Test
    public void getRandomName() {

        name =
                given()
                        .auth()
                        .basic("testuser","testpass")
                        .get(String.format("%s", TURL))
                        .then()
                        .extract()
                        .path("name");

        System.out.println(name);
    }

    @Test
    public void sameName() {

        request
                .body("{\"name\":\""+name+"\"}")
                .post(String.format("https://api-coffee-testing.herokuapp.com/v1/examen/sameName"))
                .then()
                .statusCode(200)
                .body("name",equalTo(name));

        System.out.println(name);
    }

}
