import axios from 'https://cdn.skypack.dev/axios';

const createUserForm = document.getElementById('create-user-form');
const resultInfo = document.getElementById('result-info');

createUserForm.addEventListener('submit', (event) => {
    event.preventDefault();
    const formData = new FormData(createUserForm);

    axios.post('http://localhost:3333/users', formData)
        .then(response => {
            resultInfo.innerText = 'User created successfully';
        })
        .catch(error => {
            console.error(error);
            resultInfo.innerText = 'Error creating user';
        });
});
