package org.example.RESTservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.microsoft.playwright.APIResponse;
import org.example.model.ActorModel;

import java.io.IOException;

public class ResponseHandler {

    public static boolean arrayInResponseHasValues(APIResponse response) {
        ObjectMapper objectMapper = new ObjectMapper();
        boolean returnValue = false;

        try {
            ArrayNode arrayNode = (ArrayNode) objectMapper.readTree(response.text());

            if (!arrayNode.isEmpty()) {
                return true;
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return returnValue;
    }

    public static String getValueFromResponse(APIResponse apiResponse, String key) {
        String returnValue = null;

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonResponse = objectMapper.readTree(apiResponse.body());
            returnValue = jsonResponse.get(key).asText();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return returnValue;
    }

    public static ActorModel deserialiseResponseToActorModelObject(APIResponse apiResponse) {
        ObjectMapper objectMapper = new ObjectMapper();
        ActorModel actorModel = null;

        // Actor class
        try {
            actorModel = objectMapper.readValue(apiResponse.text(), ActorModel.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return actorModel;
    }
}
