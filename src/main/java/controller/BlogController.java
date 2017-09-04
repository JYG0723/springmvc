package controller;

import model.BlogEntity;
import model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import repository.BlogRepository;
import repository.UserRepository;
import util.JsonUtil;

import java.util.List;

/**
 * @Author Nuc YongGuang Ji.
 * @Date 0:10 2017/9/4.
 * @Descrip
 */
@Controller
@RequestMapping(value = "/admin/blogs")
public class BlogController {

    @Autowired
    BlogRepository blogRepository;

    @Autowired
    UserRepository userRepository;

    /**
     * 博文管理页面
     *
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String showBlogs(ModelMap modelMap) {
        List<BlogEntity> blogList = blogRepository.findAll();
        modelMap.addAttribute("blogList", blogList);
        return "admin/blog/blogs";
    }

    /**
     * 添加博客页面
     *
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public String addBlogPage(ModelMap modelMap) {
        List<UserEntity> userList = userRepository.findAll();
        modelMap.addAttribute("userList", userList);
        return "admin/blog/addBlog";
    }

    /**
     * 添加博客操作
     *
     * @param blogEntity
     * @return
     */
    @RequestMapping(value = "/addBlog", method = RequestMethod.POST)
    public String addBlogPost(@ModelAttribute("blog") BlogEntity blogEntity) {
        blogRepository.saveAndFlush(blogEntity);
        return "redirect:/admin/blogs";
    }

    /**
     * 博文详情页面
     *
     * @param id
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String showBlog(@PathVariable("id") Integer id, ModelMap modelMap) {
        BlogEntity blogEntity = blogRepository.findOne(id);
        modelMap.addAttribute("blog", blogEntity);
        return "admin/blog/blogDetail";
    }

    /**
     * 更新博客页面
     *
     * @param id
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String updateBlogPage(@PathVariable("id") Integer id, ModelMap modelMap) {
        BlogEntity blogEntity = blogRepository.findOne(id);
        List<UserEntity> userList = userRepository.findAll();
        modelMap.addAttribute("userList", userList);
        modelMap.addAttribute("blog", blogEntity);
        return "admin/blog/updateBlog";
    }

    /**
     * 更新博客操作
     *
     * @param blogEntity
     * @return
     */
    @RequestMapping(value = "/updatePut", method = RequestMethod.PUT)
    public String updateBlog(@ModelAttribute("blog") BlogEntity blogEntity) {
        blogRepository.updateBole(blogEntity.getTitle(), blogEntity.getContent(), blogEntity.getPubDate(), blogEntity.getUserByUserId().getId(), blogEntity.getId());
        blogRepository.flush();
        return "redirect:/admin/blogs";
    }

    /**
     * 删除博客操作
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteBlog(@PathVariable("id") Integer id) {
        blogRepository.delete(id);
        blogRepository.flush();
        return JsonUtil.getJsonString(JsonUtil.REQUEST_SUCCESS);
    }

}
