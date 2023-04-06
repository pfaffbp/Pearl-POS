import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import PurchaseHistoryClient from "../api/purchaseHistoryClient";

/**
 * Logic for displaying the transactions for the current logged in customere.
 */
class PurchaseHistoryPage extends BaseClass {

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
        this.client = new PurchaseHistoryClient();

        this.dataStore.addChangeListener(this.renderInventory)
        this.onLoad();
    }

    // Render Methods --------------------------------------------------------------------------------------------------
    /**
     *renders the display for the Product history page
     */
    async renderInventory() {
        let resultArea = document.getElementById("result-info");

        const inventory = this.dataStore.get("inventory");

        if (inventory) {
            let items ="";
            for (let product of inventory){
                items += `
             <div class="wrapper">

 
  <aside class="aside aside-1">${product.customerID}</aside>
   <aside class="aside aside-2">${product.transactionID}</aside>
   <aside class="aside aside-3">${product.date}</aside>
   <aside class="aside aside-4">${product.quantity}</aside>
  <aside class="aside aside-5">${product.totalSale}</aside>
  

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

        let result = await this.client.transactionByCustomerID(this.errorHandler);
        this.dataStore.set("inventory", result);
        if (result) {
            this.showMessage(`refreshed!`)
        } else {
            this.errorHandler("Error doing GET ALL!  Try again...");
        }
    }


    async onLoad(){
        let result = await this.client.transactionByCustomerID(this.errorHandler);
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
    const purchaseHistoryPage = new PurchaseHistoryPage();
    purchaseHistoryPage.mount();
};

window.addEventListener('DOMContentLoaded', main);
