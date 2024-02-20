package com.example.wms.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.wms.common.QueryPageParam;
import com.example.wms.common.Result;
import com.example.wms.entity.Menu;
import com.example.wms.entity.User;
import com.example.wms.service.MenuService;
import com.example.wms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wms
 * @since 2024-01-25
 */
@RestController
@ResponseBody
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;
    @GetMapping("/list")
    public List<User> list(){

        return userService.list();
    }
    @PostMapping("/listP")
    public Result listP(@RequestBody User user){
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(user.getName())) {
            lambdaQueryWrapper.like(User::getName, user.getName());
        }

        return Result.suc(userService.list(lambdaQueryWrapper));
    }
    @GetMapping("/findByNo")
    public Result findByNo(@RequestParam String no){
        List list = userService.lambdaQuery().eq(User::getNo,no).list();
        return list.size()>0?Result.suc(list):Result.fail();
    }
    /*新增*/
    @PostMapping("/save")
    public Result save(@RequestBody User user){
        return userService.save(user)?Result.suc():Result.fail();
    }
//更新
@PostMapping("/update")
public Result update(@RequestBody User user){
    return userService.updateById(user)?Result.suc():Result.fail();
}

    /**修改*/
    @PostMapping("/mod")
    public boolean mod(@RequestBody User user){
        return userService.updateById(user);
    }
     /*新增或修改*/  /*id存在修改id不存在新增*/
    @PostMapping("/saveOrMod")
    public boolean saveOrMod(@RequestBody User user){
        return userService.saveOrUpdate(user);
    }
    /* 删除*/
    @GetMapping("/del")
    public Result del(@RequestParam String id){
        return userService.removeById(id)?Result.suc():Result.fail();
    }

    /*分页*/
    @PostMapping("/listPage")
    public Result listPage(@RequestBody QueryPageParam query){

       /* System.out.println(query);
        System.out.println("num=="+query.getPageNum());
        System.out.println("size=="+query.getPageSize());*/

//param传参，输出
        HashMap param = query.getParam();
        String name = (String) param.get("name");
        String sex = (String)param.get("sex");
        String roleId = (String)param.get("roleId");
/*        String no=(String) param.get("no");
        System.out.println("name=="+name);*/

        Page<User> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(name) && !"null".equals(name)){
            lambdaQueryWrapper.like(User::getName,name);
        }
        if(StringUtils.isNotBlank(sex)){
            lambdaQueryWrapper.eq(User::getSex,sex);
        }
        if(StringUtils.isNotBlank(roleId)){
            lambdaQueryWrapper.eq(User::getRoleId,roleId);
        }

        //IPage result = userService.pageC(page);
        IPage result = userService.page(page,lambdaQueryWrapper);

        System.out.println("total=="+result.getTotal());

        return Result.suc(result.getRecords(),result.getTotal());

    }

//分页2
    @PostMapping("/listPage2")
    public Result listPage2(@RequestBody QueryPageParam query){

       /* System.out.println(query);
        System.out.println("num=="+query.getPageNum());
        System.out.println("size=="+query.getPageSize());*/

//param传参，输出
        HashMap param = query.getParam();
        String name = (String) param.get("name");
        String sex = (String)param.get("sex");
        String roleId = (String)param.get("roleId");
/*        String no=(String) param.get("no");
        System.out.println("name=="+name);*/

        Page<User> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(name) && !"null".equals(name)){
            lambdaQueryWrapper.like(User::getName,name);
        }
        if(StringUtils.isNotBlank(sex)){
            lambdaQueryWrapper.eq(User::getSex,sex);
        }
        if(StringUtils.isNotBlank(roleId)){
            lambdaQueryWrapper.eq(User::getRoleId,roleId);
        }

        //IPage result = userService.pageC(page);
        IPage result = userService.pageCC(page,lambdaQueryWrapper);

        System.out.println("total=="+result.getTotal());

        return Result.suc(result.getRecords(),result.getTotal());

    }
   //登录
   @PostMapping("/login")
   public Result login(@RequestBody User user){
       List list = userService.lambdaQuery()
               .eq(User::getNo,user.getNo())
               .eq(User::getPassword,user.getPassword()).list();


       if(list.size()>0){
           User user1 = (User)list.get(0);
           List menuList = menuService.lambdaQuery().like(Menu::getMenuright,user1.getRoleId()).list();
           HashMap res = new HashMap();
           res.put("user",user1);
           res.put("menu",menuList);
           return Result.suc(res);
       }
       return Result.fail();
   }


}
