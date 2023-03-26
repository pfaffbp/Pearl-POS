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
        document.getElementById('login-form').addEventListener('submit', this.onLogin);
    }

    async onLogin(event) {
        event.preventDefault();
        const username = document.getElementById('username').value;
        const password = document.getElementById('password').value;

        try {
            const token = await this.client.login(username, password);
            if (token) {
                alert('Login successful!');
                window.location.href = 'dashboard.html'; // Redirect to dashboard page
            } else {
                alert('Incorrect username or password!');
            }
        } catch (error) {
            alert('Error logging in!');
        }
    }
}

const main = async () => {
    const loginPage = new LoginPage();
    loginPage.mount().then(r => console.log('Mounted login page'));
};

window.addEventListener('DOMContentLoaded', main);
