<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--shortcut start-->
<jsp:include page="shortcut.jsp" />
<!--shortcut end-->
<div id="header">
  <div class="header_inner">
    <div class="logo">
			<img src="/images/百货之家.png">
		</div>
    <div class="index_promo"></div>
    <div class="search">
      <form action="<%=request.getScheme() %>://<%=request.getServerName() %>:8080/solr" id="searchForm" name="query" method="GET">
        <input type="text" class="text keyword ac_input" name="keyword" id="keyword" value="${query }" style="color: rgb(153, 153, 153);" onkeydown="javascript:if(event.keyCode==13) search_keys('searchForm');" autocomplete="off">
        <input type="button" value="" class="submit" onclick="search_keys('searchForm')">
      </form>
      <div class="search_hot">
      <c:forEach items="${searchHotList}" var="searchHot" varStatus="status">
            <a target="_blank" href="${searchHot.url }">${searchHot.title }</a>
        </c:forEach> 
      </div>
    </div>
    <div class="shopingcar" id="topCart">
      <s class="setCart"></s><a href="<%=request.getScheme() %>://<%=request.getServerName() %>:8081/mall/cart/add"  class="t" rel="nofollow">我的购物车</a>
      <span class="outline"></span>
      <span class="blank"></span>
      <div id="cart_lists">
        <!--cartContent-->   
        <div class="clear"></div>
      </div>
    </div>
  </div>
  <script type="text/javascript">
  	function search_keys(formName){
	   $('#'+formName).submit();
	}
  </script>
</div>