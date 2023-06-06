export default class ClientMixin {
    async getIdentity(errorCallback){
        try{
            const isLoggedIn = await this.authenticator.isUserLoggedIn();

            if(!isLoggedIn){
                return undefined;
            }
            return await this.authenticator.getCurrentUserInfo();
        } catch (error){
            this.handleError(error, errorCallback);
        }
    }

    async login(){
        this.authenticator.login();
    }

    async logout(){
        this.authenticator.logout();
    }

    async getTokenOrThrow(unauthenticatedErrorMessage){
        const isLoggedIn = await this.authenticator.isUserLoggedIn();
        if (!isLoggedIn){
          throw new Error(unauthenticatedErrorMessage);
        }

        return await this.authenticator.getUserToken();
    }

    async getVehicle(vin, errorCallback){
        try {
            const response = await this.axiosClient.get(`vehicle/${vin}`);
            return response.data.vehicle;
        } catch (error){
          this.handleError(error, errorCallback);
        }
    }

    async getAllVehicles(errorCallback){
        try {
            const response = await this.axiosClient.get('vehicles/');
            return response.data.vehicleList;
        } catch (error) {
            this.handleError(error, errorCallback);
        }
    }

    async getProfile(id, errorCallback){
        try {
            console.log(id + ' id');
            const token = await this.getTokenOrThrow("Only authenticated users can view a profile");
            const response = await this.axiosClient.get(`profiles/${id}`, {
            Authorization: `Bearer ${token}`,
            'Content-Type': 'application/json'
        });

          return response.data;
        } catch (error) {
          this.handleError(error, errorCallback);
        }
    }
}