/*
 * 스마트폰과 그 이외의 디바이스로 접근할 경우 요청을 처리할 
 * 컨트롤러
 * */
package com.rest.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.rest.board.domain.Board;
import com.rest.board.service.BoardService;

@Controller
@RequestMapping("/rest")
public class DeviceController {
	
	@Autowired 
	private BoardService boardService;
	
	
	//json을 요청할때...
	@RequestMapping(value="/board", method=RequestMethod.GET, produces={"application/json"} )
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<Board> selectAll(){
		System.out.println("디바이스요청의 selectAll 호출");
		List list=boardService.selectAll();
		return list;
	}
	
	//jsp를 요청할때 
	@RequestMapping(value="/board", method=RequestMethod.GET)
	public ModelAndView selectList(){
		List list=boardService.selectAll();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/list");
		return mav;
	}
	
	//한건 보기
	@RequestMapping(value="/board/{id}", method=RequestMethod.GET,produces={"application/json"})
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Board select(@PathVariable("id") int board_id){
		Board board=boardService.select(board_id);
		return board;
	}
	
	//글쓰기
	@RequestMapping(value="/board", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.NO_CONTENT)//204 
	public void insert(@RequestBody Board board){
		
		System.out.println(board.getWriter());
		System.out.println(board.getTitle());
		System.out.println(board.getContent());
		
		boardService.insert(board);
	}
	
	//글 수정 
	@RequestMapping(value="/board/{id}", method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable("id") int board_id ,@RequestBody Board board){
		boardService.update(board);
	}
	
	//글 삭제 
	@RequestMapping(value="/board/{id}", method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") int board_id){
		boardService.delete(board_id);
	}
	
	
}









