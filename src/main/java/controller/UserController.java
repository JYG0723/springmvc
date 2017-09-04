package controller;

import model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import repository.UserRepository;
import util.JsonUtil;

import java.util.List;

/**
 * @Author Nuc YongGuang Ji.
 * @Date 23:36 2017/8/31.
 * @Descrip
 */
@Controller
@RequestMapping(value = "/admin/users")
public class UserController {
    //    自动装配：相当于数据库操作的极简化，只要定义了
// 就可以直接进行数据库操作，不用再去管开启连接、关闭连接等问题
    @Autowired
    UserRepository userRepository;

    /**
     * 用户管理页面
     *
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String userManagerPage(ModelMap modelMap) {
        List<UserEntity> userList = userRepository.findAll();
        modelMap.addAttribute("userList", userList);
        return "admin/user/users";
    }

    /**
     * 添加用户页面
     *
     * @return
     */
    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public String addUserPage() {
        return "admin/user/addUser";
    }

    /**
     * 添加有用户操作
     *
     * @param userEntity
     * @return
     */
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUserPost(@ModelAttribute("user") UserEntity userEntity) {
        // 注意此处，post请求传递过来的是一个UserEntity对象，里面包含了该用户的信息
        // 通过@ModelAttribute()注解可以获取传递过来的封装了前台信息的'user'实例，并创建这个对象

        // 数据库中添加一个用户，该步暂时不会刷新缓存
        //userRepository.save(userEntity);

        // 数据库中添加一个用户，并立即刷新缓存
        userRepository.saveAndFlush(userEntity);

        // 重定向到用户管理页面，方法为 redirect:url
        return "redirect:/admin/users/";
    }

    /**
     * 用户详情页面
     *
     * @param userId
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String showUserDetail(@PathVariable("id") Integer userId, ModelMap modelMap) {
        UserEntity userEntity = userRepository.findOne(userId);
        modelMap.addAttribute("user", userEntity);
        return "admin/user/userDetail";
    }

    /**
     * 更新用户页面
     *
     * @return
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String updateUserPut(@PathVariable("id") Integer userId, ModelMap modelMap) {
        UserEntity userEntity = userRepository.findOne(userId);
        modelMap.addAttribute("user", userEntity);
        return "admin/user/updateUser";
    }

    /**
     * 更新用户操作
     *
     * @param userEntity
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public String updateUserPut(@ModelAttribute("user") UserEntity userEntity) {
        userRepository.updateUser(userEntity.getNickname(), userEntity.getFirstName(),
                userEntity.getLastName(), userEntity.getPassword(), userEntity.getId());
        userRepository.flush();
        return "redirect:/admin/users";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteUser(@PathVariable("id") Integer userId) {
        userRepository.delete(userId);
        userRepository.flush();
//        return "redirect:/admin/users";
        return JsonUtil.getJsonString(JsonUtil.REQUEST_SUCCESS);
    }

}
