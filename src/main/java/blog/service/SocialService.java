package blog.service;

import blog.model.SocialAccount;

public interface SocialService {

    SocialAccount getSocialAccount(String authToken);
}
