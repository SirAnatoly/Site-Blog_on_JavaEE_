<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>Blog template</title>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="/Site_Blog_on_JavaEE_war/static/css/foundation.css">
    <link rel="stylesheet" type="text/css" href="/Site_Blog_on_JavaEE_war/static/css/foundation-icons.css">
    <link rel="stylesheet" type="text/css" href="/Site_Blog_on_JavaEE_war/static/css/app.css">

    <meta name="google-signin-scope" content="profile email">
    <meta name="google-signin-client_id" content="299849943590-v2itenkjumc8hg4tvcr5aer8jiph1cfi.apps.googleusercontent.com">

</head>
<body>
<header>
    <jsp:include page="fragment/header.jsp"/>
</header>
<div class="row">
    <nav role="navigation" class="large-12 small-6 medium-8 columns">
        <jsp:include page="fragment/breadcrumbs.jsp"/>
    </nav>
    <div class="small-6 medium-4 columns">
        <jsp:include page="fragment/categories-dropdown.jsp"/>
    </div>
</div>
<section class="row">

    <div id="mainContent" class="large-10 columns" style="min-height:600px;">
        <jsp:include page="${currentPage}"/>
    </div>

    <div class="columns large-2 show-for-large right" data-sticky-container>
        <div class="sticky categories show-for-large" data-sticky data-anchor="mainContent">
            <jsp:include page="fragment/categories-table.jsp"/>
        </div>
    </div>
</section>
<footer class="footer">
    <jsp:include page="fragment/footer.jsp"/>
</footer>


<script src="/Site_Blog_on_JavaEE_war/static/js/jquery.js"></script>
<script src="/Site_Blog_on_JavaEE_war/static/js/what-input.js"></script>
<script src="/Site_Blog_on_JavaEE_war/static/js/foundation.js"></script>
<script src="/Site_Blog_on_JavaEE_war/static/js/messages.jsp"></script>
<script src="/Site_Blog_on_JavaEE_war/static/js/app.js"></script>
</body>
</html>