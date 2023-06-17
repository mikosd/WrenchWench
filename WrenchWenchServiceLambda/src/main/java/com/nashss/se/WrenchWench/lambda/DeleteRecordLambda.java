package com.nashss.se.WrenchWench.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.WrenchWench.activity.requests.DeleteRecordRequest;
import com.nashss.se.WrenchWench.activity.results.DeleteRecordResult;

public class DeleteRecordLambda
        extends LambdaActivityRunner<DeleteRecordRequest, DeleteRecordResult>
        implements RequestHandler<AuthenticatedLambdaRequest<DeleteRecordRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<DeleteRecordRequest> input, Context context){
        return super.runActivity(
                () -> {
                    DeleteRecordRequest unauthenticatedRequest = input.fromBody(DeleteRecordRequest.class);
                    return input.fromUserClaims(claims ->
                            DeleteRecordRequest.builder()
                                    .withRecordId(unauthenticatedRequest.getRecordId())
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideDeleteRecordActivity().handleRequest(request)
        );
    }
}
