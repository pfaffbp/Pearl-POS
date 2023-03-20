import React, { useState } from 'react';
import UserClient from '../api/UserClient';


const CreateUserPage = () => {
    const [username, setUsername] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const userClient = new UserClient();

    const handleSubmit = (event) => {
        event.preventDefault();

        userClient.createUser(username, email, password, (errorMessage) => {
            console.error(errorMessage);
        }).then((createdUser) => {
            console.log(createdUser);
        });
    };

    return React.createElement("form", { onSubmit: handleSubmit },
        React.createElement("div", null,
            React.createElement("input", { htmlFor: "username" }, "Username:"),
            React.createElement("input", {
                type: "text",
                id: "username",
                value: username,
                onChange: (event) => setUsername(event.target.value)
            })
        ),
        React.createElement("div", null,
            React.createElement("input", { htmlFor: "email" }, "Email:"),
            React.createElement("input", {
                type: "email",
                id: "email",
                value: email,
                onChange: (event) => setEmail(event.target.value)
            })
        ),
        React.createElement("div", null,
            React.createElement("input", { htmlFor: "password" }, "Password:"),
            React.createElement("input", {
                type: "password",
                id: "password",
                value: password,
                onChange: (event) => setPassword(event.target.value)
            })
        ),
        React.createElement("input", { type: "submit" }, "Create User")
    );
};

export default CreateUserPage;
