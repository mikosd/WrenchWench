import WrenchWenchClient from '../api/wrenchWenchClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';


/**
 * Logic needed for the view vehicle page of the website.
 */
class Records extends BindingClass {
  constructor() {
    super();
    this.bindClassMethods(['mount', 'getRecordsForPage', 'createNewRecord',
                           'updateRecordsTable', 'getRecordById'], this);
    this.dataStore = new DataStore();
    this.header = new Header(this.dataStore);
    this.dataStore.addChangeListener(this.updateRecordsTable);
    this.update
  }

  mount() {
    this.header.addHeaderToPage();
    this.client = new WrenchWenchClient();
    this.getRecordsForPage();
    document.getElementById('submitNewRecordButton').addEventListener('click', this.createNewRecord);
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
        html += `<td><a id="recordLink-${recordId}" href="#offcanvas-update-record" data-bs-toggle="offcanvas" data-toggle="collapse">`+
        `${recordModel.recordId}</a></td><td>${recordModel.description}</td>` +
        `<td>${recordModel.status}</td><td>${recordModel.priorityLevel}</td><td>${recordModel.timestamp}</td>`;

      } else {
        html += `<td><a id="recordLink-${recordId}" href="#offcanvas-update-record" data-bs-toggle="offcanvas" data-toggle="collapse">${recordId}</td><td>${description}</td>` +
        `<td>${status}</td><td>${priorityLevel}</td><td>${timestamp}</td>`;
      }
      html += `</tr>`;

      recordsTable.innerHTML += html;

      const recordLink = document.getElementById(`recordLink-${recordId}`);
      recordLink.addEventListener('click', (event) => {
        event.preventDefault();

        console.log("eventlistener: ", recordId);
        this.getRecordById(recordId);
      });
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

   async updatedRecord(evt) {
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

   async getRecordById(recordId){
        const urlParams = new URLSearchParams(window.location.search);
        const vin = urlParams.get('vin');

        const record = await this.client.getRecordByRecordId(vin, recordId);

     if(record){
        const description = record.description;
        const status = recordModel.status;
        const priorityLevel = recordModel.priorityLevel;
        const timestamp = recordModel.timestamp;

        document.getElementById("descriptionField").value = description;

        console.log("getRecordById description:", description);
     } else {
        console.log("Record not found for ID: ", recordId);
     }
   }
}

const main = async () => {
const records = new Records();
records.mount();
$('#myCollapsible').collapse({
  toggle: false,
});
};

window.addEventListener('DOMContentLoaded', main);
