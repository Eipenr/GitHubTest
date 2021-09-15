<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>订单预览页面</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">
		function createOrder(){


			//选中地址的id
			location.href = "order?method=create&aid="+$("#address").val();
		}
</script>
</head>
<body style="background-color:#f5f5f5">
<%@ include file="header.jsp"%>
<div class="container" style="background-color: white;">
	<div class="row" style="margin-left: 40px">
		<h3>订单预览<small>温馨提示：请添加你要邮递到的地址</small></h3>
	</div>
	<div class="row" style="margin-top: 40px;">
		<div class="col-md-10 col-md-offset-1">
			<table class="table table-bordered table-striped table-hover">
 				<tr>
 					<th>序号</th>
 					<th>商品名称</th>
 					<th>价格</th>
 					<th>数量</th>
 					<th>小计</th>
 					
 				</tr>
<%-- 				<c:set value="0" var="sum"></c:set>--%>
<%-- 				<c:forEach items="${cartList}" var="c" varStatus="i">--%>
<%--	 				<tr>--%>
<%--	 					<th>${i.count}</th>--%>
<%--	 					<th>${c.product.pname}</th>--%>
<%--	 					<th>${c.product.pprice}</th>--%>
<%--	 					<th>${c.cnum}</th>--%>
<%--	 					<th>${c.ccount}</th>--%>
<%--	 				</tr>--%>
<%--	 				<c:set var="sum" value="${sum+c.ccount}"></c:set>--%>
				<c:set value="0" var="sum"></c:set>
				<c:forEach items="${cart.goodsList}" var="c" varStatus="i">
					<tr>
						<th>${i.count}</th>
						<th>${c.name}</th>
						<th>${c.price}</th>
						<th>${cart.nums[i.index]}</th>
							<%--	 					<th width="100px">--%>
							<%--		 					<div class="input-group">--%>
							<%--		 						<span class="input-group-btn">--%>
							<%--		 							<button class="btn btn-default" type="button" onclick="sub(${c.cid},${c.cnum},${c.product.pprice})">-</button>--%>
							<%--		 						</span>--%>
							<%--		 						<input type="text" class="form-control" id="num_count${i.count}" value="${c.cnum}" readonly="readonly" style="width:40px">--%>
							<%--		 						<span class="input-group-btn">--%>
							<%--		 							<button class="btn btn-default" type="button" onclick="add(${c.cid},${c.cnum},${c.product.pprice})">+</button>--%>
							<%--		 						</span>--%>
							<%--	 						</div>--%>
							<%--	 					</th>--%>
						<c:set var="count" value="${c.price*cart.nums[i.index]}"></c:set>
						<th>¥&nbsp;${count}</th>
						<th>
							<button type="button" class="btn btn-default" onclick="delCart(${c.id})">删除</button>
						</th>
					</tr>
					<c:set var="sum" value="${sum+count}"></c:set>
 				</c:forEach>
 				<tr>
 				 <td colspan="5">
 				 	<h5>收货地址</h5>
 				 	<select id="address" style="width:60%" class="form-control">
 				 		<c:forEach items="${addressList}" var="a" varStatus="ai">
 				 			<option value="${a.id}">${a.name}&nbsp;&nbsp;${a.phone}&nbsp;&nbsp;${a.detail}</option>
 				 		</c:forEach>
 				 	</select>
 				 	<c:if test="${empty addressList}">
 				 		<a href="address?method=show">添加收货地址</a>
 				 	</c:if>
 				 </td>
 				</tr>
			</table>
		</div>
	</div>
	<hr>
	<div class="row">
		<div style="margin-left: 40px;">	  
	            <h4>商品金额总计：<span id="total" class="text-danger"><b>￥&nbsp;&nbsp;${sum}</b></span></h4>
		</div>
	</div>
	<div class="row pull-right" style="margin-right: 40px;">
		 <div style="margin-bottom: 20px;">
	            <button  id="btn_add"  onclick="createOrder()" class="btn  btn-danger btn-lg" type="button">提交订单</button>
	     </div>
	</div>
</div>
	
    
<!-- 底部 -->
<%@ include file="footer.jsp"%>

</body>
</html>