import WrenchWenchClient from '../api/wrenchWenchClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

/**
 * Logic needed for the view vehicle page of the website.
 */
class ViewVehicle extends BindingClass {
        constructor() {
            super();
            this.bindClassMethods(['mount', 'createVehicleTable', 'getVehicleForPage', 'addVehicleToPage'], this);
            this.dataStore = new DataStore();
            this.header = new Header(this.dataStore);
            this.dataStore.addChangeListener(this.addVehicleToPage);
        }

        /**
         * Add the header to the page and load the WrenchWenchClient.
         */
        mount() {
            this.header.addHeaderToPage();
            this.client = new WrenchWenchClient();
            this.getVehicleForPage();
        }

        async getVehicleForPage() {
            const urlParams = new URLSearchParams(window.location.search);
            const vin = urlParams.get('vin');
            const vehicles = await this.client.getVehicle(vin);
            if (vehicles) {
              this.dataStore.set('vehicles', vehicles);
              this.createVehicleTable(vehicles);
              console.log("Vehicle is stored");
            } else {
              console.log("Vehicle not found");
            }
        }


        /**
         * Create a table for the vehicles
         */
        createVehicleTable(vehicles){
            const tableContainer = document.getElementById('table-container');

            tableContainer.innerHTML =
                 `<div class="table-responsive-xl">
                      <table class="table">
                        <tr>
                          <th>VIN</th>
                          <th>Make</th>
                          <th>Model</th>
                          <th>Year</th>
                          <th>Body Class</th>
                          <th>Type</th>
                          <th>Numbers of Doors</th>
                          <th>Manufacturer</th>
                          <th>Country</th>
                          <th>State</th>
                          <th>City</th>
                          <th>Fuel-Type</th>
                        </tr>

                        <tr>
                          <td>
                          <a href=viewVehicle.html/?vin=${vehicles.vin}>${vehicles.vin}</a>
                          </td>

                          <td>
                            ${vehicles.make}
                          </td>

                          <td>
                            ${vehicles.model}
                          </td>

                          <td>
                            ${vehicles.year}
                          </td>

                          <td>
                            ${vehicles.bodyClass}
                          </td>

                          <td>
                            ${vehicles.vehicleType}
                          </td>

                          <td>
                            ${vehicles.numOfDoors}
                          </td>

                          <td>
                            ${vehicles.manufacturerName}
                          </td>

                          <td>
                            ${vehicles.plantCountry}
                          </td>

                          <td>
                           ${vehicles.plantState}
                          </td>

                          <td>
                            ${vehicles.plantCity}
                          </td>

                          <td>
                            ${vehicles.engineCylinders}
                          </td>

                          <td>
                            ${vehicles.engineSize}
                          </td>

                          <td>
                            ${vehicles.engineHP}
                          </td>

                          <td>
                            ${vehicles.fuelType}
                          </td>

                          <td>
                          <a href="viewVehicle.html?vin=${vehicles.vin}" class="view-button">View Vehicle</a>
                          </td>
                        </tr>
            </table>
            </div>`;
        }


        /**
         * When the Vehicle is updated in the datastore, update the Vehicle metadata on the page.
         */
        addVehicleToPage() {
            const vehicles = this.dataStore.get('vehicles');
            if(vehicles == null){
            return;
            }

            //document.getElementById('tableContainer').innerHTML = this.createVehicleTable(vehicles);
        }
}
    /**
     * Main method to run when the page contents have loaded.
     */
    const main = async () => {
        const viewVehicle = new ViewVehicle();
        viewVehicle.mount();
        $('#myCollapsible').collapse({
          toggle: false
        });
    };

    window.addEventListener('DOMContentLoaded', main);