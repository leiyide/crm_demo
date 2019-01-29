<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>项目列表</title>
<!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/css/bootstrap-datetimepicker.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/css/bootstrap-table.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/css/style.css" type="text/css" />
<!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/css/bootstrap-theme.min.css">
<!-- 最新的 Bootstrap 核心 JavaScript 文件  要将jquery的包放在bootstrap之前-->
<script src="<%=request.getContextPath() %>/js/jquery-1.9.1.js"></script>
<script src="<%=request.getContextPath() %>/js/bootstrap.min.js"></script>
<script
	src="<%=request.getContextPath() %>/js/bootstrap-datetimepicker.min.js"></script>
<script
	src="<%=request.getContextPath() %>/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="<%=request.getContextPath() %>/js/bootstrap-table.min.js"></script>
<script
	src="<%=request.getContextPath() %>/js/bootstrap-table-zh-CN.min.js"></script>

</head>
<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">数据表</a></li>
			<li><a href="#">基本内容</a></li>
		</ul>
		<br> <br> <br>

		<div id="toolbar" class="bg-primary"></div>
		<div class="form-inline " role="form">
			<div class="form-group">
				<span>项目名称：</span> <input name="username" id="usernameParam"
					class="form-control" type="text" placeholder="项目名称">
			</div>
			<input type="button" class="btn btn-info" onclick="_search()"
				value="查询" />
		</div>
	</div>

	<table id="prjTable"></table>


	<!-- 删除确认框 -->
	<div class="modal fade" id="delConfirmModal" tabindex="-1"
		role="dialog" aria-labelledby="gridSystemModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="gridSystemModalLabel">删除提示</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-4">你确定要删除吗？</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary"
						onclick="delConfirm()">确认</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	
	<!-- 初始化确认框 -->
	<div class="modal fade" id="startIdConfigModel" tabindex="-1"
		role="dialog" aria-labelledby="gridSystemModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="gridSystemModalLabel">初始化密码提示</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-4">你确定要初始化吗？</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary"
						onclick="delConfirm()">确认</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

	<!-- 修改模态窗 -->
	<div class="modal fade" id="modifyModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">×</button>
					<h4 class="modal-title">修改信息</h4>
				</div>
				<div class="modal-body">
					<div class="form-inline">

						<div class="form-group">
							<label for="modifyNameText">项目名称：</label> <input id="modifyid"
								type="hidden"> <input id="modifyusername"
								class="form-control input-sm">
						</div>
						<div class="form-group">
							<label for="modifyAgeText">密码：</label> <input id="modifypass"
								class="form-control input-sm">
						</div>
					</div>
					<div class="form-group">
						<label for="modifyAddressText">昵称：</label> <input
							id="modifynickname" class="form-control input-sm">
					</div>
					<div class="form-group">
						<label for="modifyAddressText">真实姓名：</label> <input
							id="modifyrealname" class="form-control input-sm">
					</div>
					<div class="form-group">
						<label for="modifyAddressText">职位编号：</label> <input
							id="modifyjobinfoid" class="form-control input-sm">
					</div>
					<div class="form-group">
						<label for="modifyAddressText">部门编号：</label> <input
							id="modifydepartmentid" class="form-control input-sm">
					</div>
					<div class="form-group">
						<label for="modifyAddressText">手机：</label> <input
							id="modifyphoneno" class="form-control input-sm">
					</div>
					<div class="form-group">
						<label for="modifyAddressText">办公电话：</label> <input
							id="modifyofficetel" class="form-control input-sm">
					</div>
					<div class="form-group">
						<label for="modifyAddressText">在职状态：</label> <input
							id="modifyworkstatu" class="form-control input-sm">
					</div>
				</div>
				<div class="modal-footer">
					<button class="btn btn-default" data-dismiss="modal">关闭</button>
					<button class="btn btn-primary" id="saveModify">保存</button>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
var tableData = $('#prjTable');
tableData.bootstrapTable({
url: "<%=request.getContextPath()%>/getFenYe", 
dataType: "json",
pagination: true, //分页
singleSelect: false,
//toolbar:"#toolbar",
showRefresh:true,// 显示刷新按钮
showColumns:true, // 显示所有的列
//data-locale:"zh-CN", //表格汉化
search: false, //显示搜索框
striped:true,
sidePagination: "server", //服务端处理分页
pageList:[5,10,15,20,50],
sortName : 'createDate', // 排序字段
sortOrder : 'desc', // 排序方式
sortable: true, //是否启用排序
queryParams: function (params) {
    return {
            //rows: this.pageSize,
            //page: this.pageNumber,
            offset: params.offset,  //页码
            limit: params.limit,   //页面大小
            search : params.search, //搜索
            order : params.order, //排序
            ordername : params.sort, //排序
          	username:$("#usernameParam").val()
        };
    },
      columns: [
              {
                title: '用户名',
                  field: 'username',
                  align: 'left',
                  valign: 'middle'
              }, 
              {
                  title: '密码',
                  field: 'pass',
                  align: 'center'
      
              },
              {
                  title: '昵称',
                  field: 'nickname',
                  align: 'center',
                  valign: 'middle'
              }, 
              {
                  title: '真实姓名',
                  field: 'realname',
                  align: 'center'
              },
            
              {
                  title: '职位编号',
                  field: 'jobinfoid',
                  align: 'center'
              },
              {
                  title: '部门编号',
                  field: 'departmentid',
                  align: 'center'
              },
              {
                  title: '手机',
                  field: 'phoneno',
                  align: 'center'
              },
              {
                  title: '办公电话',
                  field: 'officetel',
                  align: 'center'
              },
              {
                  title: '在职状态',
                  field: 'workstatu',
                  align: 'center'
              },
               {
                   title: '操作',
                   field: 'id',
                   align: 'center',
                   formatter:function(value,row,index){
                var e = '<a href="javascript:void(0)" onclick="showEditUI(\''+ row.id +'\')" input type="button" class="btn-sm btn-info" >修改</a> ';
                var d = '<a href="javascript:void(0)" onclick="delUI(\''+ row.id +'\')" input type="button" class="btn-sm btn-info">删除</a> ';
                var s = '<a href="javascript:void(0)" onclick="startUI(\''+ row.id +'\')" input type="button" class="btn-sm btn-info">初始化密码</a> ';
                return e+d+s;
                 } 
               }
          ]   
  });
  

//查询		
function _search()
{
	 $('#prjTable').bootstrapTable('refresh', {url: 'getFenYe'});  
}

  
var delProjectId = "";
// 删除数据
function delUI(id)
{
	 // 删除时，将项目id赋值
	 delProjectId = id;
	 // 用户确认删除
	 $("#delConfirmModal").modal("show");
	 
}

// 确认删除
function delConfirm()
{
	 // 后台删除(参数名字必须与后台处理的参数名称一致)
	 $.get("deleteByPrimaryKey",{id:delProjectId},function(data){
		 // 标记是否删除成功
		 if (data == "1")
			 {
			 // 关闭弹窗
			 $("#delConfirmModal").modal("hide");
			 alert("删除成功！");
			 // 刷新数据
			 $('#prjTable').bootstrapTable('refresh', {url: 'getFenYe'});
			 }
	 });
	 
}

//初始化密码
//定义一个变量
var startid='';
function startUI(id){
	//初始化密码的将id进行赋值
	startid=id;
	//初始化的时候进行进行判断提示
	$('#startIdConfigModel').modal("show");
}

//确认进行密码的初始化
function delConfirm()
{
	 // 后台初始化(参数名字必须与后台处理的参数名称一致)
	 $.get("startPass",{id:startid},function(data){
		 // 标记是否删除成功
		 if (data == "1")
			 {
			 // 关闭弹窗
			 $("#startIdConfigModel").modal("hide");
			 alert("初始化成功！");
			 // 刷新数据
			 $('#prjTable').bootstrapTable('refresh', {url: 'getFenYe'});
			 }
	 });
	 
}

// 编辑时，弹出数据框
function showEditUI(projectEditId)
{
	 $.get("selectByPrimaryKey",{id:projectEditId},function(data){
		 // 给每一个输入框赋值
		 $("#modifyid").val(data.id);
		 $("#modifyusername").val(data.username);
		 $("#modifypass").val(data.pass);
		 $("#modifynickname").val(data.nickname);
		 $("#modifyrealname").val(data.realname);
		 $("#modifyjobinfoid").val(data.jobinfoid);
		 $("#modifydepartmentid").val(data.departmentid);
		 $("#modifyphoneno").val(data.phoneno);
		 $("#modifyofficetel").val(data.officetel);
		 $("#modifyworkstatu").val(data.workstatu);
	 },"json");
	// 显示弹窗
	 $("#modifyModal").modal("show");
}

$("#saveModify").click(function(){
	 var prjInfo = { 
			 id:$("#modifyid").val(),
			 username:$("#modifyusername").val(),
			 pass:$("#modifypass").val(),
			 nickname:$("#modifynickname").val(),
			 realname:$("#modifyrealname").val(),
			 jobinfoid:$("#modifyjobinfoid").val(),
			 departmentid:$("#modifydepartmentid").val(),
			 phoneno:$("#modifyphoneno").val(),
			 officetel:$("#modifyofficetel").val(),
			 workstatu:$("#modifyworkstatu").val()
	 };
	 // 后台提交保存的请求
	 $.post("editEmployee",{employee:JSON.stringify(prjInfo)},function(data){
		 if (data == "1")
			 {
			   alert("修改成功");
			// 关闭弹窗
			$("#modifyModal").modal("hide");
			// 刷新数据
			 $('#prjTable').bootstrapTable('refresh', {url: 'getFenYe'});
			 }
	 });
});
</script>
</body>
</html>