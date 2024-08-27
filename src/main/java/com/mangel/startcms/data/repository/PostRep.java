package com.mangel.startcms.data.repository;

import com.mangel.startcms.data.model.Post;

public interface PostRep extends BaseRep<Post>{
    public boolean deleteById(int id);
}
