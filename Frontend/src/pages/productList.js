import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import ProductClient from "../api/addproductClient";

/**
 * Logic needed for the product manager page of the website.
 */
class ProductManagerPage extends BaseClass {

    constructor() {
        super();
        this.bindClassMethods(['onGet', 'onCreate', 'onUpdate', 'onDelete', 'renderProduct'], this);
        this.dataStore = new DataStore();
    }

    /**
     * Once the page has loaded, set up the event handlers and fetch the product list.
     */
    async mount() {
        document.getElementById('get-by-id-form').addEventListener('submit', this.onGet);
        document.getElementById('create-form').addEventListener('submit', this.onCreate);
        document.getElementById('update-form').addEventListener('submit', this.onUpdate);
        document.getElementById('delete-form').addEventListener('submit', this.onDelete);
        this.client = new ProductClient();

        this.dataStore.addChangeListener(this.renderProduct)
    }

    // Render Methods --------------------------------------------------------------------------------------------------

    async renderProduct() {
        let resultArea = document.getElementById("result-info");

        const product = this.dataStore.get("product");

        if (product) {
            resultArea.innerHTML = `
                <p>ID: ${product.id}
                Name: ${product.name}
                Price: ${product.price}
                Description: ${product.description}</p>
            `
        } else {
            resultArea.innerHTML = "No Item";
        }
    }

    // Event Handlers --------------------------------------------------------------------------------------------------

    async onGet(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();

        let id = document.getElementById("id-field").value;
        this.dataStore.set("product", null);

        let result = await this.client.getProducts(id, this.errorHandler);
        this.dataStore.set("product", result);
        if (result) {
            this.showMessage(`Got ${result.name}!`)
        } else {
            this.errorHandler("Error doing GET!  Try again...");
        }
    }

    async onCreate(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();
        this.dataStore.set("product", null);

        let name = document.getElementById("create-name-field").value;
        let price = document.getElementById("create-price-field").value;
        let description = document.getElementById("create-description-field").value;

        const createdProduct = await this.client.createProduct(name, price, description, this.errorHandler);
        this.dataStore.set("product", createdProduct);

        if (createdProduct) {
            this.showMessage(`Created ${createdProduct.name}!`)
        } else {
            this.errorHandler("Error creating!  Try again...");
        }
    }

    async onUpdate(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();
        this.dataStore.set("product", null);

        let id = document.getElementById("update-id-field").value;
        let name = document.getElementById("update-name-field").value;
        let price = document.getElementById("update-price-field").value;
        let description = document.getElementById("update-description-field").value;

        const updatedProduct = await this.client.updateProduct(id, name, price, description, this.errorHandler);
        this.dataStore.set("product", updatedProduct);

        if (updatedProduct) {
            this.showMessage(`Updated ${updatedProduct.name}!`)
        } else {
            this.errorHandler("Error updating!  Try again...");
        }
    }

    async onDelete(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();
        this.dataStore.set("product", null);

        let id = document.getElementById("delete-id-field").

        value;
        const deletedProduct = await this.client.deleteProduct(id, this.errorHandler);
        this.dataStore.set("product", deletedProduct);

        if (deletedProduct) {

            this.showMessage(`Deleted ${deletedProduct.name}!`)

        } else {
            this.errorHandler("Error deleting!  Try again...");



        }

    }
}
