import BaseClass from "../util/baseClass";
import axios from 'axios'

/**
 * Client to call the UserService.
 *
 * This could be a great place to explore Mixins. Currently the client is being loaded multiple times on each page,
 * which we could avoid using inheritance or Mixins.
 * https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Classes#Mix-ins
 * https://javascript.info/mixins
 */
export default class UserClient extends BaseClass {

    constructor(props = {}) {
        super();
        const methodsToBind = ['clientLoaded', 'getUser', 'createUser', 'updateUser', 'login'];
        this.bindClassMethods(methodsToBind, this);
        this.props = props;
        this.clientLoaded(axios);
    }

    /**
     * Run any functions that are supposed to be called once the client has loaded successfully.
     * @param client The client that has been successfully loaded.
     */
    clientLoaded(client) {
        this.client = client;
        if (this.props.hasOwnProperty("onReady")) {
            this.props.onReady();
        }
    }

    /**
     * Gets the user for the given ID.
     * @param id Unique identifier for a user
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The user
     */
    async getUser(id, errorCallback) {
        try {
            const response = await this.client.get(`/user/${id}`);
            return response.data;
        } catch (error) {
            this.handleError("getUser", error, errorCallback)
        }
    }

    /**
     * Creates a new user
     * @param username The username to give the new user
     * @param email The email to give the new user
     * @param password The password to give the new user
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The newly created user
     */
    async createUser(username, email, password, errorCallback) {
        try {
            const response = await this.client.post(`/user`, {
                username: username,
                email: email,
                password: password
            });
            return response.data;
        } catch (error) {
            this.handleError("createUser", error, errorCallback);
        }
    }

    /**
     * Updates the user for the given ID.
     * @param id Unique identifier for a user
     * @param username The username to update for the user
     * @param email The email to update for the user
     * @param password The password to update for the user
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The updated user
     */
    async updateUser(id, username, email, password, errorCallback) {
        try {
            const response = await this.client.put(`/user/${id}`, {
                username: username,
                email: email,
                password: password
            });
            return response.data;
        } catch (error) {
            this.handleError("updateUser", error, errorCallback);
        }
    }

    /**
     * Authenticates the user.
     * @param username The username of the user.
     * @param password The password of the user.
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The authenticated user.
     */
    async login(username, password, errorCallback) {
        try {
            const response = await this.client.post('/user/login', {
                username: username,
                password: password
            });
            return response.data;
        } catch (error) {
            this.handleError("login", error, errorCallback);
        }
    }

    handleError(login1, error, errorCallback) {
        if (errorCallback) {
            errorCallback(error);
        } else {
            console.error(error);
        }
    }
}

