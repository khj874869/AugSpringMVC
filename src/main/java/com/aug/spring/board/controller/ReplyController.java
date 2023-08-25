package com.aug.spring.board.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.aug.spring.board.domain.Reply;
import com.aug.spring.board.service.ReplyService;

@Controller
@RequestMapping(value="/reply")
public class ReplyController {
	@Autowired
	private ReplyService rservice;
	@RequestMapping(value="/addreply.kh",method=RequestMethod.POST)
	public ModelAndView insertReply(ModelAndView mv
			,@ModelAttribute Reply reply,
			HttpSession session) {
		
		try {
			String replyWriter = (String)session.getAttribute("memberId");
			reply.setReplyWriter(replyWriter);
			int result = rservice.insertReply(reply);
			String url = "/board/detail.kh?boardNo="+reply.getRefBoardNo();
			if(result>0) {
				mv.setViewName("redirect:"+url);
			}
			
		} catch (Exception e) {
			mv.addObject("msg", "게시글 등록이 완료되지 않았습니다.");
			mv.addObject("error",e.getMessage());
			mv.addObject("url", "/board/detail.kh?boardNo=" + reply.getRefBoardNo());
			mv.setViewName("common/errorpage");
		}
		
		
		return mv;
	}
	@RequestMapping(value="/update.kh",method=RequestMethod.POST)
	public ModelAndView updateReply( ModelAndView mv,
			@ModelAttribute Reply reply , HttpSession session) {
		String url ="";
		try {
			
			String replyWriter = (String)session.getAttribute("memberId");
			if(!replyWriter.equals("")) {
				reply.setReplyWriter(replyWriter);
				url="/board/detail.kh?boardNo="+reply.getRefBoardNo();
				int result = rservice.updateReply(reply);
				mv.setViewName("redirect:"+url);
			}
			else {
				mv.addObject("msg", "로그인 되지 않습니다..");
				mv.addObject("error","로그인 정보 확인 실패");
				mv.addObject("url", "/index.jsp");
				mv.setViewName("common/errorpage");
			}
				
		} catch (Exception e) {
			mv.addObject("msg", "게시글 등록이 완료되지 않았습니다.");
			mv.addObject("error",e.getMessage());
			mv.addObject("url", "/board/detail.kh?boardNo=" + reply.getRefBoardNo());
			mv.setViewName("common/errorpage");
		}
		return mv;
	}
}
