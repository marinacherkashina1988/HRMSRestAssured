package apiSteps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import utils.APIConstants;
import utils.APIConstantsPayload;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class APISteps {

    RequestSpecification request;
    Response response;
    JsonPath jsPath;
    public static String employee_id;
    public static String token;

    @Given("generate token by providing {string} and {string}")
    public void generateTokenByProvidingAnd(String email, String password) {
        request = given().
                header(APIConstants.HEADER_CONTENT_TYPE_KEY, APIConstants.HEADER_CONTENT_TYPE_VALUE).
                body(APIConstantsPayload.generateToken(email, password));
        response = request.when().post(APIConstants.GENERATE_TOKEN);
        token = response.jsonPath().getString("token");
    }

    @Given("request to create employee: {string}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void request_to_create_employee(String firstname, String lastname, String middlename, String gender, String birthday, String status, String jobTitle) {
        request = given().
                header(APIConstants.HEADER_CONTENT_TYPE_KEY, APIConstants.HEADER_CONTENT_TYPE_VALUE).
                header(APIConstants.HEADER_AUTHORIZATION_KEY, token).
                body(APIConstantsPayload.createEmployee(firstname, lastname, middlename, gender, birthday, status, jobTitle));
    }

    @When("POST request is called")
    public void post_request_is_called() {
        response = request.when().post(APIConstants.CREATE_EMPLOYEE);
    }

    @Then("status code is {int}")
    public void status_code_is(Integer statusCode) {
        response.then().assertThat().statusCode(statusCode);
    }

    @Then("the employee id {string} is stored and verified")
    public void the_employee_id_is_stored_and_verified(String employeeID) {
        employee_id = response.jsonPath().getString(employeeID);
        String actualID = response.jsonPath().getString("Employee.employee_id");
        Assert.assertEquals(employee_id, actualID);
    }

    @Given("request to retrieve employee is prepared")
    public void request_to_retrieve_one_employee_is_prepared() {
        request = given().
                header(APIConstants.HEADER_CONTENT_TYPE_KEY, APIConstants.HEADER_CONTENT_TYPE_VALUE).
                header(APIConstants.HEADER_AUTHORIZATION_KEY, token).
                queryParam("employee_id", employee_id);
    }

    @When("GET request is called")
    public void get_request_is_called() {
        response = request.when().get(APIConstants.GET_ONE_EMPLOYEE);
    }

    @Then("retrieved employee data has to be equal to the searched employee data")
    public void retrieved_employee_data_has_to_be_equal_to_the_searched_employee_data(io.cucumber.datatable.DataTable dataTable) {

        List<Map<String, String>> expectedData = dataTable.asMaps();
        Map<String, String> actualData = response.jsonPath().getMap("employee");
        for (Map<String, String> employee : expectedData) {

            Set<String> keys = employee.keySet();
            for (String key : keys) {
                String expectedValue = employee.get(key);
                String actualValue = actualData.get(key);
                Assert.assertEquals(expectedValue, actualValue);
            }
        }
    }

    @Given("request to update an employee: {string}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void request_to_update_an_employee(String firstname, String lastname, String middlename, String gender, String birthday, String status, String jobTitle) {
        request = given().header(APIConstants.HEADER_CONTENT_TYPE_KEY, APIConstants.HEADER_CONTENT_TYPE_VALUE).
                header(APIConstants.HEADER_AUTHORIZATION_KEY, token).
                body(APIConstantsPayload.updateEmployee(firstname, lastname, middlename, gender, birthday, status, jobTitle));
    }

    @When("PUT request is called")
    public void put_request_is_called() {
        response = request.when().put(APIConstants.UPDATE_EMPLOYEE);
    }

    @Then("Message {string} is displayed and verified")
    public void message_is_displayed_and_verified(String expectedMsg) {
        String actualMsg = response.jsonPath().getString("Message");
        Assert.assertEquals(expectedMsg, actualMsg);
    }

    @Then("retrieved data for the updated employee is equal to the provided data")
    public void retrieved_data_for_the_updated_employee_is_equal_to_the_provided_data(io.cucumber.datatable.DataTable dataTable) {

        List<Map<String, String>> expectedData = dataTable.asMaps();
        Map<String, String> actualData = response.jsonPath().getMap("employee");
        for (Map<String, String> employeeData : expectedData) {
            Set<String> keys = employeeData.keySet();
            for (String key : keys) {
                String expectedValue = employeeData.get(key);
                String actualValue = actualData.get(key);
                Assert.assertEquals(expectedValue, actualValue);
            }
        }
    }

    @Given("request to update employee status to {string} is prepared")
    public void request_to_update_employee_status_to_is_prepared(String status) {
        request = given().header(APIConstants.HEADER_CONTENT_TYPE_KEY, APIConstants.HEADER_CONTENT_TYPE_VALUE).
                header(APIConstants.HEADER_AUTHORIZATION_KEY, token).
                body(APIConstantsPayload.partialUpdateEmployee(status));
    }

    @When("GET request for partial update is called")
    public void getRequestForPartialUpdateIsCalled() {
        response = request.when().put(APIConstants.PARTIAL_UPDATE_EMPLOYEE);
    }

    @Then("updated employment status {string} is verified")
    public void updated_employment_status_is_verified(String expectedStatus) {
        String actualStatus = response.jsonPath().getString("employee.emp_status");
        Assert.assertEquals(expectedStatus, actualStatus);
    }

    @Given("request to delete an employee is prepared")
    public void request_to_delete_an_employee_is_prepared() {
        request = given().
                header(APIConstants.HEADER_CONTENT_TYPE_KEY, APIConstants.HEADER_CONTENT_TYPE_VALUE).
                header(APIConstants.HEADER_AUTHORIZATION_KEY, token).
                queryParam("employee_id", employee_id);
    }

    @When("DELETE request is called")
    public void delete_request_is_called() {
        response = request.when().delete(APIConstants.DELETE_EMPLOYEE);
    }

    @Then("Message {string} is displayed and equal to response message")
    public void message_is_displayed_and_equal_to_response_message(String expectedMsg) {
        String actualMsg = response.jsonPath().getString("message");
        Assert.assertEquals(expectedMsg, actualMsg);
    }

    @Given("request to get all employees is prepared")
    public void request_to_get_all_employees_is_prepared() {
        request = given().header(APIConstants.HEADER_CONTENT_TYPE_KEY, APIConstants.HEADER_CONTENT_TYPE_VALUE).
                header(APIConstants.HEADER_AUTHORIZATION_KEY, token);
    }

    @When("GET request to retrieve all employees is called")
    public void get_request_to_retrieve_all_employees_is_called() {
        response = request.when().get(APIConstants.GET_ALL_EMPLOYEES);
    }

    @Then("all employees are retrieved and we can get all employee IDs")
    public void all_employees_are_retrieved_and_we_can_get_all_employee_IDs() {
        jsPath = response.jsonPath();
        List<String> employeeIDs = jsPath.get("Employees.employee_id");
        for (String id : employeeIDs) {
            System.out.println(id);
        }
    }

    @Given("request to get all job title is prepared")
    public void request_to_get_all_job_title_is_prepared() {
        request = given().header(APIConstants.HEADER_CONTENT_TYPE_KEY, APIConstants.HEADER_CONTENT_TYPE_VALUE).
                header(APIConstants.HEADER_AUTHORIZATION_KEY, token);
    }

    @When("GET request to retrieve all job title is called")
    public void get_request_to_retrieve_all_job_title_is_called() {
        response = request.when().get(APIConstants.GET_JOB_TITLE);
    }

    @Then("all employees job titles are retrieved")
    public void all_employees_job_titles_are_retrieved() {
        jsPath = response.jsonPath();
        List<String> jobTitles = jsPath.getList("Jobs.job");
        for (String title : jobTitles) {
            System.out.println(title);
        }
    }

    @Given("request to get all employment statuses is prepared")
    public void request_to_get_all_employment_statuses_is_prepared() {
        request = given().header(APIConstants.HEADER_CONTENT_TYPE_KEY, APIConstants.HEADER_CONTENT_TYPE_VALUE).
                header(APIConstants.HEADER_AUTHORIZATION_KEY, token);
    }

    @When("GET request to retrieve all employment statuses is called")
    public void getRequestToRetrieveAllEmploymentStatusesIsCalled() {
        response = request.when().get(APIConstants.GET_EMPLOYMENT_STATUS);
    }

    @Then("we are able to retrieve all employment statuses")
    public void we_are_able_to_retrieve_all_employment_statuses() {
        jsPath = response.jsonPath();
        //System.out.println(jsPath.prettyPrint());
        List<String> employmentStatus = jsPath.get("'Employeement Status'.name");
        for(String status : employmentStatus){
            System.out.println(status);
        }
    }
}
