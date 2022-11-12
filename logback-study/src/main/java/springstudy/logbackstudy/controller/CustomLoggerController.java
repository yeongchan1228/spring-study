package springstudy.logbackstudy.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j(topic = "CUSTOM_LOGGER")
@RestController
public class CustomLoggerController {

    @GetMapping("/customLogger")
    public String customLogger() {
        log.trace("Log -> Trace");
        log.debug("Log -> Debug");
        log.info("Log -> Info");
        log.warn("Log -> Warm");
        log.error("Log -> Error");

        return "customLogger";
    }
}
