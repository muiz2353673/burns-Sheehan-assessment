package com.aeroparker.registration.service;

import com.aeroparker.registration.model.Customer;
import com.aeroparker.registration.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Customer Service Layer
 * 
 * This service class contains the business logic for customer-related operations
 * in the AeroParker registration system. It acts as an intermediary between
 * the controller layer and the repository layer, implementing business rules
 * and ensuring data consistency.
 * 
 * The service layer is responsible for:
 * - Validating business rules before data persistence
 * - Coordinating multiple repository operations if needed
 * - Implementing transaction boundaries
 * - Converting between domain objects and DTOs if required
 * 
 * @author AeroParker Development Team
 * @version 1.0
 * @since 2024-01-01
 */
@Service
public class CustomerService {

    /**
     * Repository dependency for customer data access operations.
     * Injected through constructor injection for better testability and immutability.
     */
    private final CustomerRepository customerRepository;

    /**
     * Constructor for dependency injection.
     * Uses constructor injection (recommended over field injection) for
     * better testability and to ensure required dependencies are provided.
     * 
     * @param customerRepository the customer repository for data access operations
     */
    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /* ==========================================
     *       CUSTOMER REGISTRATION METHODS
     * ========================================== */

    /**
     * Register a new customer in the system.
     * 
     * This method handles the complete customer registration process including:
     * - Duplicate email validation (case-insensitive)
     * - Email normalization (conversion to lowercase)
     * - Data persistence through the repository layer
     * 
     * Business Rules Applied:
     * - Email addresses must be unique across all customers
     * - Email addresses are stored in lowercase for consistency
     * - Registration timestamp is automatically set by the Customer entity
     * 
     * @param customer the customer object to register (must not be null)
     * @return the saved customer object with generated ID and timestamp
     * @throws RuntimeException if email address already exists in the system
     * @throws IllegalArgumentException if customer object or email is null/empty
     */
    public Customer registerCustomer(Customer customer) {
        // Validate input parameters
        if (customer == null) {
            throw new IllegalArgumentException("Customer object cannot be null");
        }
        if (customer.getEmailAddress() == null || customer.getEmailAddress().trim().isEmpty()) {
            throw new IllegalArgumentException("Customer email address cannot be null or empty");
        }
        
        // Business rule: Check if email already exists (case-insensitive)
        if (customerRepository.existsByEmailAddressIgnoreCase(customer.getEmailAddress())) {
            throw new RuntimeException("A customer with this email address already exists");
        }
        
        // Data normalization: Ensure email is stored in lowercase
        customer.setEmailAddress(customer.getEmailAddress().toLowerCase());
        
        // Persist the customer and return the saved entity with generated fields
        return customerRepository.save(customer);
    }

    /* ==========================================
     *        EMAIL VALIDATION METHODS
     * ========================================== */

    /**
     * Check if an email address is already registered in the system.
     * 
     * This method performs a case-insensitive check to determine if a given
     * email address is already associated with an existing customer. This is
     * commonly used for real-time validation during registration forms.
     * 
     * @param emailAddress the email address to check (case-insensitive)
     * @return true if the email is already registered, false otherwise
     * @throws IllegalArgumentException if emailAddress is null or empty
     */
    public boolean isEmailRegistered(String emailAddress) {
        // Validate input parameter
        if (emailAddress == null || emailAddress.trim().isEmpty()) {
            throw new IllegalArgumentException("Email address cannot be null or empty");
        }
        
        // Perform case-insensitive existence check
        return customerRepository.existsByEmailAddressIgnoreCase(emailAddress);
    }

    /* ==========================================
     *        CUSTOMER RETRIEVAL METHODS
     * ========================================== */

    /**
     * Find a customer by their email address.
     * 
     * Performs a case-insensitive search for a customer with the specified
     * email address. This is useful for login operations or customer lookup.
     * 
     * @param emailAddress the email address to search for (case-insensitive)
     * @return an Optional containing the customer if found, empty otherwise
     * @throws IllegalArgumentException if emailAddress is null or empty
     */
    public Optional<Customer> findByEmailAddress(String emailAddress) {
        // Validate input parameter
        if (emailAddress == null || emailAddress.trim().isEmpty()) {
            throw new IllegalArgumentException("Email address cannot be null or empty");
        }
        
        // Perform case-insensitive search
        return customerRepository.findByEmailAddressIgnoreCase(emailAddress);
    }

    /**
     * Find a customer by their unique ID.
     * 
     * Retrieves a customer record using the primary key identifier.
     * This is the most efficient way to lookup a specific customer.
     * 
     * @param id the unique customer ID to search for
     * @return an Optional containing the customer if found, empty otherwise
     * @throws IllegalArgumentException if id is null
     */
    public Optional<Customer> findById(Long id) {
        // Validate input parameter
        if (id == null) {
            throw new IllegalArgumentException("Customer ID cannot be null");
        }
        
        // Perform ID-based lookup
        return customerRepository.findById(id);
    }

    /**
     * Retrieve all customers from the system.
     * 
     * Returns a list of all registered customers in the system. This method
     * should be used carefully in production environments with large datasets
     * as it loads all customer records into memory.
     * 
     * For production use, consider implementing pagination or filtering
     * to limit the result set size.
     * 
     * @return a list of all customers (may be empty but never null)
     */
    public List<Customer> getAllCustomers() {
        // Retrieve all customer records
        return customerRepository.findAll();
    }

    /* ==========================================
     *        CUSTOMER MANAGEMENT METHODS
     * ========================================== */

    /**
     * Delete a customer from the system by their ID.
     * 
     * Permanently removes a customer record from the database. This operation
     * cannot be undone, so it should be used with caution. In production
     * systems, consider implementing soft deletion instead.
     * 
     * @param id the unique customer ID to delete
     * @throws IllegalArgumentException if id is null
     * @throws org.springframework.dao.EmptyResultDataAccessException if customer doesn't exist
     */
    public void deleteCustomer(Long id) {
        // Validate input parameter
        if (id == null) {
            throw new IllegalArgumentException("Customer ID cannot be null");
        }
        
        // Perform deletion (will throw exception if customer doesn't exist)
        customerRepository.deleteById(id);
    }
}
