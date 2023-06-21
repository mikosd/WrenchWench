import WrenchWenchClient from '../api/wrenchWenchClient';
import BindingClass from "../util/bindingClass";

/**
 * The header component for the website.
 */
export default class Header extends BindingClass {
    constructor() {
        super();

        const methodsToBind = [
            'addHeaderToPage', 'createUserInfoForHeader', 'createLoginButton', 'createLogoutButton'
        ];
        this.bindClassMethods(methodsToBind, this);

        this.client = new WrenchWenchClient();
    }

    /**
     * Add the header to the page.
     */
    async addHeaderToPage() {
        const currentUser = await this.client.getIdentity();
        const userInfo = this.createUserInfoForHeader(currentUser);

    }

    createUserInfoForHeader(currentUser) {
        const userInfo = document.getElementById('loginDiv');
        userInfo.classList.add('user');

        const childContent = currentUser
            ? this.createLogoutButton(currentUser)
            : this.createLoginButton();

        userInfo.appendChild(childContent);

        return userInfo;
    }

    createLoginButton() {
        return this.createButton('Login', this.client.login);
    }

    createLogoutButton(currentUser) {
        return this.createButton(`Logout: ${currentUser.name}`, this.client.logout);
    }

    createButton(text, clickHandler) {
        const button = document.createElement('button');
        button.classList.add('btn');
        button.href = '#';
        button.innerText = text;

        button.addEventListener('click', async () => {
            await clickHandler();
        });

        return button;
    }
}
