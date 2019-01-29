<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<!-- Bootstrap Core CSS -->
<link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">

<!-- MetisMenu CSS -->
<link href="<%=request.getContextPath()%>/css/metisMenu.min.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery.js"></script>
<!-- DataTables CSS -->
<link href="<%=request.getContextPath()%>/css/dataTables.bootstrap.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="<%=request.getContextPath()%>/css/sb-admin-2.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="<%=request.getContextPath()%>/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<link href="<%=request.getContextPath()%>/css/boot-crm.css" rel="stylesheet"
	type="text/css">
<script type="text/javascript"> $(document).ready(function(){
  $(".click").click(function(){
  $(".tip").fadeIn(200);
  });
  
  $(".tiptop a").click(function(){
  $(".tip").fadeOut(200);
});

  $(".sure").click(function(){
  $(".tip").fadeOut(100);
});

  $(".cancel").click(function(){
  $(".tip").fadeOut(100);
});

});

$(document).ready(function(e) {
    $(".select1").uedSelect({
		width : 345			  
	});
	$(".select2").uedSelect({
		width : 167  
	});
	$(".select3").uedSelect({
		width : 100
	});
});

KE.show({
    id : 'content7',
    cssPath : './index.css'
}); 

</script>

</head>
<body>
	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">系统设置</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div id="usual1" class="usual"> 
    
    <div class="itab">
  	<ul> 
    <li><a href="#tab1" class="selected">增加客户记录</a></li> 
  	</ul>
    </div>  
  	<div id="tab1" class="tabson">
    </div>
    </div>
 
    <div class="formtext">Hi，<b>${sessionScope.username}</b>，欢迎您修改员工账号！</div>
<div style="float: left;">
<li><label>客户状态提示<b></b></label></li>
    <div class="vocation">
    <select name="" class="select1">
    <option>0表示新增</option>
    <option>1表示紧跟</option>
    <option>2表示已报名</option>
    <option>3死单</option>
    <option>4报名后退费</option>
    </select>
   </div>
   </div>
    <br><br> <br><br> 
     
<form action="<%=request.getContextPath() %>/newAdd" class="forminfo" method="post">
    <li><label>客户编号<b>*</b></label><input  name="customid" type="text" class="dfinput"  style="width:518px;"/></li>
    <li><label>咨询师编号<b>*</b></label><input  name="consultmanid" type="text" class="dfinput"  style="width:518px;"/></li>
    <li><label>咨询状态<b>*</b></label>
    <div class="vocation">
    <select name="consultstatu"  class="dfinput" style="width:518px">
    <option>0</option>
    <option>1</option>
    <option>2</option>
    <option>3</option>
    <option>4</option>
    </select>
    </div></li>
    <li><label>咨询日期<b>*</b></label><input name="consultdate" type="date" class="dfinput"  style="width:518px;"/></li>
    <li><label>咨询备注<b>*</b></label><input name="result" type="text" class="dfinput"   style="width:518px;"/></li> 
    <li><label><b></b></label><input type="submit" class="dfinput"  style="width:518px;"/></li>   	
</form>
    </div> 
	<script type="text/javascript">  
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>
</body>
</html>