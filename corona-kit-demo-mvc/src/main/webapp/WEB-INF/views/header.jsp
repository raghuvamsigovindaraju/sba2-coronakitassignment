<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<nav class="navbar navbar-expand-md bg-dark navbar-dark">
  <!-- Brand -->
  <a class="navbar-brand" href="#">CORONA KIT</a>

  <!-- Toggler/collapsibe Button -->
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    <span class="navbar-toggler-icon"></span>
  </button>

  <!-- Navbar links -->
  <div class="collapse navbar-collapse" id="collapsibleNavbar">
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/home">Home</a>
      </li>
     <c:choose>
     
     	<c:when test="${unm!=null && role=='ADMIN'}">
		<%--      <li class="nav-item">
		        <a class="nav-link" href="${pageContext.request.contextPath}/admin/product-list">Available Products</a>
		      </li> --%>
		      <li class="nav-item">
		        <a class="nav-link" href="${pageContext.request.contextPath}/admin/home">Admin Home</a>
		      </li>
		      <%-- <li class="nav-item">
		        <a class="nav-link" href="${pageContext.request.contextPath}/admin/product-entry">ADD New Product</a>
		      </li> --%>
		      <li class="nav-item">
		        <a class="nav-link" href="${pageContext.request.contextPath}/logout">Logout</a>
		      </li>	
     	</c:when>
     	<c:when test="${unm!=null && role=='USER'}">
     		<%--   <li class="nav-item">
		        <a class="nav-link" href="${pageContext.request.contextPath}/user/show-list">Available Products</a>
		      </li> --%>
		      <li class="nav-item">
		        <a class="nav-link" href="${pageContext.request.contextPath}/user/home">User Home</a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="${pageContext.request.contextPath}/logout">Logout</a>
		      </li>
     	</c:when>
     	<c:otherwise>
     		<li class="nav-item">
		        <a class="nav-link" href="${pageContext.request.contextPath}/login">Login</a>
		    </li>
		    <li class="nav-item">
		       <a class="nav-link" href="${pageContext.request.contextPath}/register">New User</a>
		    </li>
     	</c:otherwise>
     </c:choose>	
    
    </ul>
  </div>
</nav>