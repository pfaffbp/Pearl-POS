import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import ReportClient from "../api/reportClient";

class GenerateReport extends BaseClass {
    constructor() {
        super();
        const methodsToBind = ['mount', 'generateReport'];
        this.bindClassMethods(methodsToBind, this);
        this.dataStore = new DataStore();
    }

    async mount() {
        // add event listener to the generate report button
        document.getElementById('generate-report').addEventListener('click', this.generateReport);
        this.client = new ReportClient();
    }

    async generateReport(event) {
        event.preventDefault();
        this.dataStore.set("report", null);
        let report = await this.client.generateReport(this.errorHandler());
        this.dataStore.set("report", report);
        if (report) {
            this.showMessage("Report generated successfully");
        } else {
            this.errorHandler("Report generation failed");
        }
    }
}
const main = async () => {
    const generateReport = new GenerateReport();
    await generateReport.mount();

}

window.addEventListener('DOMContentLoaded', main);


/*    async generateReport() {
        // make a request to the backend to retrieve all transactions
        fetch('/report')
            .then(response => response.json())
            .then(transactions => {
                // create an empty report array
                let report = [];

                // loop through all transactions and add them to the report
                transactions.forEach(transaction => {
                    report.push(transaction);
                });

                // display the report on the webpage
                document.getElementById('report').innerText = JSON.stringify(report, null, 2);
            })
            .catch(error => console.error(error));
    }*/