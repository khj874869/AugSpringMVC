package com.aug.spring.notice.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
		@RequestMapping(value="/notice/modify.kh",method=RequestMethod.GET)
		public String showModifyForm(@RequestParam("noticeNo") Integer noticeNo,Model model) {
			Notice noticeOne = servicenotice.selectNoticeByNo(noticeNo);
			model.addAttribute("notice",noticeOne);
			return "notice/modify";
		}
		@RequestMapping(value="/notice/detail.kh",method=RequestMethod.GET)
		public String showNoticeDetail(@RequestParam("noticeNo") int noticeNo,Model model) {
			Notice noticeOne = servicenotice.selectNoticeByNo(noticeNo);
			model.addAttribute("notice",noticeOne);
			return "notice/detail";
		}
		@RequestMapping(value="/notice/insert.kh",method=RequestMethod.GET)
		public String showInsetForm() {
			return "notice/insert";
		}
		@RequestMapping(value="/notice/insert.kh",method=RequestMethod.POST)
		public String insetNotice(@ModelAttribute Notice notice,
				@RequestParam(value="uploadFile",required=false) MultipartFile uploadFile 
				,Model model,HttpServletRequest  request) {
			try {
				if(uploadFile!=null&&!uploadFile.getOriginalFilename().equals("")) {
					Map<String,Object> nMap = this.saveFile(uploadFile, request);
					//파일크기
					String FileName = (String)nMap.get("fileName");
					String noticeFilerename=(String)nMap.get("fileRename");
					String savePath =(String)nMap.get("filePath");
					long filelength=(long)nMap.get("fileLength");
					long fileLength=uploadFile.getSize();
					notice.setNoticeFilename(FileName);
					notice.setNoticeFilerename(noticeFilerename);
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
		@RequestMapping(value="/notice/modify.kh",method=RequestMethod.POST)
		public String updatenotice(@ModelAttribute("notice") Notice notice,Model model
				,@RequestParam(value="uploadFile" ,required=false) MultipartFile uploadFile,
				HttpServletRequest request)  {
			try {
				if(uploadFile != null && !uploadFile.isEmpty()) {
					//기존 업로드 파일 체크해야함
					//1.대체,2.삭제 후 등록
					//기존 업로드 된 파일이 있는지 존재 체크 해야하는데
					String filename =notice.getNoticeFilerename();
					if(filename!=null) {
						//있으면 기존 파일을 삭제 
						this.deleteFile(request,filename);
					}
					//없으면 새로 업로드 하려는 파일을 저장하면 된다.
					Map<String,Object> infoMap = this.saveFile(uploadFile, request);
					String noticeFilename = (String)infoMap.get("fileName");
					String noticeFilerename=(String)infoMap.get("fileRename");
					long noticeFilelength = (long)infoMap.get("fileLength");
					notice.setNoticeFilename(noticeFilename);
					notice.setNoticeFilerename(noticeFilerename);
					notice.setNoticeFilepath((String)infoMap.get("filePath"));
					notice.setNoticeFilelength(noticeFilelength);
				}
				int result = servicenotice.updateNotice(notice);
				if(result>0){
					return "redirect:/notice/detail.kh?noticeNo="+notice.getNoticeNo();
				}
				else {
					model.addAttribute("msg", "관리자에게 문의 요망");
					model.addAttribute("error", "정보가 입력되지 않았습니다.");
					model.addAttribute("url","/notice/list.kh");
					return "common/errorpage";	
				}
			} catch (Exception e) {
				model.addAttribute("msg", "관리자에게 문의 요망");
				model.addAttribute("error", e.getMessage());
				model.addAttribute("url","/notice/insert.kh");
				return "common/error";
			}
		}
		public Map<String,Object> saveFile(MultipartFile uploadFile,HttpServletRequest request) throws Exception {
			//넘겨야 하는 값이 여러개 일 때 사용하는 방법
			//1. VO 클래스를 만들거나
			//2. HashMap을 사용한다.
			Map<String,Object> infoMap = new HashMap<String,Object>();
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
//				Random rand = new  Random();
//				String strResult = "";
//				for(int i=0 ; i<7;i++) {
//					int result = rand.nextInt(100)+1;
//					strResult += result+"";
//				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMDDHHmmss");//나중에 ss랑 비교
				String strResult = sdf.format(new Date(System.currentTimeMillis()));
				String ext = fileName.substring(fileName.lastIndexOf(".")+1);
				String FileRename = "N"+strResult+"."+ext;
				String savePath=saveFolder+"\\"+fileName;
				File file = new  File(savePath);
				//파일저장
				uploadFile.transferTo(file);
				//파일크기
				long fileLength=uploadFile.getSize();
				//파일 이름 경로 크기를 넘겨주기 위해 MAP에 정보를 저장 한 후 return함
				//왜 return하는가? DB에 저장하기 위해서 필요한 정보라서 
				infoMap.put("fileName", fileName);
				infoMap.put("fileRename", FileRename);
				infoMap.put("filePath", savePath);
				infoMap.put("fileLength", fileLength);
				return infoMap;
		}
		private void deleteFile(HttpServletRequest request,String fileName) {
			String root = request.getSession().getServletContext().getRealPath("resources");
			String delFilepath =root+"\\nuploadFiles\\"+fileName;
			File file = new File(delFilepath);
			if(file.exists()) {
				file.delete();
			}
			
			
		}
	}
