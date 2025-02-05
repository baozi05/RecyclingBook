package org.example.baozi.recyclingbook.service.MessageService;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.baozi.recyclingbook.ResponseMessage.VO.MessageVO;
import org.example.baozi.recyclingbook.model.DTO.MessageDTO;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;

public interface IMessageService {
        /**
         * 发送消息
         * @param messageDTO
         * @return
         */
        boolean sendMessage(MessageDTO messageDTO);

        /**
         * 获取收件箱
         * @param receiverId
         * @param pageable
         * @return
         */
        Page<MessageVO> getInbox(Long receiverId, SpringDataWebProperties.Pageable pageable);

        /**
         * 未读消息数
         * @param userId
         * @return
         */
        int getUnreadCount(Long userId);

        /**
         * 标记已读
         * @param messageId
         * @return
         */
        boolean markAsRead(Long messageId);

}
