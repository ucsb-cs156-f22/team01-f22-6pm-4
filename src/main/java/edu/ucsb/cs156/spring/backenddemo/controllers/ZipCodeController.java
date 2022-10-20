package edu.ucsb.cs156.spring.backenddemo.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ucsb.cs156.spring.backenddemo.services.CollegeSubredditQueryService;
import edu.ucsb.cs156.spring.backenddemo.services.ZipCodeQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Api(description="Zip code info service")
@RequestMapping("/api/zipcode")
@Log4j2
public class ZipCodeController {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    ZipCodeQueryService zipCodeQueryService;

    @ApiOperation(value="get info about zip code")
    @GetMapping("/get")
    public ResponseEntity<String> getZipCode(
            @ApiParam("zip code, e.g. 93117") @RequestParam String zipCode
    ) throws JsonProcessingException {
        log.info("getCountryCodes: zipCode={}", zipCode);
        String result = zipCodeQueryService.getJSON(zipCode);
        return ResponseEntity.ok().body(result);
    }
}
