import BaseClass from "../util/baseClass";
import ExampleClient from "../api/exampleClient";
import AddProductClient from "../api/addProductClient";
import DataStore from "../util/DataStore";

class AddProductPage extends BaseClass {

    constructor() {
        super();
        this.bindClassMethods(['createProductEvent'], this);
        this.dataStore = new DataStore();
    }

    async mount() {
        document.getElementById('create-form').addEventListener('submit', this.createProductEvent);
        this.client = new AddProductClient();

        this.dataStore.addChangeListener(this.renderExample)
    }

    async createProductEvent(event) {
        event.preventDefault();
        this.dataStore.set("products", null);

        let productName = document.getElementById("product-name").value;
        let productCategory = document.getElementById("product-category").value;
        let productQuantity = document.getElementById("quantity").value;
        let productPrice = document.getElementById("product-price").value;
        let productDescription = document.getElementById("product-description").value;

        const createdProduct = await this.client.createProduct(productName, productPrice, productCategory,
            productQuantity, "12", productDescription, this.errorHandler);

        this.dataStore.set("products", createdProduct);

        if (createdProduct) {
            this.showMessage(`Created ${createdProduct.name}!`)
        } else {
            this.errorHandler("Error creating!  Try again...");
        }
    }
}

const main = async () => {
    const productPage = new AddProductPage();
    productPage.mount();
};

window.addEventListener('DOMContentLoaded', main);
