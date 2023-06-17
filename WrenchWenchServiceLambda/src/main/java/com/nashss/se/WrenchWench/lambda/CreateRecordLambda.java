package com.nashss.se.WrenchWench.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.WrenchWench.activity.requests.CreateRecordRequest;
import com.nashss.se.WrenchWench.activity.results.CreateRecordResult;

public class CreateRecordLambda
        extends LambdaActivityRunner<CreateRecordRequest, CreateRecordResult>
        implements RequestHandler<AuthenticatedLambdaRequest<CreateRecordRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<CreateRecordRequest> input, Context context){
        return super.runActivity(
                () -> {
                    CreateRecordRequest unauthenticatedRequest = input.fromBody(CreateRecordRequest.class);
                    return input.fromUserClaims(claims ->
                            CreateRecordRequest.builder()
                                    .withVin(unauthenticatedRequest.getVin())
                                    .withDescription(unauthenticatedRequest.getDescription())
                                    .withPriorityLevel(unauthenticatedRequest.getPriorityLevel())
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideCreateRecordActivity().handleRequest(request)
        );
    }
}


