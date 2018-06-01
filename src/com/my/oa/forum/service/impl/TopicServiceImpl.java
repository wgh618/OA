package com.my.oa.forum.service.impl;

import com.my.oa.forum.domain.Forum;
import com.my.oa.forum.domain.Topic;
import com.my.oa.forum.mapper.ForumMapper;
import com.my.oa.forum.mapper.TopicMapper;
import com.my.oa.forum.service.TopicService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator.
 *
 * @author 吴光辉
 */
@Service("topicService")
public class TopicServiceImpl implements TopicService {

    @Resource(name = "topicMapper")
    private TopicMapper topicMapper;

    @Resource(name = "forumMapper")
    private ForumMapper forumMapper;

    @Override
    public void addTopic(Topic topic) {
        if (topic == null) {
            throw new IllegalArgumentException();
        }
        topic.setType(0);
        topic.setReplyCount(0);
        topic.setLastReply(null);
        topic.setLastUpdateTime(topic.getPostTime());

        this.topicMapper.save(topic);

        //维护相关的信息
        Forum forum = topic.getForum();
        forum.setTopicCount(forum.getTopicCount() + 1);
        forum.setArticleCount(forum.getArticleCount() + 1);
        forum.setLastTopic(topic);
        this.forumMapper.updateByTopic(forum);
    }

    @Override
    public Topic findTopicById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("");
        }
        return this.topicMapper.load(id);
    }

    @Override
    public void deleteTopic(Topic topic) {
        if (topic == null) {
            throw new IllegalArgumentException("");
        }
        this.topicMapper.delete(topic);
    }

    @Override
    public void modifyTopic(Topic topic) {
        if (topic == null) {
            throw new IllegalArgumentException("");
        }
        this.topicMapper.update(topic);
    }

    @Override
    public List<Topic> findTopicsByForum(Forum forum) {
        if (forum == null) {
            throw new IllegalArgumentException("");
        }
        return this.topicMapper.findByForumId(forum.getId());
    }
}
