import BaseClass from "../util/baseClass";
import axios from "axios";

export default class AddProductClient extends BaseClass{

<<<<<<< HEAD

    constructor(props = {}){
        super();
        const methodsToBind = ['clientLoaded', 'createProduct', 'getAllInventory'];
=======
    constructor(props = {}){
        super();
        const methodsToBind = ['clientLoaded', 'createProduct'];
>>>>>>> 3cd46eb (save changes)
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


<<<<<<< HEAD
    async createProduct(productName, price, category, quantity, description, errorCallback){
       try {
           console.log("createProduct");
=======
    async createProduct(productName, price, category, quantity, productID, description, errorCallback){
       try {
>>>>>>> 3cd46eb (save changes)
           const response = await this.client.post(`products`, {
               productName: productName,
               price: price,
               category: category,
               quantity: quantity,
<<<<<<< HEAD
               description: description
           });
           console.log(response.data);
=======
               productID: productID,
               description: description
           });
>>>>>>> 3cd46eb (save changes)
           return response.data;
       } catch (error){
           this.handleError("createProduct", error, errorCallback)
       }
    }

<<<<<<< HEAD
    async getAllInventory(errorCallback) {
        try {
            const response = await this.client.get(`/products`);
            return response.data;
        } catch (error) {
            this.handleError("getAllProducts", error, errorCallback)
        }
    }

=======
>>>>>>> 3cd46eb (save changes)
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