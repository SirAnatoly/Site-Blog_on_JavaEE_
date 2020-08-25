package blog.service;

import java.io.IOException;

public interface AvatarService {

    int AVATAR_SIZE_IN_PX = 80;

    String MEDIA_AVATAR_PREFFIX = "/Site_Blog_on_JavaEE_war/media/avatar/";

    String downloadAvatar (String url) throws IOException;

    boolean deleteAvatarIfExists (String avatarPath);
}
