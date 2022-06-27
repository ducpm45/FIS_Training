package com.example.lambda;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Supplier;

/**
 *
 * @author oracle
 */
public class SupplierTest {
  
  public static void main(String[] args) {
    
    List<Employee> eList = Employee.createShortList();
    Employee first = eList.get(0);
    
	Supplier<Employee> eSupp = () -> new Employee.Builder().givenName("Minh").surName("Duc").age(29).gender(Gender.MALE)
			.role(Role.MANAGER).dept("Sales").startDate(LocalDate.of(2017, 9, 26)).salary(100000)
			.email("ducpm45@fpt.com.vn").phoneNumber("0708-126-730").address("6 32/84/64 Do Duc Duc").city("Ha Noi")
			.state("Ha Noi").code("9999").build();
      
    System.out.println("=== Print employee list before");
    eList.stream().forEach(Employee::printSummary);
    
	eList.add(eSupp.get());
    
    System.out.println("\n=== Print employee list after");
    eList.stream().forEach(Employee::printSummary);
    
        
  }
}
