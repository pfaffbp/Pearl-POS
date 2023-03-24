// Select the login form from the DOM
const loginForm = document.querySelector('form');

// Select the username and password input fields from the DOM
const usernameInput = document.getElementById('username');
const passwordInput = document.getElementById('password');

// Add an event listener to the login form when it is submitted
loginForm.addEventListener('submit', async (event) => {
    event.preventDefault(); // Prevent the default form submission behavior

    // Get the values of the username and password fields
    const username = usernameInput.value;
    const password = passwordInput.value;

    // Send a POST request to the /login endpoint with the username and password in the request body
    const response = await fetch('/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json' // Set the content type of the request to JSON
        },
        body: JSON.stringify({ username, password }) // Convert the username and password to a JSON string and send it in the request body
    });

    // If the response status is 200 OK, then the login was successful
    if (response.ok) {
        const data = await response.json(); // Parse the response data as JSON
        // Do something with the response data, e.g. redirect to a dashboard page
    } else {
        alert('Invalid username or password'); // Show an alert if the login was not successful
    }
});
