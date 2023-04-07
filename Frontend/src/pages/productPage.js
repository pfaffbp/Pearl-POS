import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import AddProductClient from "../api/addProductClient";
import InventoryLevelsClient from "../api/inventoryLevelsClient";

class ProductPage extends BaseClass {
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
        let resultArea = document.getElementById("result_area");

        const inventory = this.dataStore.get("inventory");

        if (inventory) {
            let items = "";
            for (let product of inventory) {
                items += `
             <div class="wrapper">

    
    <figure class="product-displayed">
   <h1 class = "product_Name">${product.productName}</h1>
   <img src="Images/pickImage.png" width="272.5" height="272.5"/>
   <div class = "price-plus">
   <div class = "dollarSign">$</div>
   <div class = "product_Price"><strong>${product.price}</strong></div>
   </div>
   <div class = "product_Category"${product.category}</div>
   <div class = "product_Description">${product.description}</div>
   <div class = "product-footer">
   <button id = "add">Add to Cart</button>
   <button class = "minus-quantity">-</button>
   <input type ="text" class = "product_Quantity" placeholder="QTY" maxlength="3" size="1" min="1" max="100" required></input> 
   <button class = "add-quantity">+</button>
        </div>
   </figure> 
</div>                    
`;}
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
}




const main = async () => {
    console.log("mounted ProductPageYea")
    const productOnPage = new ProductPage();
    productOnPage.mount();
};
window.addEventListener('DOMContentLoaded', main);