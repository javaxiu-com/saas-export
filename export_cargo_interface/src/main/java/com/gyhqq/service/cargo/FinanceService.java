package com.gyhqq.service.cargo;

import com.gyhqq.domain.cargo.Finance;
import com.gyhqq.domain.cargo.FinanceExample;
import com.github.pagehelper.PageInfo;

public interface FinanceService {

    //根据id查询
    Finance findById(String id);

    //保存
    void save(Finance finance);

    //更新
    void update(Finance finance);

    //删除
    void delete(String financeId);

    //查询列表分页
    PageInfo findAll(FinanceExample example, int page, int size);
}
