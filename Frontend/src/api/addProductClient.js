import BaseClass from "../util/baseClass";
import axios from "axios";

export default class AddProductClient extends BaseClass{

    constructor(props = {}){
        super();
        const methodsToBind = ['clientLoaded', 'createProduct'];
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


    async createProduct(productName, price, category, quantity, productID, description, errorCallback){
       try {
           const response = await this.client.post(`products`, {
               productName: productName,
               price: price,
               category: category,
               quantity: quantity,
               productID: productID,
               description: description
           });
           return response.data;
       } catch (error){
           this.handleError("createProduct", error, errorCallback)
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