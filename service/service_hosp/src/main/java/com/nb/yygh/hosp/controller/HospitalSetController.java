package com.nb.yygh.hosp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nb.yygh.common.result.Result;
import com.nb.yygh.common.utils.MD5;
import com.nb.yygh.hosp.service.HospitalSetService;
import com.nb.yygh.model.hosp.HospitalSet;
import com.nb.yygh.vo.hosp.HospitalSetQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

/**
 * @author yunfu.ye
 * @date 2022/11/17 15:02
 * @desciption:
 */
@Api(tags = "医院设置管理")
@RestController
@RequestMapping("/admin/hosp/hospitalSet")
public class HospitalSetController {
    @Autowired
    private HospitalSetService hospitalSetService;

    // http://localhost:8160/admin/hosp/hospitalSet/findAll
    // http://localhost:8160/swagger-ui.html
    @ApiOperation(value = "获取医院所有设置")
    @GetMapping("findAll")
    public Result findAllHospitalSet(){
        List<HospitalSet> list = hospitalSetService.list();
        return Result.ok(list);
    }

    @ApiOperation(value = "逻辑删除医院设置")
    @DeleteMapping("{id}")
    public Result removeHospSet(@PathVariable Long id){
        boolean flag = hospitalSetService.removeById(id);
        if(flag){
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @ApiOperation(value = "条件查询带分页")
    @PostMapping("findPageHospSet/{current}/{limit}")
    public Result findPageHospSet(@PathVariable Long current,
                                  @PathVariable Long limit,
                                  @RequestBody(required = false) HospitalSetQueryVo hospitalSetQueryVo){
        //创建page对象，vo作为查询的医院名称和编号可以在搜索的时候传入进来搜索
        Page<HospitalSet> page = new Page<>(current,limit);
        //构建条件
        QueryWrapper<HospitalSet> queryWrapper = new QueryWrapper<>();
        String hosname = hospitalSetQueryVo.getHosname();
        String hoscode = hospitalSetQueryVo.getHoscode();
        queryWrapper.like("hoscode",hoscode);
        if(!StringUtils.isEmpty(hoscode)){
            queryWrapper.like("hoscode",hospitalSetQueryVo.getHoscode());
        }
        if(!StringUtils.isEmpty(hosname)){
            queryWrapper.like("hosname",hospitalSetQueryVo.getHosname());
        }
        //调用方法实现查询
        Page<HospitalSet> hospitalSetPage = hospitalSetService.page(page, queryWrapper);
        //返回结果
        return Result.ok(hospitalSetPage);
    }

    @ApiOperation(value = "根据id获取医院设置")
    @GetMapping("getHostSet/{id}")
    public Result getHostSet(@PathVariable Long id) {
        HospitalSet byId = hospitalSetService.getById(id);
        return Result.ok(byId);
    }

    @ApiOperation(value = "修改医院设置")
    @PostMapping("updateHospitalSet")
    public Result getHostSet(@RequestBody HospitalSet hospitalSet) {
        boolean flag = hospitalSetService.updateById(hospitalSet);
        if(flag){
            return Result.ok();
        }else {
            return Result.fail();
        }

    }

    @ApiOperation(value = "批量删除医院设置")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> ids){
        hospitalSetService.removeByIds(ids);
        return Result.ok();
    }

    @ApiOperation(value = "添加医院设置")
    @PostMapping("saveHospitalSet")
    public Result saveHostSet(@RequestBody HospitalSet hospitalSet) {
        boolean save = hospitalSetService.save(hospitalSet);
        hospitalSet.setStatus(1);
        //签名密钥
        Random random = new Random();
        hospitalSet.setSignKey(MD5.encrypt(System.currentTimeMillis()+""+ random.nextInt(1000)));
        if(save){
            return Result.ok(save);
        } else {
            return Result.fail();
        }
    }

    //医院设置锁定和解锁
    @PutMapping("lockHospitalSet/{id}/{status}")
    public Result lockHospitalSet(@PathVariable Long id,
                                  @PathVariable Integer status) {
        //根据id查询医院设置信息
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        //设置状态
        hospitalSet.setStatus(status);
        //调用方法
        hospitalSetService.updateById(hospitalSet);
        return Result.ok();
    }

    //9 发送签名秘钥
    @PutMapping("sendKey/{id}")
    public Result lockHospitalSet(@PathVariable Long id) {
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        String signKey = hospitalSet.getSignKey();
        String hoscode = hospitalSet.getHoscode();
        //TODO 发送短信
        return Result.ok();
    }
}
