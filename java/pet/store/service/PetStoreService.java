package pet.store.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pet.store.controller.model.PetStoreCustomer;
import pet.store.controller.model.PetStoreData;
import pet.store.controller.model.PetStoreEmployee;
import pet.store.dao.CustomerDao;
import pet.store.dao.EmployeeDao;
import pet.store.dao.PetStoreDao;
import pet.store.entity.Customer;
import pet.store.entity.Employee;
import pet.store.entity.PetStore;


@Service
public class PetStoreService {

	@Autowired
	private PetStoreDao petStoreDao;
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	
//CREATE STORE	
	@Transactional(readOnly = false)
	public PetStoreData savePetStore(PetStoreData petStoreData) {
	//	Long petStoreId = petStoreData.getPetStoreId();
		
		PetStore petStore = findOrCreatePetStore(petStoreData.getPetStoreId());
		
		copyFieldsInPetStore(petStore, petStoreData);
			
		PetStore daPetStore = petStoreDao.save(petStore);
		
		return new PetStoreData(daPetStore);
			
	}

		private void copyFieldsInPetStore(PetStore petStore, PetStoreData petStoreData) {
			petStore.setPetStoreId(petStoreData.getPetStoreId());
			petStore.setPetStoreName(petStoreData.getPetStoreName());
			petStore.setPetStorePhone(petStoreData.getPetStorePhone());
			petStore.setPetStoreState(petStoreData.getPetStoreState());
			petStore.setPetStoreCity(petStoreData.getPetStoreCity());
			petStore.setPetStoreAddress(petStoreData.getPetStoreAddress());
			petStore.setPetStoreZip(petStoreData.getPetStoreZip());
				
		}

//Get		
	private PetStore findOrCreatePetStore(Long petStoreId) {
		PetStore petStore;
				
			if(Objects.isNull(petStoreId)) {
				petStore = new PetStore();		
			} else {	
				petStore = findPetStoreById(petStoreId);
			}
				
			return petStore;
			}

			
		private PetStore findPetStoreById(Long petStoreId) {
			return petStoreDao.findById(petStoreId).orElseThrow(() -> new NoSuchElementException("Pet store with ID=" + petStoreId + " does not exist."));
			}

		
		
	@Transactional(readOnly = true)
	public List<PetStoreData> retrieveAllPetStores() {
		return petStoreDao.findAll()
				.stream()
				.map(PetStoreData::new)
				.toList();
	}
	
	@Transactional(readOnly = true)
	public PetStoreData retrievePetStoreById(Long petStoreId ) {
		PetStore petStore = findPetStoreById(petStoreId);
		return new PetStoreData(petStore);
	}

	
	
//DELETE	
	@Transactional(readOnly = false)
	public void deletePetStoreById(Long petStoreId) {
		PetStore petStore = petStoreDao.findById(petStoreId).orElseThrow(() -> new NoSuchElementException("Pet store with ID=" + petStoreId + " does not exist."));
		petStoreDao.delete(petStore);
	}

	
//CREATE EMPLOYEE	
    @Transactional(readOnly = false)
    public PetStoreEmployee saveEmployee(Long petStoreId, PetStoreEmployee petStoreEmployee) {

        PetStore petStore = findPetStoreById(petStoreId);

        Long employeeId = petStoreEmployee.getEmployeeId();

        Employee employee = findOrCreateEmployee(employeeId, petStoreId);

        
        copyEmployeeFields(employee, petStoreEmployee);

        	employee.setPetStore(petStore);

        	petStore.getEmployees().add(employee);

        	
        Employee daEmployee = employeeDao.save(employee);

        return new PetStoreEmployee(daEmployee);

    }

    public Employee findEmployeeById(Long petStoreId, Long employeeId) {
        Employee employee = employeeDao.findById(employeeId).orElse(null);

        if (employee == null) {
            throw new NoSuchElementException("Employee with ID=" + employeeId + " does not exist.");
        }

        if (!employee.getPetStore().getPetStoreId().equals(petStoreId)) {
            throw new IllegalArgumentException(
                    "Employee with ID=" + employeeId + " does not exist in pet store with ID=" + petStoreId);
        }

        return employee;
    }

    private Employee findOrCreateEmployee(Long employeeId, Long petStoreId) {

        if (Objects.isNull(employeeId)) {
            return new Employee();
        }
        return findEmployeeById(petStoreId, employeeId);

    }

    private void copyEmployeeFields(Employee employee, PetStoreEmployee petStoreEmployee) {
        employee.setEmployeeId(petStoreEmployee.getEmployeeId());
        employee.setEmployeeFirstName(petStoreEmployee.getEmployeeFirstName());
        employee.setEmployeeLastName(petStoreEmployee.getEmployeeLastName());
        employee.setEmployeePhone(petStoreEmployee.getEmployeePhone());
        employee.setEmployeeJobTitle(petStoreEmployee.getEmployeeJobTitle());
    }


    
    

//CREATE CUSTOMER	
    @Transactional(readOnly = false)
    public PetStoreCustomer saveCustomer(Long petStoreId, PetStoreCustomer petStoreCustomer)  {

        PetStore petStore = findPetStoreById(petStoreId);

        Long customerId = petStoreCustomer.getCustomerId();
        
        Customer customer = findOrCreateCustomer(customerId, petStoreId);

        
        copyCustomerFields(customer, petStoreCustomer);

        	customer.getPetStores().add(petStore);

        	petStore.getCustomers().add(customer);

        	
        Customer daCustomer = customerDao.save(customer);

        return new PetStoreCustomer(daCustomer);

    }

    public Customer findCustomerById(Long petStoreId, Long customerId) {
        Customer customer = customerDao.findById(customerId).orElse(null);

        if (customer == null) {
            throw new NoSuchElementException("Customer with ID=" + customerId + " does not exist.");
        }

        boolean petStoreExist = customer.getPetStores().stream().anyMatch(petStore -> petStore.getPetStoreId().equals(petStoreId));
        
        if (!petStoreExist) {
            throw new IllegalArgumentException(
                    "Pet store with ID=" + petStoreId + " does not exist.");
        }

        return customer;
    }

    private Customer findOrCreateCustomer(Long customerId, Long petStoreId) {

        if (Objects.isNull(customerId)) {
            return new Customer();
        }
        return findCustomerById(petStoreId, customerId);

    }

    private void copyCustomerFields(Customer customer, PetStoreCustomer petStoreCustomer) {
        customer.setCustomerId(petStoreCustomer.getCustomerId());
        customer.setCustomerFirstName(petStoreCustomer.getCustomerFirstName());
        customer.setCustomerLastName(petStoreCustomer.getCustomerLastName());
        customer.setCustomerEmail(petStoreCustomer.getCustomerEmail());	
    }
	
	
}
