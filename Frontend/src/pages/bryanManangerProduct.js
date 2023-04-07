// Select the form element
document.addEventListener('DOMContentLoaded', function() {
    const addProductForm = document.querySelector('#add-product-form');

    // Add an event listener to the form submit event
    addProductForm.addEventListener('submit', function(event) {
        event.preventDefault(); // prevent the form from submitting

        // Get the form values
        const productName = document.querySelector('#product-name').value;
        const productCategory = document.querySelector('#product-category').value;
        const quantity = document.querySelector('#quantity').value;
        const productPrice = document.querySelector('#product-price').value;
        const productDescription = document.querySelector('#product-description').value;
        const productImage = document.querySelector('#product-image').value;

        // Create a new product object
        const newProduct = {
            name: productName,
            category: productCategory,
            quantity: quantity,
            price: productPrice,
            description: productDescription,
            image: productImage
        };

        // Send the new product data to the server using fetch
        fetch('/api/products', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(newProduct)
        })
            .then(response => {
                if (response.ok) {
                    // If the response is ok, clear the form and show a success message
                    addProductForm.reset();
                    alert('Product added successfully!');
                } else {
                    // If the response is not ok, show an error message
                    alert('An error occurred while adding the product.');
                }
            })
            .catch(error => {
                // If there is an error, show an error message
                alert('An error occurred while adding the product.');
            });
    });
});
