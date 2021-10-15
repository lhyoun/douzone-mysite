package com.douzone.mysite.mvc.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class BoardViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strNo = request.getParameter("no"); 	// Read하는 Board의 No
		int no = Integer.parseInt(strNo);

		BoardVo vo = new BoardDao().selectById(no);	// Read하는 Board의 Vo
		request.setAttribute("vo", vo);				

		Cookie[] cookies = request.getCookies();	// 전체 쿠키
		boolean isNotView = true;						// Read하는 Board 열람 여부(1시간 쿠키 유지)
		
		String strCookie = "";						// 열람한 Board가 저장되어 있는 쿠키 정보를 담을 String
		
		for (Cookie c : cookies) {
			if (c.getName().equals("isView")) {		// 1시간 안에 게시물을 하나라도 열람 한 경우 isView cookie가 존재
				strCookie = c.getValue();			// 현재의 쿠키 정보를 담음
				
				String[] arrCookie = c.getValue().split("A");	// A기준으로 자르면 각각의 value는 열람한 Board의 No 
				
				for(String cookieValue :arrCookie) {
					if(Integer.parseInt(cookieValue) == no) isNotView = false;	// 쿠키에 현재 열람하고자 하는 Board의 No와 동일한 No가 있으면 해당 Board를 열람한 적이 있음
				}
			} 
		}
		
		if(isNotView) {	// 1시간 이내에 해당 Board를 열람한 적이 없으면 쿠키에 해당 Board의 No를 추가하고 조회수를 +1
			Cookie cookie = new Cookie("isView", strCookie+strNo+"A");
			cookie.setMaxAge(60 * 60); // 1시간
			new BoardDao().updateHit(vo);
			response.addCookie(cookie);
		}

		MvcUtil.forward("board/view", request, response);
	}

}
