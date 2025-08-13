-- ==========================================
--     BURNS SHEEHAN TALENT REGISTRATION
--           DATABASE SETUP SCRIPT
-- ==========================================
--
-- This SQL script sets up the complete database structure for the
-- Burns Sheehan talent registration system. It includes:
--
-- - Database creation with proper character encoding
-- - Talent table with all required fields and constraints
-- - Performance indexes for optimized queries
-- - Sample test data for development and testing
-- - Verification queries to confirm setup
--
-- Target Database: MySQL 8.0+
-- Character Set: UTF8MB4 (full Unicode support)
-- Collation: utf8mb4_unicode_ci (case-insensitive Unicode)
--
-- Author: Burns Sheehan Development Team
-- Version: 1.0
-- Last Updated: 2024-01-01
--
-- Usage Instructions:
-- 1. Connect to MySQL server as root or admin user
-- 2. Execute this script: mysql -u root -p < database-setup.sql
-- 3. Verify creation with: SHOW DATABASES; USE aeroparker; SHOW TABLES;
-- ==========================================

-- ==========================================
--          DATABASE CREATION
-- ==========================================

-- Create the main application database
-- Uses UTF8MB4 for full Unicode support (including emojis)
-- Case-insensitive collation for user-friendly email comparisons
CREATE DATABASE IF NOT EXISTS burnssheehan_talent
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

-- Switch to the newly created database
USE burnssheehan_talent;

-- ==========================================
--         CUSTOMER TABLE CREATION
-- ==========================================

-- Main customer table storing all registration information
-- Designed with performance, data integrity, and scalability in mind
CREATE TABLE IF NOT EXISTS customers (
    
    -- Primary key: Auto-incrementing unique identifier
    -- INT provides sufficient range for millions of customers
    id INT AUTO_INCREMENT PRIMARY KEY,
    
    -- Registration timestamp: When customer account was created
    -- DATETIME for precise timestamp storage
    -- DEFAULT CURRENT_TIMESTAMP automatically sets registration time
    registered DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    
    -- Email address: Primary customer identifier
    -- VARCHAR(255) accommodates long email addresses
    -- UNIQUE constraint prevents duplicate registrations
    -- NOT NULL ensures every customer has contact information
    email_address VARCHAR(255) NOT NULL UNIQUE,
    
    -- Customer title: Courtesy title for personalization
    -- VARCHAR(5) accommodates Mr, Mrs, Miss, Ms, Dr, Prof
    -- Required field for proper addressing
    title VARCHAR(5) NOT NULL,
    
    -- Customer first name: Given name
    -- VARCHAR(50) balances storage efficiency with name length support
    -- Required for personalization and communication
    first_name VARCHAR(50) NOT NULL,
    
    -- Customer last name: Family name/surname
    -- VARCHAR(50) matches first_name for consistency
    -- Required for full customer identification
    last_name VARCHAR(50) NOT NULL,
    
    -- Address line 1: Primary address information
    -- VARCHAR(255) accommodates street address, house number, building name
    -- Required field for service delivery and legal compliance
    address_line_1 VARCHAR(255) NOT NULL,
    
    -- Address line 2: Additional address information (optional)
    -- NULL allowed for apartment number, suite, floor, etc.
    -- Many addresses don't require this additional detail
    address_line_2 VARCHAR(255) NULL,
    
    -- City: Customer's city/town (optional)
    -- NULL allowed as some postal systems don't require city
    -- VARCHAR(255) accommodates long place names
    city VARCHAR(255) NULL,
    
    -- Postcode: Postal/ZIP code for address verification
    -- VARCHAR(10) supports various international postcode formats
    -- Required for address validation and service delivery
    postcode VARCHAR(10) NOT NULL,
    
    -- Phone number: Customer contact number (optional)
    -- VARCHAR(20) supports international formats with country codes
    -- NULL allowed as email is primary contact method
    phone_number VARCHAR(20) NULL,
    
    -- ==========================================
    --            PERFORMANCE INDEXES
    -- ==========================================
    
    -- Email index: Fastest lookups for login and duplicate checking
    -- Most frequently used query pattern
    INDEX idx_email_address (email_address),
    
    -- Registration date index: For reporting and analytics
    -- Enables efficient queries by date range
    INDEX idx_registered (registered),
    
    -- Last name index: For alphabetical customer listing
    -- Supports customer search functionality
    INDEX idx_last_name (last_name)
    
-- ==========================================
--           TABLE ENGINE AND ENCODING
-- ==========================================

) ENGINE=InnoDB                    -- InnoDB for ACID compliance and foreign keys
  DEFAULT CHARSET=utf8mb4          -- Full Unicode character support
  COLLATE=utf8mb4_unicode_ci;      -- Case-insensitive Unicode collation

-- ==========================================
--           SAMPLE TEST DATA
-- ==========================================

-- Insert representative test data for development and testing
-- Covers different scenarios: various titles, address formats, optional fields
INSERT INTO customers (
    email_address, 
    title, 
    first_name, 
    last_name, 
    address_line_1, 
    address_line_2, 
    city, 
    postcode, 
    phone_number
) VALUES
    -- Customer 1: Complete information with apartment number
    (
        'john.doe@example.com', 
        'Mr', 
        'John', 
        'Doe', 
        '123 Main Street', 
        'Apt 4B', 
        'London', 
        'SW1A 1AA', 
        '+44 20 7946 0958'
    ),
    
    -- Customer 2: Missing apartment number and phone
    (
        'jane.smith@example.com', 
        'Mrs', 
        'Jane', 
        'Smith', 
        '456 Oak Avenue', 
        NULL, 
        'Manchester', 
        'M1 1AA', 
        '+44 161 496 0000'
    ),
    
    -- Customer 3: Professional title with suite information
    (
        'dr.brown@example.com', 
        'Dr', 
        'Robert', 
        'Brown', 
        '789 Pine Road', 
        'Suite 100', 
        'Birmingham', 
        'B1 1AA', 
        '+44 121 496 0000'
    ),
    
    -- Customer 4: Different title and postcode format
    (
        'prof.wilson@university.ac.uk',
        'Prof',
        'Sarah',
        'Wilson',
        'University Campus Building',
        'Office 42',
        'Cambridge',
        'CB2 1TN',
        '+44 1223 334400'
    ),
    
    -- Customer 5: Minimal required information only
    (
        'mike.johnson@email.com',
        'Mr',
        'Michael',
        'Johnson',
        '15 High Street',
        NULL,
        NULL,
        'OX1 4BH',
        NULL
    );

-- ==========================================
--           VERIFICATION QUERIES
-- ==========================================

-- Display table structure for verification
-- Shows column definitions, types, and constraints
DESCRIBE customers;

-- Display all sample data for verification
-- Confirms successful data insertion and formatting
SELECT 
    id,
    registered,
    email_address,
    title,
    CONCAT(first_name, ' ', last_name) AS full_name,
    CONCAT_WS(', ', address_line_1, address_line_2, city, postcode) AS full_address,
    phone_number
FROM customers
ORDER BY registered;

-- ==========================================
--           DATABASE STATISTICS
-- ==========================================

-- Show table information and row count
SELECT 
    TABLE_NAME,
    TABLE_ROWS,
    DATA_LENGTH,
    INDEX_LENGTH,
    (DATA_LENGTH + INDEX_LENGTH) AS total_size
FROM information_schema.TABLES 
WHERE TABLE_SCHEMA = 'burnssheehan_talent' 
  AND TABLE_NAME = 'customers';

-- Display index information for performance verification
SHOW INDEX FROM customers;

-- ==========================================
--           COMPLETION MESSAGE
-- ==========================================

-- Success message
SELECT 'Burns Sheehan talent registration database setup completed successfully!' AS Status;
