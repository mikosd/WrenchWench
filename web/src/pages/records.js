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
    this.bindClassMethods(['mount', 'getRecordsForPage', 'createNewRecord', 'deleteRecord',
                           'updateRecordsTable', 'getRecordById', 'updateRecordById'], this);
    this.dataStore = new DataStore();
    this.header = new Header(this.dataStore);
    this.dataStore.addChangeListener(this.updateRecordsTable);
  }

  mount() {
    this.client = new WrenchWenchClient();
    this.getRecordsForPage();
    document.getElementById('submitNewRecordButton').addEventListener('click', this.createNewRecord);
    document.getElementById('submitUpdateButton').addEventListener('click', this.updateRecordById);
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

    const handleClick = (event) => {
        event.preventDefault();
        const target = event.target.closest('a');
        if(target && target.id.startsWith('recordLink-')){
            const recordId = target.id.split('-')[1];
            this.getRecordById(recordId);
        }
    };

    recordsTable.addEventListener('click', (event) => {
        const target = event.target;
        if (target.tagName === 'A' && target.id.startsWith('recordLink-')) {
          event.preventDefault();
          const recordId = target.id.split('-')[1];
          this.getRecordById(recordId);
        }
      });

    records.recordsList.forEach((record) => {
      let recordId = record.recordId;
      let description = record.description;
      let status = record.status;
      let priorityLevel = record.priorityLevel;
      let timestamp = record.timestamp;

      const isNewRecord = record.isNewRecord
      const recordModel = record.recordModel;

      let html = `<tr>`

      if(isNewRecord){
        recordId = recordModel.recordId;
        description = recordModel.description;
        status = recordModel.status;
        priorityLevel = recordModel.priorityLevel;
        timestamp = recordModel.timestamp;
      }
      const row = document.createElement('tr');

      row.innerHTML = `<td><a id="recordLink-${recordId}" href="#offcanvas-update-record" data-bs-toggle="offcanvas" data-toggle="collapse">${recordId}</a></td>
      <td id="description-${recordId}">${description}</td>
      <td id="status-${recordId}">${status}</td>
      <td id="priority-${recordId}">${priorityLevel}</td>
      <td id="timestamp-${recordId}">${timestamp}</td>
      <td><button id="deleteButton-${recordId}" data-recordId="${recordId}">Delete</button></td>
      </tr>`;

      recordsTable.appendChild(row);

      const deleteButton = row.querySelector(`#deleteButton-${recordId}`);
      deleteButton.addEventListener('click', this.deleteRecord.bind(this));
    });

    recordsTable.addEventListener('click', handleClick);
  }

  async createNewRecord(event) {
    const urlParams = new URLSearchParams(window.location.search);
    const vin = urlParams.get('vin');
    event.preventDefault();

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
        console.log("getRecordById is called");
        const urlParams = new URLSearchParams(window.location.search);
        const vin = urlParams.get('vin');


        const description = document.getElementById(`description-${recordId}`).textContent;
        const status = document.getElementById(`status-${recordId}`).textContent;
        const priorityLevel = document.getElementById(`priority-${recordId}`).textContent;

        document.getElementById("recordIdField").value = recordId;
        document.getElementById("descriptionField").value = description;
        document.getElementById("statusField").value = status;
        document.getElementById("priorityField").value = priorityLevel;
   }


    async updateRecordById(){
        console.log("updateRecord is called");
        const urlParams = new URLSearchParams(window.location.search);
        const vin = urlParams.get('vin');
        const recordId = document.getElementById("recordIdField").value;
        const description = document.getElementById("descriptionField").value;
        const status = document.getElementById("statusField").value;
        const priorityLevel = document.getElementById("priorityField").value;

        console.log("VIN: ",vin);
        console.log("recordId: ",recordId);
        console.log("description: ",description);
        console.log("status: ",status);
        console.log("priorityLevel: ",priorityLevel);

        const record = await this.client.updateRecord(vin, recordId, description, status, priorityLevel);
    }

    async deleteRecord(event){
        if(confirm("Delete this record?")){
            event.preventDefault();
            const urlParams = new URLSearchParams(window.location.search);
            const vin = urlParams.get('vin');
            const recordId = event.currentTarget.getAttribute('data-recordId');
            console.log("Record to be deleted: " + recordId + " with VIN: "+ vin);
            await this.client.deleteRecord(vin, recordId);
            alert(recordId + " has been deleted.");
            location.reload();
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
