import BaseClass from "../util/baseClass.js";
import axios from 'axios'

export default class UserClient extends BaseClass {
    constructor(props = {}) {
        super();
        const methodsToBind = ['clientLoaded', 'createUser', 'getUser', 'checkEmailUniqueness', 'loginUser', 'deleteUser'];
        this.bindClassMethods(methodsToBind, this);
        this.props = props;
        this.clientLoaded(axios);
    }

    // Check if the client is loaded
    clientLoaded(client) {
        this.client = client;
        if (this.props.hasOwnProperty('onReady')) {
            this.props.onReady();
        }
    }

    // Create a new user with the given email and password
    async createUser(email, password, errorCallback) {
        try {
            const response = await this.client.post('/userTable/register', {
                email: email,
                password: password
            });
            return response.data;
        } catch (error) {
            this.handleError("createUser",error, errorCallback);
            throw error;
        }
    }

    // Get a user with the given email
    async getUser(email, errorCallback) {
        try {
            const response = await this.client.get('userTable/get', {
                params: {
                    email: email
                }
            });
            return response.data;
        }
        catch (error) {
            this.handleError(error, errorCallback);
            throw error;
        }
    }

    async getAllUsers() {
        try {
            const response = await this.client.get('/userTable/getAll');
            return response.data;
        } catch (error) {
            console.error(error);
            throw error;
        }
    }

    async logoutUser() {
        try {
            const response = await this.client.get('/userTable/logout');
            return response.data;
        } catch (error) {
            console.error(error);
            throw error;
        }
    }




    // Check if the given email is unique
    async checkEmailUniqueness(email, errorCallback) {
        try {
            const response = await this.client.post('/userTable/check', {
                email: email
            });
            return response.data;
        }
        catch (error) {
            this.handleError(error, errorCallback);
            throw error;
        }
    }

    // Login a user with the given email and password
    async loginUser(email, password, errorCallback) {
        try {
            const response = await this.client.post('/userTable/login', {
                email: email,
                password: password
            });
            return response.data;
        }
        catch (error) {
            this.handleError(error, errorCallback);
            throw error;
        }
    }

    // Delete a user with the given email
    async deleteUser(email, errorCallback) {
        try {
            if (!email) {
                new Error('Email parameter is required');
            }
            const response = await this.client.delete('/userTable/delete', {
                params: {
                    email: email
                }
            });
            return response.data;
        } catch (error) {
            this.handleError(error, errorCallback);
            throw error;
        }
    }

    async deleteUserByEmail(email, errorCallback) {
        try {
            if (!email) {
                new Error('Email parameter is required');
            }

            const response = await this.client.delete(`/userTable/deleteUserByEmail?email=${email}`);
            return response.data;
        } catch (error) {
            this.handleError(error, errorCallback);
            throw error;
        }
    }

    async updateUser(email, password, errorCallback) {
        try {
            if (!email) {
                new Error('Email parameter is required');
            }

            const response = await this.client.put(`/userTable/updateUser?email=${email}&password=${password}`);

            if (response.status === 200) {
                return response.data;
            } else {
                new Error(`Server error: ${response.status}`);
            }
        } catch (error) {
            if (errorCallback) {
                this.handleError(error, errorCallback);
            }
            throw error;
        }
    }

    // Get a user with the given email

    async findByEmail(email) {
        try {
            const response = await this.client.get(`/userTable/getByEmail/${email}`);
            return response.data;
        } catch (error) {
            console.error(error);
            throw error;
        }
    }


    // Handle errors by logging them and calling the error callback if provided
    handleError(error, errorCallback) {
        console.error(error);
        errorCallback && errorCallback(error);
    }

}

