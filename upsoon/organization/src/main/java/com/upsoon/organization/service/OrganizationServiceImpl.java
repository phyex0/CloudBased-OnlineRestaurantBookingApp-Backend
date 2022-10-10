package com.upsoon.organization.service;

import com.upsoon.common.dto.OrganizationDTO;
import com.upsoon.organization.mapper.OrganizationMapper;
import com.upsoon.organization.model.Organization;
import com.upsoon.organization.repository.OrganizationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final OrganizationMapper organizationMapper;

    public OrganizationServiceImpl(OrganizationRepository organizationRepository, OrganizationMapper organizationMapper) {
        this.organizationRepository = organizationRepository;
        this.organizationMapper = organizationMapper;
    }

    @Override
    public ResponseEntity<OrganizationDTO> createOrganization(OrganizationDTO organizationDTO) {

        Organization organization = organizationMapper.toEntity(organizationDTO);



        return new ResponseEntity<OrganizationDTO>(organizationDTO, HttpStatus.OK);

    }
}
