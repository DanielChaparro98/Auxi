package com.gateway.microservice.entity

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
data class RequestDto(
    val uri:String,
    val method: String
)
