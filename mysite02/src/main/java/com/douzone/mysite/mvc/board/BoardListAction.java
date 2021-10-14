package com.douzone.mysite.mvc.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.paging.Page;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class BoardListAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int count = new BoardDao().getCount();

		int no;
		if(request.getParameter("no") == null) no = 1; 
		else no = Integer.parseInt(request.getParameter("no"));
		
		Page page = new Page(no, count);
		
		System.out.println(page);
		
		List<BoardVo> list = new BoardDao().findAll(page);
		request.setAttribute("list", list);
		request.setAttribute("page", page);
		
		MvcUtil.forward("board/list", request, response);
	}
}
