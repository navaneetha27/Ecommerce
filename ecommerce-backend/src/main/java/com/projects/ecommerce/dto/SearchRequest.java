package com.projects.ecommerce.dto;

import lombok.Data;

@Data
public class SearchRequest {
    String query;
    String anonymousUserId;
    String registeredUserId;
    String requestId;

}
