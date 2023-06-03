import WrenchWenchClient from '../api/wrenchWenchClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

/**
 * Logic needed for the view playlist page of the website.
 */
class ViewVehicle extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'addVehicleToPage'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.addVehicleToPage);
        this.header = new Header(this.dataStore);
        console.log("viewvehicle constructor");
    }

    /**
     * Once the client is loaded, get the playlist metadata and song list.
     */
    async clientLoaded() {
        const urlParams = new URLSearchParams(window.location.search);
        const vin = urlParams.get('vin');
        document.getElementById('vehicle-name').innerText = "Loading Vehicle ...";
        const vehicle = await this.client.getVehicle(vin);
        this.dataStore.set('vehicle', vehicle);
        document.getElementById('records').innerText = "(loading records...)";
        const records = await this.client.getVehicleRecords(vin);
        this.dataStore.set('records', records);
    }

    /**
     * Add the header to the page and load the MusicPlaylistClient.
     */
    mount() {
        document.getElementById('add-record').addEventListener('click', this.addRecord);

        this.header.addHeaderToPage();

        this.client = new WrenchWenchClient();
        this.clientLoaded();
    }

    /**
     * When the playlist is updated in the datastore, update the playlist metadata on the page.
     */
    addVehicleToPage() {
        const vehicle = this.dataStore.get('vehicle');
        if (vehicle == null) {
            return;
        }

//        document.getElementById('playlist-name').innerText = playlist.name;
//        document.getElementById('playlist-owner').innerText = playlist.customerName;
//
//        let tagHtml = '';
//        let tag;
//        for (tag of playlist.tags) {
//            tagHtml += '<div class="tag">' + tag + '</div>';
//        }
//        document.getElementById('tags').innerHTML = tagHtml;
    }

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const viewVehicle = new ViewVehicle();
    viewVehicle.mount();
};

window.addEventListener('DOMContentLoaded', main);
