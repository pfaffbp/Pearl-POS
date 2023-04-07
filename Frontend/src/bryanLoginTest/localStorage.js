// Save user login information to localStorage
localStorage.setItem('users', JSON.stringify([
    { email: 'example1@example.com', password: 'password123' },
    { email: 'example2@example.com', password: 'password456' }
]));

// Retrieve user login information from localStorage
const users = JSON.parse(localStorage.getItem('users'));

// Remove user login information from localStorage
localStorage.removeItem('users');
