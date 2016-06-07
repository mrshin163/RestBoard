package com.rest.board.service;

import java.util.List;
import com.rest.board.domain.Board;

public interface BoardService {
	public List selectAll();
	public Board select(int board_id);
	public void insert(Board board);
	public void update(Board board);
	public void delete(int board_id);

}
