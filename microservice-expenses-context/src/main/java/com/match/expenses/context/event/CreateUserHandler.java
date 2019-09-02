package com.match.expenses.context.event;

import com.github.middleware.event.EventHandler;
import com.match.expenses.context.domain.service.ExpensesService;
import com.match.user.event.EventUserCreateDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author zhangchao
 * @Date 2019/9/2 18:16
 * @Version v1.0
 */
@Component
@Slf4j
public class CreateUserHandler implements EventHandler<EventUserCreateDTO> {

    @Autowired
    ExpensesService expensesService;


    @Override
    public void handler(EventUserCreateDTO eventUserCreateDTO) {

        log.info("CreateUserHandler ===> {},{}",eventUserCreateDTO.getUserId(),eventUserCreateDTO.getUsername());

        expensesService.createGroupMember(eventUserCreateDTO.getUserId(),eventUserCreateDTO.getUsername());

    }

    @Override
    public String getEventName() {
        return EventUserCreateDTO.EVENT_NAME;
    }
}
