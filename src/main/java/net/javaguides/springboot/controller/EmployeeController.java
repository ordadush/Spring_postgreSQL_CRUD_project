package net.javaguides.springboot.controller;

import jakarta.validation.Valid;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    //get employees
    @GetMapping("/employees")
    public List<Employee> getAllEmployee(){
        return this.employeeRepository.findAll();
    }

    //get employees who do not speak a language
    @GetMapping("/employees/not-speaking/{language}")
    public List<Employee> getEmployeesWhoDoNotSpeakLanguage(@PathVariable("language") String language) {
        return employeeRepository.findEmployeesWhoDoNotSpeakLanguage(language);
    }


    //get employees by id
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
            throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById((employeeId))
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id : : " + employeeId));
        return ResponseEntity.ok().body(employee);
    }

    //save employee
    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee){
        return this.employeeRepository.save(employee);
    }

    //update employee
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
                                                 @Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmail(employeeDetails.getEmail());
        employee.setSalary(employeeDetails.getSalary());
        employee.setLanguages(employeeDetails.getLanguages());

        return ResponseEntity.ok(this.employeeRepository.save(employee));
    }
    //delete employee
    @DeleteMapping("/employees/{id}")
    public Map<String,Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        this.employeeRepository.delete(employee);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    //Get status about languages
    @GetMapping("/employees/languages/count")
    public List<Map<String, Object>> getLanguagesSpokenCount() {
        List<Employee> employees = employeeRepository.findAll();

        Map<String, Integer> languageCounts = new HashMap<>();

        for (Employee employee : employees) {
            if (employee.getLanguages() != null) {
                for (String lang : employee.getLanguages()) {
                    languageCounts.put(lang, languageCounts.getOrDefault(lang, 0) + 1);
                }
            }
        }

        List<Map<String, Object>> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : languageCounts.entrySet()) {
            Map<String, Object> langMap = new HashMap<>();
            langMap.put("language", entry.getKey());
            langMap.put("count", entry.getValue());
            result.add(langMap);
        }

        return result;
    }

    //Get workers with more than salary X and more them Y languages
    @GetMapping("/employees/languages/{languages}/salary/{salary}")
    public List<Employee> getEmployeesBySalaryAndLanguages(
            @PathVariable("languages") int languages,
            @PathVariable("salary") int salary) {

        return employeeRepository.findEmployeesByLanguagesAndSalary(languages, salary);
    }
}
