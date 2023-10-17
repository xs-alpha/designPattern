package com.cf.sqlTest.api.health;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 健康检查(k8s那边只需要get url通即可，不关心返回值)
 */
@Slf4j
@RestController
@RequestMapping("ops")
public class HealthCheckController {

    /**
     * 存活检查
     *
     * @return
     */
    @GetMapping("/heart")
    public String aliveCheck() {
//        log.info("<--------aliveCheck------->");
        return "200";
    }

    /**
     * 就绪检查
     *
     * @return
     */
    @GetMapping("/ready")
    public String readyCheck() {
//        log.info("<--------readyCheck------->");
        return "200";
    }

}
