package com.nashss.se.WrenchWench.dependency;

import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.WrenchWench.activity.*;
import com.nashss.se.WrenchWench.activity.requests.UpdateRecordRequest;
import dagger.Component;

import javax.inject.Singleton;

/**
 * Dagger component for providing dependency injection in the Music Playlist Service.
 */
@Singleton
@Component(modules = {DaoModule.class, MetricsModule.class})
public interface ServiceComponent {

    /**
     * Provides the relevant activity.
     * @return GetVehicleActivity
     */
    GetVehicleActivity provideGetVehicleActivity();

    GetAllVehiclesActivity provideGetAllVehiclesActivity();

    GetVehicleRecordsActivity provideGetVehicleRecordsActivity();

    CreateVehicleActivity provideCreateVehicleActivity();

    CreateRecordActivity provideCreateRecordActivity();

    DeleteRecordActivity provideDeleteRecordActivity();

    UpdateRecordRequest provideUpdateRecordActivity();
}