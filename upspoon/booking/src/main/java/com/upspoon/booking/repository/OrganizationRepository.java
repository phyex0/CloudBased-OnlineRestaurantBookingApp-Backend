package com.upspoon.booking.repository;

import com.upspoon.booking.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author burak.yesildal
 */


@Repository
public interface OrganizationRepository extends JpaRepository<Organization, UUID> {

    Organization findByExactOrganizationId(UUID organizationId);
}
