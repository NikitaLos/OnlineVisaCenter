package com.vironit.onlinevisacenter.validation;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence({Default.class, DateValidateGroup.class})
public interface ValidationSequence {
}
