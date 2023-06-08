import WrenchWenchClient from "../api/wrenchWenchClient"
import BindingClass from "../util/bindingClass";
import Header from '../components/header';
import DataStore from "../util/DataStore";

class LandingPage extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'createTable', 'addVehiclesToPage'], this);
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


//        const loggedIn = await this.client.isLoggedIn();
//        console.log("HERE", loggedIn);
//        if(loggedIn){
//            window.location.href= "/index.html";
//        }
//        document.getElementById('logout').addEventListener('click', this.login);
    async mount() {
        this.header.addHeaderToPage();
        this.client = new WrenchWenchClient();
        this.clientLoaded();

    }

    async login(){
            await this.client.login();
    }

    createTable(vehicles){
            if (vehicles.length === 0) {
                return '<h4>No results found</h4>';
            }

            let html = '<table><tr><th>VIN</th><th>Model</th><th>Year</th></tr>';
            for (const res of vehicles) {
                html += `
                <tr>
                    <td>
                        <a href=viewVehicle.html?projectId=${res.vin}>${res.vin}</a>
                    </td>
                    <td>
                        ${res.model}
                    </td>
                    <td><a href="viewVehicle.html?vin=${res.vin}" class="view-button">View Vehicle</a>
                </tr>`;
            }
            html += '</table>';

            return html;
        }

    addVehiclesToPage() {
            const vehicles = this.dataStore.get('vehicles');
            if (vehicles == null) {
                return;
            }

            //document.getElementById('project-title').innerText = project.title;
            //document.getElementById('project-description').innerText = project.description;

            let ticketHtml = '';
            let vin;
            for (vin of vehicles) {
                ticketHtml += '<div class="vehiclesDiv">' + vin + '</div>';
            }

            document.getElementById('vehiclesDiv').innerHTML = this.createTable(vin);
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