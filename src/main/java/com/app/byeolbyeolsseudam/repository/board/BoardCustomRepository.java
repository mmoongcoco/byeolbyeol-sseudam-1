package com.app.byeolbyeolsseudam.repository.board;

import com.app.byeolbyeolsseudam.domain.board.BoardDTO;
import com.app.byeolbyeolsseudam.entity.board.Board;
import com.app.byeolbyeolsseudam.type.BoardCategory;

import java.util.List;

public interface BoardCustomRepository {
    public List<BoardDTO> selectTopView();
    public List<BoardDTO> selectBoards();
    public List<BoardDTO> selectBoardsofCategory(BoardCategory boardCategory);
    public List<BoardDTO> selectBoardsofKeyword(String keyword);
    public List<BoardDTO> selectScrollBoards(int page);
    public BoardDTO readBoard(Long boardId);
    public void saveMemberofBoard(BoardDTO boardDTO, Board board);
    public Board updateBoard(BoardDTO boardDTO);
    public Board plusView(BoardDTO boardDTO);
}