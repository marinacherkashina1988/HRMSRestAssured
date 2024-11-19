package utils;

import apiSteps.APISteps;
import org.json.JSONObject;

public class APIConstantsPayload {

    public static String generateToken(String email, String password){
        JSONObject obj = new JSONObject();
        obj.put("email", email);
        obj.put("password", password);
        return obj.toString();
    }

    public static String createEmployee(String emp_firstname,
                                        String emp_lastname,
                                        String emp_middle_name,
                                        String emp_gender,
                                        String emp_birthday,
                                        String emp_status,
                                        String emp_job_title) {
        JSONObject obj = new JSONObject();
        obj.put("emp_firstname", emp_firstname);
        obj.put("emp_lastname", emp_lastname);
        obj.put("emp_middle_name", emp_middle_name);
        obj.put("emp_gender", emp_gender);
        obj.put("emp_birthday", emp_birthday);
        obj.put("emp_status", emp_status);
        obj.put("emp_job_title", emp_job_title);
        return obj.toString();
    }

    public static String updateEmployee(String emp_firstname,
                                        String emp_lastname,
                                        String emp_middle_name,
                                        String emp_gender,
                                        String emp_birthday,
                                        String emp_status,
                                        String emp_job_title){
        JSONObject obj = new JSONObject();
        obj.put("employee_id", APISteps.employee_id);
        obj.put("emp_firstname", emp_firstname);
        obj.put("emp_lastname", emp_lastname);
        obj.put("emp_middle_name", emp_middle_name);
        obj.put("emp_gender", emp_gender);
        obj.put("emp_birthday", emp_birthday);
        obj.put("emp_status", emp_status);
        obj.put("emp_job_title", emp_job_title);
        return obj.toString();
    }

    public static String partialUpdateEmployee(String emp_status){
        JSONObject obj = new JSONObject();
        obj.put("employee_id", APISteps.employee_id);
        obj.put("emp_status", emp_status);
        return obj.toString();
    }

}
