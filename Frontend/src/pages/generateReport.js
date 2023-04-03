import BaseClass from "../util/baseClass";
import ReportClient from "../api/reportClient";

class generateReport extends BaseClass {
    constructor() {
        super();
        const methodsToBind = ["generateReport"];
        this.bindClassMethods(methodsToBind, this);
        this.client = new ReportClient();
    }

    async mount() {
        try {
            await this.client.login("username", "password");
            await this.client.mount();
            await this.generateReport();
            await this.client.logout();
        } catch (error) {
            alert("Error generating report!");
        }
    }

    async generateReport() {
        try {
            const report = await this.client.generateReport();
            console.log(report);
            const reportElement = document.getElementById("report");
            if (reportElement) {
                reportElement.innerText = report;
            }
        } catch (error) {
            alert("Error generating report!");
        }
    }
}

const main = async () => {
    const generateReportPage = new generateReport();
    await generateReportPage.mount();
};

window.addEventListener("DOMContentLoaded", main);
