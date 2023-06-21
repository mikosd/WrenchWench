import WrenchWenchClient from '../api/wrenchWenchClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';


/**
 * Logic needed for the view vehicle page of the website.
 */
class Vehicles extends BindingClass {
  constructor() {
    super();
    this.bindClassMethods(['mount','getVehicleForPage'], this);
    this.dataStore = new DataStore();
    this.header = new Header(this.dataStore);
  }

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
      console.log('Vehicle is stored');
    } else {
      console.log('Vehicle was not found');
    }
  }

   createVehicleTable(vehicles){
      const vehicleTableContainer = document.getElementById('vehicle-table-container');

      let html =
           `<div class="table-responsive-xl">
                <table class="table-striped">
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
  const vehicles = new Vehicles();
  vehicles.mount();
};

window.addEventListener('DOMContentLoaded', main);
