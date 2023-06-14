package com.nashss.se.WrenchWench.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = CreateRecordRequest.Builder.class)
public class CreateRecordRequest {

    private final String vin;
    private final String description;
    private final String priorityLevel;


    public CreateRecordRequest(String vin, String description, String priorityLevel){
        this.vin = vin;
        this.description = description;
        this.priorityLevel = priorityLevel;
    }

    public String getVin(){
        return vin;
    }

    public String getDescription(){
        return description;
    }

    public String getPriorityLevel(){
        return priorityLevel;
    }


    @Override
    public String toString(){
        return "CreateRecordRequest{" +
                "vin='" + vin + '\'' +
                ", description='" + description + '\'' +
                ", priorityLevel='" + priorityLevel + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder(){
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String vin;
        private String description;
        private String priorityLevel;

        public Builder withVin(String vin){
            this.vin = vin;
            return this;
        }
        public Builder withDescription(String description){
            this.description = description;
            return this;
        }
        public Builder withPriorityLevel(String priorityLevel){
            this.priorityLevel = priorityLevel;
            return this;
        }

        public CreateRecordRequest build(){
            return new CreateRecordRequest(vin, description, priorityLevel);
        }
    }
}
