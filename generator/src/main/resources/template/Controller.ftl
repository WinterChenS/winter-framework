package ${cfg.basePackage}.controller;

import io.swagger.annotations.Api;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RestController;

@Api(tags="xx控制器")
@RestController
@RequestMapping("${entity?uncap_first}")
public class ${entity}Controller extends BaseController {

    @Autowired
    private ${entity}Service ${entity?uncap_first}Service;


}
