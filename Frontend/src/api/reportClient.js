import BaseClass from "../util/baseClass";
import axios from 'axios'

class ReportClient extends BaseClass {
    constructor(props = {}){
        super();
        const methodsToBind = ['clientLoaded', 'login', 'logout', 'generateReport', 'getReport'];
        this.bindClassMethods(methodsToBind, this);
        this.props = props;
        this.clientLoaded(axios);
    }


    clientLoaded(client) {
        this.client = client;
        if (this.props.hasOwnProperty("onReady")){
            this.props.onReady();
        }
    }

    async generateReport() {
        try {
            const response = await this.client.get('/report/generate', {
                headers: {
                    Authorization: localStorage.getItem('token')
                }
            });
            return response.data;
        } catch (error) {
            this.handleError("generateReport", error)
        }
    }

    async getReport() {
        try {
            const response = await this.client.get('/report', {
                headers: {
                    Authorization: localStorage.getItem('token')
                }
            });
            return response.data;
        } catch (error) {
            this.handleError("getReport", error)
        }
    }

    handleError(method, error, errorCallback) {
        if (errorCallback) {
            errorCallback(error);
        } else {
            console.error(`Error in ${method}: ${error}`);
        }
    }
}

export default ReportClient;
