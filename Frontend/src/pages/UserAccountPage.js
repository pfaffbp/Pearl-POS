import React from 'react';
import UserClient from '../api/userClient';



class UserAccountPage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            user: null
        };

        this.client = new UserClient();
    }

    componentDidMount() {
        const id = 1; // Example user ID
        this.client.getUser(id)
            .then((user) => {
                this.setState({ user });
            })
            .catch((error) => {
                console.error(error);
            });
    }

    onUpdate = (event) => {
        event.preventDefault();

        const id = 1; // Example user ID
        const username = document.getElementById("update-username-field").value;
        const email = document.getElementById("update-email-field").value;
        const password = document.getElementById("update-password-field").value;

        this.client.updateUser(id, username, email, password)
            .then((updatedUser) => {
                this.setState({ user: updatedUser });
            })
            .catch((error) => {
                console.error(error);
            });
    }

    render() {
        const user = this.state.user;

        if (!user) {
            return <div>Loading user information...</div>
        }

        return (
            <div>
                <div>ID: {user.id}</div>
                <div>Username: {user.username}</div>
                <div>Email: {user.email}</div>
                <form onSubmit={this.onUpdate}>
                    <input id="update-username-field" type="text" placeholder="Username" />
                    <input id="update-email-field" type="text" placeholder="Email" />
                    <input id="update-password-field" type="password" placeholder="Password" />
                    <button type="submit">Update</button>
                </form>
            </div>
        );
    }
}

export default UserAccountPage;
