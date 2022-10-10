package com.upsoon.organization.service;

import com.upsoon.common.dto.OrganizationDTO;
import org.springframework.http.ResponseEntity;


public interface OrganizationService {

    ResponseEntity<OrganizationDTO> createOrganization(OrganizationDTO organizationDTO);
}
