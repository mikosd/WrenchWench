import WrenchWenchClient from '../api/wrenchWenchClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

/**
 * Logic needed for the view playlist page of the website.
 */
class ViewVehicle extends BindingClass {
        constructor() {
            super();
            this.bindClassMethods(['mount', 'addVehicleToPage', 'createTable', 'getVehicleForPage'], this);
            this.dataStore = new DataStore();
            this.dataStore.addChangeListener(this.addVehicleToPage);
            this.header = new Header(this.dataStore);
        }

        /**
         * Add the header to the page and load the WrenchWenchClient.
         */
        mount() {
            //document.getElementById('add-record').addEventListener('click', this.addRecord);
            this.header.addHeaderToPage();
            this.client = new WrenchWenchClient();
            this.getVehicleForPage();
        }

        async getVehicleForPage() {
            const urlParams = new URLSearchParams(window.location.search);
            const vin = urlParams.get('vin');
            const vehicles = await this.client.getVehicle(vin);
            this.dataStore.set('vehicles', vehicles);
            this.addVehicleToPage();
            console.log("vehicles is stored");
        }



        createTable(vehicles){
            if (vehicles.length === 0) {
                return '<h4>No results found</h4>';
            }

            let html = '<table><tr><th>VIN</th><th>Make</th><th>Model</th><th>Year</th></tr>';

                html += `
                <tr>
                    <td>
                        <a href=viewVehicle.html?vin=${vehicles.vin}>${vehicles.model}</a>
                    </td>
                    <td>
                        ${vehicles.year}
                    </td>
                    <td><a href="viewVehicle.html?vin=${vehicles.vin}" class="view-button">View Project</a>
                </tr>`;
            html += '</table>';

            return html;
        }


        /**
         * When the playlist is updated in the datastore, update the playlist metadata on the page.
         */
        async addVehicleToPage() {
            const vehicle = this.dataStore.get('vehicles');
            if (vehicle == null) {
                return;
            }
            document.getElementById('vin').textContent = `VIN: ${vehicle.vin}`;
            document.getElementById('make').textContent = `Make: ${vehicle.make}`;
            document.getElementById('model').textContent = `Model: ${vehicle.model}`;
            document.getElementById('year').textContent = `Year: ${vehicle.year}`;
            //document.getElementById('viewVehiclesTable').innerHTML = this.createTable(vehicle);
        }
}
    /**
     * Main method to run when the page contents have loaded.
     */
    const main = async () => {
        const viewVehicle = new ViewVehicle();
        viewVehicle.mount();
    };

    window.addEventListener('DOMContentLoaded', main);