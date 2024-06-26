package services.petstore.pet;


import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.util.Map;

import static io.restassured.RestAssured.given;


@RequiredArgsConstructor
public class PetUnit {

    public static final String url = "https://petstore.swagger.io";

    public ValidatableResponse getPetInfoById(String id) {
        return given().log().all()
                .get(url + "/v2/pet/{id}", id)
                .then()
                .log().all();
    }

    public ValidatableResponse addNewPet(String json) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .body(json)
                .post(url + "/v2/pet")
                .then()
                .log().all();
    }

    public ValidatableResponse uploadImageById(String id, Map<String, String> parameters, File file) {
        return given().log().all()
                .contentType(ContentType.MULTIPART)
                .multiPart("file", file)
                .multiPart("additionalMetadata", parameters.get("additionalMetadata"))
                .post(url + "/v2/pet/{id}/uploadImage", id)
                .then()
                .log().all();
    }

    public ValidatableResponse updateExistingPet(String json) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .body(json)
                .post(url + "/v2/pet")
                .then()
                .log().all();
    }

    public ValidatableResponse deletePetById(String id) {
        return given().log().all()
                .delete(url + "/v2/pet/{id}", id)
                .then()
                .log().all();
    }

}
