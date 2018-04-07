package com.vironit.onlinevisacenter.service.inrefaces;

import com.vironit.onlinevisacenter.entity.Application;
import com.vironit.onlinevisacenter.entity.enums.Status;

public interface StatusChangeable {
    default void changeApplicationStatus(Application application, Status status){
        application.setStatus(status);
    }
}
