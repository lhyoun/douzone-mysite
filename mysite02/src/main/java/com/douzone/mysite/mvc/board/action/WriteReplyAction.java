package com.douzone.mysite.mvc.board.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class WriteReplyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int groupNo = Integer.parseInt(request.getParameter("group"));
		int orderNo = Integer.parseInt(request.getParameter("order"));
		Long no = Long.valueOf(request.getParameter("no")).longValue();
		
		String title = request.getParameter("title");
		String contents = request.getParameter("content");
		
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		BoardVo vo = new BoardVo();
		vo.setNo(no);
		vo.setTitle(title);
		vo.setContents(contents);
		vo.setUserNo(Long.valueOf(authUser.getNo()).intValue());
		vo.setGroupNo(groupNo);
		vo.setOrderNo(orderNo);
		
		System.out.println(vo);
		
		new BoardDao().insertReply(vo);
		MvcUtil.redirect(request.getContextPath() + "/board?a=list", request, response);
	}

}
