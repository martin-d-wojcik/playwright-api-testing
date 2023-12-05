package org.example.RESTservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.microsoft.playwright.APIResponse;

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
}