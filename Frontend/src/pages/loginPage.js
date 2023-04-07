import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import LoginClient from "../api/loginClient";

class LoginPage extends BaseClass {
    constructor() {
        super();
        this.bindClassMethods(['onLogin'], this);
        this.dataStore = new DataStore();
        this.client = new LoginClient();
    }

    async mount() {
<<<<<<< HEAD
<<<<<<< HEAD
        document.getElementById('login-form').addEventListener('submit', this.onLogin);
=======
        document
            .getElementById("login-form")
            .addEventListener("submit", this.onLogin);
=======
        document.getElementById('login-form').addEventListener('submit', this.onLogin);
>>>>>>> eb90ed7 (Revert "login and create user all test passing for service and controller")

        // Load users from local storage
        const storedUsers = localStorage.getItem('users');
        if (storedUsers) {
            this.users = JSON.parse(storedUsers);
        }
<<<<<<< HEAD

        this.checkLoggedInUser();
>>>>>>> 78e3b20 (login and create user all test passing for service and controller)
=======
>>>>>>> eb90ed7 (Revert "login and create user all test passing for service and controller")
    }

    async onLogin(event) {
        event.preventDefault();
<<<<<<< HEAD
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
=======
        const email = document.getElementById('email').value;
        const password = document.getElementById('password').value;
>>>>>>> eb90ed7 (Revert "login and create user all test passing for service and controller")

        try {
            const user = this.users.find(u => u.email === email && u.password === password);
            if (user) {
                alert(`Welcome, ${user.name}!`);
                localStorage.setItem('user', JSON.stringify(user));
                window.location.href = 'productpage.html'; // Redirect to product page
            } else {
<<<<<<< HEAD
                alert("Incorrect email or password!");
>>>>>>> 78e3b20 (login and create user all test passing for service and controller)
=======
                alert('Incorrect email or password!');
>>>>>>> eb90ed7 (Revert "login and create user all test passing for service and controller")
            }
        } catch (error) {
            alert('Error logging in!');
        }
    }
}

const main = async () => {
    const loginPage = new LoginPage();
<<<<<<< HEAD
<<<<<<< HEAD
    loginPage.mount().then(r => console.log('Mounted login page'));
=======
    await loginPage.mount();
    console.log("Mounted login page");
>>>>>>> 78e3b20 (login and create user all test passing for service and controller)
=======
    loginPage.mount().then(r => console.log('Mounted login page'));

    // Check if a user is already logged in
    const storedUser = localStorage.getItem('user');
    if (storedUser) {
        const user = JSON.parse(storedUser);
        alert(`Welcome back, ${user.name}!`);
        window.location.href = 'login.html'; // Redirect to dashboard page
    }
>>>>>>> eb90ed7 (Revert "login and create user all test passing for service and controller")
};

window.addEventListener('DOMContentLoaded', main);
