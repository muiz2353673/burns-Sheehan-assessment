package com.aeroparker.registration.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

/**
 * Talent Entity Class
 * 
 * This class represents a talent candidate in the Burns Sheehan recruitment system.
 * It maps to the 'customers' table in the database and contains all the
 * necessary information required for customer registration.
 * 
 * The class uses JPA annotations for database mapping and Bean Validation
 * annotations for input validation to ensure data integrity.
 * 
 * @author AeroParker Development Team
 * @version 1.0
 * @since 2024-01-01
 */
@Entity
@Table(name = "customers")
public class Customer {

    /**
     * Primary key for the customer record.
     * Uses auto-increment strategy for unique ID generation.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Timestamp indicating when the customer was registered.
     * Automatically set to current time when customer is created.
     */
    @Column(name = "registered", nullable = false)
    private LocalDateTime registered;

    /**
     * Customer's email address.
     * Must be unique across all customers and serves as the primary
     * identifier for login and communication purposes.
     * Validated for proper email format and stored in lowercase.
     */
    @Email(message = "Please provide a valid email address")
    @NotBlank(message = "Email address is required")
    @Column(name = "email_address", nullable = false, unique = true, length = 255)
    private String emailAddress;

    /**
     * Customer's title (Mr, Mrs, Miss, Ms, Dr, Prof, etc.).
     * Required field with maximum length of 5 characters.
     */
    @NotBlank(message = "Title is required")
    @Size(max = 5, message = "Title must be 5 characters or less")
    @Column(name = "title", nullable = false, length = 5)
    private String title;

    /**
     * Customer's first name.
     * Required field with maximum length of 50 characters.
     */
    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name must be 50 characters or less")
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    /**
     * Customer's last name (surname).
     * Required field with maximum length of 50 characters.
     */
    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name must be 50 characters or less")
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    /**
     * First line of customer's address.
     * Required field containing street address, house number, etc.
     * Maximum length of 255 characters.
     */
    @NotBlank(message = "Address line 1 is required")
    @Size(max = 255, message = "Address line 1 must be 255 characters or less")
    @Column(name = "address_line_1", nullable = false, length = 255)
    private String addressLine1;

    /**
     * Second line of customer's address (optional).
     * Used for apartment numbers, suite information, etc.
     * Maximum length of 255 characters.
     */
    @Size(max = 255, message = "Address line 2 must be 255 characters or less")
    @Column(name = "address_line_2", length = 255)
    private String addressLine2;

    /**
     * Customer's city (optional).
     * Maximum length of 255 characters.
     */
    @Size(max = 255, message = "City must be 255 characters or less")
    @Column(name = "city", length = 255)
    private String city;

    /**
     * Customer's postal/zip code.
     * Required field with maximum length of 10 characters.
     * Supports various international postcode formats.
     */
    @NotBlank(message = "Postcode is required")
    @Size(max = 10, message = "Postcode must be 10 characters or less")
    @Column(name = "postcode", nullable = false, length = 10)
    private String postcode;

    /**
     * Customer's phone number (optional).
     * Maximum length of 20 characters to support international formats.
     */
    @Size(max = 20, message = "Phone number must be 20 characters or less")
    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    /* ==========================================
     *           CONSTRUCTORS
     * ========================================== */
    
    /**
     * Default constructor.
     * Automatically sets the registration timestamp to the current time.
     * Required by JPA for entity instantiation.
     */
    public Customer() {
        this.registered = LocalDateTime.now();
    }

    /**
     * Constructor with all customer fields (excluding ID and registration timestamp).
     * Automatically converts email to lowercase for consistency.
     * 
     * @param emailAddress Customer's email address
     * @param title Customer's title (Mr, Mrs, etc.)
     * @param firstName Customer's first name
     * @param lastName Customer's last name
     * @param addressLine1 First line of address
     * @param addressLine2 Second line of address (can be null)
     * @param city Customer's city (can be null)
     * @param postcode Customer's postcode
     * @param phoneNumber Customer's phone number (can be null)
     */
    public Customer(String emailAddress, String title, String firstName, String lastName,
                   String addressLine1, String addressLine2, String city, String postcode, String phoneNumber) {
        this(); // Call default constructor to set registration time
        this.emailAddress = emailAddress != null ? emailAddress.toLowerCase() : null;
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.postcode = postcode;
        this.phoneNumber = phoneNumber;
    }

    /* ==========================================
     *        GETTERS AND SETTERS
     * ========================================== */
    /**
     * Gets the unique identifier for this customer.
     * @return the customer ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier for this customer.
     * Note: This should typically only be set by the persistence framework.
     * @param id the customer ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the registration timestamp for this customer.
     * @return the registration date and time
     */
    public LocalDateTime getRegistered() {
        return registered;
    }

    /**
     * Sets the registration timestamp for this customer.
     * @param registered the registration date and time
     */
    public void setRegistered(LocalDateTime registered) {
        this.registered = registered;
    }

    /**
     * Gets the customer's email address.
     * @return the email address in lowercase
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Sets the customer's email address.
     * Automatically converts to lowercase for consistency.
     * @param emailAddress the customer's email address
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress != null ? emailAddress.toLowerCase() : null;
    }

    /**
     * Gets the customer's title.
     * @return the customer's title (Mr, Mrs, etc.)
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the customer's title.
     * @param title the customer's title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the customer's first name.
     * @return the customer's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the customer's first name.
     * @param firstName the customer's first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the customer's last name.
     * @return the customer's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the customer's last name.
     * @param lastName the customer's last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the first line of the customer's address.
     * @return the first address line
     */
    public String getAddressLine1() {
        return addressLine1;
    }

    /**
     * Sets the first line of the customer's address.
     * @param addressLine1 the first address line
     */
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    /**
     * Gets the second line of the customer's address.
     * @return the second address line (may be null)
     */
    public String getAddressLine2() {
        return addressLine2;
    }

    /**
     * Sets the second line of the customer's address.
     * @param addressLine2 the second address line (optional)
     */
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    /**
     * Gets the customer's city.
     * @return the customer's city (may be null)
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the customer's city.
     * @param city the customer's city (optional)
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets the customer's postcode.
     * @return the customer's postcode
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * Sets the customer's postcode.
     * @param postcode the customer's postcode
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    /**
     * Gets the customer's phone number.
     * @return the customer's phone number (may be null)
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the customer's phone number.
     * @param phoneNumber the customer's phone number (optional)
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /* ==========================================
     *           UTILITY METHODS
     * ========================================== */

    /**
     * Returns a string representation of the Customer object.
     * Useful for debugging and logging purposes.
     * 
     * @return a formatted string containing all customer fields
     */
    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", registered=" + registered +
                ", emailAddress='" + emailAddress + '\'' +
                ", title='" + title + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", city='" + city + '\'' +
                ", postcode='" + postcode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
