import WrenchWenchClient from '../api/wrenchWenchClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

/**
 * Logic needed for the view playlist page of the website.
 */
class LoadGarage extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'addVehiclesToPage', 'createTable'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.addVehiclesToPage);
        this.header = new Header(this.dataStore);
        console.log("load-vehicle constructor");
    }

    createTable(vehicles){
        if (vehicles.length === 0) {
            return '<h4>No results found</h4>';
        }

        let html = '<table><tr><th>Project Title</th><th>Status</th><th>Actions</th></tr>';
        for (const res of vehicles) {
            html += `
            <tr>
                <td>
                    <a href=viewVehicle.html?projectId=${res.vin}>${res.model}</a>
                </td>
                <td>
                    ${res.year}
                </td>
                <td><a href="viewProject.html?projectId=${res.projectId}" class="view-button">View Project</a>
            </tr>`;
        }
        html += '</table>';

        return html;
    }

    /**
     * Once the client is loaded, get the playlist metadata and song list.
     */
    async clientLoaded() {
        const vehicles = await this.client.getVehicle();
        this.dataStore.set('vehicles', vehicles);

        console.log("vehicles", vehicles);
    }

    /**
     * Add the header to the page and load the TicketTrackerClient.
     */
    mount() {
        this.header.addHeaderToPage();

        this.client = new WrenchWenchClient();
        this.clientLoaded();
    }

    /**
     * When the playlist is updated in the datastore, update the playlist metadata on the page.
     */
    addVehiclesToPage() {
        const vehicles = this.dataStore.get('vehicles');
        if (vehicles == null) {
            return;
        }

        let recordHtml = '';
        for (vehicle of vehicles) {
            recordHtml += '<div class="vehicles"> ${vehicle.vin} </div>';
        }

        document.getElementById('vehicles').innerHTML = this.createTable(vehicles);
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const loadGarage = new LoadGarage();
    loadGarage.mount();
};

window.addEventListener('DOMContentLoaded', main);