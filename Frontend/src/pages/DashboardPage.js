import BaseClass from "../util/baseClass";
import ReportClient from "../api/reportClient";

class DashboardPage extends BaseClass {
    constructor() {
        super();
        const methodsToBind = ['mount', 'generateReport'];
        this.bindClassMethods(methodsToBind, this);
        this.client = new ReportClient();
    }

    async mount() {
        await this.client.mount();
        await this.generateReport();
    }

    async generateReport() {
        try {
            const report = await this.client.generateReport();
            console.log(report);
        } catch (error) {
            alert('Error generating report!');
        }
    }
}

const main = async () => {
    const dashboardPage = new DashboardPage();
    await dashboardPage.mount();
}

window.addEventListener('DOMContentLoaded', main);
