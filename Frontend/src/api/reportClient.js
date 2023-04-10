import BaseClass from "../util/baseClass";
import axios from 'axios'

export default class ReportClient extends BaseClass {
    constructor(props = {}) {
        super();
        const methodsToBind = ['clientLoaded', 'generateReport'];
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

    async generateReport(errorCallback) {
        try {
            const response = await this.client.get('/transaction');
            return response.data;
        } catch (error) {
            this.handleError("generateReport", error, errorCallback)
        }
    }

    handleError(method, error, errorCallback) {
        console.error(method + "failed - " + error);
        if (error.response.data.message !== undefined) {
            console.error(error.response.data.message);
        }

        if (errorCallback) {
            errorCallback(method + "failed - " + error);
        }
    }
}

