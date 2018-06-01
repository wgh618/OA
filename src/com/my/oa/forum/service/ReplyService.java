package com.my.oa.forum.service;

import com.my.oa.forum.domain.Reply;
import com.my.oa.forum.domain.Topic;

import java.util.List;

/**
 * Created by Administrator.
 *
 * @author 吴光辉
 */
public interface ReplyService {
    public void addReply(Reply reply);

    public Reply findReplyById(Integer id);

    public void deleteReply(Reply reply);

    public void modifyReply(Reply reply);

    public List<Reply> findReplyByTopic(Topic topic);
}
