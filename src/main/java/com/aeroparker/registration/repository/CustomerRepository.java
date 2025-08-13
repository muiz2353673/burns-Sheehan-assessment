package com.aeroparker.registration.repository;

import com.aeroparker.registration.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Customer Repository Interface
 * 
 * This repository interface provides data access operations for Customer entities.
 * It extends Spring Data JPA's JpaRepository to inherit standard CRUD operations
 * and defines additional custom query methods for specific business requirements.
 * 
 * The repository layer is responsible for:
 * - Abstracting database access details from the service layer
 * - Providing type-safe query methods using Spring Data conventions
 * - Implementing custom queries using JPQL or native SQL when needed
 * - Handling database transactions automatically through Spring's proxy mechanisms
 * 
 * Key Features:
 * - Case-insensitive email searches for user-friendly lookups
 * - Automatic query generation based on method naming conventions
 * - Custom JPQL queries for complex search requirements
 * 
 * @author AeroParker Development Team
 * @version 1.0
 * @since 2024-01-01
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    /* ==========================================
     *        EMAIL-BASED QUERY METHODS
     * ========================================== */

    /**
     * Find a customer by email address using case-insensitive matching.
     * 
     * This method uses Spring Data JPA's automatic query generation based on
     * method naming conventions. The "IgnoreCase" suffix instructs Spring Data
     * to perform a case-insensitive comparison, making email lookups user-friendly.
     * 
     * Example Usage:
     * - findByEmailAddressIgnoreCase("john@example.com") will match "JOHN@EXAMPLE.COM"
     * - Useful for login scenarios where users might vary email case
     * 
     * @param emailAddress the email address to search for (case-insensitive)
     * @return an Optional containing the customer if found, empty otherwise
     */
    Optional<Customer> findByEmailAddressIgnoreCase(String emailAddress);

    /**
     * Check if a customer exists with the specified email address (case-insensitive).
     * 
     * This method provides an efficient way to check for email existence without
     * loading the entire Customer entity. It's optimized for validation scenarios
     * where only the existence check is needed, such as during registration.
     * 
     * Performance Note:
     * - This method is more efficient than findByEmailAddressIgnoreCase().isPresent()
     * - The database only needs to return a boolean result, not the full entity
     * 
     * @param emailAddress the email address to check for existence (case-insensitive)
     * @return true if a customer with this email exists, false otherwise
     */
    boolean existsByEmailAddressIgnoreCase(String emailAddress);

    /* ==========================================
     *          CUSTOM JPQL QUERIES
     * ========================================== */

    /**
     * Alternative implementation for case-insensitive email search using JPQL.
     * 
     * This method demonstrates how to write custom JPQL queries when Spring Data's
     * automatic query generation doesn't meet specific requirements. It performs
     * the same function as findByEmailAddressIgnoreCase() but uses explicit JPQL.
     * 
     * JPQL Features Demonstrated:
     * - Entity-based queries (Customer vs table-based)
     * - LOWER() function for case normalization
     * - Named parameters (@Param annotation)
     * 
     * Note: This method is provided as an example of custom JPQL queries.
     * In practice, prefer the Spring Data convention methods when possible
     * as they're more maintainable and less error-prone.
     * 
     * @param emailAddress the email address to search for (case will be normalized)
     * @return an Optional containing the customer if found, empty otherwise
     */
    @Query("SELECT c FROM Customer c WHERE LOWER(c.emailAddress) = LOWER(:emailAddress)")
    Optional<Customer> findByEmailAddressCaseInsensitive(@Param("emailAddress") String emailAddress);

    /* ==========================================
     *        INHERITED REPOSITORY METHODS
     * ========================================== */

    /*
     * This repository interface inherits the following methods from JpaRepository<Customer, Long>:
     * 
     * Basic CRUD Operations:
     * - save(Customer entity)                    : Save or update a customer
     * - saveAll(Iterable<Customer> entities)     : Save or update multiple customers
     * - findById(Long id)                        : Find customer by primary key
     * - existsById(Long id)                      : Check if customer exists by ID
     * - findAll()                                : Find all customers
     * - findAllById(Iterable<Long> ids)          : Find customers by multiple IDs
     * - count()                                  : Count total number of customers
     * - deleteById(Long id)                      : Delete customer by ID
     * - delete(Customer entity)                  : Delete specific customer entity
     * - deleteAllById(Iterable<Long> ids)        : Delete customers by multiple IDs
     * - deleteAll()                              : Delete all customers
     * 
     * Pagination and Sorting:
     * - findAll(Sort sort)                       : Find all customers with sorting
     * - findAll(Pageable pageable)               : Find customers with pagination
     * 
     * Batch Operations:
     * - saveAllAndFlush(Iterable<Customer> entities) : Save and immediately flush to database
     * - deleteInBatch(Iterable<Customer> entities)   : Batch delete without loading entities
     * - deleteAllInBatch()                           : Batch delete all customers
     * 
     * For custom queries not covered by these inherited methods, add new method
     * declarations to this interface following Spring Data JPA naming conventions.
     */
}
