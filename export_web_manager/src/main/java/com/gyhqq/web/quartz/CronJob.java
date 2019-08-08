package com.gyhqq.web.quartz;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gyhqq.domain.cargo.Export;
import com.gyhqq.domain.cargo.ExportExample;
import com.gyhqq.service.cargo.ExportService;
import com.gyhqq.vo.ExportResult;
import org.apache.cxf.jaxrs.client.WebClient;

import java.util.List;

public class CronJob {

    @Reference
    private ExportService exportService;

    //查询海关报运结果
    public void queryResult() {
        //id去数据库里查状态3是已上报的,但是一次只能查一个id所有还要循环
        ExportExample exportExample = new ExportExample();
        ExportExample.Criteria criteria = exportExample.createCriteria();
        criteria.andStateEqualTo(3);
        List<Export> exports = exportService.findStateExport(exportExample);
        for (Export export : exports) {
            WebClient webClient = WebClient.create("http://localhost:8080/jk_export_war/ws/export/user/" + export.getId());
            ExportResult exportResult = webClient.get(ExportResult.class);
            exportService.updateResult(exportResult);
        }
    }
}
