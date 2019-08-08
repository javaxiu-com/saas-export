package com.gyhqq.service.cargo;

import com.gyhqq.domain.cargo.PackingList;
import com.gyhqq.domain.cargo.PackingListExample;
import com.github.pagehelper.PageInfo;

public interface PackingListService {

    PageInfo findAll(Integer page, Integer size, PackingListExample example);

    PackingList findById(String id);

    void save(PackingList packingList);

    void update(PackingList packingList);

    void delete(String id);

}
