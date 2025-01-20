package com.bspicinini.config;

import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

@MapperConfig(
    componentModel = "jakarta",
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface MapStructConfig {

}
