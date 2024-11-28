package com.yrgo.services.diary;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.yrgo.dataaccess.ActionDao;
import com.yrgo.dataaccess.ActionDaoJdbcImpl;
import com.yrgo.domain.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DiaryManagementServiceMockImpl implements DiaryManagementService {

    @Autowired
    private ActionDao actionDao;

    @Override
    public void recordAction(Action action) {
        actionDao.create(action);
    }

    public List<Action> getAllIncompleteActions(String requiredUser) {
        return actionDao.getIncompleteActions(requiredUser);
    }
}

