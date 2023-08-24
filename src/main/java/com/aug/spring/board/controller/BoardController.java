package com.aug.spring.board.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.aug.spring.board.domain.BoardDomain;
import com.aug.spring.board.domain.PageInfo;
import com.aug.spring.board.service.BoardService;

@Controller
public class BoardController {
 @Autowired
 private BoardService service;

 
 @RequestMapping(value="/board/detail.kh",method=RequestMethod.GET)
 public ModelAndView showBoardDetail(ModelAndView mv,@RequestParam("boardNo") Integer boardNo) {
	try {
		
		BoardDomain board =service.selectBoardOne(boardNo);
		if(board!=null) {
		mv.addObject("board",board);	 
		mv.setViewName("board/detail");
		
		}
		else {
			mv.addObject("msg","게시글 조회가 완료되지 않았습니다.").addObject("error", "게시글 상세 조회 실패").addObject("url","board/list");
			mv.setViewName("common/erroPage");
			
		}
		return mv;
	} catch (Exception e) {
		mv.addObject("msg", "게시글 등록이 완료되지 않았습니다.");
		mv.addObject("error",e.getMessage());
		mv.addObject("url", "/board/write.do");
		mv.setViewName("common/errorpage");
	}
	return mv;
 }
 
 
 
 
 @RequestMapping(value="/board/list.kh" , method=RequestMethod.GET)
 public ModelAndView ShowNoticeList(ModelAndView mv,@RequestParam(value="page",required=false,defaultValue="1") Integer currentPage)
 {
	 Integer totalCount = service.getListCount();
	 PageInfo pInfo = this.getPageInfo(currentPage,totalCount);
	 List<BoardDomain> bList = service.selectBoardList(pInfo);
	 mv.addObject("bList",bList).addObject("pInfo",pInfo).setViewName("board/list");
	 return mv;
 }
 
 public PageInfo getPageInfo(Integer currentPage, Integer totalCount)
 {
	 int recordCountPerPage =10;
	 int naviCountPerPage =10;
	 int naviTotelCount;
	 naviTotelCount = (int)Math.ceil((double)totalCount/recordCountPerPage); 
	 int startNavi =((int)((double)currentPage/naviCountPerPage+0.9)-1)*naviCountPerPage+1;
	 int endNavi = startNavi + naviCountPerPage -1;
	 if(endNavi>naviTotelCount) {
		 endNavi = naviTotelCount;
	 }
	 PageInfo pInfo = new PageInfo(currentPage, totalCount, naviTotelCount, recordCountPerPage, naviCountPerPage, startNavi, endNavi);
	 return pInfo;
 }
 @RequestMapping(value="/board/write.kh" , method=RequestMethod.GET)
 public ModelAndView showWriterForm(ModelAndView mv) {
	 
	 mv.setViewName("board/write");
	 return mv;
 }
 @RequestMapping(value="/board/write.kh" , method=RequestMethod.POST)
 public ModelAndView boardRegister(@ModelAttribute BoardDomain board,
		 @RequestParam(value="uploadFile", required =false) MultipartFile uploadFile,ModelAndView mv,
		 HttpServletRequest request ) {
	 	try {
			if(uploadFile!=null && !uploadFile.getOriginalFilename().equals("")) {
				//파일정보(이름 리네임 경로 크기 ) 및 파일저장
				Map<String,Object> nMap = this.saveFile(request,uploadFile);
				board.setBoardFilename((String)nMap.get("fileName"));
				board.setBoardFilerename((String)nMap.get("fileRename"));
				board.setBoardFilepath((String)nMap.get("filePath"));
				board.setBoardFilelength((String)nMap.get("fileLength"));
			}
			int result = service.writeBoard(board);
			mv.setViewName("redirect:/board/list.kh");
		} catch (Exception e) {
			mv.addObject("msg", "게시글 등록이 완료되지 않았습니다.");
			mv.addObject("error",e.getMessage());
			mv.addObject("url", "/board/write.do");
			mv.setViewName("common/errorpage");
		}
	 	return mv;
 }
 public Map<String,Object> saveFile(HttpServletRequest request, MultipartFile uploadFile) throws Exception {
	 Map<String,Object> fileMap = new HashMap<String,Object>();
	 //파일 경로
	 String root = request.getSession().getServletContext().getRealPath("resources");
	 //파일 저장경로
	 String savePath = root + "\\buploadFile";
	 //파일 이름
	 String fileName = uploadFile.getOriginalFilename();
	 //확장자 명
	 String extension = fileName.substring(fileName.lastIndexOf(".")+1);
	 //파일생성시간형태
	 SimpleDateFormat  sdf = new SimpleDateFormat("yyyyMMDDHHss");
	 //파일 재삽입시 파일이름
	 String fileRename = sdf.format(new Date(System.currentTimeMillis()))+"."+extension;
	 //파일 저장
	 File saveFolder = new File(savePath);
	 if(!saveFolder.exists()) {
		 saveFolder.mkdir();
	 }
	 //저장된 파일 경로 
	 File savefile =new File(savePath+"\\"+fileRename);
	 uploadFile.transferTo(savefile);
	 //파일 길이
	 long fileLength = uploadFile.getSize();
	 //파일 정보 리턴
	 fileMap.put("fileName",fileName );
	 fileMap.put("fileRename", fileRename);
	 fileMap.put("filePath","../resources/buploadFiles/"+ fileRename);
	 fileMap.put("fileLength", fileLength);
	 return fileMap;
 }
}
