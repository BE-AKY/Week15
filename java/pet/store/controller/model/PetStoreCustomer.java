package pet.store.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import pet.store.entity.Customer;

//THIS IS THE DTO (DATE TRANSFER OBJECT) LAYER!!!	

@Data
@NoArgsConstructor
public class PetStoreCustomer {

	private Long customerId;
	
	private String customerFirstName;
	private String customerLastName;
	
	private String customerEmail;
		
//	private Set<PetStoreResponse> petStoreResponse = new HashSet<>();


//CREATED FROM StoreService CLASS
	//Constructor that converts customer object to customer data object

	public PetStoreCustomer(Customer customer) {
		customerId = customer.getCustomerId();
		customerFirstName = customer.getCustomerFirstName();
		customerLastName = customer.getCustomerLastName();
		customerEmail = customer.getCustomerEmail();
		
//		for (PetStore petStore : customer.getPetStores()) {
//			petStoreResponse.add(new PetStoreResponse(petStore));
//		}
	}
//END StoreService CLASS
	
	
	
//@Data
//@NoArgsConstructor
//static class PetStoreResponse {
//	private Long petStoreId;
//	private String petStoreName;
//	private String petStoreAddress;
//	private String petStoreCity;
//	private String petStoreState;
//	private String petStoreZip;
//	private String petStorePhone;
////	private GeoLocation geoLocation;
////	private Set<Customer> customers = new HashSet<>();
////	private Set<String> employees = new HashSet<>();
//	private Set<Employee> employees = new HashSet<>();
//	
//			
//	PetStoreResponse(PetStore petStore) {
//		petStoreId = petStore.getPetStoreId();
//		petStoreName = petStore.getPetStoreName();
//		petStoreAddress = petStore.getPetStoreAddress();
//		petStoreCity = petStore.getPetStoreCity();
//		petStoreState = petStore.getPetStoreState();
//		petStoreZip = petStore.getPetStoreZip();
//		petStorePhone = petStore.getPetStorePhone();
////		geoLocation = new GeoLocation(petStore.getGeoLocation());
//		
//		for(Employee employee : petStore.getEmployees())
//			employees.add(employee);
//					//MIGHT BE MISSING SOMETHING...
//					//employees.add(employee.getEmployee());
//		}
//		
//	}

	
}

