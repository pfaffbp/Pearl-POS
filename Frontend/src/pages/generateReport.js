import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import ReportClient from "../api/reportClient";

class GenerateReport extends BaseClass {
    constructor() {
        super();
        const methodsToBind = ['generateReport', 'renderReport'];
        this.bindClassMethods(methodsToBind, this);
        this.dataStore = new DataStore();
    }

    async mount() {
        // add event listener to the generate report button
        document.getElementById('report').addEventListener('click', this.generateReport);
        this.client = new ReportClient();
        this.dataStore.addEventListener(this.renderReport);
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

    async renderReport() {
        let report = document.getElementById("report");
        const reportData = this.dataStore.get("report");
        if (reportData) {
            let items = "";
            for (let transaction of reportData) {
                items += `<div class="wrapper">
    <figure class="product-displayed">
    <div class = "transactionID">${transaction.transactionId}</div>
   <div class = "productID">${transaction.productID}</div>
  <div class = "quantity">${transaction.quantity}</div> 
   <div class = "totalSale">${transaction.totalSale}</div> </figure></div>`;
            }
            report.innerHTML = items;
        } else {
            report.innerHTML = "no report data";
        }
    }
}
    const main = async () => {
        const generateReport = new GenerateReport();
        await generateReport.mount();

    };
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