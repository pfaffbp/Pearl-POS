import baseClass from "../util/baseClass.js";
import DataStore from "../util/DataStore.js";

class CreateUserPage extends baseClass {
    constructor() {
        super();
        this.bindClassMethods(['onCreate'], this);
        this.dataStore = new DataStore();
    }

    async mount() {
        document.getElementById('create-user-form').addEventListener('submit', this.onCreate);
    }

    async onCreate(event) {
        event.preventDefault();
        const email = document.getElementById('email').value;
        const password = document.getElementById('password').value;

        try {
            // Save user data to local storage
            const users = JSON.parse(localStorage.getItem('users')) || [];
            users.push({ email: email, password: password });
            localStorage.setItem('users', JSON.stringify(users));

            alert('User created successfully!');
            window.location.href = 'login.html'; // Redirect to login page
        } catch (error) {
            alert('Error creating user!');
        }
    }
}

const main = async () => {
    const createUserPage = new CreateUserPage();
    createUserPage.mount().then(r => console.log('Mounted create user page'));
};

window.addEventListener('DOMContentLoaded', main);
