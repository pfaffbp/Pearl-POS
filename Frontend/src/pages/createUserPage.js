// Import the baseClass and DataStore modules from their respective files
import baseClass from "../util/baseClass.js";
import DataStore from "../util/DataStore.js";

// Define a class called CreateUserPage that extends baseClass
class CreateUserPage extends baseClass {
    constructor() {
        // Call the baseClass constructor
        super();
        // Bind the 'onCreate', 'checkEmailUniqueness', and 'validatePassword' methods to the current instance
        // This is done to ensure that 'this' will refer to the correct instance when these methods are called
        this.bindClassMethods(['onCreate', 'checkEmailUniqueness', 'validatePassword'], this);
        // Create a new instance of the DataStore class and assign it to the 'dataStore' property of the current instance
        this.dataStore = new DataStore();
    }

    // Define an async method called 'mount'
    async mount() {
        // Get the HTML element with the ID 'create-user-form' and add an event listener for the 'submit' event
        // The event listener will call the 'onCreate' method when the form is submitted
        document.getElementById('create-user-form').addEventListener('submit', this.onCreate);
    }

    // Define an async method called 'onCreate' that takes an 'event' parameter
    async onCreate(event) {
        // Prevent the default form submission behavior
        event.preventDefault();
        // Get the values of the 'email', 'password', and 'confirm-password' input fields
        const email = document.getElementById('email').value;
        const password = document.getElementById('password').value;
        const confirmPassword = document.getElementById('confirm-password').value;

        // Validate the password
        const isValidPassword = this.validatePassword(password, confirmPassword);
        if (!isValidPassword) {
            // If the password is not valid, display an error message and return early
            alert('Password and confirm password do not match or password is less than 8 characters');
            return;
        }

        // Check if the email is unique
        const isEmailUnique = this.checkEmailUniqueness(email);
        if (!isEmailUnique) {
            // If the email is not unique, display an error message and return early
            alert('Email already exists!');
            return;
        }

        try {
            // Save the user data to local storage
            const users = JSON.parse(localStorage.getItem('users')) || [];
            users.push({ email: email, password: password });
            localStorage.setItem('users', JSON.stringify(users));

            // Display a success message and redirect to the login page
            alert('User created successfully!');
            window.location.href = 'login.html';
        } catch (error) {
            // If there was an error, display an error message
            alert('Error creating user!');
        }
    }

    // Define a method called 'checkEmailUniqueness' that takes an 'email' parameter
    checkEmailUniqueness(email) {
        // Get the user data from local storage and parse it as JSON
        const users = JSON.parse(localStorage.getItem('users')) || [];
        // Find a user with the given email
        const user = users.find(user => user.email === email);
        // If a user was found, the email is not unique; otherwise, it is
        return !user;
    }

    // Define a method called 'validatePassword' that takes 'password' and 'confirmPassword' parameters
    validatePassword(password, confirmPassword) {
        // Check that the password is at least 8 characters long and matches the confirmation password
        return password.length >= 8 && password === confirmPassword;
    }
}

const main = async () => {
    const createUserPage = new CreateUserPage();
    createUserPage.mount().then(r => console.log('Mounted create user page'));
};

window.addEventListener('DOMContentLoaded', main);