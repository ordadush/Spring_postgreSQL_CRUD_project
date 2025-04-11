package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e WHERE :lang NOT IN elements(e.languages)")
    List<Employee> findEmployeesWhoDoNotSpeakLanguage(@Param("lang") String lang);

    @Query("SELECT e FROM Employee e WHERE SIZE(e.languages) >= :languages AND e.salary >= :salary")
    List<Employee> findEmployeesByLanguagesAndSalary(@Param("languages") int languages, @Param("salary") int salary);

}
