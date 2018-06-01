package com.my.oa.forum.web.spring.controller;

import com.my.oa.core.vo.PageBean;
import com.my.oa.forum.domain.Forum;
import com.my.oa.forum.domain.Reply;
import com.my.oa.forum.domain.Topic;
import com.my.oa.forum.service.ForumService;
import com.my.oa.forum.service.ReplyService;
import com.my.oa.forum.service.TopicService;
import com.my.oa.system.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator.
 *
 * @author 吴光辉
 */
@Controller
@RequestMapping("/topic")
public class TopicController {

    @Resource(name = "forumService")
    private ForumService forumService;

    @Resource(name = "topicService")
    private TopicService topicService;

    @Resource(name = "replyService")
    private ReplyService replyService;

    /**
     * 未分页前
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String show(@PathVariable Integer id, Model model) {
        Topic topic = this.topicService.findTopicById(id);
        List<Reply> replyList = replyService.findReplyByTopic(topic);
        Forum forum = this.forumService.findForumById(topic.getForum().getId());
        topic.setForum(forum);
        model.addAttribute("topic",topic);
        model.addAttribute("replyList",replyList);
        return "topic/topicShow";
    }

    /**
     * 分页后
     * @param id
     * @param model
     * @return
     */
    /*@RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String show(@PathVariable Integer id, Model model, @RequestParam("pageNum") int pageNum) {
        if (pageNum == 0) {
            pageNum = 1;
        }
        Topic topic = this.topicService.findTopicById(id);
        List<Reply> replyList = replyService.findReplyByTopic(topic);
        Forum forum = this.forumService.findForumById(topic.getForum().getId());
        topic.setForum(forum);
        model.addAttribute("topic",topic);
        model.addAttribute("replyList",replyList);
        model.addAttribute("pageBean",new PageBean(pageNum,2,replyList,replyList.size()));
        return "topic/topicShow";
    }*/

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Integer id) {
        Topic topic = this.topicService.findTopicById(id);
        this.topicService.deleteTopic(topic);
        return "redirect:/forum/listForum";
    }

    @RequestMapping(value = "/addUI/{id}", method = RequestMethod.GET)
    public String add(@PathVariable Integer id, Model model) {
        Forum forum = this.forumService.findForumById(id);
        model.addAttribute("forum",forum);
        return "topic/saveUI";
    }

    @RequestMapping(value = "/add/{id}", method = RequestMethod.POST)
    public String add(Topic topic, @PathVariable Integer id, HttpSession session, HttpServletRequest request) {
        User loginUser = (User) session.getAttribute("loginUser");
        Forum forum = this.forumService.findForumDetailById(id);

        topic.setForum(forum);

        topic.setAuthor(loginUser);
        topic.setIpAddr(request.getRemoteAddr());
        topic.setPostTime(new Date());

        //其他业务信息的值在service中处理
        this.topicService.addTopic(topic);
        return "redirect:/topic/show/" + topic.getId();
    }
}