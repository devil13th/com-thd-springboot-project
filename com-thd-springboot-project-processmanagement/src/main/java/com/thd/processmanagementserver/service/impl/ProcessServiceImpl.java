package com.thd.processmanagementserver.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.thd.processmanagementserver.constant.ProcConstant;
import com.thd.processmanagementserver.dao.ProcessMapper;
import com.thd.processmanagementserver.entity.*;
import com.thd.processmanagementserver.service.ProcessService;
import com.thd.processmanagementserver.utils.MyActivitiUtil;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * com.thd.processmanagementserver.service.impl.ProcessServiceImpl
 *
 * @author: wanglei62
 * @DATE: 2021/1/25 15:06
 **/
@Service
public class ProcessServiceImpl implements ProcessService {
    @Autowired
    private MyActivitiUtil myActivitiUtil;


    @Autowired
    private ProcessMapper processMapper;


    public PageInfo<ProcDefEntity> queryProcessDef(ProcDefEntity condition){
        PageHelper.startPage(condition.getCurrent(), condition.getPageSize()).setOrderBy(condition.getOrderBy());
        PageInfo<ProcDefEntity> pageInfo = new PageInfo<ProcDefEntity>(this.processMapper.queryProcessDef(condition));
        return pageInfo;

    };
    public PageInfo<ProcessInstanceTaskEntity> queryTaskPage(ProcessInstanceTaskEntity condition){
        PageHelper.startPage(condition.getCurrent(), condition.getPageSize()).setOrderBy(condition.getOrderBy());
        PageInfo<ProcessInstanceTaskEntity> pageInfo = new PageInfo<ProcessInstanceTaskEntity>(this.processMapper.queryTask(condition));
        return pageInfo;
    };
    public PageInfo<ProcessInstanceTaskHisEntity> queryTaskHisPage(ProcessInstanceTaskHisEntity condition){
        PageHelper.startPage(condition.getCurrent(), condition.getPageSize()).setOrderBy(condition.getOrderBy());
        PageInfo<ProcessInstanceTaskHisEntity> pageInfo = new PageInfo<ProcessInstanceTaskHisEntity>(this.processMapper.queryTaskHis(condition));
        return pageInfo;
    };

    public List<ProcessInstanceTaskHisEntity> queryTaskHisList(ProcessInstanceTaskHisEntity condition){
        List<ProcessInstanceTaskHisEntity> l = this.processMapper.queryTaskHis(condition);
        return l;
    };


    public PageInfo<ProcessInstanceEntity> queryProcessInstancePage(ProcessInstanceEntity condition){
        PageHelper.startPage(condition.getCurrent(), condition.getPageSize()).setOrderBy(condition.getOrderBy());
        PageInfo<ProcessInstanceEntity> pageInfo = new PageInfo<ProcessInstanceEntity>(this.processMapper.queryProcessInstance(condition));
        return pageInfo;

    };


    public void nextStep(String taskId, String operator, Map<String,Object> processVar){
        Task t = this.myActivitiUtil.getTaskService().createTaskQuery().taskId(taskId).singleResult();
        t.setAssignee(operator);
        this.myActivitiUtil.completeTask(taskId,processVar);
    };



    public ProcessInstanceTaskEntity queryTaskByTaskId(String taskId){
        Task task = this.myActivitiUtil.getTaskService().createTaskQuery().taskId(taskId).singleResult();
        ProcessInstanceTaskEntity entity = new ProcessInstanceTaskEntity();
        if(null != task){
            entity.setTaskName(task.getName());
            entity.setTaskStartTime(task.getCreateTime());
            entity.setTaskOwner(task.getOwner());
            entity.setTaskAssignee(task.getAssignee());
            entity.setTaskDefKey(task.getTaskDefinitionKey());
            entity.setTaskId(task.getId());
            entity.setExecutionId(task.getExecutionId());
            entity.setProcessInstanceId(task.getProcessInstanceId());

            return entity;
        }else{
            return null;
        }
    };

    public ProcessInstanceEntity queryProcessInstanceByInstanceId(String processInstanceId){
        ProcessInstance pi = this.myActivitiUtil.getRuntimeService().createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        if(null != pi) {
            ProcessInstanceEntity entity = new ProcessInstanceEntity();
            entity.setBusinessKey(pi.getBusinessKey());
            entity.setProcessInstanceId(pi.getProcessInstanceId());
            entity.setProcessInstanceStartTime(pi.getStartTime());
            entity.setProcDefId(pi.getProcessDefinitionId());
            return entity;
        }else{
            return null;
        }
    };

    public ProcDefEntity queryProcDefByDefId(String procDefId){
        ProcessDefinition procDef = this.myActivitiUtil.getRepositoryService().createProcessDefinitionQuery().processDefinitionId(procDefId).singleResult();
        if(null != procDef){
            ProcDefEntity entity = new ProcDefEntity();
            entity.setCategory(procDef.getCategory());
            entity.setDefKey(procDef.getKey());
            entity.setDefName(procDef.getName());
            entity.setRev(procDef.getVersion());
            entity.setDefVersion(String.valueOf(procDef.getVersion()));
            entity.setDeploymentId(procDef.getDeploymentId());
            entity.setProcDefId(procDef.getId());
            entity.setDeploymentId(procDef.getDeploymentId());
            return entity;
        }else{
            return null;
        }

    };


    public ProcDeployEntity queryProcDeployByDeployId(String procDeployId){
        Deployment deployment = this.myActivitiUtil.getRepositoryService().createDeploymentQuery().deploymentId(procDeployId).singleResult();
        if(null != deployment){
            ProcDeployEntity entity = new ProcDeployEntity();
            entity.setDeployId(deployment.getId());
            entity.setDeployTime(deployment.getDeploymentTime());
            return entity;
        }else{
            return null;
        }

    };





    public ProcInfo queryProcInfo( ProcConstant type,String id){
        ProcInfo procInfo = new ProcInfo();
        String selfId = id;
        if(ProcConstant.PROCINSTTASK.getLevel() >= type.getLevel()){
            if(ProcConstant.PROCINSTTASK.getLevel().compareTo(type.getLevel()) == 0){
                selfId = id;
            }else{
                selfId = procInfo.getTaskId();
            }
            Optional<ProcessInstanceTaskEntity> taskOptional = Optional.ofNullable(this.queryTaskByTaskId(selfId));
            ProcessInstanceTaskEntity task = taskOptional.orElseGet( () -> {
                return new ProcessInstanceTaskEntity();
            });
            BeanUtils.copyProperties(task,procInfo);

        }


        if(ProcConstant.PROCINST.getLevel() >= type.getLevel()){
            if(ProcConstant.PROCINST.getLevel().compareTo(type.getLevel()) == 0){
                selfId = id;
            }else{
                selfId = procInfo.getProcessInstanceId();
            }
            Optional<ProcessInstanceEntity> piOptional = Optional.ofNullable(this.queryProcessInstanceByInstanceId(selfId));
            ProcessInstanceEntity pi = piOptional.orElseGet( () -> {
                return new ProcessInstanceEntity();
            });

            BeanUtils.copyProperties(pi,procInfo);
//            procInfo.setProcessInstanceId(selfId);
        }


        if(ProcConstant.PROCDEF.getLevel() >= type.getLevel()){
            if(ProcConstant.PROCDEF.getLevel().compareTo(type.getLevel()) == 0){
                selfId = id;
            }else{
                selfId = procInfo.getProcDefId();
            }
            Optional<ProcDefEntity> pdefOptional = Optional.ofNullable(this.queryProcDefByDefId(selfId));
            ProcDefEntity pdef = pdefOptional.orElseGet( () -> {
                return new ProcDefEntity();
            });

            BeanUtils.copyProperties(pdef,procInfo);
//            procInfo.setProcDefId(selfId);
        }

        if(ProcConstant.PROCDEPL.getLevel() >= type.getLevel()){
            if(ProcConstant.PROCDEPL.getLevel().compareTo(type.getLevel()) == 0){
                selfId = id;
            }else{
                selfId = procInfo.getDeploymentId();
            }
            Optional<ProcDeployEntity> pdOptional = Optional.ofNullable(this.queryProcDeployByDeployId(selfId));
            ProcDeployEntity pd = pdOptional.orElseGet( () -> {
                return new ProcDeployEntity();
            });

            BeanUtils.copyProperties(pd,procInfo);
//            procInfo.setProcDefId(selfId);
        }

        return procInfo;


    };


    public List<ProcDefEntity> queryAllProcDefKeyList(){
        return this.processMapper.queryAllProcDefKeyList();
    };

}
