package com.yrgo.services.diary;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.yrgo.dataaccess.ActionDao;
import com.yrgo.dataaccess.ActionDaoJdbcImpl;
import com.yrgo.domain.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("diaryService")
public class DiaryManagementServiceMockImpl implements DiaryManagementService {
    private Set<Action> allActions = new HashSet<Action>();

    @Override
    public void recordAction(Action action) {
        allActions.add(action);
    }

    public List<Action> getAllIncompleteActions(String requiredUser) {
        List<Action> actions = new ArrayList<Action>();

        for (Action action : allActions) {
            if (action.getOwningUser().equals(requiredUser) && !action.isComplete()) {
                actions.add(action);
            }
        }
        return actions;
    }
}

