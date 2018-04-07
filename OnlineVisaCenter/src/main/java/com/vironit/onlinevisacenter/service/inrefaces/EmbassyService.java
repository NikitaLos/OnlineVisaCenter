package com.vironit.onlinevisacenter.service.inrefaces;

import com.vironit.onlinevisacenter.entity.Application;

public interface EmbassyService extends StatusChangeable{
    void addApplicationToQueue(Application application);
}
