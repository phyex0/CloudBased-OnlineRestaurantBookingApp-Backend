package com.upsoon.organization.service;

import com.upsoon.common.dto.NewOrganizationCreateDTO;
import com.upsoon.common.dto.OrganizationDTO;
import org.springframework.http.ResponseEntity;


public interface OrganizationService {

    ResponseEntity<NewOrganizationCreateDTO> createOrganization(NewOrganizationCreateDTO newOrganizationCreateDTO);
}
