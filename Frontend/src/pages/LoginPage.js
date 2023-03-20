// Get the form element and add a submit event listener
const loginForm = document.getElementById('login-form');
loginForm.addEventListener('submit', loginUser);

// Function to handle user login
function loginUser(event) {
    event.preventDefault();

    // Get the user's email and password from the form
    const email = document.getElementById('login-email-field').value;
    const password = document.getElementById('login-password-field').value;

    // Make a fetch request to the login API endpoint
    fetch('https://login.com/api/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ email, password })
    })
        .then(response => response.json())
        .then(data => {
            // If the login was successful, show a success message
            // and redirect the user to the dashboard page
            if (data.success) {
                Toastify({
                    text: 'Login successful!',
                    backgroundColor: 'green',
                    duration: 3000,
                    position: 'center'
                }).showToast();
                setTimeout(() => {
                    window.location.href = 'dashboard.html';
                }, 3000);
            } else {
                // If the login failed, show an error message
                Toastify({
                    text: 'Login failed. Please try again.',
                    backgroundColor: 'red',
                    duration: 3000,
                    position: 'center'
                }).showToast();
            }
        })
        .catch(error => {
            console.error('Error:', error);
            // If there was an error, show an error message
            Toastify({
                text: 'There was an error. Please try again.',
                backgroundColor: 'red',
                duration: 3000,
                position: 'center'
            }).showToast();
        });
}
async function handleLoginFormSubmit(event) {
    event.preventDefault();

    const email = document.getElementById('login-email-field').value;
    const password = document.getElementById('login-password-field').value;

    try {
        const token = await client.login(email, password);
        // Login was successful, update result section
        document.getElementById('result-info').textContent = 'Login successful. Redirecting...';
        window.location.replace('dashboard.html'); // Redirect to dashboard page
    } catch (error) {
        // Login failed, update result section
        document.getElementById('result-info').textContent = 'Login failed. Please try again.';
        Toastify({
            text: error.message,
            backgroundColor: 'var(--error-color)',
            close: true,
            duration: 3000
        }).showToast();
    }
}

document.getElementById('login-form').addEventListener('submit', handleLoginFormSubmit);
