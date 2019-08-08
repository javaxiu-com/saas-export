package com.gyhqq.web.controller.cargo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gyhqq.domain.cargo.Contract;
import com.gyhqq.domain.cargo.ContractExample;
import com.gyhqq.domain.system.User;
import com.gyhqq.service.cargo.ContractService;
import com.gyhqq.web.controller.BaseController;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cargo/contract")
public class ContractController extends BaseController {
    @Reference
    private ContractService contractService;

    @RequestMapping("/list")
    public String list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer size, Model model) {
        ContractExample example = new ContractExample();
        ContractExample.Criteria criteria = example.createCriteria();
        criteria.andCompanyIdEqualTo(getLoginCompanyId());
        User user = getLoginUser();
        if (user.getDegree() == 4) {//普通员工
            criteria.andCreateByEqualTo(user.getId());
        } else if (user.getDegree() == 3) {//部门经理
            criteria.andCreateDeptEqualTo(user.getDeptId());
        } else if (user.getDegree() == 2) {
            //管理本部门及其下属部门
            criteria.andCreateDeptLike(user.getDeptId() + "%");
        }

        example.setOrderByClause("create_time desc");
        PageInfo info = contractService.findAll(example, page, size);
        model.addAttribute("page", info);
        return "/cargo/contract/contract-list";
    }

    @RequestMapping("/toAdd")
    public String toAdd() {
        return "/cargo/contract/contract-add";
    }

    @RequestMapping("/edit")
    public String edit(Contract contract) {

        if (StringUtils.isEmpty(contract.getId())) {
            contract.setCompanyId(getLoginCompanyId());
            contract.setCompanyName(getLoginCompanyName());

            contract.setCreateBy(getLoginUser().getId());
            contract.setCreateDept(getLoginUser().getDeptId());
            contractService.save(contract);
        } else {
            contractService.update(contract);
        }
        return "redirect:/cargo/contract/list.do";
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(String id, Model model) {
        Contract contract = contractService.findById(id);
        model.addAttribute("contract", contract);
        return "/cargo/contract/contract-update";
    }

    @RequestMapping("/delete")
    public String delete(String id) {
        contractService.delete(id);
        return "redirect:/cargo/contract/list.do";
    }

    @RequestMapping("/submit")
    public String submit(String id) {
        //status 改为1
        Contract contract = new Contract();
        contract.setId(id);
        contract.setState(1);
        contractService.update(contract);
        return "redirect:/cargo/contract/list.do";
    }

    @RequestMapping("/cancel")
    public String cancel(String id) {
        //status 改为0
        Contract contract = new Contract();
        contract.setId(id);
        contract.setState(0);
        contractService.update(contract);
        return "redirect:/cargo/contract/list.do";
    }

}
