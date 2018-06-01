package com.my.oa.forum.service.impl;

import com.my.oa.forum.domain.Forum;
import com.my.oa.forum.domain.Reply;
import com.my.oa.forum.domain.Topic;
import com.my.oa.forum.mapper.ForumMapper;
import com.my.oa.forum.mapper.ReplyMapper;
import com.my.oa.forum.mapper.TopicMapper;
import com.my.oa.forum.service.ForumService;
import com.my.oa.forum.service.ReplyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator.
 *
 * @author 吴光辉
 */
@Service("replyService")
public class ReplyServiceImpl implements ReplyService {

    @Resource(name = "replyMapper")
    private ReplyMapper replyMapper;

    @Resource(name = "forumService")
    private ForumService forumService;

    @Resource(name = "topicMapper")
    private TopicMapper topicMapper;

    @Resource(name = "forumMapper")
    private ForumMapper forumMapper;

    @Override
    public void addReply(Reply reply) {
        if (reply == null) {
            throw new IllegalArgumentException();
        }

        this.replyMapper.save(reply);

        Topic topic = reply.getTopic();
        Forum forum = this.forumService.findForumDetailById(topic.getForum().getId());

        forum.setArticleCount(forum.getArticleCount() + 1);

        topic.setReplyCount(topic.getReplyCount() + 1);
        topic.setLastReply(reply);
        topic.setLastUpdateTime(reply.getPostTime());

        this.forumMapper.updateByTopic(forum);
        this.topicMapper.update(topic);
    }

    @Override
    public Reply findReplyById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("");
        }
        return this.replyMapper.load(id);
    }

    @Override
    public void deleteReply(Reply reply) {
        if (reply == null) {
            throw new IllegalArgumentException("");
        }
        this.replyMapper.delete(reply);
    }

    @Override
    public void modifyReply(Reply reply) {
        if (reply == null) {
            throw new IllegalArgumentException("");
        }
        this.replyMapper.update(reply);
    }

    @Override
    public List<Reply> findReplyByTopic(Topic topic) {
        if (topic == null) {
            throw new IllegalArgumentException("");
        }
        return this.replyMapper.findByTopicId(topic.getId());
    }
}
