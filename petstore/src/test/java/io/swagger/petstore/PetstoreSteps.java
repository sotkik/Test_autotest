package io.swagger.petstore;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import io.restassured.response.ValidatableResponse;

import lombok.RequiredArgsConstructor;
import services.petstore.pet.PetUnit;
import utils.ResourceUtils;
import utils.RestUtils;
import utils.TestContext;

import static java.util.stream.Collectors.toMap;
import static net.javacrumbs.jsonunit.JsonMatchers.jsonEquals;
import static net.javacrumbs.jsonunit.core.Option.IGNORING_ARRAY_ORDER;
import static net.javacrumbs.jsonunit.core.Option.IGNORING_EXTRA_FIELDS;
import static utils.TestContext.replaceHoldersFromContext;

@RequiredArgsConstructor
public class PetstoreSteps {

    private final TestContext context;
    private final RestUtils restUtils;
    private final PetUnit petUnit;
    private ValidatableResponse lastResponse;

    @Когда("получаем информацию о питомце по {word} вернет {int}")
    public void getPetById (String petId, int httpCode){
        lastResponse = petUnit.getPetInfoById((String) context.get(petId))
                .statusCode(httpCode);
    }

    @Пусть("добавляем нового питомца вернет {int}")
    public void addNewPet (int httpCode, String body){
        lastResponse = petUnit.addNewPet(body)
                .statusCode(httpCode);

        if (httpCode == 200){
            context.put("petId", lastResponse.extract().jsonPath().getString("id"));
        }
    }

    @Тогда("json соответствует")
    public void jsonContains (String expectedJson){
        lastResponse.body("", jsonEquals(replaceHoldersFromContext(expectedJson, context))
                .when(IGNORING_ARRAY_ORDER, IGNORING_EXTRA_FIELDS));
    }

    @Когда("загружаем изображение {word} для {word} c параметрами")
    public void attachFile(String filePath, String petId, DataTable dataTable) {
        var parameters = restUtils.extentParams(dataTable.asMap(), context);

        lastResponse = petUnit.uploadImageById((String) context.get(petId), parameters, ResourceUtils.resourceAsFile(filePath))
                .statusCode(200);
    }

    @Пусть("обновляем информацию о существующем питомце вернет {int}")
    public void updateExistingPet (int httpCode, String body){
        lastResponse = petUnit.updateExistingPet(replaceHoldersFromContext(body, context))
                .statusCode(httpCode);
    }

    @Пусть("удаляем информацию о питомце по {word} вернет {int}")
    public void deletePet (String petId, int httpCode){
        lastResponse = petUnit.deletePetById((String) context.get(petId))
                .statusCode(httpCode);
    }

}
