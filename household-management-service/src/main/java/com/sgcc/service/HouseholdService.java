package com.sgcc.service;


import com.sgcc.entity.event.HouseholdEventEntity;
import com.sgcc.entity.query.HouseholdQueryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class HouseholdService {
    @Autowired
    private HouseholdEventEntity householdEventEntity;
    @Autowired
    private HouseholdQueryEntity householdQueryEntity;
}
