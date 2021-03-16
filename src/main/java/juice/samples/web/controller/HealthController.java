package juice.samples.web.controller;

import juice.contracts.ResultDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 健康检查
 */
@RestController
public class HealthController {

    @RequestMapping({"/health", "/healthCheck"})
    public ResultDTO healthCheck(){
        return ResultDTO.ok();
    }
}
