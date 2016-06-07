
/*���������� �����ϴ� ��û�� ó��*/
package com.rest.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.rest.board.domain.Board;
import com.rest.board.service.BoardService;

@Controller
@RequestMapping("/web")
public class PCController {
	@Autowired 
	private BoardService boardService;

	@RequestMapping(value="/board", method=RequestMethod.GET)
	public ModelAndView selectAll(){
		System.out.println("������ ��û�� ó���� selectAll ȣ��");
		return null;
	}

	//�۾���
	@RequestMapping(value="/board", method=RequestMethod.POST)
	//@ResponseStatus(HttpStatus.NO_CONTENT)//204 
	public String insert(Board board){
		System.out.println("�۾��� ��û ����");	
		boardService.insert(board);
		return "redirect:/web/board/list";
	}
	
}









