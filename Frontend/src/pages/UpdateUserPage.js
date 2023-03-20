import UserClient from "../api/userClient";


const userClient = new UserClient();

const id = 1;
const username = "newUsername";
const email = "newEmail@example.com";
const password = "newPassword";

userClient.updateUser(id, username, email, password, (errorMessage) => {
    console.error(errorMessage);
}).then((updatedUser) => {
    console.log(updatedUser);
});
export default class UpdateUserPage {
}