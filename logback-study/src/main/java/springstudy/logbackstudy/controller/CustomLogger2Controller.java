package springstudy.logbackstudy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CustomLogger2Controller {

    public static final Logger log = LoggerFactory.getLogger("CUSTOM_LOGGER");

    @GetMapping("/customLogger2")
    public String customLogger() {
        log.trace("Log -> Trace");
        log.debug("Log -> Debug");
        log.info("Log -> Info");
        log.warn("Log -> Warm");
        log.error("Log -> Error");

        return "customLogger2";
    }
}
