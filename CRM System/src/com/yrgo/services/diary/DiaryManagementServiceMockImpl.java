package com.yrgo.services.diary;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.yrgo.dataaccess.ActionDao;
import com.yrgo.dataaccess.ActionDaoJdbcImpl;
import com.yrgo.domain.Action;

public class DiaryManagementServiceMockImpl implements DiaryManagementService {
	private ActionDao actionDao;

	public DiaryManagementServiceMockImpl(ActionDao actionDao) {
		this.actionDao = actionDao;
	}
	// private Set<Action>allActions= new HashSet<Action>();

	@Override
	public void recordAction(Action action) {
	actionDao.create(action);
	}

	public List<Action> getAllIncompleteActions(String requiredUser) {
		return actionDao.getIncompleteActions(requiredUser);
	}
}

		//for(Action action : allActions) {
		//		if(action.getOwningUser().equals(requiredUser) && !action.isComplete()){
		//		incompleteActions.add(action);
		//		}
		//		}
		//		return incompleteActions;


