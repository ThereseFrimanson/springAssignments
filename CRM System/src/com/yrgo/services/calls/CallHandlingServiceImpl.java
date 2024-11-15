package com.yrgo.services.calls;

import com.yrgo.dataaccess.CustomerDao;
import com.yrgo.domain.Action;
import com.yrgo.domain.Call;
import com.yrgo.services.customers.CustomerManagementService;
import com.yrgo.services.customers.CustomerNotFoundException;
import com.yrgo.services.diary.DiaryManagementService;

import java.util.Collection;

public class CallHandlingServiceImpl implements CallHandlingService{

    private CustomerManagementService customerManagementService;
    private DiaryManagementService diaryManagementService;
    private CustomerDao customerDao;

    public CallHandlingServiceImpl(CustomerManagementService customerManagementService, DiaryManagementService diaryManagementService, CustomerDao customerDao) {
        this.customerManagementService = customerManagementService;
        this.diaryManagementService = diaryManagementService;
        this.customerDao = customerDao;
    }

    @Override
    public void recordCall(String customerId, Call newCall, Collection<Action> actions) throws CustomerNotFoundException {
        customerManagementService.recordCall(customerId, newCall);

        for (Action action : actions) {
            diaryManagementService.recordAction(action);
        }
    }
}

