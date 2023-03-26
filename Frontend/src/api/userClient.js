import BaseClass from "../util/baseClass";
import axios from 'axios'

/**
 * Client to call the User Service.
 *
 * This could be a great place to explore Mixins. Currently, the client is being loaded multiple times on each page,
 * which we could avoid using inheritance or Mixins.
 * https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Classes#Mix-ins
 * https://javascript.info/mixins
 */
export default class UserClient extends BaseClass {

    constructor(props = {}) {
        super();
        const methodsToBind = ['clientLoaded', 'login', 'logout', 'createUser'];
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
     * Sends a login request with the given credentials.
     * @param username The username of the user.
     * @param password The password of the user.
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The login token for the user.
     */
    async login(username, password, errorCallback) {
        try {
            const response = await this.client.post('/login', {
                username: username,
                password: password
            });
            return response.data.token;
        } catch (error) {
            this.handleError("login", error, errorCallback)
        }
    }

    /**
     * Sends a logout request with the given token.
     * @param token The login token of the user.
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns A success message if the token was invalidated successfully.
     */
    async logout(token, errorCallback) {
        try {
            const response = await this.client.post('/login/logout', null, {
                headers: {
                    Authorization: token
                }
            });
            return response.data;
        } catch (error) {
            this.handleError("logout", error, errorCallback)
        }
    }

    /**
     * Sends a request to create a new user with the given credentials.
     * @param username The desired username of the new user.
     * @param password The desired password of the new user.
     * @param confirmPassword The confirmation of the desired password.
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns A success message if the user was created successfully.
     */
    async createUser(username, password, confirmPassword, errorCallback) {
        try {
            const response = await this.client.post('/users', {
                username: username,
                password: password,
                confirmPassword: confirmPassword
            });
            return response.data;
        } catch (error) {
            this.handleError("createUser", error, errorCallback);
        }
    }

    /**

     Helper method to log the error and run any error functions.
     @param method The name of the method that failed.
     @param error The error received from the server.
     @param errorCallback (Optional) A function to execute if the call fails.
     */
    handleError(method, error, errorCallback) {
        console.error(method + " failed - " + error);
        if (error.response.data.message !== undefined) {
            console.error(error.response.data.message);
        }
        if (errorCallback) {
            errorCallback(method + " failed - " + error);
        }
    }
}
