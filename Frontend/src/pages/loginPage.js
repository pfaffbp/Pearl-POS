import baseClass from "../util/baseClass.js";
import DataStore from "../util/DataStore.js";

class LoginPage extends baseClass {
    constructor() {
        super();
        // Bind class methods to the current instance
        this.bindClassMethods(["onLogin", "checkLoggedInUser"], this);
        // Create a new instance of DataStore
        this.dataStore = new DataStore();
        // Initialize an empty array to store user data
        this.users = [];
    }

    async mount() {
        // Add a "submit" event listener to the login form, calling the "onLogin" method when submitted
        document
            .getElementById("login-form")
            .addEventListener("submit", this.onLogin);

        // Load users from local storage
        const storedUsers = localStorage.getItem("users");
        if (storedUsers) {
            // Parse the JSON string of users and store it in the instance variable
            this.users = JSON.parse(storedUsers);
        }

        // Check if a user is already logged in
        this.checkLoggedInUser();
    }

    async onLogin(event) {
        event.preventDefault();
        // Get the values of the email and password fields
        const email = document.getElementById("email").value;
        const password = document.getElementById("password").value;

        try {
            // Find a user with matching email and password
            const user = this.users.find(
                (u) => u.email === email && u.password === password
            );
            if (user) {
                // If a matching user is found, store it in local storage and redirect to the product page
                alert("Welcome !");
                localStorage.setItem("user", JSON.stringify(user));
                window.location.href = "productPage.html"; // Redirect to product page
            } else {
                // If no matching user is found, display an error message
                alert("Incorrect email or password!");
            }
        } catch (error) {
            // If there is an error, display an error message
            alert("Error logging in!");
        }
    }

    checkLoggedInUser() {
        // Check if a user is already logged in
        const storedUser = localStorage.getItem("user");
        if (storedUser) {
            try {
                // Parse the JSON string of the stored user
                const user = JSON.parse(storedUser);
                if (user && user.name) {
                    // If the user has a name property, display a welcome message and redirect to the product page
                    alert("Welcome Back");
                    window.location.href = "productPage.html"; // Redirect to product page
                } else {
                    // If the user does not have a name property, remove the user from local storage
                    localStorage.removeItem("user");
                }
            } catch (error) {
                // If there is an error, remove the user from local storage
                console.error(error);
                localStorage.removeItem("user");
            }
        }
    }
}

// Create a new instance of LoginPage and call the "mount" method
const main = async () => {
    const loginPage = new LoginPage();
    await loginPage.mount();
    console.log("Mounted login page");
};

// Add a "DOMContentLoaded" event listener to the window, calling the "main" function when the page is loaded
window.addEventListener("DOMContentLoaded", main);