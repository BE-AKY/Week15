package pet.store.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import pet.store.entity.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Long> {

//	Set<Employee> findAllByEmployeeIn(Set<String> employees);
		//CHANGED TO <String> from <Employee> PER OFFICE HOURS 06.08.23


}
