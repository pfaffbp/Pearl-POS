// Import UserClient from userClient.js
import UserClient from '../bryanLoginTest/userClient.js';
import baseClass from '../util/baseClass.js';

class UserTable extends baseClass {
    constructor() {
        super();
        this.userClient = new UserClient();
        this.tableBody = document.getElementById('user-table');
        this.tableBody.addEventListener('click', this.onTableClick.bind(this));
        this.render().then(r => console.log(r));
    }

    async render() {
        try {
            // Get the user data from UserClient
            const userData = await this.userClient.getAllUsers();

            // Clear the table body
            this.tableBody.innerHTML = '';

            // Add each user to the table
            userData.forEach(user => {
                const row = document.createElement('tr');

                // Add the email address to the row
                const emailCell = document.createElement('td');
                emailCell.textContent = user.email;
                row.appendChild(emailCell);

                // Add a delete button to the row
                const deleteCell = document.createElement('td');
                const deleteButton = document.createElement('button');
                deleteButton.innerText = 'Delete';
                deleteButton.classList.add('delete-button');

                deleteButton.dataset.email = user.email;
                deleteCell.appendChild(deleteButton);
                row.appendChild(deleteCell);

                // Add the row to the table body
                this.tableBody.appendChild(row);
            });
        } catch (error) {
            console.error(error);
        }
    }

    async deleteUser(email) {
        try {
            // Delete the user from local storage and dynamodb
            const storedUsers = JSON.parse(localStorage.getItem('users')) || [];
            const remainingUsers = storedUsers.filter(user => user.email !== email);
            localStorage.setItem('users', JSON.stringify(remainingUsers));

            // Delete the user from the server
            await this.userClient.deleteUserByEmail(email);

            // Re-render the table
            await this.render();
        } catch (error) {
            console.error(error);
        }
    }




    async onLogout() {
        localStorage.removeItem("user"); // Remove the user data from local storage
        try {
            await this.userClient.logoutUser(); // Call the logout endpoint
            alert("Logged out successfully!");
            window.location.href = "loginPage.html";
        } catch (error) {
            console.error(error);
            alert("Error logging out!");
        }
    }


    onTableClick(event) {
        if (event.target.tagName === 'BUTTON') {
            const email = event.target.dataset.email;
            this.deleteUser(email).then(r => console.log(r));
        }
    }
}

// Initialize the page
window.addEventListener('DOMContentLoaded', () => {
    const userTable = new UserTable();
    document.getElementById('logout-btn').addEventListener('click', userTable.onLogout.bind(userTable));
});

// Export class for testing purposes
export default UserTable;
