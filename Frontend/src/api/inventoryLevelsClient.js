import BaseClass from "../util/baseClass";
import axios from 'axios'

/**
 * Client to call the MusicPlaylistService.
 *
 * This could be a great place to explore Mixins. Currently the client is being loaded multiple times on each page,
 * which we could avoid using inheritance or Mixins.
 * https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Classes#Mix-ins
 * https://javascript.info/mixins
 */
export default class InventoryLevelsClient extends BaseClass {

    constructor(props = {}){
        super();
        const methodsToBind = ['clientLoaded', 'getAllInventory'];
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
        if (this.props.hasOwnProperty("onReady")){
            this.props.onReady();
        }
    }

    /**
     * Gets the concert for the given ID.
     * @param id Unique identifier for a concert
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The concert
     */
    async getAllInventory(errorCallback) {
        try {
            const response = await this.client.get(`/products`);
            return response.data;
        } catch (error) {
            this.handleError("getAllProducts", error, errorCallback)
        }
    }


    async buyProducts(errorCallback, productsInCart, quantity, username){
        let url = "/products/"
        let quantityArr = [];
        // let quantityJson = `{"quantity": [`;
        productsInCart.forEach(item => {
            quantityArr.push(quantity.get(item.productID));
            // quantityJson += `${quantity.get(item.productID)}`;
            // quantityJson +=
            url += item.productID;
            url += ',';
        });
        let newUrl = url.substring(0, url.length - 1);
        try{
            console.log(newUrl);
            const response = await this.client.put(newUrl, {
                quantity: quantityArr,
                userName: username
            });
            return response.data;
            console.log(response.data)
        } catch (error){
            this.handleError("buyProducts", error, errorCallback);
        }
    }

    /**
     * Helper method to log the error and run any error functions.
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