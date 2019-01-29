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
	<br>

	<div id="toolbar">
		<div class="form-inline" role="form">
			<div class="form-group">
				<span>客户名字：</span> <input name="name" id="nameParam"
					class="form-control" type="text" placeholder="客户名字">
			</div>
			<div class="form-group">
				<span>手机号码：</span> <input name=phoneno id="phonenoParam"
					class="form-control" type="text" placeholder="手机号码">
			</div>
			<div class="form-group">
				<span>邀约人：</span> <input name="invitename" id="invitenameParam"
					class="form-control" type="text" placeholder="邀约人">
			</div>
			<div class="form-group">
				<span>日期：</span> <input name="createdate" id="createdateParam"
					class="form-control" type="date" placeholder="日期">
			</div>
			<input type="button" class="btn-sm btn-primary" onclick="_search()"
				value="查询" />
		</div>
	</div>
	<br>
	 <div class="form-group" style="float: left">
		<input type="button" id="item_info_btn" class="btn btn-info" onclick="_download()" value="导出" />
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 </div>

    <div class="form-group" style="float: left">
    <form action="getInsert"  method="post" enctype="multipart/form-data">
                <input class="btn btn-info" type="file" name="myFile"><br>
             <input class="btn btn-info" type="submit" value="上传">
     </form>
	</div>
	
	<table id="prjTable"></table>
	

	
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
							<label for="modifyNameText">客户姓名：</label> <input id="modifyid"
								type="hidden"> <input id="modifyname"
								class="form-control input-sm">
						</div>
						<div class="form-group">
							<label for="modifyAgeText">教育水平：</label> <input
								id="modifyeducation" class="form-control input-sm">
						</div>
					</div>
					<div class="form-group">
						<label for="modifyAddressText">手机：</label> <input
							id="modifyphoneno" class="form-control input-sm">
					</div>
					<div class="form-group">
						<label for="modifyAddressText">qq：</label> <input id="modifyqq"
							class="form-control input-sm">
					</div>
					<div class="form-group">
						<label for="modifyAddressText">邮箱：</label> <input id="modifyemail"
							class="form-control input-sm">
					</div>
					<div class="form-group">
						<label for="modifyAddressText">客户状态：</label>
						<div class="vocation">
							<select id="modifycustomstatu" class="form-control input-sm"
								style="width: 518px">
								<option>0</option>
								<option>1</option>
								<option>2</option>
								<option>3</option>
								<option>4</option>
								<option>5</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="modifyAddressText">邀请人姓名：</label> <input
							id="modifyinvitename" class="form-control input-sm">
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
url: "<%=request.getContextPath()%>/selectCustom", 
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
          	name:$("#nameParam").val(),
          	phoneno:$("#phonenoParam").val(),
          	invitename:$("#invitenameParam").val(),
          	createdate:$("#createdateParam").val()
        };
    },
      columns: [
    	      {
              title: '客户编号',
                field: 'id',
                align: 'left',
                valign: 'middle'
              }, 
              {
                title: '客户名称',
                  field: 'name',
                  align: 'left',
                  valign: 'middle'
              }, 
              {
                  title: '教育水平',
                  field: 'education',
                  align: 'center'
      
              },
              {
                  title: '手机',
                  field: 'phoneno',
                  align: 'center',
                  valign: 'middle'
              }, 
              {
                  title: 'qq',
                  field: 'qq',
                  align: 'center'
              },
              {
                  title: '邮箱',
                  field: 'email',
                  align: 'center'
              },
              {
                  title: '客户状态',
                  field: 'customstatu',
                  align: 'center'
              },
              {
                  title: '创建日期',
                  field: 'createdate',
                  align: 'center',
                  formatter: function (value, row, index) {
                      return changeDateFormat(value)
                  }

              },
              {
                  title: '邀请人姓名',
                  field: 'invitename',
                  align: 'center'
              },
               {
                   title: '操作',
                   field: 'id',
                   align: 'center',
                   formatter:function(value,row,index){
                var e = '<a href="javascript:void(0)" onclick="showEditUI(\''+ row.id +'\')" input type="button" class="btn-sm btn-info" >修改</a> ';
                return e;
                 } 
               }
          ]   
  });
  
//修改——转换日期格式(时间戳转换为datetime格式)
function changeDateFormat(cellval) {
    if (cellval != null) {
        var date = new Date(cellval);
        var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
        var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
        return date.getFullYear() + "/" + month + "/" + currentDate;
    }
}

//查询		
function _search()
{
	 $('#prjTable').bootstrapTable('refresh', {url: 'selectCustom'});  
}
// 编辑时，弹出数据框
function showEditUI(projectEditId)
{
	 $.get("selectByPKey",{id:projectEditId},function(data){
		 // 给每一个输入框赋值
		 $("#modifyid").val(data.id);
		 $("#modifyname").val(data.name);
		 $("#modifyeducation").val(data.education);
		 $("#modifyphoneno").val(data.phoneno);
		 $("#modifyqq").val(data.qq);
		 $("#modifyemail").val(data.email);
		 $("#modifycustomstatu").val(data.customstatu);
		 $("#modifyinvitename").val(data.invitename);
	 },"json");
	// 显示弹窗
	 $("#modifyModal").modal("show");
}

$("#saveModify").click(function(){
	 var prjInfo = { 
			 id:$("#modifyid").val(),
			 name:$("#modifyname").val(),
			 education:$("#modifyeducation").val(),
			 phoneno:$("#modifyphoneno").val(),
			 qq:$("#modifyqq").val(),
			 email:$("#modifyemail").val(),
			 customstatu:$("#modifycustomstatu").val(),
			 invitename:$("#modifyinvitename").val()
	 };
	 // 后台提交保存的请求
	 $.post("editCustom",{custom:JSON.stringify(prjInfo)},function(data){
		 if (data == "1")
			 {
			   alert("修改成功");
			// 关闭弹窗
			$("#modifyModal").modal("hide");
			// 刷新数据
			 $('#prjTable').bootstrapTable('refresh', {url: 'selectCustom'});
			 }
	 });
});

     
// 文件的导出
function _download()
{
	window.location.href='<%=request.getContextPath()%>/getAllCostom';
}

//文件的导出
function _export()
{
	window.location.href='<%=request.getContextPath()%>/getAllCostom';
}

</script>
</body>
</html>