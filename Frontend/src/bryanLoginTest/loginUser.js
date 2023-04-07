// import UserClient module
import UserClient from "./userClient";

// function to toggle password visibility
function togglePasswordVisibility(pwFields, pwIcons) {
    pwFields.forEach(pwField => {
        if (pwField.type === "password") {
            pwField.type = "text";
            pwIcons.forEach(icon => {
                icon.classList.replace("uil-eye-slash", "uil-eye");
            });
        } else {
            pwField.type = "password";
            pwIcons.forEach(icon => {
                icon.classList.replace("uil-eye", "uil-eye-slash");
            });
        }
    });
}

// function to toggle authentication forms
function toggleAuthForms(container, isSignup) {
    if (isSignup) {
        container.classList.add("active");
    } else {
        container.classList.remove("active");
    }
}

// function to handle login button click event
async function handleLogin(emailInput, passwordInput, userClient) {
    const email = emailInput.value;
    const password = passwordInput.value;

    try {
        const token = await userClient.login(email, password, error => {
            console.error(error);
        });
        console.log("Login successful. Token: " + token);
    } catch (error) {
        console.error("Login failed. Error: " + error);
    }
}

// function to handle signup button click event
function handleSignup(nameInput, emailInput, passwordInput, confirmPasswordInput) {
    const name = nameInput.value;
    const email = emailInput.value;
    const password = passwordInput.value;
    const confirmPassword = confirmPasswordInput.value;

    if (name === "" || email === "" || password === "" || confirmPassword === "") {
        alert("Please fill in all fields.");
        return;
    }
    if (password !== confirmPassword) {
        alert("Passwords do not match.");
        return;
    }
    if (!document.querySelector("#termCon").checked) {
        alert("Please accept terms and conditions.");
        return;
    }

    fetch("https://example.com/signup", {
        method: "POST",
        body: JSON.stringify({
            name: name,
            email: email,
            password: password
        }),
        headers: {
            "Content-Type": "application/json"
        }
    })
        .then(response => {
            if(response.ok){
                // signup successful, do something
                console.log("Signup successful.");
            }else{
                // signup failed, do something
                console.error("Signup failed.");
            }
        })
        .catch(error => {
            console.error("Error:", error);
        });
}


// function to validate signup inputs
function validateSignupInputs() {
    const name = document.querySelector("#signup-name").value;
    const email = document.querySelector("#signup-email").value;
    const password = document.querySelector("#signup-password").value;
    const confirmPassword = document.querySelector("#signup-confirm-password").value;


    if(name === "" || email === "" || password === "" || confirmPassword === ""){
        alert("Please fill in all fields.");
        return false;
    }
    if(password !== confirmPassword){
        alert("Passwords do not match.");
        return false;
    }
    if(!document.querySelector("#termCon").checked){
        alert("Please accept terms and conditions.");
        return false;
    }

    return true;
}

// function to handle signup
async function handleSignup() {
    if (validateSignupInputs()) {
        const name = document.querySelector("#signup-name").value;
        const email = document.querySelector("#signup-email").value;
        const password = document.querySelector("#signup-password").value;


        // create a new instance of UserClient
        const userClient = new UserClient();

        try {
            // call the createUser method of the UserClient instance
            const result = await userClient.createUser(name, email, password);
            // signup successful, do something
            console.log("Signup successful. Result: " + result);
        } catch (error) {
            // signup failed, do something
            console.error("Signup failed. Error: " + error);
        }
    }
}

// js code for signup button click event
const signupBtn = document.querySelector("#signup-btn");
signupBtn.addEventListener("click", handleSignup);

// module exports
export { validateSignupInputs, handleSignup };

