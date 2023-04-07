import BaseClass from "../util/baseClass";
import axios from "axios";

export default class AddProductClient extends BaseClass{


    constructor(props = {}){
        super();
        const methodsToBind = ['clientLoaded', 'createProduct', 'getAllInventory'];
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


    async createProduct(productName, price, category, quantity, description, errorCallback){
       try {
           console.log("createProduct");
           const response = await this.client.post(`products`, {
               productName: productName,
               price: price,
               category: category,
               quantity: quantity,
               description: description
           });
           console.log(response.data);
           return response.data;
       } catch (error){
           this.handleError("createProduct", error, errorCallback)
       }
    }

    async getAllInventory(errorCallback) {
        try {
            const response = await this.client.get(`/products`);
            return response.data;
        } catch (error) {
            this.handleError("getAllProducts", error, errorCallback)
        }
    }


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