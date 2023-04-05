import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import ReportClient from "../api/reportClient";

class GenerateReportPage extends BaseClass {
    constructor() {
        super();
        const methodsToBind = ["generateReport", "renderReport"];
        this.bindClassMethods(methodsToBind, this);
        this.dataStore = new DataStore();
        this.client = new ReportClient({
            apiUrl: "https://localhost:5001",
        });
    }

    async mount() {
        // add event listener to the generate report button
        const generateButton = document.getElementById("generate-report-btn");
        generateButton.addEventListener("click", (e)=>this.generateReport(e));

        // render the report
        await this.renderReport();

        // add event listener to the data store
        this.dataStore.addChangeListener(this.renderReport)
    }

    async generateReport(event) {
        event.preventDefault();
        this.dataStore.set("report", null);
        try {
            const report = await this.client.generateReport(this.errorHandler);
            this.dataStore.set("report", report);
            this.showMessage("Report generated successfully");
        } catch (error) {
            this.errorHandler("Report generation failed", error);
        }
    }

    async renderReport() {
        const reportContainer = document.getElementById("report-container");
        const report = this.dataStore.get("report");
        console.log(report);
        if (report) {
            const reportTable = document.createElement("table");
            reportTable.className = "report-table";
            const tableHead = document.createElement("thead");
            const tableHeaderRow = document.createElement("tr");
            const tableHeaderKeys = Object.keys(report[0]);
            tableHeaderKeys.forEach((key) => {
                const tableHeaderCell = document.createElement("th");
                tableHeaderCell.innerText = key;
                tableHeaderRow.appendChild(tableHeaderCell);
            });
            tableHead.appendChild(tableHeaderRow);
            reportTable.appendChild(tableHead);
            const tableBody = document.createElement("tbody");
            report.forEach((transaction) => {
                const tableBodyRow = document.createElement("tr");
                tableHeaderKeys.forEach((key) => {
                    const tableBodyCell = document.createElement("td");
                    tableBodyCell.innerText = transaction[key];
                    tableBodyRow.appendChild(tableBodyCell);
                });
                tableBody.appendChild(tableBodyRow);
            });
            reportTable.appendChild(tableBody);
            reportContainer.innerHTML = "";
            reportContainer.appendChild(reportTable);
        } else {
            reportContainer.innerHTML = "<p>No report generated yet.</p>";
        }
    }
}

const main = async () => {
    const page = new GenerateReportPage();
    await page.mount();
}

window.addEventListener("DOMContentLoaded", main)


export default GenerateReportPage;
