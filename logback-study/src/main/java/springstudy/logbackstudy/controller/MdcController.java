package springstudy.logbackstudy.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MdcController {
    @GetMapping("/mdc")
    public String mdc() {
        MDC.put("userId", "dev");
        log.trace("Log -> Trace");
        log.debug("Log -> Debug");
        log.info("Log -> Info");
        log.warn("Log -> Warm");
        log.error("Log -> Error");
        MDC.clear();
        return "mdc";
    }
}