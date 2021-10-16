package com.douzone.mysite.mvc.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class SearchAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tp = request.getParameter("tp"); 	// 검색 조건 w:글쓴이 t:제목 tw:둘 다
		String kwd = request.getParameter("kwd"); 	// 검색 키워드

		String title = "";
		String writer = "";
		String contents = "";
		
		
		if("w".equals(tp)) { // 검색조건이 글쓴이면 
			writer = "%"+kwd+"%";
			title = "%";
			contents = "%";
		} else if("t".equals(tp)) { // 제목
			writer = "%";
			title = "%"+kwd+"%";
			contents = "%";
		} else if("c".equals(tp)) { // 내용
			writer = "%";
			title = "%";
			contents = "%"+kwd+"%";
		}
		
		List<BoardVo> list = new BoardDao().Search(title, writer, contents, null);
		request.setAttribute("list", list);
		//request.setAttribute("page", page);
		// select해서 담아서 넘어가기
		
		MvcUtil.forward("board/list", request, response);
	}

}
