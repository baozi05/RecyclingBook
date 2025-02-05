package org.example.baozi.recyclingbook.service.MessageService;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.example.baozi.recyclingbook.ResponseMessage.VO.MessageVO;
import org.example.baozi.recyclingbook.mapper.Messages.MessageMapper;
import org.example.baozi.recyclingbook.mapper.Student.StudentMapper;
import org.example.baozi.recyclingbook.model.DTO.MessageDTO;
import org.example.baozi.recyclingbook.model.Entity.Message.Messages;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MessageService implements IMessageService {

    private final MessageMapper messageMapper;

    // Entity --> VO 返回前端
    private MessageVO convertToVO(Messages message) {
        MessageVO vo = new MessageVO();
        BeanUtils.copyProperties(message, vo);
        return vo;
    }

    @Override
    @Transactional
    // @Transactional 注解是为了当后续操作失败时，使数据回滚，以防数据消失
    public boolean sendMessage(MessageDTO messageDTO) {
        Messages message = new Messages();
        BeanUtils.copyProperties(messageDTO, message);
        return messageMapper.insert(message)>0;
    }

    @Override
    public Page<MessageVO> getInbox(Long receiverId, SpringDataWebProperties.Pageable pageable) {
        return null;
    }

    @Override
    public int getUnreadCount(Long userId) {
        return 0;
    }

    @Override
    public boolean markAsRead(Long messageId) {
        return false;
    }
}
