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
	private Set<Action> allActions = new HashSet<Action>();

	public DiaryManagementServiceMockImpl(ActionDao actionDao) {
		this.actionDao = actionDao;
	}


	@Override
	public void recordAction(Action action) {
		allActions.add(action);
	}

	public List<Action> getAllIncompleteActions(String requiredUser) {
		List<Action> actions = new ArrayList<Action>();

		for(Action action: allActions){
			if (action.getOwningUser().equals(requiredUser) && !action.isComplete()) {
				actions.add(action);
			}
		}
		return actions;
	}
}

