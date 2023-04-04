import BaseClass from "../util/baseClass";
import axios from 'axios';


export default class UserClient extends BaseClass {
    constructor(props = {}) {
        super();
        const methodsToBind = ['clientLoaded', 'login', 'register'];
        this.bindClassMethods(methodsToBind, this);
        this.props = props;
        this.clientLoaded(axios);
    }

    clientLoaded(client) {
        this.client = client;
        if (this.props.hasOwnProperty("onReady")) {
            this.props.onReady();
        }
    }

    async login(username, password, errorCallback) {
        try {
            const response = await this.client.post('/login', {username, password});
            return response.data.token;
        } catch (error) {
            this.handleError('login', error, errorCallback);
        }
    }

    async register(username, password, errorCallback) {
        try {
            const response = await this.client.post('/register', {username, password});
            return response.data;
        } catch (error) {
            this.handleError('register', error, errorCallback);
        }
    }

    handleError(method, error, errorCallback) {
        console.error(`${method} failed - ${error}`);
        if (error.response.data.message !== undefined) {
            console.error(error.response.data.message);
        }
        if (errorCallback) {
            errorCallback(`${method} failed - ${error}`);
        }
    }

    async createUser(name, email, password) {
        try {
            const response = await fetch("/createUser", {
                method: "POST",
                body: JSON.stringify({
                    name: name,
                    email: email,
                    password: password
                }),
                headers: {
                    "Content-Type": "application/json"
                }
            });

            if (response.ok) {
                return await response.json();
            } else {
                throw new Error("Failed to create user.");
            }
        } catch (error) {
            console.error("Error:", error);
            throw error;
        }
    }
}

