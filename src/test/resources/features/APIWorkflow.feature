Feature: API Workflow

  Background:
    Given generate token by providing "main_admin@example.com" and "Pass123"

  @createEmployee @api
  Scenario: create a new employee
    Given request to create employee: "John", "Doeson", "Michael", "M", "1985-05-20", "active", "Software Engineer"
    When POST request is called
    Then status code is 201
    Then the employee id "Employee.employee_id" is stored and verified

  @getOneEmployee @api
  Scenario: get one employee
    Given request to retrieve employee is prepared
    When GET request is called
    Then status code is 200
    Then retrieved employee data has to be equal to the searched employee data
      | emp_firstname | emp_lastname | emp_middle_name | emp_gender | emp_birthday | emp_status | emp_job_title     |
      | John          | Doeson       | Michael         | Male       | 1985-05-20   | active     | Software Engineer |

  @updateEmployee @api
  Scenario: update an existing employee
    Given request to update an employee: "Michael", "Smith", "J", "M", "1990-05-14", "eployeed", "QA Engineer"
    When PUT request is called
    Then status code is 200
    Then Message "Employee record Updated" is displayed and verified

  @getUpdatedEmployee @api
  Scenario: get an updated employee
    Given request to retrieve employee is prepared
    When GET request is called
    Then status code is 200
    Then retrieved data for the updated employee is equal to the provided data
      | emp_firstname | emp_lastname | emp_middle_name | emp_gender | emp_birthday | emp_status | emp_job_title |
      | Michael       | Smith        | J               | Male       | 1990-05-14   | eployeed   | QA Engineer   |

  @partialUpdate @api
  Scenario: partial update of the employee
    Given request to update employee status to "terminated" is prepared
    When GET request for partial update is called
    Then status code is 201
    Then updated employment status "terminated" is verified

  @deleteEmployee @api
  Scenario: delete an employee
    Given request to delete an employee is prepared
    When DELETE request is called
    Then status code is 200
    Then Message "Employee deleted" is displayed and equal to response message

  @getAllEmployees @api
  Scenario: get all employees
    Given request to get all employees is prepared
    When GET request to retrieve all employees is called
    Then status code is 200
    Then all employees are retrieved and we can get all employee IDs

  @getAllJobTitles @api
  Scenario: get all job titles
    Given request to get all job title is prepared
    When GET request to retrieve all job title is called
    Then status code is 200
    Then all employees job titles are retrieved

  @getAllEmploymentStatus @api
  Scenario: get all employment statuses
    Given request to get all employment statuses is prepared
    When GET request to retrieve all employment statuses is called
    Then status code is 200
    Then we are able to retrieve all employment statuses