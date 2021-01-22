<!-- ************************************************************* -->
<!-- JSP 기술의 한종류인 [Page Directive]를 이용하여 현 JSP 페이지 처리 방식 선언하기 -->
<!-- ************************************************************* -->
	<!-- 현재 이 JSP 페이지 실행 후 생성되는 문서는 HTML 이고, 이 문서 안의 데이터는 UTF-8 방식으로 인코딩한다 라고 설정함 -->
	<!-- 현재 이 JSP 페이지는 UTF-8 방식으로 인코딩한다 -->
	<!-- UTF-8 인코딩 방식은 한글을 포함 전 세계 모든 문자열을 부호화 할 수 있는 방법이다. -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>

<!--*****************************************************-->
<!-- JSP 기술의 한 종류인 [Include Directive]를 이용하여 -->
<!-- common.jsp 파일 내의 소스를 삽입하기 -->
<!--*****************************************************-->
<%@include file="common.jsp" %>

<head>
<title>게시판 목록</title>
	<script>
	//*********************************
	// [게시판 입력 화면]으로 이동하는 함수 선언 
	//*********************************
	function goBoardRegForm(){
		return;
		//-----------------
		// 웹서버에 boardRegForm.do URL로 접속하기 
		//-----------------
		location.replace("/z_spring/boardRegForm.do");
	}
	function goContentForm(b_no){
		alert(b_no);
		location.replace("/z_spring/boardContentFrom.do");
	}
	</script>
</head>
<body><center>

	<!-- ************************************************ -->
	<!-- [로그인 정보 입력 양식] 내포한 form 태그 선언 -->
	<!-- ************************************************ -->
	<form name="boardListForm" method="post" action="/z_spring/boardList.do">
		<a href="javascript:goBoardRegForm();">[새글쓰기]</a>
		
		<table border=1 class="tbcss1">
		<tr><th>제목<th>글쓴이<th>등록일<th>조회수
		
		<%
		//여긴 자바 세상 .
		//---------------------------------------------------------
		// HttpServletRequest 객체에 "boardList" 란 키값으로 저장된 객체 꺼내기
		// ModelAndView 객체에 addObject("boardList",boardList)로 저장된 놈이다. 
		//---------------------------------------------------------
		List<Map<String,String>> boardList = (List<Map<String,String>>)request.getAttribute("boardList");
		if(boardList!=null){
			for( int i=0 ; i<boardList.size(); i++){
				
				String bgColor = "";
				if(i%2==1){
					bgColor = "bgcolor='lightgray";
				}
				
				Map<String,String> map = boardList.get(i);
				
				out.print( "<tr style='cusor:pointer'"+bgColor+" onClick='goContentForm("+map.get("b_no")+")'>" );
				
				int print_level = Integer.parseInt(map.get("print_level"),10);
				String blank = "";
				
				
				for(int j=0 ; j<print_level ; j++){
					blank = blank + "&bsp;&bsp;&bsp;";
				}
				if(print_level>0){
					blank=blank + "ㄴ";
				}
				//out.print("<td>" + map.get("b_no"));
				out.print("<td>&nbsp;&nbsp;&nbsp;" + blank + map.get("subject"));
				out.print("<td>&nbsp;&nbsp;&nbsp;" + map.get("writer"));
				out.print("<td>&nbsp;&nbsp;&nbsp;" + map.get("reg_date"));
				out.print("<td>&nbsp;&nbsp;&nbsp;" + map.get("readcount"));
			}
		}
		%>
		</table>
	</form>
	
</body>
</html>

