<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" 	  		uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" 	  	uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" 	  		uri="http://java.sun.com/jsp/jstl/functions"%>

<script src="https://apis.google.com/js/platform.js" async defer></script>

<div class="article thumbnail">
    <c:set var="category" value="${CATEGORY_MAP[article.idCategory] }" />
    <img src="/Site_Blog_on_JavaEE_war${article.logo }" alt="${article.title }" />
    <div class="data">
        <%-- ----------------------------------------- Article content ----------------------------------------- --%>
        <h3>${article.title }</h3>
        <ul class="vertical large-horizontal menu">
            <li><i class="fi-folder"></i><a href="/Site_Blog_on_JavaEE_war/news${category.url}">${category.name }</a></li>
            <li><i class="fi-comments"></i><fmt:formatNumber value="${article.comments }" /> comments</li>
            <li><i class="fi-clock"></i><fmt:formatDate value="${article.created }" dateStyle="FULL" timeStyle="SHORT" type="both" /></li>
            <li><i class="fi-eye"></i>Hits: <fmt:formatNumber value="${article.views }" /></li>
        </ul>
        <hr/>
        <div class="content">${article.content }</div>
        <%-- ----------------------------------------- Social buttons ----------------------------------------- --%>
        <div class="row columns social">
            <img src="http://placehold.it/32x32?text=f" alt="social" />
            <img src="http://placehold.it/32x32?text=t" alt="social" />
            <img src="http://placehold.it/32x32?text=g" alt="social" />
            <img src="http://placehold.it/32x32?text=f" alt="social" />
            <img src="http://placehold.it/32x32?text=t" alt="social" />
            <img src="http://placehold.it/32x32?text=g" alt="social" />
        </div>
        <br>
        <%-- ----------------------------------------- Comments section ----------------------------------------- --%>
            <div class="comments">
                <jsp:include page="../fragment/new-comment.jsp" />
                <div id="comments-list-container" data-comments-count="${article.comments }" data-id-article="${article.id }">
                    <jsp:include page="../fragment/comments.jsp" />
                </div>
                <div id="comments-load-more-ctrl" class="row column text-center">

                    <script>
                        function moreComments() {
                            let offset = $('#comments-list-container .comment-item').length;
                            let idArticle = $('#comments-list-container').attr('data-id-article');
                            $('#comments-load-more-ctrl .load-more-btn').css('display', 'none');
                            $('#comments-load-more-ctrl .loading-indicator').css('display', 'block');
                            $.ajax({
                                url : '/Site_Blog_on_JavaEE_war/ajax/comments?offset=' + offset + '&idArticle=' + idArticle,
                                success : function(data) {
                                    $('#comments-load-more-ctrl .loading-indicator').css('display', 'none');
                                    $('#comments-list-container').append(data);
                                    let actualTotal = $('#comments-list-container .comment-item').length;
                                    let expectedTotal = $('#comments-list-container').attr('data-comments-count');
                                    if (actualTotal == expectedTotal) {
                                        $('#comments-load-more-ctrl .load-more-btn').css('display', 'none');
                                    } else {
                                        $('#comments-load-more-ctrl .load-more-btn').css('display', 'block');
                                    }
                                },
                                error : function(data) {
                                    alert('Error. Please try again later...');
                                }
                            });
                        }

                        // ------------------------ Google plus integration ------------------------
                        let googleProfile = null;

                        function submitComment() {
                            if (googleProfile == null) {
                                $('#sigin-form').foundation('open');
                            } else {
                                var authToken = googleProfile.authToken;
                                var idArticle = $('#comments-list-container').attr('data-id-article');
                                var content = $('#new-comment-container textarea').val();
                                if (content.trim() != '') {
                                    $('#comment-content').parent().find('.form-error').css('display', 'none');
                                    $('#new-comment-container textarea').val('');
                                    $('#new-comment-loading img').css('display', 'block');
                                    $.ajax({
                                        url : '/Site_Blog_on_JavaEE_war/ajax/comment',
                                        method : 'post',
                                        data : {
                                            idArticle : idArticle,
                                            authToken : authToken,
                                            content : content
                                        },
                                        success : function(data) {
                                            $('#new-comment-loading img').css('display', 'none');
                                            $('#comments-list-container').prepend(data);
                                        },
                                        error : function(data) {
                                            alert(messages.errorAjax);
                                        }
                                    });
                                }
                                else{
                                    $('#comment-content').parent().find('.form-error').css('display', 'inline');
                                    $('#new-comment-container textarea').val('');
                                }
                            }
                        }

                        function onSignIn(googleUser) {
                            googleProfile = googleUser.getBasicProfile();
                            googleProfile.authToken = googleUser.getAuthResponse().id_token;
                            $('#sigin-form').foundation('close');
                            if (googleProfile.getImageUrl() != null) {
                                $('#new-comment-container img').attr('src', googleProfile.getImageUrl());
                            }
                            $('#new-comment-container img').attr('alt', googleProfile.getName());
                            $('#new-comment-container a.logout').css('display', 'block');
                        }

                        function gpLogout() {
                            let auth2 = gapi.auth2.getAuthInstance();
                            auth2.signOut();
                            googleProfile = null;
                            $('#new-comment-container a.logout').css('display', 'none');
                            $('#new-comment-container img').attr('src', '/Site_Blog_on_JavaEE_war/static/img/no_avatar.png');
                            $('#new-comment-container img').attr('alt', messages.anonym);
                        }
                    </script>


                    <a href="javascript:moreComments();" class="button hollow expanded load-more-btn"
                    ${article.comments >  fn:length(comments) ? '' : 'style="display:none"' }>Load More</a>
                    <img src="/Site_Blog_on_JavaEE_war/static/img/loading.gif" alt="Loading..." class="loading-indicator" />
                </div>
            </div>
    </div>
</div>