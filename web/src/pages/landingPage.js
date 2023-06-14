import WrenchWenchClient from '../api/wrenchWenchClient';
import BindingClass from '../util/bindingClass';
import Header from '../components/header';
import DataStore from '../util/DataStore';

class LandingPage extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'login', 'redirectToViewVehicle', 'addEventListener'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.addVehiclesToPage);
        this.header = new Header(this.dataStore);
        this.client = new WrenchWenchClient();
        this.clientLoaded();
    }

//    async isLoggedIn(errorCallback){
//        const isLoggedIn = await this.authenticator.isUserLoggedIn();
//        if (!isLoggedIn){
//            return undefined;
//        }
//        return true;
//    }


    async clientLoaded() {
        const vehicles = await this.client.getAllVehicles();
        this.dataStore.set('vehicles', vehicles);
        console.log("vehicles", vehicles);
    }


    async mount() {
        document.getElementById('submitVinButton').addEventListener('click', this.submit);
        this.header.addHeaderToPage();
        this.client = new WrenchWenchClient();
        this.clientLoaded();
    }

    async login(){
        await this.client.login();
    }


    async submit(evt) {
    evt.preventDefault();

    const errorMessageDisplay = document.getElementById('error-message');
    errorMessageDisplay.innerText = ``;
    errorMessageDisplay.classList.add('hidden');

    const submitButton = document.getElementById('submitVinButton');
    const origButtonText = submitButton.innerText;
    submitButton.innerText = 'Loading...';

//    var submitVinButton = document.getElementById('submitVinButton');
//    submitVinButton.addEventListener('click', function(){
//        const vin = document.getElementById('inputVin').value;
//    });

    const vin = document.getElementById('inputVin').value;

    const vehicles = await this.client.landingPage(vin, (error) => {
        createButton.innerText = origButtonText;
        errorMessageDisplay.innerText = `Error: ${error.message}`;
        errorMessageDisplay.classList.remove('hidden');
    });
    this.dataStore.set('vehicles', vehicles);
    }

   redirectToViewVehicle() {
        const vehicles = this.dataStore.get('vehicles');
        if (vehicles != null) {
            window.location.href = `/viewVehicle.html?id=${vehicles.vin}`;
        }
   }



}
/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const landingPage = new LandingPage();
    landingPage.mount();
};


window.addEventListener('DOMContentLoaded', main);