import axios from "axios";
import BindingClass from "../util/bindingClass";
import Authenticator from "./authenticator";

/**
 * Client to call the WrenchWenchService
 *
 * This could be a great place to explore Mixins. Currently the client is being loaded multiple times on each page,
 * which we could avoid using inheritance or Mixins.
 * https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Classes#Mix-ins
 * https://javascript.info/mixins
  */
export default class WrenchWenchClient extends BindingClass {
    constructor(props = {}) {
        super();

        this.bindClassMethods(['clientLoaded','getVehicle', 'getAllVehicles',
                               'getIdentity','getProfile', 'createVehicle',
                               'login', 'logout', 'createVehicleRecord',
                               'getVehicleRecordsList', 'updateRecord', 'deleteRecord'
                               ],this);

        this.authenticator = new Authenticator();
        this.props = props;

        axios.defaults.baseURL = process.env.API_BASE_URL;
        this.axiosClient = axios;
        this.clientLoaded();
    }

    /**
     * Run any functions that are supposed to be called once the client has loaded successfully.
     */
    clientLoaded() {
        if (this.props.hasOwnProperty("onReady")) {
            this.props.onReady(this);
        }
    }

    /**
     * Get the identity of the current user
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The user information for the current user.
     */
    async getIdentity(errorCallback) {
        try {
            const isLoggedIn = await this.authenticator.isUserLoggedIn();

            if (!isLoggedIn) {
                return undefined;
            }

            return await this.authenticator.getCurrentUserInfo();
        } catch (error) {
            this.handleError(error, errorCallback);
        }
    }

    async isLoggedIn(errorCallback){
        const isLoggedIn = await this.authenticator.isUserLoggedIn();
        if (!isLoggedIn){
            return undefined;
        }
        return true;
    }

    async login() {
        this.authenticator.login();
    }

    async logout() {
        this.authenticator.logout();
    }

    async getTokenOrThrow(unauthenticatedErrorMessage) {
        const isLoggedIn = await this.authenticator.isUserLoggedIn();
        if (!isLoggedIn) {
            throw new Error(unauthenticatedErrorMessage);
        }

        return await this.authenticator.getUserToken();
    }
    /**
     * Gets the vehicle for the given vin.
     * @param vin a unique identifier for a vehicle
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The vehicle's metadata.
     */
    async getVehicle(vin, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can view vehicles.");
            const response = await this.axiosClient.get(`/vehicles/${vin}`, {
                headers: {
                    Authorization: `Bearer ${token}`
                },
            });
            return response.data.vehicle;
        } catch (error) {
            this.handleError(error, errorCallback);
        }
    }

    async getAllVehicles(errorCallback){
        try{
            const token = await this.getTokenOrThrow("Only authenticated users can view vehicles.");
            const response = await this.axiosClient.get(`/vehicles`, {
                headers: {
                    Authorization: `Bearer: ${token}`
                },
            });
            return response.data.vehicleList;
        } catch (error){
            this.handleError(error, errorCallback);
        }
    }

    async createVehicle(vin, errorCallback) {
    try {
        const token = await this.getTokenOrThrow("Only authenticated users can create vehicles.");

        const vehicle = {
          vin: vin,
        };

        const response = await this.axiosClient.post(`vehicles`, vehicle, {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });

        return response.data.vehicle;
      } catch (error) {
        this.handleError(error, errorCallback);
      }
    }

    async createVehicleRecord(vin, description, priorityLevel, errorCallback) {
            console.log("vin:" + vin + " description:" + description + " priorityLevel: "+priorityLevel);
            try {
                const token = await this.getTokenOrThrow("Only authenticated users can create records for vehicles.");



                //console.log("Record" + record + " vin:" + record.vin + " description: " + record.description);

                const response = await this.axiosClient.post(`vehicles/${vin}/records`,{
                    vin: vin,
                    description: description,
                    priorityLevel: priorityLevel
                },{
                  headers: {
                    Authorization: `Bearer ${token}`
                  },
                });

                return response.data;
              } catch (error) {
                this.handleError(error, errorCallback);
              }
        }

    async getVehicleRecordsList(vin, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can view vehicles records.");
            const response = await this.axiosClient.get(`vehicles/${vin}/records`,{
                headers: {
                    Authorization: `Bearer ${token}`
                },
            });
            return response.data;
        } catch (error) {
            this.handleError(error, errorCallback);
        }
    }

    async updateRecord(vin, recordId, description, status, priorityLevel, errorCallback){
        try{
            const token = await this.getTokenOrThrow("Only authenticated users can update a record.");
            const response = await this.axiosClient.put(`vehicles/${vin}/records/${recordId}`, {
                vin: vin,
                recordId: recordId,
                description: description,
                status: status,
                priorityLevel: priorityLevel,
            }, {
                headers: {
                    Authorization: `Bearer ${token}`
                },
            });
            return response.data.record;
        } catch(error){
            this.handleError(error, errorCallback);
        }
    }

    async deleteRecord(vin, recordId, errorCallback){
        try{
            const token = await this.getTokenOrThrow("Only authenticated users can delete a record.");
            console.log("Token: "+ token);
            const response = await this.axiosClient.delete(`vehicles/${vin}/records/${recordId}`, {
                headers: {
                    Authorization: `Bearer ${token}`
            }});
            console.log("Response: ", response);

            return response.data;
        } catch (error) {
            console.log(error);
            this.handleError(error, errorCallback);
        }
    }

    async getProfile(id, errorCallback){
        try {
            console.log(id + " id");
            const token = await this.getTokenOrThrow("Only authenticated users can view a profile");
            const response = await this.axiosClient.get(`profiles/${id}`, {
                Authorization: `Bearer ${token}`,
                'Content-Type': 'application/json'
            });

            return response.data;
        } catch(error) {
            this.handleError(error, errorCallback);
        }
    }

    /**
     * Helper method to log the error and run any error functions.
     * @param error The error received from the server.
     * @param errorCallback (Optional) A function to execute if the call fails.
     */
    handleError(error, errorCallback) {
        console.error(error);

        const errorFromApi = error?.response?.data?.error_message;
        if (errorFromApi) {
            console.error(errorFromApi);
            error.message = errorFromApi;
        }

        if (errorCallback) {
            errorCallback(error);
        }
    }
}
