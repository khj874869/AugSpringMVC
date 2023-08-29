package com.aug.spring.board.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.aug.spring.board.domain.Reply;
import com.aug.spring.board.service.BoardService;
import com.aug.spring.board.service.ReplyService;

@Controller
public class BoardController {
 @Autowired
 private BoardService service;
 @Autowired
 private ReplyService rserivce;
 @RequestMapping(value="/board/detail.kh",method=RequestMethod.GET)
 public ModelAndView showBoardDetail(ModelAndView mv,@RequestParam("boardNo") Integer boardNo) {
	try {
		BoardDomain boardOne = service.selectBoardOne(boardNo);
		if(boardOne != null) {
			List<Reply> replyList = rserivce.selectRelfList(boardNo);
			if(replyList.size() > 0) {
				mv.addObject("rList", replyList);
			}
			mv.addObject("board", boardOne);
			mv.setViewName("board/detail");
		}else {
			mv.addObject("msg", "게시글 조회가 완료되지 않았습니다.");
			mv.addObject("error", "게시글 상세 조회 실패");
			mv.addObject("url", "/board/list.kh");
			mv.setViewName("common/errorPage");
		}
	} catch (Exception e) {
		mv.addObject("msg", "게시글 등록이 완료되지 않았습니다.");
		mv.addObject("error",e.getMessage());
		mv.addObject("url", "/board/write.kh");
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
 public ModelAndView boardRegister(@ModelAttribute BoardDomain board,HttpSession session,
		 @RequestParam(value="uploadFile", required =false) MultipartFile uploadFile,ModelAndView mv,
		 HttpServletRequest request ) {
	 	try {
	 		String boardWriter = (String)session.getAttribute("memberId");
	 		if(boardWriter !=null&&  !boardWriter.equals("")) {
	 			board.setBoardWriter(boardWriter);
	 		
	 		if(uploadFile!=null && !uploadFile.getOriginalFilename().equals("")) {
				//파일정보(이름 리네임 경로 크기 ) 및 파일저장
				Map<String,Object> nMap = this.saveFile(request,uploadFile);
				board.setBoardFilename((String)nMap.get("fileName"));
				board.setBoardFilerename((String)nMap.get("fileRename"));
				board.setBoardFilepath((String)nMap.get("filePath"));
				board.setBoardFilelength((long)nMap.get("fileLength"));
			}
			int result = service.writeBoard(board);
			mv.setViewName("redirect:/board/list.kh");
		}else {
			 mv.addObject("msg","게시판 삭제 실패");
			 mv.addObject("error","게시판 삭제 실패");
			 mv.addObject("url","/board/list.kh");
			 mv.setViewName("common/errorpage");
		}
	 		} catch (Exception e) {
			mv.addObject("msg", "게시글 등록이 완료되지 않았습니다.");
			mv.addObject("error",e.getMessage());
			mv.addObject("url", "/board/write.kh");
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
 @RequestMapping(value="/board/delete.kh",method=RequestMethod.GET)
 public ModelAndView deleteBoard(ModelAndView mv, @ModelAttribute BoardDomain board,HttpSession session) {
	 String url ="";
	 try {
		 url = "/board/list.kh?boardNo="+board.getBoardNo();
		 String memberId= (String)session.getAttribute("memberId");
		 String boardWriter=board.getBoardWriter();
		 if(boardWriter!=null&& boardWriter.equals(memberId)) {
			 int result = service.deleteBoard(board);
			 if(result>0) {
				 mv.setViewName("redirect:"+url);
			 }else {
				 mv.addObject("msg","게시판 삭제 실패");
				 mv.addObject("error","게시판 삭제 실패");
				 mv.addObject("url",url);
				 mv.setViewName("common/errorpage");
			 }
		 }
	} catch (Exception e) {
		mv.addObject("msg",
				"관리자에게 문의 바랍니다.");
		mv.addObject("error",e.getMessage());
		mv.addObject("url", url);
		mv.setViewName("common/errorpage");
	}
	 
	 
	 return mv;
 }
 @RequestMapping(value="/board/modify.kh",method=RequestMethod.GET)
 public ModelAndView ModifyBoard(ModelAndView mv,@RequestParam("boardNo") Integer boardNo,HttpSession session)
 {
		try {
			BoardDomain board = service.selectBoardOne(boardNo);
			mv.addObject("board",board);
			mv.setViewName("board/modify");

		} catch (Exception e) {
			mv.addObject("msg",
					"관리자에게 문의 바랍니다.");
			mv.addObject("error",e.getMessage());
			mv.addObject("url", "/board/write.kh");
			mv.setViewName("common/errorpage");
		}

	 
	 return mv;
 }
 
 
 @RequestMapping(value="/board/modify.kh",method=RequestMethod.POST)
 public ModelAndView updateBoard(@ModelAttribute BoardDomain board,
		 HttpSession session,
		 ModelAndView mv,
		 HttpServletRequest request
		 ,
		 @RequestParam(value="uploadFile",required=false) MultipartFile uploadFile) {
	 String url ="";
	 try {
		String memberId = (String)session.getAttribute("memberId");
		String boardWriter = board.getBoardWriter();
		url = "/board/list.kh?boardNo"+ board.getBoardNo();
		if(boardWriter!=null&&board.getBoardWriter().equals(memberId)) {
		if(uploadFile!=null && !uploadFile.isEmpty()) {
			String filename = board.getBoardFilerename();
			if(filename!=null) {
				this.deleteFile(request,filename);
			}
			Map<String, Object> boardMap = this.saveFile(request, uploadFile);
			String boardFilename = (String)boardMap.get("fileName");
			String boardFilerename =(String)boardMap.get("fileRename");
			long boardFilelength = (long)boardMap.get("fileLength");
			board.setBoardFilename(boardFilename);
			board.setBoardFilerename(boardFilerename);
			board.setBoardFilepath((String)boardMap.get("filePath"));
			board.setBoardFilelength(boardFilelength);
		}
			int result = service.updateboard(board);
			if(result>0) {
				mv.setViewName("redirect:/board/detail.kh?boardNo="+board.getBoardNo());
			}
			else {
				 mv.addObject("msg","게시판 삭제 실패");
				 mv.addObject("error","게시판 삭제 실패");
				 mv.addObject("url",url);
				 mv.setViewName("common/errorpage");
			}
	
	}
	 }catch (Exception e) {
		mv.addObject("msg",
				"관리자에게 문의 바랍니다.");
		mv.addObject("error",e.getMessage());
		mv.addObject("url", url);
		mv.setViewName("common/errorpage");
	}
	 return mv;
 }




private void deleteFile(HttpServletRequest request, String filename) {
 String root = request.getSession().getServletContext().getRealPath("resources");
 String delFilepath = root+"\\buploadFiles\\"+filename;
 File file = new File(delFilepath);
 if(file.exists()) {
	 file.delete();
 }
}
}
