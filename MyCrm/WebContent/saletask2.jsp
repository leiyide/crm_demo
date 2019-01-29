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
		<div class="form-group">
			<input type="button" id="item_info_btn_do" class="btn btn-info"
				value="分配任务" />
		</div>
	</div>

	<!-- 任务分配确认框 -->
	<div class="modal fade" id="item_project_modal" tabindex="-1"
		role="dialog" aria-labelledby="gridSystemModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="gridSystemModalLabel">任务分配提示</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-4">你确定要分配任务吗？</div>
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

	<table id="prjTable"></table>

	<script type="text/javascript">
var tableData = $('#prjTable');
tableData.bootstrapTable({
url: "<%=request.getContextPath()%>/getConsult", 
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
            offset: params.offset,  //页码
            limit: params.limit,   //页面大小
            search : params.search, //搜索
            order : params.order, //排序
            ordername : params.sort, //排序
        };
    },
      columns: [
    	    {
              checkbox:true,
              formatter:function(value,row,index){
            	  if(row.workstatu==0){
            		  disable:false;
            	  }else{
            		return value;  
            	  }
              }
            }, 
              {
                title: '员工名',
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
          ]  
  });
 
//定义一个全局变量存储点击的id
var ids = new Array();
var count=0;
//任务分配后的单选框之后所有的数据
 $('#item_info_btn_do').click(function(){  
	 //定义一个数组
	 var list=new Array();
     list= $("#prjTable").bootstrapTable('getSelections'); 
     if(list.length==0) {
    	 alert("请选择是一列"); 
         return false;  
     }else{ 
    	  $.map(list,function(row){
    		 ids[count]=row.id;
    		 count++;
    	 }) ;
         $('#item_project_modal').modal('show'); 
     }  
    });  
     
// 确认任务的分配
function delConfirm()
{
	 // 后台删除(参数名字必须与后台处理的参数名称一致)
	 $.get("fenpeiTaskConsult",{consultId:ids},function(data){
		 // 标记是否删除成功
		 if (data == "1")
			 {
			 // 关闭弹窗
			 $("#item_project_modal").modal("hide");
			 alert("任务分配成功！");
			 // 刷新数据
			 $('#prjTable').bootstrapTable('refresh', {url: 'getConsult'});
			 }
	 }); 
}

</script>
</body>
</html>