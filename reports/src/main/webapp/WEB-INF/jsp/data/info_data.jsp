<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/common/setting.jsp"%>
<html>
<title>${title }</title>
<link rel="stylesheet" type="text/css" href="${ctx}/reports/tiles/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/reports/tiles/jsonformat/jsonFormater.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/reports/skins/css/reports.css" />
<script type="text/javascript" src="${ctx}/reports/tiles/js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="${ctx}/reports/tiles/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/reports/tiles/jsonformat/jsonFormater.js"></script>
<body>
<jsp:include page="/common/header.jsp"/>
<div class="container">
<div class="row">
<div class="page-header">
	<h3>
		${dataInfo.dataName}
	</h3>
</div><!-- /.page-header -->
	<div class="col-md-12" id="data">
	</div>
</div>
</div>
<jsp:include page="/common/foot.jsp"/>
<script type="text/javascript">
$(document).ready(function(){
	var options = {  
		dom: document.getElementById('data'),
    };  
    var jf = new JsonFormatter(options); //创建对象  
    jf.doFormat('${dataInfo.result}'); //格式化json  
});
</script>
</body>
</html>
