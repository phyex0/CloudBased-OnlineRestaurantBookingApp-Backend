package com.upsoon.organization.controller;


import com.upsoon.common.dto.NewOrganizationCreateDTO;
import com.upsoon.organization.service.OrganizationService;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/organization")
@Api(value = "Organization Controller")
public class OrganizationController {

    private final OrganizationService organizationSerice;

    public OrganizationController(OrganizationService organizationSerice) {
        this.organizationSerice = organizationSerice;
    }


    @PostMapping
    public ResponseEntity<NewOrganizationCreateDTO> createOrganization(@RequestBody NewOrganizationCreateDTO newOrganizationCreateDTO) {
        return organizationSerice.createOrganization(newOrganizationCreateDTO);
    }
}
