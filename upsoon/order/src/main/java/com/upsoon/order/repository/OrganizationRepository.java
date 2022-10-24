package com.upsoon.order.repository;

import com.upsoon.order.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrganizationRepository extends JpaRepository<Organization, UUID> {

    Boolean existsOrganizationByExactOrganizationId(UUID exactOrganizationId);

}
