package com.naver.erp;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm
//[DAO 클래스]인 [LoginDAOImpl 클래스]선언 
//mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm
	// @Repository를 붙임으로써 [DAO 클래스]임을 지정하게되고
	// bean 태그로 자동 등록 된다. 
//mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm
@Repository
public class BoardDAOImpl implements BoardDAO {
		
	//++++++++++++++++++++++++++++++++++++++++
	// SqlSessionTemplate 객체를 생성해 속변 sqlSession 에 저장
	// @Autowired 어노테이션을 붙이면 자료형에 맞는 SqlSessionTemplate 객체를 생성한다. 
	//++++++++++++++++++++++++++++++++++++++++
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	//+++++++++++++++++++++++++++++++++++++++++
	// [게시판 글 입력 후 입력 적용 행의 개수를 리턴하는 메소드 선언
	//+++++++++++++++++++++++++++++++++++++++++
	public int insertBoard(BoardDTO boardDTO) {
		//------------------------------------------
		// SqlSessionTemplate 객체의 selectOne 메소드 호출로
		// 게시판 글 입력 SQL 구문을 실행하고 입력 성공 행의 개수 얻기 
		//------------------------------------------
		//********************************************
		// insert("com.naver.erp.BoardDAO.insertBoard", boardDTO); 의미 
		//********************************************
			// mybatis SQL 구문 설정 XML 파일에서
			// <mapper namespace="com.naver.erp.BoardDAO.insertBoard"> 태그 내부의
			// <insert id="insertBoard" ~> 태그 내부의
			// [insert 쿼리문]을 실행하고 입력 성공 행의 개수를 얻기
		int boardRegCnt = this.sqlSession.insert(
				"com.naver.erp.BoardDAO.insertBoard" //실행할 SQL 구문의 위치 지정    
				,boardDTO							// 실행할 SQL 구문에서 사용할 데이터 지정
		);
		System.out.println("BoardDAOImpl.insertBoard 실행 성공");
		
		return boardRegCnt;
	}
	//*********************************************
	// [검색한 게시판 목록] 리턴하는 메소드 선언
	//*********************************************
	public List<Map<String,String>> getBoardList(){
		
		
		List<Map<String,String>> boardList = this.sqlSession.selectList(
				"com.naver.erp.BoardDAO.getBoardList" //실행할 SQL 구문의 위치 지정
		);
		return boardList;
	}
	//*********************************************
	// [1개 게시판 글 정보] 리턴하는 메소드 선언
	//*********************************************
	public BoardDTO getBoard(int b_no) {
		//------------------------------------------
		// [SqlSessionTemplate 객체]의 selectOne(~,~) 를 호출하여
		// [1개 게시판 글 정보] 얻기
		//------------------------------------------
		BoardDTO board = this.sqlSession.selectOne(
				"com.naver.erp.BoardDAO.getBoard"
				,b_no 
		);
		//------------------------------------------
		// [1개 게시판 글] 정보 리턴하기
		//------------------------------------------
		return board;
				
	}
	//*********************************************
	//[게시판 글 조회수 증가하고 수정행의 개수] 리턴하는 메소드 선언
	//*********************************************
	public int updateReadcount(int b_no) {
		//------------------------------------------
		//[SqlSessionTemplate 객체]의 update(~,~) 를 호출하여 [조회수 증가] 하기 
		//------------------------------------------
		int updateCnt = this.sqlSession.update(
				"com.naver.erp.BoardDAO.updateReadcount" // 실행할 SQL 구문의 위치 지정 
				,b_no							 // 실행할 SQL 구문에서 사용할 데이터 지정
				);
		return updateCnt;
	}
}















