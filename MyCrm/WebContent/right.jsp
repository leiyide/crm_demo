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
<script type="text/javascript">
$(document).ready(function(){
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
</script>

</head>
<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">数据表</a></li>
			<li><a href="#">基本内容</a></li>
		</ul>
	</div>

	<div class="rightinfo">

		<div class="tools">

			<ul class="toolbar">
				<li class="click"><span><img src="images/t01.png" /></span>添加</li>
				<li class="click"><span><img src="images/t02.png" /></span>修改</li>
				<li><span><img src="images/t03.png" /></span>删除</li>
				<li><span><img src="images/t04.png" /></span>统计</li>
			</ul>

			<ul class="toolbar1">
				<li><span><img src="images/t05.png" /></span>设置</li>
			</ul>
		</div>

		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">客户信息列表</div>
					<!-- /.panel-heading -->
					<table class="tablelist">
						<thead>
							<tr>
								<th><input name="" type="checkbox" value=""
									checked="checked" /></th>
								<th>编号<i class="sort"><img src="images/px.gif" /></i></th>
								<th>标题</th>
								<th>用户</th>
								<th>籍贯</th>
								<th>发布时间</th>
								<th>是否审核</th>
								<th>操作</th>
							</tr>
						</thead>
					
						<tbody>
							<c:forEach items="${page.rows}" var="row">
								<tr>
									<td>${row.cust_id}</td>
									<td>${row.cust_name}</td>
									<td>${row.cust_source}</td>
									<td>${row.cust_industry}</td>
									<td>${row.cust_level}</td>
									<td>${row.cust_phone}</td>
									<td>${row.cust_mobile}</td>
									<td><a href="#" class="btn btn-primary btn-xs"
										data-toggle="modal" data-target="#customerEditDialog"
										onclick="editCustomer(${row.cust_id})">修改</a> 
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="col-md-12 text-right">
						<itcast:page
							url="${pageContext.request.contextPath }/customer/list.action" />
					</div>
					<!-- /.panel-body -->
				</div>
				<!-- /.panel -->
			</div>
			<!-- /.col-lg-12 -->
		</div>
	</div>
	<!-- /#page-wrapper -->

	</div>

	<!-- 客户编辑对话框 -->
	<div class="modal fade" id="customerEditDialog" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">增加员工</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="edit_customer_form">
						<input type="hidden" id="edit_cust_id" name="cust_id" />
						<div class="form-group">
							<label for="edit_customerName" class="col-sm-2 control-label">客户名称</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="edit_customerName"
									placeholder="客户名称" name="cust_name">
							</div>
						</div>
						<div class="form-group">
							<label for="edit_customerFrom"
								style="float: left; padding: 7px 15px 0 27px;">客户来源</label>
							<div class="col-sm-10">
								<select class="form-control" id="edit_customerFrom"
									placeholder="客户来源" name="cust_source">
									<option value="">--请选择--</option>
									<%-- <c:forEach items="${fromType}" var="item">
										<option value="${item.dict_id}"<c:if test="${item.dict_id == custSource}"> selected</c:if>>${item.dict_item_name }</option>
									</c:forEach> --%>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="edit_custIndustry"
								style="float: left; padding: 7px 15px 0 27px;">所属行业</label>
							<div class="col-sm-10">
								<select class="form-control" id="edit_custIndustry"
									name="cust_industry">
									<option value="">--请选择--</option>
									<%-- <c:forEach items="${industryType}" var="item">
										<option value="${item.dict_id}"<c:if test="${item.dict_id == custIndustry}"> selected</c:if>>${item.dict_item_name }</option>
									</c:forEach> --%>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="edit_custLevel"
								style="float: left; padding: 7px 15px 0 27px;">客户级别</label>
							<div class="col-sm-10">
								<select class="form-control" id="edit_custLevel"
									name="cust_level">
									<option value="">--请选择--</option>
									<%-- <c:forEach items="${levelType}" var="item">
										<option value="${item.dict_id}"<c:if test="${item.dict_id == custLevel}"> selected</c:if>>${item.dict_item_name }</option>
									</c:forEach> --%>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="edit_linkMan" class="col-sm-2 control-label">联系人</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="edit_linkMan"
									placeholder="联系人" name="cust_linkman">
							</div>
						</div>
						<div class="form-group">
							<label for="edit_phone" class="col-sm-2 control-label">固定电话</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="edit_phone"
									placeholder="固定电话" name="cust_phone">
							</div>
						</div>
						<div class="form-group">
							<label for="edit_mobile" class="col-sm-2 control-label">移动电话</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="edit_mobile"
									placeholder="移动电话" name="cust_mobile">
							</div>
						</div>
						<div class="form-group">
							<label for="edit_zipcode" class="col-sm-2 control-label">邮政编码</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="edit_zipcode"
									placeholder="邮政编码" name="cust_zipcode">
							</div>
						</div>
						<div class="form-group">
							<label for="edit_address" class="col-sm-2 control-label">联系地址</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="edit_address"
									placeholder="联系地址" name="cust_address">
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary"
						onclick="updateCustomer()">保存增加</button>
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">  
    function editCustomer(id) {
		$.ajax({
			type:"get",
			url:"<%=request.getContextPath()%>customer/edit.action",
			data:{"id":id},
			success:function(data) {
				$("#edit_cust_id").val(data.cust_id);
				$("#edit_customerName").val(data.cust_name);
				$("#edit_customerFrom").val(data.cust_source)
				$("#edit_custIndustry").val(data.cust_industry)
				$("#edit_custLevel").val(data.cust_level)
				$("#edit_linkMan").val(data.cust_linkman);
				$("#edit_phone").val(data.cust_phone);
				$("#edit_mobile").val(data.cust_mobile);
				$("#edit_zipcode").val(data.cust_zipcode);
				$("#edit_address").val(data.cust_address);
				
			}
		});
	}
	function updateCustomer() {
		$.post("<%=request.getContextPath()%>/customer/update.action",$("#edit_customer_form").serialize(),function(data){
			alert("客户信息更新成功！");
			window.location.reload();
		});
	}
	
    
    
    
    
    
    
    
    
    
    
    
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>
</body>
</html>