import BaseClass from "../util/baseClass";
import axios from 'axios'

/**
 * Client to call the product service.
 */
export default class ProductClient extends BaseClass {

    constructor(props = {}) {
        super();
        const methodsToBind = ['clientLoaded', 'getProducts', 'getProductById', 'addProductToCart'];
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
     * Get all products
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns All products
     */
    async getProducts(errorCallback) {
        try {
            const response = await this.client.get(`/products`);
            return response.data;
        } catch (error) {
            this.handleError("getProducts", error, errorCallback)
        }
    }

    /**
     * Get a product by ID
     * @param productId The ID of the product
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The product
     */
    async getProductById(productId, errorCallback) {
        try {
            const response = await this.client.get(`/products/${productId}`);
            return response.data;
        } catch (error) {
            this.handleError("getProductById", error, errorCallback)
        }
    }

    /**
     * Add a product to the cart
     * @param productId The ID of the product to add to the cart
     * @param quantity The quantity of the product to add to the cart
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The updated cart
     */
    async addProductToCart(productId, quantity, errorCallback) {
        try {
            const response = await this.client.post(`/cart`, {
                productId: productId,
                quantity: quantity
            });
            return response.data;
        } catch (error) {
            this.handleError("addProductToCart", error, errorCallback);
        }
    }

    /**
     * Helper method to log the error and run any error functions.
     * @param method The name of the method that failed.
     * @param error The error received from the server.
     * @param errorCallback (Optional) A function to execute if the call fails.
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