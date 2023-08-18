package com.aug.spring.notice.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.aug.spring.notice.domain.Notice;
import com.aug.spring.notice.domain.PageInfo;
import com.aug.spring.notice.service.ServiceNotice;


@Controller
public class NoticeController {
	@Autowired
	private ServiceNotice servicenotice;
	@RequestMapping(value="/notice/insert.kh",method=RequestMethod.GET)
	public String showInsetForm() {
		return "notice/insert";
	}
	@RequestMapping(value="/notice/insert.kh",method=RequestMethod.POST)
	public String insetNotice(@ModelAttribute Notice notice,
			@RequestParam(value="uploadFile",required=false) MultipartFile uploadFile 
			,Model model,HttpServletRequest  request) {
		try {
			if(!uploadFile.getOriginalFilename().equals("")) {
				//파일이름
				String fileName= uploadFile.getOriginalFilename();
				//파일경로(내가 저장한 후 그 경로를 DB에 저장하도록 준비하는 것)
				String root =
						request.getSession().getServletContext().getRealPath("resources");
				//폴더가 없을 경우 자동 생성(내가 업로드한 파일을 저장할 폴더)
				String saveFolder = root+"\\nuploadFiles";
				File folder = new File(saveFolder);
				if(!folder.exists()) {
					folder.mkdir();
				}
				//파일경로
				String savePath=saveFolder+"\\"+fileName;
				File file = new  File(savePath);
				//파일저장
				uploadFile.transferTo(file);
				//파일크기
				long fileLength=uploadFile.getSize();
				notice.setNoticeFilename(fileName);
				notice.setNoticeFilepath(savePath);
				notice.setNoticeFilelength(fileLength);
			}
			int result = servicenotice.insertNotice(notice);
			if(result>0) {
				return "redirect:/notice/list.kh";
			}
			else {
				model.addAttribute("msg", "공지사항 등록이 실패 하였습니다.");
				model.addAttribute("error", "공지사항 등록 실패");
				model.addAttribute("url","/notice/insert.kh");
				return "common/errorpage";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "관리자에게 문의 요망");
			model.addAttribute("error", e.getMessage());
			model.addAttribute("url","/notice/insert.kh");
			return "common/error";
		}
	}
	@RequestMapping(value="/notice/list.kh",method=RequestMethod.GET)
	public String showNoticeList(Model model,@RequestParam(value="currentPage",required=false,defaultValue="1") Integer currentPage
			) {
		try {
			
//			int currentPage = page!=0?page:1;
			int totalCount = servicenotice.getListCount();
			PageInfo pInfo = this.getPageInfo(totalCount, currentPage);
			List<Notice> nList =servicenotice.selectNoticeList(pInfo);
			//List 데이터의 유효성 체크 방법 2가지
			//1. isEmpty
			//2. size()
			if(nList.size()>0) {
			model.addAttribute("pInfo", pInfo);
			model.addAttribute("nList",nList);
			return "notice/list";
			}else {
				model.addAttribute("msg", "관리자에게 문의 요망");
				model.addAttribute("error", "데이터가 입력되지 않았습니다.");
				model.addAttribute("url","/index.jsp");
				return "common/error";	
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "관리자에게 문의 요망");
			model.addAttribute("error", e.getMessage());
			model.addAttribute("url","/index.jsp");
			return "common/error";		}
	}
	public PageInfo getPageInfo(int totalCount,int currentPage) {
		PageInfo page =null;
		int recordCountPerPage =10;
		int naviCountPerPage =10;
		int naviTotalCount;
		int startNavi;
		int endNavi;
		naviTotalCount =(int)((double)totalCount/recordCountPerPage + 0.9);
		startNavi = (((int)((double)currentPage/naviCountPerPage+0.9))-1)*naviCountPerPage+1;
		endNavi = startNavi + naviCountPerPage - 1;
		//endNavi는 startNavi에 무조건 naviCountPerPage에 값을 더하므로 실제 최대값 보다 무조건 클 수 있다.
		if(endNavi>naviTotalCount) {
			endNavi=naviTotalCount;
		}
		page = new PageInfo(recordCountPerPage, currentPage, startNavi, endNavi, naviCountPerPage, naviTotalCount);
				
		return page;
	}
		@RequestMapping(value="/notice/search.kh",method=RequestMethod.GET)
		public String serachnoticeList(@RequestParam("searchCondition") String searchCondition,@RequestParam("searchKeyword") String searchKeyword,
				@RequestParam(value="page", required=false,defaultValue="1") Integer currentPage
				,Model model) {
			//SELECT * FROM NOTICE_TBL WHERE NOTICE_SUBJECT LIKE '%11%'
			//1.vo class만들기
			//2.hashmap사용
			Map<String,String> paraMap = new HashMap<String,String>();
			paraMap.put("searchCondition",searchCondition);
			paraMap.put("searchKeyword",searchKeyword);
			int totalCount=servicenotice.getListCount(paraMap);
			PageInfo pInfo = this.getPageInfo(totalCount, currentPage);
			//put()메소드를 이용해 키와 값을 넘겨줌
			//notice-mapper.xml에서는 파란색 부분이 넘어가 사용됨
			List<Notice> searchList = servicenotice.searchNoticesByKeyword(pInfo,paraMap);
//			switch(searchCondition) {
//			case "all" : 
//				searchList = servicenotice.searchAllData(searchKeyword);
//				break;
//			case "writer" :
//				searchList = servicenotice.searchWriterBysearch(searchKeyword);
//				break;
//			case "title" :
//				//SELECT * FROM NOTICE_TBL WHERE NOTICE_SUBJECT LIKE '%11%'
//				searchList = servicenotice.searchNoticeByTitle(searchKeyword);
//				break;
//			case "content":
//				searchList=servicenotice.searchContentByCotent(searchKeyword);
//				break;
//			}
			if(!searchList.isEmpty()) {
				model.addAttribute("searchCondition", searchCondition);
				model.addAttribute("searchKeyword",searchKeyword);
				model.addAttribute("pInfo",pInfo);
				model.addAttribute("sList",searchList);
				return "notice/search";
			}
			else {model.addAttribute("msg", "관리자에게 문의 요망");
			model.addAttribute("error", "데이터가 입력되지 않았습니다.");
			model.addAttribute("url","/notice/list.kh");
			return "common/errorpage";	
			
			}
		}
	}
