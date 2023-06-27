package pet.store.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import pet.store.entity.Customer;
import pet.store.entity.Employee;
import pet.store.entity.PetStore;

@Data
@NoArgsConstructor
public class PetStoreData {

	private Long petStoreId;
	
	private String petStoreName;
	private String petStoreAddress;
	private String petStoreCity;
	private String petStoreState;
	private String petStoreZip;
	private String petStorePhone;
	
//	private GeoLocation geoLocation;
	
	
//Losing functionality by making Set<String>... 	
	
	private Set<PetStoreCustomer> customers = new HashSet<>();
//	private Set<String> customers = new HashSet<>();

	
	private Set<PetStoreEmployee> employees = new HashSet<>();
//	private Set<String> employees = new HashSet<>();
	
	
	public PetStoreData(PetStore petStore) {
		petStoreId = petStore.getPetStoreId();
		petStoreName = petStore.getPetStoreName();
		petStoreAddress = petStore.getPetStoreAddress();
		petStoreCity = petStore.getPetStoreCity();
		petStoreState = petStore.getPetStoreState();
		petStoreZip = petStore.getPetStoreZip();
		petStorePhone = petStore.getPetStorePhone();
//		geoLocation = petStore.getGeoLocation();
		
		for(Customer customer : petStore.getCustomers()) {
			customers.add(new PetStoreCustomer(customer));
			
//			customers.add(customer.getCustomerLastName());
//			customers.add(customer.getCustomerFirstName());
//			customers.add(customer.getCustomerEmail());
			//CHANGED PER OFFICE HOURS 06.08.23
		}
	
//		customers = new Customer(petStore.getCustomers());
		
		
		for(Employee employee : petStore.getEmployees()) {
			employees.add(new PetStoreEmployee(employee));
			
//			employees.add(employee.getEmployeeLastName());
//			employees.add(employee.getEmployeeFirstName());
//			employees.add(employee.getEmployeeJobTitle());
			//CHANGED PER OFFICE HOURS 06.08.23
		}
	}


//DON'T NEED BELOW BECAUSE CUSTOMER WAS ENTERED INTO SEPARATE CLASS	
//@Data
//@NoArgsConstructor
//public static class PetStoreCustomer {
//		private Long customerId;
//		
//		private String customerFirstName;
//		private String customerLastName;
//
//		private String customerEmail;
//
//	
//	public PetStoreCustomer(Customer customer) {
//		customerId = customer.getCustomerId();
//		customerFirstName = customer.getCustomerFirstName();
//		customerLastName = customer.getCustomerLastName();
//		customerEmail = customer.getCustomerEmail();
//		
//		}
//	}



}
