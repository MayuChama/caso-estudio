package com.fatima.casoestudio.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatima.casoestudio.entity.Employee;
import com.fatima.casoestudio.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository repository;

	@Override
	public Iterable<Employee> getAllEmployees() {
		return repository.findAll();
	}

	private boolean checkEmployeeAvailable(Employee employee) throws Exception {
		String firstName = employee.getFirstName();
		String midleName = employee.getMidleName();
		String lastName = employee.getLastName();
		LocalDate birthDate = employee.getBirthDate();
		Optional<Employee> employeeFound = repository.verifyEmployee(firstName, midleName, lastName, birthDate);
		if (employeeFound.isPresent()) {
			throw new Exception("This Employee already exists.");
		}
		return true;
	}

	@Override
	public Employee createEmployee(Employee employee) throws Exception {
		if (checkEmployeeAvailable(employee)) {
			employee = repository.save(employee);
		}
		return employee;
	}

	Employee emp;

	@Override
	public Employee getEmployeeById(Long id) throws Exception {

		Optional<Employee> employeeOptional = repository.findById(id);
		if (employeeOptional.isPresent())
			emp = employeeOptional.get();
		return emp;
	}

	@Override
	public Employee updateEmployee(Employee fromEmployee) throws Exception {
		Employee toEmployee = getEmployeeById(fromEmployee.getId());
		mapEmployee(fromEmployee, toEmployee);
		return repository.save(toEmployee);

	}
	
	@Override
	public List<Employee> getEmployeesByFilter(Employee employee) {
		String firstName = employee.getFirstName().isBlank() ? null : employee.getFirstName();
		String lastName = employee.getLastName().isBlank() ? null : employee.getLastName();
		String position = employee.getPosition().isBlank() ? null : employee.getPosition();
		List<Employee> empleados = repository.findByFilters(firstName, lastName, position);
		return empleados;
	}
	
/*
 * Mapeando todo
 * */
	protected void mapEmployee(Employee from, Employee to) {
		to.setId(from.getId());
		to.setFirstName(from.getFirstName());
		to.setMidleName(from.getMidleName());
		to.setLastName(from.getLastName());
		to.setBirthDate(from.getBirthDate());
		to.setPosition(from.getPosition());
	}
}
