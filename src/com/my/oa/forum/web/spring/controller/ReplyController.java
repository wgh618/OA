package com.my.oa.forum.web.spring.controller;

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
@RequestMapping("/reply")
public class ReplyController {

    @Resource(name = "forumService")
    private ForumService forumService;

    @Resource(name = "topicService")
    private TopicService topicService;

    @Resource(name = "replyService")
    private ReplyService replyService;

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Integer id) {
        Reply reply = this.replyService.findReplyById(id);
        this.replyService.deleteReply(reply);
        return "redirect:/forum/listForum";
    }

    @RequestMapping(value = "/addUI/{id}", method = RequestMethod.GET)
    public String add(@PathVariable Integer id, Model model) {
        Topic topic = this.topicService.findTopicById(id);
        Forum forum = this.forumService.findForumById(topic.getForum().getId());
        topic.setForum(forum);
        model.addAttribute("topic",topic);

        return "reply/saveUI";
    }

    @RequestMapping(value = "/add/{id}", method = RequestMethod.POST)
    public String add(Reply reply, @PathVariable Integer id, HttpSession session, HttpServletRequest request) {
        User loginUser = (User) session.getAttribute("loginUser");
        Forum forum = this.forumService.findForumDetailById(id);

        reply.setAuthor(loginUser);
        reply.setIpAddr(request.getRemoteAddr());
        reply.setPostTime(new Date());

        Topic topic = this.topicService.findTopicById(id);

        reply.setTopic(topic);
        //其他业务信息的值在service中处理
        this.replyService.addReply(reply);
        return "redirect:/topic/show/" + id;
    }
}