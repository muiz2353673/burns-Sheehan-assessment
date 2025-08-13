package com.aeroparker.registration.controller;

import com.aeroparker.registration.model.Customer;
import com.aeroparker.registration.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Registration Controller
 * 
 * This controller handles all web requests related to customer registration.
 * It provides endpoints for displaying the registration form, processing
 * form submissions, showing success pages, and AJAX email validation.
 * 
 * The controller follows the Model-View-Controller (MVC) pattern and uses
 * Thymeleaf templates for rendering HTML pages. It includes proper
 * error handling and validation to ensure data integrity.
 * 
 * @author AeroParker Development Team
 * @version 1.0
 * @since 2024-01-01
 */
@Controller
public class RegistrationController {

    /**
     * Service layer dependency for customer-related business operations.
     * Injected through constructor injection for better testability and immutability.
     */
    private final CustomerService customerService;

    /**
     * Constructor for dependency injection.
     * Uses constructor injection (recommended over field injection) for
     * better testability and to ensure required dependencies are provided.
     * 
     * @param customerService the customer service for business logic operations
     */
    @Autowired
    public RegistrationController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /* ==========================================
     *           PAGE RENDERING ENDPOINTS
     * ========================================== */

    /**
     * Display the customer registration form.
     * 
     * This endpoint serves the main registration page with an empty Customer
     * object bound to the form. The Thymeleaf template uses this object
     * for form data binding and validation display.
     * 
     * @param model Spring MVC model for passing data to the view
     * @return the name of the Thymeleaf template to render ("registration")
     */
    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        // Create a new Customer object for form binding
        model.addAttribute("customer", new Customer());
        return "registration"; // Returns registration.html template
    }

    /**
     * Display the registration success page.
     * 
     * This endpoint shows a confirmation page after successful registration.
     * It expects flash attributes (successMessage, customerId) to be passed
     * from the registration POST endpoint via redirect attributes.
     * 
     * @param model Spring MVC model (flash attributes automatically added)
     * @return the name of the Thymeleaf template to render ("success")
     */
    @GetMapping("/success")
    public String showSuccessPage(Model model) {
        return "success"; // Shows success.html
    }

    /**
     * Home page redirect.
     * 
     * Redirects users from the root URL to the registration form.
     * This ensures users land on the main functionality of the application.
     * 
     * @return redirect instruction to the registration endpoint
     */
    @GetMapping("/")
    public String home() {
        return "redirect:/registration";
    }

    /* ==========================================
     *         FORM PROCESSING ENDPOINTS
     * ========================================== */

    /**
     * Process customer registration form submission.
     * 
     * This endpoint handles the POST request from the registration form,
     * validates the submitted data, checks for duplicate emails, and either
     * saves the customer or returns validation errors.
     * 
     * The method performs the following steps:
     * 1. Validates form data using Bean Validation annotations
     * 2. Checks if email address is already registered
     * 3. Attempts to save the customer through the service layer
     * 4. Redirects to success page or returns to form with errors
     * 
     * @param customer the customer object populated from form data
     * @param bindingResult validation results from Bean Validation
     * @param model Spring MVC model for passing data to the view
     * @param redirectAttributes attributes to pass to redirect target
     * @return either redirect to success page or return to registration form
     */
    @PostMapping("/registration")
    public String registerCustomer(@Valid @ModelAttribute("customer") Customer customer,
                                 BindingResult bindingResult,
                                 Model model,
                                 RedirectAttributes redirectAttributes) {
        
        // Step 1: Check for Bean Validation errors (e.g., @NotBlank, @Email)
        if (bindingResult.hasErrors()) {
            // Return to form with validation error messages
            return "registration";
        }

        // Step 2: Business logic validation - check for duplicate email
        if (customerService.isEmailRegistered(customer.getEmailAddress())) {
            // Add custom validation error for duplicate email
            bindingResult.rejectValue("emailAddress", "error.customer", 
                "A customer with this email address already exists");
            model.addAttribute("errorMessage", "A customer with this email address already exists");
            return "registration";
        }

        try {
            // Step 3: Attempt to register the customer through service layer
            Customer savedCustomer = customerService.registerCustomer(customer);
            
            // Step 4: Prepare success data for redirect
            // Using flash attributes to survive the redirect
            redirectAttributes.addFlashAttribute("successMessage", 
                "Registration successful! Welcome " + savedCustomer.getFirstName() + " " + savedCustomer.getLastName());
            redirectAttributes.addFlashAttribute("customerId", savedCustomer.getId());
            
            // Redirect to success page (PRG pattern - Post-Redirect-Get)
            return "redirect:/success";
            
        } catch (Exception e) {
            // Handle any unexpected errors during registration
            bindingResult.rejectValue("emailAddress", "error.customer", 
                "Registration failed: " + e.getMessage());
            return "registration";
        }
    }

    /* ==========================================
     *           AJAX API ENDPOINTS
     * ========================================== */

    /**
     * AJAX endpoint for real-time email availability checking.
     * 
     * This endpoint is called by JavaScript on the registration form to
     * provide immediate feedback on email availability as the user types.
     * It performs a case-insensitive check against existing customer records.
     * 
     * @param email the email address to check (passed as query parameter)
     * @return "exists" if email is already registered, "available" if not
     */
    @GetMapping("/check-email")
    @ResponseBody
    public String checkEmail(@RequestParam String email) {
        // Perform case-insensitive email existence check
        boolean exists = customerService.isEmailRegistered(email);
        
        // Return simple string response for JavaScript consumption
        return exists ? "exists" : "available";
    }
}
