import BaseClass from "../util/baseClass";
import axios from 'axios'
let user = localStorage.getItem("user").split(",")
let anotherUser = user[1].substring(user[1].lastIndexOf(":") + 2, user[1].lastIndexOf(`"`))

/**
 * Client to call the Transaction DynamoDB table to populate all the items they have bought.

 */
export default class PurchaseHistoryClient extends BaseClass {

    constructor(props = {}){
        super();
        const methodsToBind = ['clientLoaded', 'transactionByCustomerID'];
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
     * Gets the transactions by the logged in customer by the customerID.
     * @param id Unique identifier for a customer
     * @param errorCallback (Optional) A function to execute if the call fails.
     */
    async transactionByCustomerID(errorCallback) {
        //  localStorage.getItem(TestCustomer)
        try {
            const response = await this.client.get(`/transaction/customer/${anotherUser}`);
            return response.data;
        } catch (error) {
            this.handleError("transactionByCustomerID", error, errorCallback)
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
