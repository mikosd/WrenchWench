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
            this.bindClassMethods(['mount', 'createVehicleTable', 'getVehicleForPage','addVehicleToPage',
                                   'createRecordsTable', 'getRecordsForPage','addRecordsToPage',
                                   'createNewRecord'], this);
            this.dataStore = new DataStore();
            this.header = new Header(this.dataStore);
            this.dataStore.addChangeListener(this.addVehicleToPage);
            this.dataStore.addChangeListener(this.addRecordsToPage);
        }

        /**
         * Add the header to the page and load the WrenchWenchClient.
         */
        mount() {
            document.getElementById('submitNewRecordButton').addEventListener('click', this.createNewRecord.bind(this));
            this.header.addHeaderToPage();
            this.client = new WrenchWenchClient();
            this.getVehicleForPage();
            this.getRecordsForPage();
        }

        async getVehicleForPage() {
            const urlParams = new URLSearchParams(window.location.search);
            const vin = urlParams.get(`vin`);
            const vehicles = await this.client.getVehicle(vin);
            const records  = await this.client.getVehicleRecordsList(vin);


            if (vehicles) {
              this.dataStore.set(`vehicles`, vehicles);
              console.log("Vehicle is stored");

              if(records){
                this.dataStore.set(`records`, records);
                console.log("records are stored");
              }
            } else {
              console.log("Vehicle was not found");
            }
        }

    createVehicleTable(vehicles){
        const vehicleTableContainer = document.getElementById('vehicle-table-container');

        let html =
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
                      <th>Cylinders</th>
                      <th>Engine Size (L)</th>
                      <th>Horsepower (HP)</th>
                      <th>Fuel-Type</th>
                    </tr>

                    <tr>
                      <td>
                      <a href=viewVehicle.html/?vin=${vehicles.vin} id="vin-td">${vehicles.vin}</a>
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
                          <p>
                              <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseRecords" aria-expanded="false" aria-controls="collapseRecords">
                                  Vehicle Records
                              </button>
                          </p>
                      </td>
                    </tr>
                </table>
             </div>`;

             vehicleTableContainer.innerHTML += html;
        }

        /**
         * When the Vehicle is updated in the datastore, update the Vehicle metadata on the page.
         */
        addVehicleToPage() {
            const vehicles = this.dataStore.get(`vehicles`);
            if(vehicles == null){
            return;
            }
            this.createVehicleTable(vehicles);
        }


        async getRecordsForPage() {
            const urlParams = new URLSearchParams(window.location.search);
            const vin = urlParams.get('vin');
            const records = await this.client.getVehicleRecordsList(vin);
            if (records !== null) {
              this.dataStore.set('records', records);


              console.log("Records have been stored.");
            } else {
              console.log("Records were not found");
            }
        }

        createRecordsTable(records){
            const recordsTable = document.getElementById('records-table');

                let html = `<tr>
                              <td>
                              <a href=viewVehicle.html/?vin=${records.vin}>${records.vin}</a>
                              </td>

                              <td>
                                ${records.description}
                              </td>

                              <td>
                                ${records.status}
                              </td>

                              <td>

                              </td>
                            </tr>`;

                recordsTable.innerHTML += html;
        }

        createNewRecordTable(vin){
                    const recordsTable = document.getElementById('create-new-record-table');

                        let html = `<tr>
                                      <td>
                                        <a href=viewVehicle.html/?vin=${records.vin}>${records.vin}</a>
                                      </td>

                                      <td>
                                        ${records.description}
                                      </td>

                                      <td>
                                        ${records.status}
                                      </td>

                                      <td>

                                      </td>
                                    </tr>`;

                        recordsTable.innerHTML += html;
                }

        addRecordsToPage(){
            const records = this.dataStore.get(`records`);
            if(records == null){
                return;
            }
            this.createRecordsTable(records);
        }

        async createNewRecord(evt){
            evt.preventDefault();

            var checkedValue = $("input[name='flexRadioDefault']:checked").attr('id');
            console.log(checkedValue);

            const vin = document.getElementById("vin-td").innerText;
            const description = document.getElementById("input-description").innerText;
            const priority = checkedValue;

            const newRecord = await this.client.createVehicleRecord(vin, description, priority);

            if(newRecord){
                this.dataStore.set('records', newRecord);
                console.log("New Record Saved");
            } else {
                console.log("Failed to create a new record");
            }
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