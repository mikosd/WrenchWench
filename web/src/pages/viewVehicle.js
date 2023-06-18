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
    this.bindClassMethods(['mount', 'getVehicleForPage', 'getRecordsForPage',
                           'createNewRecord','updateRecordsTable'], this);
    this.dataStore = new DataStore();
    this.header = new Header(this.dataStore);
    this.dataStore.addChangeListener(this.updateRecordsTable);
  }

  mount() {
    this.header.addHeaderToPage();
    this.client = new WrenchWenchClient();
    this.getVehicleForPage();
    this.getRecordsForPage();
    document.getElementById('submitNewRecordButton').addEventListener('click', this.createNewRecord);
  }

  async getVehicleForPage() {
    const urlParams = new URLSearchParams(window.location.search);
    const vin = urlParams.get('vin');
    const vehicles = await this.client.getVehicle(vin);

    if (vehicles) {
      this.dataStore.set('vehicles', vehicles);
      this.createVehicleTable(vehicles);
      console.log('Vehicle is stored');
    } else {
      console.log('Vehicle was not found');
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
                  </tr>
              </table>
           </div>`;

           vehicleTableContainer.innerHTML += html;
  }

  async getRecordsForPage() {

    const urlParams = new URLSearchParams(window.location.search);
    const vin = urlParams.get('vin');
    const records = await this.client.getVehicleRecordsList(vin);

    if (records !== null) {
      this.dataStore.set('records', records);
      console.log('Records have been stored.');
    } else {
      console.log('Records were not found');
    }
  }

  updateRecordsTable() {
    const records = this.dataStore.get('records');
    if (records === null) {
      return;
    }

    const recordsTable = document.getElementById('records-table');
    recordsTable.innerHTML = ''; // Clear the table



    records.recordsList.forEach((record) => {
      const recordId = record.recordId;
      const description = record.description;
      const status = record.status;
      const priorityLevel = record.priorityLevel;
      const timestamp = record.timestamp;
      const isNewRecord = record.isNewRecord

      const recordModel = record.recordModel;
      console.log('Record in UpdateRecordTable: ', record); // Debugging statement

      let html = `<tr>`

      if(isNewRecord){
        html += `<td>${recordModel.recordId}</td><td>${recordModel.description}</td>` +
        `<td>${recordModel.status}</td><td>${recordModel.priorityLevel}</td><td>${recordModel.timestamp}</td>`;

      } else {
        html += `<td>${record.recordId}</td><td>${description}</td>` +
        `<td>${status}</td><td>${priorityLevel}</td><td>${timestamp}</td>`;
      }
      html += `</tr>`;
      console.log('HTML: ', html);

      recordsTable.innerHTML += html;
    });
  }

  async createNewRecord(evt) {
    const urlParams = new URLSearchParams(window.location.search);
    const vin = urlParams.get('vin');
    evt.preventDefault();

    const checkedValue = $("input[name='flexRadioDefault']:checked").attr('value');

    const description = document.getElementById('input-description').value;
    const priorityLevel = checkedValue;

    const record = await this.client.createVehicleRecord(vin, description, priorityLevel);

    if (record) {
        record.isNewRecord = true;

        this.dataStore.update((state) => {
          const updatedRecords = { ...state.records};
          updatedRecords.recordsList.push(record);
          return { ...state, records: updatedRecords};
        });
        console.log('Record in CreateNewRecord: ', record); // Debugging statement
        console.log('New Record Saved');
    } else {
        console.log('Failed to create a new record');
    }
  }
}

const main = async () => {
  const viewVehicle = new ViewVehicle();
  viewVehicle.mount();
  $('#myCollapsible').collapse({
    toggle: false,
  });
};

window.addEventListener('DOMContentLoaded', main);
