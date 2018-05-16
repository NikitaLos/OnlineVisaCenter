package com.vironit.onlinevisacenter.dto.validation;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence({Default.class, DateValidateGroup.class})
public interface ValidationSequence {
}
