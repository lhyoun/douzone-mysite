package com.douzone.mysite.mvc.board;

import com.douzone.mysite.mvc.guestbook.AddAction;
import com.douzone.mysite.mvc.guestbook.DeleteFormAction;
import com.douzone.mysite.mvc.guestbook.ListAction;
import com.douzone.web.mvc.Action;
import com.douzone.web.mvc.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		if("list".equals(actionName)) {
			action = new BoardListAction();
		} else if("view".equals(actionName)) {
			action = new BoardViewAction();
		} else if("writeform".equals(actionName)) {
			action = new WriteFormAction();
		} else if("write".equals(actionName)) {
			action = new WriteAction();
		} else {
			action = new ListAction();
		}
		
		return action;
	}

}
