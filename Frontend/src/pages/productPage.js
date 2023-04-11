import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import AddProductClient from "../api/addProductClient";
import InventoryLevelsClient from "../api/inventoryLevelsClient";
const productMap = new Map;
const productAndQuantityMap = new Map;
let cartItems = [];
let itemCart;
let productID;
let checkOutCart;
const sidebar = document.getElementById(".sidebar");
const cartItemsList = document.getElementById(".cart-items");
const closeSidebarButton = document.getElementById("checkout");


class ProductPage extends BaseClass {
    constructor() {
        super();
        this.bindClassMethods(['renderInventory', 'onRefresh', 'onLoad', 'addToCart', 'addButton', 'checkout', 'showSidebar'], this);
        this.dataStore = new DataStore();
    }

    /**
     * Once the page has loaded, set up the event handlers and fetch the concert list.
     */
    async mount() {
        this.client = new InventoryLevelsClient();
        this.dataStore.addChangeListener(this.renderInventory)
        this.onLoad();
        document.getElementById('checkout').addEventListener('click', this.checkout)
        document.getElementById('cart-icon').addEventListener('click', this.showSidebar)
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
   <form class = "product-footer">
   <button class = "add" id = ${product.productID}>Add to Cart</button>
   <input type ="text" class = "quantity" id = "${product.productID}qty" placeholder="QTY" maxlength="3" size="1" min="1" max="100" required></input> 
        </form>
   </figure> 
</div>                    
`;}
            //current map of all items
            for(let product of inventory){
                if(productMap.has(product.productID) != true){
                    productMap.set(product.productID, product);
                }
            }
            console.log(productMap);


            resultArea.innerHTML = items;
            itemCart = document.querySelectorAll('.add');
            itemCart.forEach(button => button.addEventListener('click', async function () {
                event.preventDefault();
                  combine(button.id, button.id + "qty");
                  add(button.id, button.id + "qty");
            }));



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

     addToCart(productId){
        console.log(productId)
    }

    // async updateCartItems() {
    //
    // }

    async onLoad(){
        let result = await this.client.getAllInventory(this.errorHandler);
        this.dataStore.set("inventory", result);
    }

    async addButton(event, productID){
    }

    async checkout(){
        const itemsToBuy = cartItems;
        const quantityOfItems = productAndQuantityMap;

        const results = await this.client.buyProducts(this.errorHandler(), itemsToBuy, quantityOfItems);
    }

    async showSidebar() {
        const sidebar = document.getElementById("sidebar");
        if(sidebar.style.display === "none") {
            sidebar.style.display = "block";
        } else{
            sidebar.style.display = "none"
        }
    }


}

function combine(productID, qty){
    const quantity = document.getElementById(`${qty}`);
    productAndQuantityMap.set(productID, quantity.value);
}

function add(productID) {
    // if(cartItems)
    let edit = productMap.get(productID)
    if(cartItems.includes(productMap.get(productID)) === false) {
        cartItems.push(productMap.get(productID));
        console.log("In methods")
    }
        console.log(cartItems)
        updateCartItems();
        showSidebar();
}

function updateCartItems() {
    const cartItemsList = document.getElementById("cart-items");

    let totalSale = 0;
    let createdHtml = false;
    let allItems = ""
    let moneySale = ""
    let createdCheckout = false;

    cartItemsList.innerHTML = ""
    cartItems.forEach((item) => {
        const currentQuantity = productAndQuantityMap.get(item.productID);
        totalSale += totalSales(currentQuantity, item.price)
        allItems += `
<div class = "shopping-cart">
    <div class = "add-cart-inline">
    <img src="Images/pickImage.png" width="80" height="80"/>
        <div class = "inline">
    <h3 class = "test">${item.productName}</h3>
    <div class = "current-price"><strong>Price:$${item.price}</strong></div>
    <div class = "current-quantity"><strong>Quantity:${currentQuantity}</strong></div>
        </div>
    </div>
</div>
`
        if(createdHtml !== true){
            createdHtml = true;
        moneySale += `
    <div id = "totalSale">0</div>
    `}

        checkOutCart = document.getElementById('checkout');
        const li = document.createElement("li");
        // li.innerText = allItems;
        cartItemsList.innerHTML = allItems;
        cartItemsList.innerHTML += moneySale;
    });
    const currentTotalSale = document.getElementById("totalSale");
    currentTotalSale.innerHTML = totalSale;
    console.log(cartItemsList);
}

function showSidebar() {
    const sidebar = document.getElementById("sidebar");
    sidebar.style.display = "block";
}

function hideSidebar() {
    sidebar.style.display = "none";
}

function totalSales(quantity, price){
    let totalSale = quantity * price
    return totalSale;
}

const main = async () => {
    console.log("mounted ProductPageYea")
    const productOnPage = new ProductPage();
    productOnPage.mount();
};
window.addEventListener('DOMContentLoaded', main);