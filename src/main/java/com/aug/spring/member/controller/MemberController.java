package com.aug.spring.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aug.spring.member.domain.Member;
import com.aug.spring.member.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	private MemberService service;
	

	@RequestMapping(value="/member/register.kh",method=RequestMethod.GET)
	public String registerform() {
		return "member/register";
	}
	@RequestMapping(value="/member/register.kh",method=RequestMethod.POST)
	public String register(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute Member member
			,Model model)
	{
		try {
		int result = service.registerMember(member);
		if(result>0) {
			return "redirect:/index.jsp";
		}else {
			model.addAttribute("msg", "회원가입이 실패 하였습니다.");
			model.addAttribute("error", "회원가입 실패");
			model.addAttribute("url","/member/register.kh");
			return "common/error";
		}
	} catch (Exception e) {
		e.printStackTrace();
		model.addAttribute("msg", "관리자에게 문의 요망");
		model.addAttribute("error", e.getMessage());
		model.addAttribute("url","/member/register.kh");
		return "common/errorpage";
		
	}	
	}
	@RequestMapping(value="/home.kh",method=RequestMethod.GET)
	public String login() {
		return "/home";
	}

	@RequestMapping(value="/member/login.kh",method=RequestMethod.POST)
	public String logindo(HttpServletRequest request,HttpServletResponse response,
			Model model, @ModelAttribute Member member)
	{
		try {
			int result= service.selectOneById(member);
			if(result>0) {
				model.addAttribute("memberName", member.getMemberName());
				model.addAttribute("memberId", member.getMemberId());
				return "/home";
			}else {
				model.addAttribute("msg", "로그인에 실패 하였습니다.");
				model.addAttribute("error", "로그인 실패");
				model.addAttribute("url","/member/register.kh");
				return "common/errorpage";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "관리자에게 문의 요망");
			model.addAttribute("error", e.getMessage());
			model.addAttribute("url","/member/register.kh");
			return "common/errorpage";
			
		}
		
	}
	@RequestMapping(value="/member/logout.kh",method=RequestMethod.GET)
	public String logout(HttpSession session, Model model) {
			if(session !=null) {
				session.invalidate();
				return "redirect:/index.jsp";
			}else {
				model.addAttribute("msg","로그아웃 실패");
				model.addAttribute("error","로그아웃 실패");
				model.addAttribute("url","/index.jsp");
				return "common/errorpage";

			}
	}
	@RequestMapping(value="/member/myinfo.kh",method=RequestMethod.GET)
	public String getmembermyinfo(HttpServletRequest request,HttpServletResponse response,
			Model model ,@RequestParam("memberId") String memberId) {
	try {
		Member mOne = service.checkedMember(memberId);
		if(mOne!=null) {
			model.addAttribute("member",mOne);
			return "member/myinfo";
		}
		else {
			model.addAttribute("msg","조회실패");
			model.addAttribute("error","조회실패");
			model.addAttribute("url", "/index.jsp");
			return "common/errorpage";
		}
		
	} catch (Exception e) {
		e.printStackTrace();
		model.addAttribute("msg", "관리자에게 문의 요망");
		model.addAttribute("error", e.getMessage());
		model.addAttribute("url","/member/register.kh");
		return "common/errorpage";
	}
	}
	@RequestMapping(value="/member/update.kh",method=RequestMethod.GET)
	public String updateMember(Model model,@RequestParam("memberId") String memberId) {
		try {
			
			Member mOne = service.checkedMember(memberId);
			if(mOne!=null) {
				model.addAttribute("member", mOne);
				return "member/modify";
			}else {
				model.addAttribute("msg","수정조회실패");
				model.addAttribute("error","수정조회실패");
				model.addAttribute("url", "/index.jsp");
				return "common/errorpage";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "관리자에게 문의 요망");
			model.addAttribute("error", e.getMessage());
			model.addAttribute("url","/member/register.kh");
			return "common/errorpage";
		}
	}
	@RequestMapping(value="/member/update.kh",method=RequestMethod.POST)
	public String modifyMember(@ModelAttribute Member member, Model model) {
		try {
			int result = service.updateMember(member);
			if(result>0) {
				return "redirect:/index.jsp";
			}else {
				model.addAttribute("msg","수정실패");
				model.addAttribute("error","수정실패");
				model.addAttribute("url", "/member/mypage.kh?memberId"+member.getMemberId());
				return "common/errorpage";
			}
		} catch (Exception e) {
			model.addAttribute("msg","수정실패");
			model.addAttribute("error","수정실패");
			model.addAttribute("url", "/index.jsp");
			return "common/errorpage";
		}
	}
	@RequestMapping(value="/member/delete.kh",method=RequestMethod.GET)
	public String deleteMember(Model model,@RequestParam("memberId") String memberId)
	{
		try {
			int result = service.deleteMember(memberId);
			if(result>0) {
				return "redirect:/member/logout.kh";
			}else {
				model.addAttribute("msg","회원탈퇴가 완료되지 않았습니다.");
				model.addAttribute("error","탈퇴실패");
				model.addAttribute("url", "/index.jsp");
				return "common/errorpage";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "관리자에게 문의 요망");
			model.addAttribute("error", e.getMessage());
			model.addAttribute("url","/index.jsp");
			return "common/errorpage";
		}
	}
}
