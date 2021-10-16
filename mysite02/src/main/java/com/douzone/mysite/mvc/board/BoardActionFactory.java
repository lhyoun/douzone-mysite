package com.douzone.mysite.mvc.board;

import com.douzone.mysite.mvc.board.action.DeleteAction;
import com.douzone.mysite.mvc.board.action.ModifyAction;
import com.douzone.mysite.mvc.board.action.WriteAction;
import com.douzone.mysite.mvc.board.action.WriteReplyAction;
import com.douzone.mysite.mvc.board.formAction.BoardListAction;
import com.douzone.mysite.mvc.board.formAction.BoardViewAction;
import com.douzone.mysite.mvc.board.formAction.ModifyFormAction;
import com.douzone.mysite.mvc.board.formAction.WriteFormAction;
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
		} else if("writeReply".equals(actionName)) {
			action = new WriteReplyAction();
		} else if("modifyform".equals(actionName)) {
			action = new ModifyFormAction();
		} else if("modify".equals(actionName)) {
			action = new ModifyAction();
		} else if("delete".equals(actionName)) {
			action = new DeleteAction();
		} else if("search".equals(actionName)) {
			action = new SearchAction();
		} else {
			action = new BoardListAction();
		}
		
		return action;
	}

}
