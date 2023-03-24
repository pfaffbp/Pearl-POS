const form = document.querySelector('form');

form.addEventListener('submit', function(event) {
    event.preventDefault();

    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    if (username === 'admin' && password === 'password') {
        alert('Login successful!');
        window.location.href = 'dashboard.html'; // Redirect to dashboard page
    } else {
        alert('Incorrect username or password!');
    }
});
