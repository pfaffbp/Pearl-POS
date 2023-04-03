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
            const response = await this.client.get('/transactions', {
                headers: {
                    Authorization: localStorage.getItem('token')
                }
            });
            const transactions = response.data;
            // Generate report based on transactions data
            const report = {
                transactions: transactions,
                // Add other report data as needed
            };
            // Save report to server
            await this.client.post('/report', report, {
                headers: {
                    Authorization: localStorage.getItem('token')
                }
            });
        }
        catch (error) {
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
        }
        catch (error) {
            this.handleError("getReport", error)
        }
    }

    handleError(method, error) {
        if (error.response) {
            console.log(error.response.data);
            console.log(error.response.status);
            console.log(error.response.headers);
        } else if (error.request) {
            console.log(error.request);
        } else {
            console.log('Error', error.message);
        }
    }
}

export default ReportClient;
