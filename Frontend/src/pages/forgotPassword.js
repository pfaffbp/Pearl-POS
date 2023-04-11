import UserClient from "../bryanLoginTest/userClient.js";
import baseClass from "../util/baseClass.js";


document.addEventListener("DOMContentLoaded", () => {

class ResetPassword extends baseClass {
    constructor() {
        super();
        this.bindClassMethods(["handleSubmit"], this);
        this.userClient = new UserClient();
    }

    async mount() {
        const resetPasswordForm = document.getElementById("reset-password-form");
        resetPasswordForm.addEventListener("submit", this.handleSubmit);
    }

    async handleSubmit(event) {
        const successMessage = document.getElementById("success-message");
        successMessage.classList.add("hidden");
        event.preventDefault();

        const email = document.getElementById("email").value;
        const newPassword = document.getElementById("new-password").value;
        const confirmNewPassword = document.getElementById("confirm-new-password").value;

        if (newPassword !== confirmNewPassword) {
            alert("New password and confirm password must match.");
            return;
        }

        try {
            // Call the reset password endpoint on the server
            await this.userClient.updateUser(email, newPassword);

            // Show a success message
            const successMessage = document.getElementById("success-message");
            successMessage.classList.remove("hidden");
            // Hide the password reset form
            const resetPasswordForm = document.getElementById("reset-password-form");
            resetPasswordForm.classList.add("hidden");

        } catch (error) {
            console.error(error);
            alert("Failed to reset password.");
        }
    }
}

    const resetPassword = new ResetPassword();
    resetPassword.mount().then(() => console.log("Mounted reset password page."));
});


