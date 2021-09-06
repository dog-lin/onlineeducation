package org.lf.cmsservice.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.lf.cmsservice.entity.CrmBanner;
import org.lf.cmsservice.service.CrmBannerService;
import org.lf.commonutils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@RestController
@RequestMapping("/cmsservice/banner")
@Api(description = "前台banner展示")
@CrossOrigin //跨域

public class CrmBannerApiController {

    @Autowired
    private CrmBannerService bannerService;

    @ApiOperation(value = "获取首页banner")
    @GetMapping("getAllBanner")
    public R getAllBanner() {
        List<CrmBanner> list = bannerService.getAllBanner();
        //List<CrmBanner> list = bannerService.list(null);
        return R.ok().data("bannerList", list);
    }

}
