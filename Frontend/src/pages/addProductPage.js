import BaseClass from "../util/baseClass";
import AddProductClient from "../api/addProductClient";
import DataStore from "../util/DataStore";
import {Cloudinary} from '@cloudinary/url-gen'
import {Resize} from '@cloudinary/url-gen/actions'
const productImageMap = new Map;
let resultToLocal;
let myWidget;
class AddProductPage extends BaseClass {
    constructor() {
        super();
        this.bindClassMethods(['createProductEvent'], this);
        // this.bindClassMethods(['createProductEvent', 'uploadImage'], this);
        this.dataStore = new DataStore();
    }

    async mount() {
        document.getElementById('add-product-block').addEventListener('submit', this.createProductEvent);
        // document.getElementById('submit-product').addEventListener('submit', this.createProductEvent);
        // document.getElementById('upload_widget').addEventListener('click', this.uploadImage);
        // document.getElementById("upload_widget").addEventListener(
        //     "click",
        //     function () {
        //         myWidget.open();
        //     },
        //     false
        // );

        this.client = new AddProductClient();


        // this.dataStore.addChangeListener(this.renderExample)
    }

    async createProductEvent(event) {
        event.preventDefault();
        console.log("createProductEvent")
         // this.dataStore.set("products", null);

        var myWidget = cloudinary.createUploadWidget({
                cloudName: 'devbshzwb',
                uploadPreset: 'gix982o5',
                cropping: true,
                maxImageWidth: 272.5,}, (error, result) => {
                if (!error && result && result.event === "success") {
                    console.log('Done! Here is the image info: ', result.info);
                    resultToLocal = result.info;
                    console.log(resultToLocal);
                }
            }
        );


        let productName = document.getElementById("product-name").value;
        let productCategory = document.getElementById("product-category").value;
        let productQuantity = document.getElementById("quantity").value;
        let productPrice = document.getElementById("product-price").value;
        let productDescription = document.getElementById("product-description").value;
        console.log(productName, productPrice, productCategory);



        const createdProduct = await this.client.createProduct(productName, productPrice, productCategory,
            productQuantity, productDescription, this.errorHandler);

        const delay = ms => new Promise(res => setTimeout(res, ms));
        await myWidget.open();
        await delay(1000)
        while(myWidget.isShowing() === true){
            await delay(3000);
            console.log("waiting");
        }

        this.dataStore.set("products", createdProduct);
        const productToUse = this.dataStore.get("products");
        localStorage.setItem(productToUse.productID, resultToLocal.url);

        if (createdProduct) {
            this.showMessage(`Your Product ${createdProduct.productName} was created!`)
            await delay(3000);
            location.reload();
        } else {
            this.errorHandler("Error creating!  Try again...");
        }
    }
}





const main = async () => {
    console.log("mounted")
    const productPage = new AddProductPage();
    await productPage.mount();

};

window.addEventListener('DOMContentLoaded', main);
