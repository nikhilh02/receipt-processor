package com.receiptprocessor.receipt_processor.controller;

import com.atlassian.oai.validator.OpenApiInteractionValidator;
import com.atlassian.oai.validator.model.SimpleRequest;
import com.atlassian.oai.validator.report.ValidationReport;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.receiptprocessor.receipt_processor.entity.Receipt;
import com.receiptprocessor.receipt_processor.service.ReceiptService;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {

    private final ReceiptService receiptService;
    private final OpenApiInteractionValidator validator;

    ReceiptController(ReceiptService receiptService) throws IOException {
        OpenAPI openAPI = new OpenAPIV3Parser().read("api.yml");
        this.validator = OpenApiInteractionValidator.createFor(openAPI).build();
        this.receiptService = receiptService;
    }

    @GetMapping("/{id}/points")
    public ResponseEntity<Object> getReceiptPointsById(@PathVariable String id) {
        Map<String, Integer> responseMap = new HashMap<>();
        try{
            int responsePoints = receiptService.getReceiptPoints(id);
            responseMap.put("points", responsePoints);
            return ResponseEntity.status(HttpStatus.OK).body(responseMap);
        }catch (NullPointerException e) {
            Map<String, String> errorRes = new HashMap<>();
            errorRes.put("description", "No receipt found for that ID.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorRes);
        }
    }

    @PostMapping("/process")
    public ResponseEntity<Map<String, String>> processReceipt(@RequestBody Receipt receipt) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String requestBody = ow.writeValueAsString(receipt);
        ValidationReport report = validator.validateRequest(
                SimpleRequest.Builder.post("/receipts/process")
                        .withContentType("application/json")
                        .withBody(requestBody)
                        .build()
        );

        if(report != null && report.hasErrors()){
            Map<String, String> errorRes = new HashMap<>();
            errorRes.put("description", "The receipt is invalid.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorRes);
        }

        Map<String, String> responseId = new HashMap<>();
        String receiptId = receiptService.saveReceipt(receipt);
        responseId.put("id", receiptId);
        return ResponseEntity.status(HttpStatus.OK).body(responseId);
    }
}
