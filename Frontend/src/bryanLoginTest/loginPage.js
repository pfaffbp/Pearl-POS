import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import UserClient from "../bryanLoginTest/userClient";

/**
 * Logic needed for the login page of the website.
 */
class LoginPage extends BaseClass {

    constructor() {
        super();
        this.bindClassMethods(['onLogin', 'renderLoginResult'], this);
        this.dataStore = new DataStore();
    }

    /**
     * Once the page has loaded, set up the event handlers.
     */
    async mount() {
        document.getElementById('login-form').addEventListener('submit', this.onLogin);
        this.client = new UserClient();
        this.dataStore.addChangeListener(this.renderLoginResult);
    }

    // Render Methods --------------------------------------------------------------------------------------------------

    async renderLoginResult() {
        let resultArea = document.getElementById("login-result");

        const user = this.dataStore.get("user");

        if (user) {
            resultArea.innerHTML = `
                <p>Welcome, ${user.username}!</p>
            `
        } else {
            resultArea.innerHTML = "";
        }
    }

    // Event Handlers --------------------------------------------------------------------------------------------------

    async onLogin(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();

        let username = document.getElementById("username-field").value;
        let password = document.getElementById("password-field").value;

        this.dataStore.set("user", null);

        let result = await this.client.login(username, password, this.errorHandler);
        this.dataStore.set("user", result);

        if (result) {
            this.showMessage(`Logged in as ${result.username}!`);
        } else {
            this.errorHandler("Invalid username or password.");
        }
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    console.log("Mounted");
    const loginPage = new LoginPage();
    await loginPage.mount();
};

window.addEventListener('DOMContentLoaded', main);
