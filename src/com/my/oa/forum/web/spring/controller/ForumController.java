package com.my.oa.forum.web.spring.controller;

import com.my.oa.forum.domain.Forum;
import com.my.oa.forum.domain.Topic;
import com.my.oa.forum.service.ForumService;
import com.my.oa.forum.service.TopicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator.
 *
 * @author 吴光辉
 */
@Controller
@RequestMapping("/forum")
public class ForumController {

    @Resource(name = "forumService")
    private ForumService forumService;

    @Resource(name = "topicService")
    private TopicService topicService;

    @RequestMapping("/list")
    public String list(Model model) {
        List<Forum> forumList = this.forumService.findAllForums();
        model.addAttribute("forumList",forumList);
        return "forumManage/list";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Integer id) {
        Forum forum = this.forumService.findForumById(id);
        this.forumService.deleteForum(forum);
        return "redirect:/forum/list";
    }

    @RequestMapping(value = "/editUI/{id}", method = RequestMethod.GET)
    public String modify(@PathVariable Integer id, Model model) {
        Forum forum = this.forumService.findForumById(id);
        model.addAttribute("forum",forum);
        return "forumManage/editUI";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String modify(Forum forum) {
        this.forumService.modifyForum(forum);
        return "redirect:/forum/list";
    }

    @RequestMapping(value = "/addUI", method = RequestMethod.GET)
    public String add(Model model) {

        return "forumManage/saveUI";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(Forum forum) {
        forum.setArticleCount(0);
        forum.setTopicCount(0);
        forum.setLastTopic(null);
        this.forumService.addForum(forum);
        return "redirect:/forum/list";
    }

    @RequestMapping(value = "/moveUp/{id}")
    public String moveUp(@PathVariable Integer id) {
        this.forumService.moveUp(id);
        return "redirect:/forum/list";
    }

    @RequestMapping(value = "/moveDown/{id}")
    public String moveDown(@PathVariable Integer id) {
        this.forumService.moveDown(id);
        return "redirect:/forum/list";
    }

    //下面是论坛部分

    @RequestMapping("/listForum")
    public String show(Model model) {
        List<Forum> forumList = this.forumService.findAllForumsDetail();
        model.addAttribute("forumList",forumList);
        return "forum/list";
    }

    @RequestMapping("/show/{id}")
    public String show(@PathVariable("id") Integer forumId, Model model) {
        Forum forum = this.forumService.findForumById(forumId);
        model.addAttribute("forum",forum);

        List<Topic> topicList = this.topicService.findTopicsByForum(forum);
        model.addAttribute("topicList",topicList);
        return "forum/show";
    }
}
