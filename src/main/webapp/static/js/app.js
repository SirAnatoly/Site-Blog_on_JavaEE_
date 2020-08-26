$(document).foundation();
$(document).ready(function(){
	$('#mobile-category-menu ul.menu').removeAttr('style');
});

function reply(name) {
	$('#new-comment-container textarea').val(name + ', ');
	$('#new-comment-container textarea').focus();
	$('html, body').animate({
		scrollTop: $('#new-comment-container textarea').offset().top
	}, 2000);
}

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
		let authToken = googleProfile.authToken;
		let idArticle = $('#comments-list-container').attr('data-id-article');
		let content = $('#new-comment-container textarea').val();
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
					location.reload()
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