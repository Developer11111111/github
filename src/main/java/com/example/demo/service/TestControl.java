package com.example.demo.service;

import com.example.demo.request.GetRequest;
import com.example.demo.utils.GetResponse;
import com.fasterxml.jackson.databind.JsonNode;
import org.json.JSONObject;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TestControl {
    @GetMapping("/test/{id}")
    public ResponseEntity getAll(@PathVariable int id){
        GetRequest getRequest = new GetRequest(id,"Mark","Succerberg");
        GetRequest getRequest1 = new GetRequest(1,"Bill","Geyts");
        return ResponseEntity.ok(getRequest);
    }

    @RequestMapping(value = "/test1",method = RequestMethod.GET)
    public ResponseEntity get(Object object){

        return ResponseEntity.ok("hello");
    }

    @PostMapping("/post")
    public ResponseEntity getPost(@RequestBody JsonNode jsonpObject){

        String msgmethodparams = jsonpObject.get("msgmethodparams").toString();
        GetResponse gt = new GetResponse();

            try {
                JSONObject json = new JSONObject(gt.getResponseFromService(msgmethodparams));
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(json);
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity
                        .status(HttpStatus.BAD_GATEWAY)
                        .body("gt.sendPostRequest(msgmethodparams)");
            }


    }
}
