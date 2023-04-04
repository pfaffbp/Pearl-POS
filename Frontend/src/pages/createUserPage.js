// Get the HTML form elements
const createUserForm = document.getElementById('createUserForm');
const usernameInput = document.getElementById('username');
const passwordInput = document.getElementById('password');
const confirmPasswordInput = document.getElementById('confirmPassword');

<<<<<<< HEAD
// Add an event listener to the form submission
createUserForm.addEventListener('submit', (event) => {
    event.preventDefault(); // Prevent the default form submission behavior

    // Check that the password and confirm password fields match
    if (passwordInput.value !== confirmPasswordInput.value) {
        alert('Passwords do not match.');
        return;
=======
class CreateUserPage extends baseClass {
    constructor() {
        super();
        this.bindClassMethods(['onCreate', 'checkEmailUniqueness', 'validatePassword'], this);
        this.dataStore = new DataStore();
>>>>>>> 78e3b20 (login and create user all test passing for service and controller)
    }

    // Create a new user object with the form data
    const newUser = {
        username: usernameInput.value,
        password: passwordInput.value
    };

<<<<<<< HEAD
    // Make a POST request to the server to create the new user
    fetch('/api/users', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(newUser)
    })
        .then(response => {
            if (response.ok) {
                alert('User created successfully.');
                createUserForm.reset();
            } else {
                alert('Failed to create user.');
            }
        })
        .catch(error => {
            console.error('Error creating user:', error);
            alert('An error occurred while creating the user.');
        });
});
=======
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
>>>>>>> 78e3b20 (login and create user all test passing for service and controller)
