package com.nashss.se.WrenchWench.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.WrenchWench.activity.requests.UpdateRecordRequest;
import com.nashss.se.WrenchWench.activity.results.UpdateRecordResult;

public class UpdateRecordLambda
        extends LambdaActivityRunner<UpdateRecordRequest, UpdateRecordResult>
    implements RequestHandler<AuthenticatedLambdaRequest<UpdateRecordRequest>, LambdaResponse>{

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<UpdateRecordRequest> input, Context context){
            return super.runActivity(
            () -> {
                UpdateRecordRequest unauthenticatedRequest = input.fromBody(UpdateRecordRequest.class);
                return input.fromUserClaims(claims ->
                    UpdateRecordRequest.builder()
                    .withVin(unauthenticatedRequest.getVin())
                    .withRecordId(unauthenticatedRequest.getRecordId())
                    .withDescription(unauthenticatedRequest.getDescription())
                    .withStatus(unauthenticatedRequest.getStatus())
                    .withPriorityLevel(unauthenticatedRequest.getPriorityLevel())
                    .build());
            },
            (request, serviceComponent) ->
                    serviceComponent.provideUpdateRecordActivity().handleRequest(request)
            );
        }
}
