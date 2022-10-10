package com.upsoon.gateway.controller;


import com.upsoon.common.dto.OrganizationDTO;
import com.upsoon.gateway.client.OrganizationClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/organization/api")
public class OrganizationController {

    private final OrganizationClient organizationClient;

    public OrganizationController(OrganizationClient organizationClient) {
        this.organizationClient = organizationClient;
    }

    @PostMapping
    public ResponseEntity<OrganizationDTO> createOrganization(@RequestBody OrganizationDTO organizationDTO){
        return organizationClient.createOrganization(organizationDTO);
    }
}
