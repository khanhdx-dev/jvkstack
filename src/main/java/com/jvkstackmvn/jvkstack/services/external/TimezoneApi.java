package com.jvkstackmvn.jvkstack.services.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "worldtimeapi", url = "https://worldtimeapi.org/api/timezone")
public interface TimezoneApi {
    @GetMapping("/{timezone}")
    String getTimezone(@PathVariable String timezone);
}
