package com.thd.processmanagementserver.service;

import com.github.pagehelper.PageInfo;
import com.thd.processmanagementserver.constant.ProcConstant;
import com.thd.processmanagementserver.entity.*;

import java.util.List;
import java.util.Map;

/**
 * com.thd.processmanagementserver.service.impl.ProcessService
 *
 * @author: wanglei62
 * @DATE: 2021/1/25 15:05
 **/
public interface ProcessService {
    public PageInfo<ProcDefEntity> queryProcessDef(ProcDefEntity condition);
    public PageInfo<ProcessInstanceTaskEntity> queryTaskPage(ProcessInstanceTaskEntity condition);
    public PageInfo<ProcessInstanceTaskHisEntity> queryTaskHisPage(ProcessInstanceTaskHisEntity condition);
    public List<ProcessInstanceTaskHisEntity> queryTaskHisList(ProcessInstanceTaskHisEntity condition);
    public PageInfo<ProcessInstanceEntity> queryProcessInstancePage(ProcessInstanceEntity condition);

    public void nextStep(String taskId, String operator,  Map<String,Object> processVar);

    public ProcessInstanceTaskEntity queryTaskByTaskId(String taskId);
    public ProcessInstanceEntity queryProcessInstanceByInstanceId(String processInstanceId);
    public ProcDefEntity queryProcDefByDefId(String procDefId);
    public ProcDeployEntity queryProcDeployByDeployId(String procDeployId);
    public ProcInfo queryProcInfo( ProcConstant type,String id);

    public List<ProcDefEntity> queryAllProcDefKeyList();
}
