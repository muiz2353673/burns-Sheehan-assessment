# My Registration App

Hey! This is my registration app I made with Spring Boot. It's for people to sign up and register their info. I think it turned out pretty cool!

## What it does

So basically this app lets people fill out a form with their name, email, address and stuff. Then it saves it to a database. Pretty neat right?

The app uses:

- Java (I'm still learning this)
- Spring Boot (this framework is confusing but powerful)
- HTML for the web pages
- A database to store stuff

## Features

- You can register with your info
- It checks if your email is already used
- Nice looking form (I tried to make it look good)
- Saves everything to database
- Shows a success page when done

## Tech stuff

I used these technologies:

- **Java** - The programming language
- **Spring Boot** - Makes Java web apps easier
- **HTML/CSS** - For the website part
- **H2 Database** - This is like a mini database that runs in memory
- **Maven** - For building the project (still figuring this out)

## Database

The database has a table called "customers" with these fields:

- id (auto generated)
- email_address
- title (like Mr. or Mrs.)
- first_name
- last_name
- address_line_1
- address_line_2
- city
- postcode
- phone_number
- registered (timestamp when they signed up)

I made sure email has to be unique so no duplicates!

## How to run it

You need Java installed on your computer (I think version 8 or higher works).

1. Download the code:

   ```
   git clone <the-repo-url>
   cd burns-Sheehan-assessment
   ```

2. Run it:

   ```
   mvn spring-boot:run
   ```

3. Open your browser and go to: http://localhost:8080

That's it! The app should be running.

## Viewing the database

If you want to see the data, go to http://localhost:8080/h2-console

Use these settings:

- JDBC URL: `jdbc:h2:mem:testdb`
- User: `sa`
- Password: `password`

## File structure

Here's what the important files do:

- `RegistrationApplication.java` - This starts the app
- `RegistrationController.java` - Handles the web requests
- `Customer.java` - Defines what a customer looks like
- `CustomerRepository.java` - Talks to the database
- `CustomerService.java` - Has the business logic
- `registration.html` - The form page
- `success.html` - Shows when registration works

## Problems I ran into

- Had trouble getting Maven to work at first
- Took me a while to figure out the HTML templates
- Database stuff is still confusing but I got it working
- Still don't fully understand Spring Boot but it works!

## TODO

Things I want to add later:

- Better error messages
- Maybe a login page?
- Make it look nicer
- Add more validation
- Figure out how to deploy this somewhere

## Notes

This was a fun project to learn Spring Boot! Still have lots to learn about web development but this was a good start.
