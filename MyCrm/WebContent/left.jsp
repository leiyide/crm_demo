<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery.js"></script>

<script type="text/javascript">
	$(function() {
		//导航切换
		$(".menuson li").click(function() {
			$(".menuson li.active").removeClass("active")
			$(this).addClass("active");
		});

		$('.title').click(function() {
			var $ul = $(this).next('ul');
			$('dd').find('ul').slideUp();
			if ($ul.is(':visible')) {
				$(this).next('ul').slideUp();
			} else {
				$(this).next('ul').slideDown();
			}
		});
	})
</script>


</head>

<body style="background: #f0f9fd;">
	<div class="lefttop">
		<span></span>通讯录
	
	</div>

	<dl class="leftmenu">

		<dd>
		
			<c:forEach items="${sessionScope.parent }" var="p">
				<div class="title">
				<span><img src="images/leftico01.png"/></span><a href="${p.url}" >${p.rightname}</a>
				</div>
				<dl>
				<ul class="menuson">
					<c:forEach items="${sessionScope.allpc[p.pid]}" var="c">
					<li><cite></cite><a href="${c.url}" target="rightFrame">${c.rightname }</a><i></i></li>
									
					</c:forEach>
					</ul>
				</dl>
			</c:forEach>
			</dd>
			
</body>
</html>