package org.lf.eduservice.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.lf.commonutils.R;
import org.springframework.web.bind.annotation.*;

@Api(description = "登录")
@RestController
@RequestMapping("/eduuser")
@CrossOrigin
public class EduLoginController {

    @ApiOperation(value = "登录")
    @PostMapping("login")
    public R login() {
        return R.ok().data("token","admin");
    }

    @ApiOperation(value = "用户信息")
    @GetMapping("info")
    public R info() {
        return R.ok().data("roles","admin").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}
