// Get the HTML form elements
const createUserForm = document.getElementById('createUserForm');
const usernameInput = document.getElementById('username');
const passwordInput = document.getElementById('password');
const confirmPasswordInput = document.getElementById('confirmPassword');

// Add an event listener to the form submission
createUserForm.addEventListener('submit', (event) => {
    event.preventDefault(); // Prevent the default form submission behavior

    // Check that the password and confirm password fields match
    if (passwordInput.value !== confirmPasswordInput.value) {
        alert('Passwords do not match.');
        return;
    }

    // Create a new user object with the form data
    const newUser = {
        username: usernameInput.value,
        password: passwordInput.value
    };

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
