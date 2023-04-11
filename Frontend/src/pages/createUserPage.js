import baseClass from "../util/baseClass.js";
import DataStore from "../util/DataStore";
import UserClient from "../bryanLoginTest/userClient.js";

class CreateUserPage extends baseClass {
    constructor() {
        super();
        this.onCreate = this.onCreate.bind(this);
        this.onGet = this.onGet.bind(this);
        this.checkEmailUniqueness = this.checkEmailUniqueness.bind(this);
        this.validatePassword = this.validatePassword.bind(this);

        this.client = new UserClient();
        this.dataStore = new DataStore();
    }

    async mount() {
        this.client = new UserClient();
        document.getElementById('create-user-form').addEventListener('submit', this.onCreate);
    }

    async onGet(event) {
        event.preventDefault();
        const email = document.getElementById("create-email-field").value;
        this.dataStore.set("user", null);
        const result = await this.client.getUser(email, this.errorHandler);
        this.dataStore.set("user", result);
        if (result) {
            this.showMessage(`Got ${result.name}!`)
        } else {
            this.errorHandler("Error doing GET! Try again...");
        }
    }

    async onCreate(event) {
        event.preventDefault();
        console.log("Creating user...");
        this.dataStore.set("user", null);
        const email = document.getElementById("email").value;
        const password = document.getElementById("password").value;
        const confirmPassword = document.getElementById("confirm-password").value;

        try {
            await this.validateUserInput(email, password, confirmPassword);
            const user = await this.client.createUser(email, password);
            this.dataStore.set("user", user);
            console.log('Created user', user);
            this.showMessage(`User ${user.email} created successfully!`);
            window.location.href = './login.html';

        } catch (error) {
            console.error(error);
            this.errorHandler("Error creating user! Try again...");
        }
    }


    async validateUserInput(email, password, confirmPassword) {
        if (!this.validatePassword(password, confirmPassword)) {
            throw new Error('Password must be at least 8 characters long and match the confirm password');
        }

        try {
            const emailUniqueness = await this.checkEmailUniqueness(email);
            if (!emailUniqueness) {
                new Error('Email is already in use');
            }
        } catch (error) {
            if (error.response && error.response.status === 404) {
                // assume email is not in use if server returns 404 error
                return;
            }
            throw error;
        }
    }


    async checkEmailUniqueness(email) {
        const user = await this.client.findByEmail(email);
        return user === null;
    }



    validatePassword(password, confirmPassword) {
        return password.length >= 8 && password === confirmPassword;
    }
}

const main = async () => {
    const createUserPage = new CreateUserPage();
    await createUserPage.mount();
    console.log('Mounted create user page');
};

window.addEventListener('load', main);