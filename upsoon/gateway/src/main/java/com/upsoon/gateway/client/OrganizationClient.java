package com.upsoon.gateway.client;

import com.upsoon.common.dto.OrganizationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Halit Burak Yeşildal
 */

@FeignClient(name = "${client.organization-api.name}", url = "${client.organization-api.url}" /*,configuration*/)
public interface OrganizationClient {

    @PostMapping("/api/organization")
    ResponseEntity<OrganizationDTO> createOrganization(@RequestBody OrganizationDTO organizationDTO);

}
