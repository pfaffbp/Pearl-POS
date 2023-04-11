import baseClass from "../util/baseClass.js";
import userClient from "../bryanLoginTest/userClient.js";

class LoginPage extends baseClass {
    constructor() {
        super();
        // Bind class methods to the current instance
        this.bindClassMethods(["onLogin", "checkLoggedInUser"], this);
        // Initialize an empty array to store user data
        this.users = [];
        this.userClient = new userClient();
    }

    async mount() {
        // Add a "submit" event listener to the login form, calling the "onLogin" method when submitted
        document.getElementById("login-form").addEventListener("submit", this.onLogin);

        // Check if a user is already logged in
        await this.checkLoggedInUser();
    }

    async onLogin(event) {
        event.preventDefault();
        const email = document.getElementById("email").value;
        const password = document.getElementById("password").value;

        try {
            const user = await this.userClient.loginUser(email, password);
            if (user) {
                alert(`Welcome Back!`);
                localStorage.setItem("user", JSON.stringify(user)); // Store the user data in the local storage
                window.location.href = "productPage.html";
            } else {
                alert("Incorrect email or password!");
            }
        } catch (error) {
            alert("Error logging in!");
        }
    }


    async checkLoggedInUser() {
        try {
            const user = JSON.parse(localStorage.getItem("user"));
            if (!user || !user.email) return;

            const matchingUser = this.users.find((u) => u.email === user.email);
            if (matchingUser && matchingUser.name) {
                alert(`Welcome Back`);
                window.location.href = "productPage.html";
            } else {
            }
        } catch (error) {
            console.error(error);
        }
    }
}
const forgotPasswordLink = document.getElementById('forgot-password-link');
forgotPasswordLink.addEventListener('click', (event) => {
    event.preventDefault();
    window.location.href = './forgotPassword.html';
});


const main = async () => {
    const loginPage = new LoginPage();
    await loginPage.mount();
    console.log("Mounted login page");
};



window.addEventListener("DOMContentLoaded", main);