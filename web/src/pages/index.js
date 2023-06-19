import WrenchWenchClient from '../api/wrenchWenchClient';
import BindingClass from '../util/bindingClass';
import Header from '../components/header';
import DataStore from '../util/DataStore';

//Logic for the createVehicle part of the site

class CreateVehicle extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'submit', 'redirectToViewVehicle'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.redirectToViewVehicle);
        this.header = new Header(this.dataStore);
    }

    mount() {
        document.getElementById('submitVinButton').addEventListener('click', this.submit);
        this.header.addHeaderToPage();
        this.client = new WrenchWenchClient();
    }

     async submit(evt) {
        evt.preventDefault();

        const vin = document.getElementById('inputVin').value; // Get the value from the "inputVin" input field
        const vehicles = await this.client.createVehicle(vin);

        this.dataStore.set('vehicles', vehicles);
     }

    redirectToViewVehicle(){
        const vehicles = this.dataStore.get('vehicles');
        const vin = document.getElementById('inputVin').value;
        if(vehicles != null){
            window.location.href = `/viewVehicle.html?vin=${vehicles.vin}`;
        }
    }
}

const main = async () => {
    const createVehicle = new CreateVehicle();
    createVehicle.mount();
};

window.addEventListener('DOMContentLoaded', main);