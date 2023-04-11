// Define variables for the cart and product elements
const cart = document.querySelector('.cart');
const productItems = document.querySelectorAll('.product-item');

// Add event listener to each "Add to Cart" button
productItems.forEach((productItem) => {
    const addButton = productItem.querySelector('.add-button');
    const id = productItem.dataset.productId;
    const name = productItem.querySelector('.product-name').textContent;
    const price = parseFloat(productItem.querySelector('.product-price').textContent.replace('$', ''));

    addButton.addEventListener('click', () => {
        addToCart(id, name, price);
    });
});

// Function to add product to cart
function addToCart(id, name, price) {
    // Check if product already exists in cart
    const existingItem = cart.querySelector(`.cart-item[data-product-id="${id}"]`);
    if (existingItem) {
        // If product already exists, increment the quantity
        const quantity = existingItem.querySelector('.cart-item-quantity');
    } else {
        // If product doesn't exist, create a new cart item
        const cartItem = document.createElement('li');
        cartItem.classList.add('cart-item');
        cartItem.dataset.productId = id;
        cartItem.innerHTML = `
      <span class="cart-item-name">${name}</span>
      <span class="cart-item-quantity">1</span>
      <span class="cart-item-price">$${price.toFixed(2)}</span>
      <button class="remove-button">Remove</button>
    `;
        cart.appendChild(cartItem);

        // Add event listener to remove button
        const removeButton = cartItem.querySelector('.remove-button');
        removeButton.addEventListener('click', () => {
            cart.removeChild(cartItem);
        });
    }
}
