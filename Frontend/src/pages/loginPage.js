import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import LoginClient from "../api/loginClient";

class LoginPage extends BaseClass {
    constructor() {
        super();
        this.bindClassMethods(["onLogin", "checkLoggedInUser"], this);
        this.dataStore = new DataStore();
        this.client = new LoginClient();
    }

    async mount() {
<<<<<<< HEAD
        document.getElementById('login-form').addEventListener('submit', this.onLogin);
=======
        document
            .getElementById("login-form")
            .addEventListener("submit", this.onLogin);

        // Load users from local storage
        const storedUsers = localStorage.getItem("users");
        if (storedUsers) {
            this.users = JSON.parse(storedUsers);
        }

        this.checkLoggedInUser();
>>>>>>> 78e3b20 (login and create user all test passing for service and controller)
    }

    async onLogin(event) {
        event.preventDefault();
<<<<<<< HEAD
        const username = document.getElementById('username').value;
        const password = document.getElementById('password').value;

        try {
            const token = await this.client.login(username, password);
            if (token) {
                alert('Login successful!');
                window.location.href = 'dashboard.html'; // Redirect to dashboard page
            } else {
                alert('Incorrect username or password!');
=======
        const email = document.getElementById("email").value;
        const password = document.getElementById("password").value;

        try {
            const user = this.users.find(
                (u) => u.email === email && u.password === password
            );
            if (user) {
                alert(`Welcome !`);
                localStorage.setItem("user", JSON.stringify(user));
                window.location.href = "productpage.html"; // Redirect to product page
            } else {
                alert("Incorrect email or password!");
>>>>>>> 78e3b20 (login and create user all test passing for service and controller)
            }
        } catch (error) {
            alert("Error logging in!");
        }
    }

    checkLoggedInUser() {
        // Check if a user is already logged in
        const storedUser = localStorage.getItem("user");
        if (storedUser) {
            try {
                const user = JSON.parse(storedUser);
                if (user && user.name) {
                    alert(`Welcome Back`);
                    window.location.href = "productpage.html"; // Redirect to product page
                } else {
                    localStorage.removeItem("user");
                }
            } catch (error) {
                console.error(error);
                localStorage.removeItem("user");
            }
        }
    }
}

    const main = async () => {
    const loginPage = new LoginPage();
<<<<<<< HEAD
    loginPage.mount().then(r => console.log('Mounted login page'));
=======
    await loginPage.mount();
    console.log("Mounted login page");
>>>>>>> 78e3b20 (login and create user all test passing for service and controller)
};

window.addEventListener("DOMContentLoaded", main);
