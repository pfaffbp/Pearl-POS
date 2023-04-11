import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import InventoryLevelsClient from "../api/inventoryLevelsClient";

/**
 * Logic needed for the view playlist page of the website.
 */
class InventoryLevelsPage extends BaseClass {

    constructor() {
        super();
        this.bindClassMethods(['renderInventory', 'onRefresh', 'onLoad'], this);
        this.dataStore = new DataStore();
    }

    /**
     * Once the page has loaded, set up the event handlers and fetch the concert list.
     */
    async mount() {
        document.getElementById('submit-refresh').addEventListener('click', this.onRefresh);
        this.client = new InventoryLevelsClient();

        this.dataStore.addChangeListener(this.renderInventory)
        this.onLoad();
    }

    // Render Methods --------------------------------------------------------------------------------------------------

    async renderInventory() {
        let resultArea = document.getElementById("result-info");

        const inventory = this.dataStore.get("inventory");

        if (inventory) {
            let items ="";
            for (let product of inventory){
                items += `
             <div class="wrapper">
                <aside class="aside aside-left">${product.productID}</aside>
                <aside class="aside aside-middle">${product.productName}</aside>
                <aside class="aside aside-right">${product.quantity}</aside>
            </div>                              
                `;
            }
            resultArea.innerHTML = items;
        } else {
            resultArea.innerHTML = "No Item";
        }
    }

    // Event Handlers --------------------------------------------------------------------------------------------------

    async onRefresh(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();

        this.dataStore.set("inventory", null);

        let result = await this.client.getAllInventory(this.errorHandler);
        this.dataStore.set("inventory", result);
        if (result) {
            this.showMessage(`refreshed!`)
        } else {
            this.errorHandler("Error doing GET ALL!  Try again...");
        }
    }


    async onLoad(){
        let result = await this.client.getAllInventory(this.errorHandler);
        this.dataStore.set("inventory", result);
    }
/*    async onCreate(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();
        this.dataStore.set("example", null);

        let name = document.getElementById("create-name-field").value;

        const createdExample = await this.client.createExample(name, this.errorHandler);
        this.dataStore.set("example", createdExample);

        if (createdExample) {
            this.showMessage(`Created ${createdExample.name}!`)
        } else {
            this.errorHandler("Error creating!  Try again...");
        }
    }*/
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const inventoryLevelsPage = new InventoryLevelsPage();
    inventoryLevelsPage.mount();
};

window.addEventListener('DOMContentLoaded', main);
