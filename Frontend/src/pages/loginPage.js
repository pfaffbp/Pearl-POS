import baseClass from "../util/baseClass.js";
import DataStore from "../util/DataStore.js";

class LoginPage extends baseClass {
    constructor() {
        super();
        this.bindClassMethods(['onLogin'], this);
        this.dataStore = new DataStore();
        this.users = [];
    }

    async mount() {
        document.getElementById('login-form').addEventListener('submit', this.onLogin);

        // Load users from local storage
        const storedUsers = localStorage.getItem('users');
        if (storedUsers) {
            this.users = JSON.parse(storedUsers);
        }
    }

    async onLogin(event) {
        event.preventDefault();
        const email = document.getElementById('email').value;
        const password = document.getElementById('password').value;

        try {
            const user = this.users.find(u => u.email === email && u.password === password);
            if (user) {
                alert(`Welcome, ${user.name}!`);
                localStorage.setItem('user', JSON.stringify(user));
                window.location.href = 'productpage.html'; // Redirect to product page
            } else {
                alert('Incorrect email or password!');
            }
        } catch (error) {
            alert('Error logging in!');
        }
    }
}

const main = async () => {
    const loginPage = new LoginPage();
    loginPage.mount().then(r => console.log('Mounted login page'));

    // Check if a user is already logged in
    const storedUser = localStorage.getItem('user');
    if (storedUser) {
        const user = JSON.parse(storedUser);
        alert(`Welcome back, ${user.name}!`);
        window.location.href = 'login.html'; // Redirect to dashboard page
    }
};

window.addEventListener('DOMContentLoaded', main);
