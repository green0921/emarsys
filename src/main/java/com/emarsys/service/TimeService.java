package com.emarsys.service;

import com.emarsys.model.TimeRequest;

public interface TimeService {

   long calculateDueDate(TimeRequest timeRequest);
}
