import baseClass from "../util/baseClass.js";
import DataStore from "../util/DataStore.js";

class CreateUserPage extends baseClass {
    constructor() {
        super();
        this.bindClassMethods(['onCreate', 'checkEmailUniqueness', 'validatePassword'], this);
        this.dataStore = new DataStore();
    }

    async mount() {
        document.getElementById('create-user-form').addEventListener('submit', this.onCreate);
    }

    async onCreate(event) {
        event.preventDefault();
        const email = document.getElementById('email').value;
        const password = document.getElementById('password').value;
        const confirmPassword = document.getElementById('confirm-password').value;

        // Validate password
        const isValidPassword = this.validatePassword(password, confirmPassword);
        if (!isValidPassword) {
            alert('Password and confirm password do not match or password is less than 8 characters');
            return;
        }

        // Check email uniqueness
        const isEmailUnique = this.checkEmailUniqueness(email);
        if (!isEmailUnique) {
            alert('Email already exists!');
            return;
        }

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

    checkEmailUniqueness(email) {
        const users = JSON.parse(localStorage.getItem('users')) || [];
        const user = users.find(user => user.email === email);
        return !user;
    }

    validatePassword(password, confirmPassword) {
        return password.length >= 8 && password === confirmPassword;
    }
}

const main = async () => {
    const createUserPage = new CreateUserPage();
    createUserPage.mount().then(r => console.log('Mounted create user page'));
};

window.addEventListener('DOMContentLoaded', main);
