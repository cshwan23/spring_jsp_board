package com.naver.erp;

import java.util.List;
import java.util.Map;

//mmmmmmmmmmmmmmmmmmmmmmmm
// [DAO 인터페이스] 선언
//mmmmmmmmmmmmmmmmmmmmmmmm
public interface BoardDAO {
	
	//********************************
	int insertBoard(BoardDTO boardDTO);
	//********************************
	//[검색한 게시판 목록] 리턴하는 메소드 선언
	//********************************
	List<Map<String,String>> getBoardList();
	//********************************
	// [1개의 게시판 정보]를 리턴하는 메소드 선언
	//********************************
	BoardDTO getBoard(int b_no);
	
}
