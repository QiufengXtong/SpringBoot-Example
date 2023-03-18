package cn.xtong.example.user.controller;

import cn.xtong.example.user.entity.SysUser;
import cn.xtong.example.user.service.SysUserService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/user")
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    @RequestMapping("/page")
    public PageInfo<SysUser> page(@RequestBody SysUser sysUser,
                                  @RequestParam(name = "pageNum", defaultValue = "1", required = false) Integer pageNum,
                                  @RequestParam(name = "pageNum", defaultValue = "10", required = false) Integer pageSize) {
        return sysUserService.page(sysUser,pageNum,pageSize);
    }

    @GetMapping("/detail")
    public SysUser list(@RequestParam(name = "id") Integer id) {
        return sysUserService.getById(id);
    }

    @GetMapping("/add")
    public SysUser add(@RequestBody SysUser sysUser) {
        sysUserService.save(sysUser);
        return sysUser;
    }

    @GetMapping("/edit")
    public SysUser edit(@RequestBody SysUser sysUser) {
        sysUserService.updateById(sysUser);
        return sysUser;
    }

    @GetMapping("/deleteById")
    public void deleteById(@RequestBody SysUser sysUser) {
        sysUserService.removeById(sysUser);
    }

    @GetMapping("/deleteByIds")
    public void deleteByIds(List<String> ids) {
        sysUserService.removeBatchByIds(ids);
    }
}
