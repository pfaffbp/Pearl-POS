import BaseClass from "../util/baseClass";
import AddProductClient from "../api/addProductClient";
import DataStore from "../util/DataStore";

class AddProductPage extends BaseClass {

    constructor() {
        super();
        this.bindClassMethods(['createProductEvent'], this);
        this.dataStore = new DataStore();

    }

    async mount() {
        document.getElementById('add-product-block').addEventListener('submit', this.createProductEvent);
        this.client = new AddProductClient();

        // this.dataStore.addChangeListener(this.renderExample)
    }

    async createProductEvent(event) {
        event.preventDefault();
        console.log("createProductEvent")
        // this.dataStore.set("products", null);
        //this section is to add A image
        // const image_input = document.querySelector('#image_input')
        // let upload_image = "";
        // image_input.addEventListener("change", function (){
        //     const reader = new FileReader();
        //     reader.addEventListener("load", () =>{
        //         upload_image = reader.result;
        //         console.log(upload_image);
        //         document.addEventListener("")
        //         document.querySelector("#ImageToBeAdded").style.backgroundImage = `url(${upload_image})`
        //     });
        //     reader.readAsDataURL(this.files[0]);
        // })


        let productName = document.getElementById("product-name").value;
        let productCategory = document.getElementById("product-category").value;
        let productQuantity = document.getElementById("quantity").value;
        let productPrice = document.getElementById("product-price").value;
        let productDescription = document.getElementById("product-description").value;
        console.log(productName, productPrice, productCategory);


        const createdProduct = await this.client.createProduct(productName, productPrice, productCategory,
            productQuantity, productDescription, this.errorHandler);

        this.dataStore.set("products", createdProduct);
        console.log(this.dataStore.get("products"))

        if (createdProduct) {
            this.showMessage(`Created ${createdProduct.name}!`)
        } else {
            this.errorHandler("Error creating!  Try again...");
        }
    }


}



const main = async () => {
    console.log("mounted")
    const productPage = new AddProductPage();
    productPage.mount();
};

window.addEventListener('DOMContentLoaded', main);