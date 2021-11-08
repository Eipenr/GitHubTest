package com.qianfeng.smartdevices.controller;


//
//                            _ooOoo_  
//                           o8888888o  
//                           88" . "88  
//                           (| -_- |)  
//                            O\ = /O  
//                        ____/`---'\____  
//                      .   ' \\| |// `.  
//                       / \\||| : |||// \  
//                     / _||||| -:- |||||- \  
//                       | | \\\ - /// | |  
//                     | \_| ''\---/'' | |  
//                      \ .-\__ `-` ___/-. /  
//                   ___`. .' /--.--\ `. . __  
//                ."" '< `.___\_<|>_/___.' >'"".  
//               | | : `- \`.;`\ _ /`;.`/ - ` : | |  
//                 \ \ `-. \_ __\ /__ _/ .-` / /  
//         ======`-.____`-.___\_____/___.-`____.-'======  
//                            `=---='  
//  
//         .............................................  
//                  佛祖镇楼            BUG辟易  
//          佛曰:  
//                  写字楼里写字间，写字间里程序员；  
//                  程序人员写程序，又拿程序换酒钱。  
//                  酒醒只在网上坐，酒醉还来网下眠；  
//                  酒醉酒醒日复日，网上网下年复年。  
//                  但愿老死电脑间，不愿鞠躬老板前；  
//                  奔驰宝马贵者趣，公交自行程序员。  
//                  别人笑我忒疯癫，我笑自己命太贱；  


import com.github.pagehelper.PageInfo;
import com.qianfeng.smartdevices.annotation.AopLogAnnotation;
import com.qianfeng.smartdevices.dto.R;
import com.qianfeng.smartdevices.exceptions.UpdateErrorException;
import com.qianfeng.smartdevices.pojo.Areas;
import com.qianfeng.smartdevices.service.AreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by jackiechan on 2021/10/12 14:46
 *
 * @author jackiechan
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/areas")
@Api("区域管理模块")
public class AreaController {

    private AreaService areaService;

    @Autowired
    public void setAreaService(AreaService areaService) {
        this.areaService = areaService;
    }

    /**
     * 查询对应状态的所有的父区域
     *
     * @param status 状态,可以为空
     * @return
     */
    @GetMapping("/areas/parent")
    @ApiOperation("根据状态查找有效的父类区域")
    public R findParentAreaByStatus(@ApiParam(name = "status",value = "状态",defaultValue = "1",example = "2") Long status) {
        List<Areas> areasList = areaService.findParentAreaByStatus(status);
        return R.setOK(areasList);
    }


    @PostMapping("/area")
    @AopLogAnnotation("区域的添加")
    public R addArea(@RequestBody Areas areas) throws Exception {
        areaService.addArea(areas);
        return R.setOK();
    }
    @GetMapping("/areas")
    @AopLogAnnotation("查询所有区域")
    public R findAllAreasByNameLikeAndStatusEquals(int page,int limit,String areaname, Long status,Long parentid) {
        PageInfo<Areas> pageInfo = areaService.findAllAreasByNameLikeAndStatusEquals(page, limit, areaname, status,parentid);
        return R.setOK(pageInfo);
    }

    @PutMapping("/area")
    @ApiOperation("更新区域")
    public R updateArea(@RequestBody Areas areas){
        areaService.updateArea(areas);
        return R.setOK();
    }

    @DeleteMapping("/areas")
    @AopLogAnnotation("删除区域")
    public R deleteArea( @RequestParam List<Long> ids){
        areaService.deleteAreas(ids);
        return R.setOK();
    }

    @GetMapping("/area/parent/{id}")
    @ApiOperation("根据ID查找有效的父类区域")
    public R getAreaByParentId( @PathVariable @ApiParam(name = "id",value = "主键",defaultValue = "1",example = "2") Long id) {
        List<Areas> areaList = areaService.getAreaByParentId(id);
        return R.setOK(areaList);
    }


    @GetMapping("/area/{id}")
    @AopLogAnnotation("通过id 查询 区域")
    public R getAreaById(@PathVariable Long id) {
        Areas areas = areaService.getAreaById(id);
        return R.setOK(areas);
    }


}
