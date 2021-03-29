package com.thd.processmanagementserver.web;


import com.github.pagehelper.PageInfo;
import com.thd.processmanagementserver.constant.ProcConstant;
import com.thd.processmanagementserver.entity.*;
import com.thd.processmanagementserver.service.ProcessService;
import com.thd.processmanagementserver.utils.MyActivitiUtil;
import com.thd.processmanagementserver.vo.StartProcessVo;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * com.thd.processmanagementserver.web.Controller
 *
 * @author: wanglei62
 * @DATE: 2021/1/25 14:40
 **/
@Controller
// url : http://127.0.0.1:2345/test
public class ProcessesController {


    @Autowired
    public ProcessEngine processEngine;
    @Autowired
    public RuntimeService runtimeService;
    @Autowired
    public RepositoryService repositoryService;
    @Autowired
    public TaskService taskService;
    @Autowired
    public ManagementService managementService;
    @Autowired
    public IdentityService identityService;
    @Autowired
    public HistoryService historyService;
    @Autowired
    public FormService formService;
    @Autowired
    public MyActivitiUtil myActivitiUtil;
    @Autowired
    private ProcessService processService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 测试
     */
    @RequestMapping("/index")
    @ResponseBody
    public String index(){
        logger.info("index");
        System.out.println("http://127.0.0.1:2345/actuator");
        System.out.println(runtimeService);
        return "http://127.0.0.1:2345/actuator";
    }


    /** ======================================   流程部署相关  ====================================== **/

    /**
     * 部署流程
     * @param file bpmn流程定义文件
     */
    @RequestMapping(value="/deploy",method = RequestMethod.POST)
    @ResponseBody
    // url : http://127.0.0.1:2345/deploy
    public ResponseEntity deploy(@RequestParam("file") MultipartFile file) throws Exception{
        logger.info("deploy");
        logger.info("================ deploy [" + file.getOriginalFilename()	+ "] Start ===================");
        Deployment deployment = this.processEngine.getRepositoryService().createDeployment().addInputStream(file.getOriginalFilename(),file.getInputStream()).deploy();
        logger.info("---- Deploy ID：" + deployment.getId());
        logger.info("================ deploy [" + file.getOriginalFilename()	+ "] End ===================");
        Map<String,String> result = new HashMap<String,String>();
        result.put("deployId" , deployment.getId());
        result.put("name",deployment.getName());
        result.put("key",deployment.getKey());
        result.put("category",deployment.getCategory());
        return ResponseEntity.ok(result);
    }




    /**
     * 删除部署
     * @param deploymentId 部署ID
     *
     */
    @RequestMapping(value="/deleteDeploy/{deploymentId}",method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity deleteDeploy(@PathVariable String deploymentId) {
        logger.info("deleteDeploy");
        this.myActivitiUtil.deleteDeploy(deploymentId);
        return ResponseEntity.ok("SUCCESS");
    }


    /** ======================================   查询相关  ====================================== **/

    /**
     * 查询流程定义
     */
    @RequestMapping(value="/queryProcessDef",method = RequestMethod.GET)
    @ResponseBody
    // url : http://127.0.0.1:2345/queryProcessDef
    public ResponseEntity queryProcessDef(ProcDefEntity condition) throws Exception{
        logger.info("queryProcessDef");
        PageInfo<ProcDefEntity> result = this.processService.queryProcessDef(condition);
        return ResponseEntity.ok(result);
    }


    /**
     * 查询流程实例
     */
    @RequestMapping(value="/queryProcessInstancePage",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity queryProcessInstancePage(ProcessInstanceEntity condition){
        logger.info("queryProcessInstancePage");
        PageInfo<ProcessInstanceEntity> result = this.processService.queryProcessInstancePage(condition);
        return ResponseEntity.ok(result);
    };

    /**
     * 查询待办
     */
    @RequestMapping(value="/queryTaskPage",method = RequestMethod.GET)
    @ResponseBody
    // url : http://127.0.0.1:2345/queryTaskPage
    public ResponseEntity queryTaskPage(ProcessInstanceTaskEntity condition) throws Exception{
        logger.info("queryTaskPage");
        PageInfo<ProcessInstanceTaskEntity> result = this.processService.queryTaskPage(condition);
        return ResponseEntity.ok(result);
    }

    /**
     * 查询历史步骤
     */
    @RequestMapping(value="/queryTaskHisPage",method = RequestMethod.GET)
    @ResponseBody
    // url : http://127.0.0.1:2345/queryTaskHisPage
    public ResponseEntity queryTaskHisPage(ProcessInstanceTaskHisEntity condition) throws Exception{
        logger.info("queryTaskHisPage");
        PageInfo<ProcessInstanceTaskHisEntity> result = this.processService.queryTaskHisPage(condition);
        return ResponseEntity.ok(result);
    }

    /**
     * 查询历史步骤
     */
    @RequestMapping(value="/queryTaskHis/{processInstanceId}",method = RequestMethod.GET)
    @ResponseBody
    // url : http://127.0.0.1:2345/queryTaskHisList/{processInstanceId}
    public ResponseEntity queryTaskHisList(@PathVariable String processInstanceId) throws Exception{
        logger.info("queryTaskHis");
        ProcessInstanceTaskHisEntity condition = new ProcessInstanceTaskHisEntity();
        condition.setProcessInstanceId(processInstanceId);
        List<ProcessInstanceTaskHisEntity> result = this.processService.queryTaskHisList(condition);
        return ResponseEntity.ok(result);
    }


    /** ======================================   流程实例操作相关  ====================================== **/

    /**
     * 开启流程
     */
    @RequestMapping(value="/startProcess",method = RequestMethod.GET)
    @ResponseBody
    // url : http://127.0.0.1:2345/startProcess?businessKey=a01&processDefinedKey=SubProcess
    public ResponseEntity startProcess(@RequestParam String processDefinedKey,@RequestParam String businessKey) throws Exception{
        logger.info("startProcess");
        logger.info("================ startProcess [" + processDefinedKey	+ "] Start ===================");

        ProcessInstance pi = this.runtimeService.startProcessInstanceByKey(processDefinedKey,businessKey);
        logger.info("================ startProcess [" + processDefinedKey	+ "] End ===================");

        Map<String,String> result = new HashMap<String,String>();
        result.put("processInstanceId" , pi.getId());
        result.put("businessKey",pi.getBusinessKey());
        return ResponseEntity.ok(result);
    }



    /**
     * 开启流程 - 带有流程变量
     */
    @RequestMapping(value="/startProcessByKey",method = RequestMethod.POST)
    @ResponseBody
    // url : http://127.0.0.1:2345/startProcess
    public ResponseEntity startProcessByDefKey(@RequestBody ProcessInstanceEntity processInstanceEntity) throws Exception{
        logger.info("startProcess width variable");
        logger.info("================ startProcess [" + processInstanceEntity.getDefKey()	+ "] Start ===================");

        ProcessInstance pi = this.runtimeService.startProcessInstanceByKey(processInstanceEntity.getDefKey(),processInstanceEntity.getBusinessKey(),processInstanceEntity.getProcessVariable());
        logger.info("================ startProcess [" + processInstanceEntity.getDefKey()	+ "] End ===================");

        Map<String,String> result = new HashMap<String,String>();
        result.put("processInstanceId" , pi.getId());
        result.put("businessKey",pi.getBusinessKey());
        return ResponseEntity.ok(result);
    }

    /**
     * 开启流程 - 带有流程变量
     */
    @RequestMapping(value="/startProcessByDefId",method = RequestMethod.POST)
    @ResponseBody
    // url : http://127.0.0.1:2345/startProcessByDefId
    public ResponseEntity startProcessByDefId(@RequestBody ProcessInstanceEntity processInstanceEntity) throws Exception{
        logger.info("startProcess width variable");
        logger.info("================ startProcess [" + processInstanceEntity.getProcDefId()	+ "] Start ===================");

        ProcessInstance pi = this.runtimeService.startProcessInstanceById(processInstanceEntity.getProcDefId(),processInstanceEntity.getBusinessKey(),processInstanceEntity.getProcessVariable());
        logger.info("================ startProcess [" + processInstanceEntity.getProcDefId()	+ "] End ===================");

        Map<String,String> result = new HashMap<String,String>();
        result.put("processInstanceId" , pi.getId());
        result.put("businessKey",pi.getBusinessKey());
        return ResponseEntity.ok(result);
    }


    /**
     * 开启流程(带有普通流程变量)
     */
    @RequestMapping(value="/startProcessWithProcessVar",method = RequestMethod.POST)
    @ResponseBody
    // url : http://127.0.0.1:2345/startProcessWithProcessVar
    public ResponseEntity startProcessWithProcessVar(@RequestBody StartProcessVo vo) throws Exception{
        logger.info("startProcessWithProcessVar");
        logger.info("================ startProcess [" + vo.getBusinessKey()	+ "] Start ===================");
        ProcessInstance pi = this.runtimeService.startProcessInstanceByKey(vo.getProcessKey(),vo.getBusinessKey(),vo.getProcessVar());
        logger.info("================ startProcess [" + vo.getBusinessKey()	+ "] End ===================");
        return ResponseEntity.ok("SUCCESS");
    }

    @RequestMapping(value="/nextStep",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity nextStep(@RequestBody ProcessInstanceTaskEntity vo){
        this.processService.nextStep(vo.getTaskId(),vo.getTaskAssignee(),vo.getProcessVariable());
        return ResponseEntity.ok("SUCCESS");
    }
    /**
     * 挂起某个流程实例 Method Description : 挂起某个流程实例
     * @param processInstanceId
     *            流程实例ID
     */
    public ResponseEntity suspendsProcessInstance(String processInstanceId) {
        logger.info("suspendsProcessInstance");
        this.myActivitiUtil.suspendsProcessInstance(processInstanceId);
        return ResponseEntity.ok("SUCCESS");
    }

    /**
     * 激活某个已挂起的流程实例 Method Description : 激活某个已挂起的流程实例
     * @param processInstanceId
     *            流程实例ID
     */
    public ResponseEntity activateProcessInstance(String processInstanceId) {
        logger.info("cancelProcessInstance");
        this.myActivitiUtil.activateProcessInstance(processInstanceId);
        return ResponseEntity.ok("SUCCESS");
    }


    /**
     * 删除某个流程实例
     * Method Description : 删除某个流程实例
     * @param processInstanceId 流程实例ID
     */
    @RequestMapping(value="/cancelProcessInstance")
    @ResponseBody
    public ResponseEntity cancelProcessInstance(String processInstanceId) {
        logger.info("cancelProcessInstance");
        this.myActivitiUtil.cancelProcessInstance(processInstanceId,"delete by system administrator");
        return ResponseEntity.ok("SUCCESS");
    }

    /** ======================================   人员操作相关  ====================================== **/


    /**
     *
     * Method Description : 设置某个task的代办人
     * @param taskId
     *            任务ID
     * @param userId
     *            人员ID
     */
    @RequestMapping(value="/setAssigneeOfTask")
    @ResponseBody
    public ResponseEntity setAssigneeOfTask(String taskId, String userId) {
        logger.info("setAssigneeOfTask");
        this.taskService.setAssignee(taskId,userId);
        return ResponseEntity.ok("SUCCESS");
    }


    /**
     *
     * Method Description : 设置某个task的owner
     * @param taskId
     *            任务ID
     * @param userId
     *            人员ID
     */
    @RequestMapping(value="/setOwnerOfTask")
    @ResponseBody
    public ResponseEntity setOwnerOfTask(String taskId, String userId) {
        logger.info("setOwnerOfTask");
        this.taskService.setOwner(taskId,userId);
        return ResponseEntity.ok("SUCCESS");
    }


    /**
     *
     * Method Description : 委托任务给某人
     * @param taskId
     *            任务ID
     * @param userId
     *            人员ID
     */
    @RequestMapping(value="/delegateTask")
    @ResponseBody
    public ResponseEntity delegateTask(String taskId, String userId) {
        logger.info("delegateTask");
        this.taskService.delegateTask(taskId,userId);
        return ResponseEntity.ok("SUCCESS");
    }


    /**
     *
     * Method Description : 为某个Task添加候选人
     * @param taskId
     *            任务ID
     * @param userId
     *            人员ID
     */
    @RequestMapping(value="/addCandidateUserToTask")
    @ResponseBody
    public ResponseEntity addCandidateUserToTask(String taskId, String userId) {
        logger.info("addCandidateUserToTask");
        this.myActivitiUtil.addCandidateUserToTask(taskId, userId);
        return ResponseEntity.ok("SUCCESS");
    }

    /**
     *
     * Method Description : 为某个Task删除加候选人
     *
     * @param taskId
     *            任务ID
     * @param userId
     *            人员ID
     */
    @RequestMapping(value="/deleteCandidateUserFromTask")
    @ResponseBody
    public ResponseEntity deleteCandidateUserFromTask(String taskId, String userId) {
        logger.info("addCandidateUserToTask");
        this.myActivitiUtil.deleteCandidateUserFromTask(taskId, userId);
        return ResponseEntity.ok("SUCCESS");
    }


    /** ======================================   完成任务相关  ====================================== **/

    /**
     * 待办人完成某个任务
     * @param taskId
     * @return
     */
    @RequestMapping(value="/completeTask")
    @ResponseBody
    public ResponseEntity completeTask(String taskId){
        logger.info("completeTask");
        this.myActivitiUtil.completeTask(taskId, null);
        return ResponseEntity.ok("SUCCESS");
    }

    /**
     * 委派人完成任务
     * @param taskId
     * @return
     */
    @RequestMapping(value="/resolveTask")
    @ResponseBody
    public ResponseEntity resolveTask(String taskId){
        logger.info("resolveTask");
        this.taskService.resolveTask(taskId, null);
        return ResponseEntity.ok("SUCCESS");
    }
    /**
     * 某人签收某个任务
     * @param taskId
     * @param userId
     * @return
     */
    @RequestMapping(value="/claimTask")
    @ResponseBody
    public ResponseEntity claimTask(String taskId,String userId){
        logger.info("claimTask");
        this.myActivitiUtil.claimTask(taskId, userId);
        return ResponseEntity.ok("SUCCESS");
    }

    /**
     * 某人签收并完成某个工作
     * @param taskId
     * @param userId
     * @return
     */
    @RequestMapping(value="/claimAndComplateTask")
    @ResponseBody
    public ResponseEntity claimAndComplateTask(String taskId,String userId){
        logger.info("claimAndComplateTask");
        this.myActivitiUtil.claimAndComplateTask(taskId, userId,null);
        return ResponseEntity.ok("SUCCESS");
    }



    /** ======================================   流程变量相关  ====================================== **/

    /**
     * 设置流程变量 - execution global
     * @return
     */

    @RequestMapping(value="/setProcessVarGlobalByExecutionId",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity setProcessVarGlobalByExecutionId(@RequestBody ProcessInstanceTaskEntity taskEntity){
        logger.info("setProcessVarGlobalByExecutionId");
        this.runtimeService.setVariables(taskEntity.getExecutionId(),taskEntity.getProcessVariable());
        return ResponseEntity.ok("SUCCESS");
    }
    /**
     * 设置流程变量 - execution local
     * @return
     */
    @RequestMapping(value="/setProcessVarLocalByExecutionId",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity setProcessVarLocalByExecutionId(@RequestBody ProcessInstanceTaskEntity taskEntity){
        logger.info("setProcessVarGlobalByExecutionId");
        this.runtimeService.setVariablesLocal(taskEntity.getExecutionId(),taskEntity.getProcessVariable());
        return ResponseEntity.ok("SUCCESS");
    }
    /**
     * 设置流程变量 - task global
     * @return
     */
    @RequestMapping(value="/setProcessVarGlobalByTaskId",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity setProcessVarGlobalByTaskId(@RequestBody ProcessInstanceTaskEntity taskEntity){
        logger.info("setProcessVarGlobalByTaskId");
        this.taskService.setVariables(taskEntity.getExecutionId(),taskEntity.getProcessVariable());
        return ResponseEntity.ok("SUCCESS");
    }
    /**
     * 设置流程变量 - task local
     * @return
     */
    @RequestMapping(value="/setProcessVarLocalByTaskId",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity setProcessVarLocalByTaskId(@RequestBody ProcessInstanceTaskEntity taskEntity){
        logger.info("setProcessVarLocalByTaskId");
        this.taskService.setVariablesLocal(taskEntity.getExecutionId(),taskEntity.getProcessVariable());
        return ResponseEntity.ok("SUCCESS");
    }


    /**
     * 设置流程变量 - 流程全局
     * @param executionId
     * @return
     */
    @RequestMapping(value="/setProcessVarByExecutionId")
    @ResponseBody
    public ResponseEntity setProcessVarByExecutionId(String executionId,String processVarName,String processVarValue){
        logger.info("setProcessVarByExecutionId");
        this.runtimeService.setVariable(executionId,processVarName,processVarValue);
        return ResponseEntity.ok("SUCCESS");
    }

    /**
     * 设置流程变量 - 流程本地
     * @param executionId
     * @return
     */
    @RequestMapping(value="/setProcessVarByLocalExecutionId")
    @ResponseBody
    public ResponseEntity setProcessVarByLocalExecutionId(String executionId,String processVarName,String processVarValue){
        logger.info("setProcessVarByLocalExecutionId");
        this.runtimeService.setVariableLocal(executionId,processVarName,processVarValue);
        return ResponseEntity.ok("SUCCESS");
    }

    /**
     * 设置流程变量 - task
     * @param taskId
     * @return
     */
    @RequestMapping(value="/setProcessVarByTaskId")
    @ResponseBody
    public ResponseEntity setProcessVarByTaskId(String taskId,String processVarName,String processVarValue){
        logger.info("setProcessVarByTaskId");
        this.taskService.setVariable(taskId,processVarName,processVarValue);
        return ResponseEntity.ok("SUCCESS");
    }

    /**
     * 设置流程变量 - task本地
     * @param taskId
     * @return
     */
    @RequestMapping(value="/setProcessVarByLocalTaskId")
    @ResponseBody
    public ResponseEntity setProcessVarByLocalTaskId(String taskId,String processVarName,String processVarValue){
        logger.info("setProcessVarByLocalTaskId");
        this.taskService.setVariableLocal(taskId,processVarName,processVarValue);
        return ResponseEntity.ok("SUCCESS");
    }





    /**
     * 查看流程变量 - 流程全局
     * @param executionId
     * @return
     */
    @RequestMapping(value="/queryProcessVarByExecutionId")
    @ResponseBody
    public ResponseEntity queryProcessVarByExecutionId(String executionId){
        logger.info("queryProcessVarByExecutionId");
        Map<String,Object> processVar = this.runtimeService.getVariables(executionId);
        return ResponseEntity.ok(processVar);
    }

    /**
     * 查看流程变量 - 流程本地
     * @param executionId
     * @return
     */
    @RequestMapping(value="/queryProcessVarLocalByExecutionId")
    @ResponseBody
    public ResponseEntity queryProcessVarLocalByExecutionId(String executionId){
        logger.info("getProcessVarByLocalExecutionId");
        Map<String,Object> processVar = this.runtimeService.getVariablesLocal(executionId);
        return ResponseEntity.ok(processVar);
    }

    /**
     * 查看流程变量 - task
     * @param taskId
     * @return
     */
    @RequestMapping(value="/queryProcessVarByTaskId")
    @ResponseBody
    public ResponseEntity queryProcessVarByTaskId(String taskId){
        logger.info("queryProcessVarByTaskId");
        Map<String,Object> processVar = this.taskService.getVariables(taskId);
        return ResponseEntity.ok(processVar);
    }

    /**
     * 查看流程变量 - task本地
     * @param taskId
     * @return
     */
    @RequestMapping(value="/queryProcessVarLocalByTaskId")
    @ResponseBody
    public ResponseEntity queryProcessVarByLocalTaskId(String taskId){
        logger.info("queryProcessVarByLocalTaskId");
        Map<String,Object> processVar = this.taskService.getVariablesLocal(taskId);
        return ResponseEntity.ok(processVar);
    }






//    @RequestMapping(value="/startProcess",method = RequestMethod.POST)
//    @ResponseBody
//    public ResponseEntity startProcess(@RequestBody ) throws Exception{
//        logger.info("startProcess");
//
//        this.processEngine.getRuntimeService().startProcessInstanceByKey();
//        return ResponseEntity.ok(result);
//    }


    @RequestMapping(value="/queryTaskByTaskId/{taskId}")
    @ResponseBody
    public ResponseEntity queryTaskByTaskId(@PathVariable String taskId){
        Task task = this.myActivitiUtil.getTaskService().createTaskQuery().taskId(taskId).singleResult();

        ProcessInstance pi = this.myActivitiUtil.getRuntimeService().createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();

        ProcessInstanceTaskEntity myTask = new ProcessInstanceTaskEntity();
        myTask.setTaskId(taskId);
        myTask.setExecutionId(task.getExecutionId());
        myTask.setTaskAssignee(task.getAssignee());
        myTask.setTaskDefKey(task.getTaskDefinitionKey());
        myTask.setTaskFormKey(task.getFormKey());
        myTask.setTaskOwner(task.getOwner());
        myTask.setTaskName(task.getName());
        myTask.setTaskStartTime(task.getCreateTime());
        myTask.setBusinessKey(pi.getBusinessKey());
        myTask.setProcessInstanceStartTime(pi.getStartTime());
        myTask.setProcessInstanceId(pi.getId());
        myTask.setDefKey(pi.getProcessDefinitionKey());
        myTask.setDefName(pi.getProcessDefinitionName());
        myTask.setDeploymentId(pi.getDeploymentId());
        myTask.setProcDefId(pi.getProcessDefinitionId());

        Map<String,Object> taskVar = this.myActivitiUtil.getTaskService().getVariablesLocal(taskId);
        myTask.setTaskVar(taskVar);

        Map<String,Object> executionVar = this.myActivitiUtil.getRuntimeService().getVariablesLocal(myTask.getExecutionId());
        myTask.setExecutionVar(executionVar);

        Map<String,Object> processVar = this.myActivitiUtil.getRuntimeService().getVariablesLocal(pi.getProcessInstanceId());
        myTask.setProcessInstanceVar(processVar);

        return ResponseEntity.ok(myTask);
    }


    @RequestMapping(value="/queryTaskHisByTaskHisId/{taskHisId}")
    @ResponseBody
    public ResponseEntity queryTaskHisByTaskHisId(@PathVariable String taskHisId){
        HistoricTaskInstance task = this.myActivitiUtil.getHistoryService().createHistoricTaskInstanceQuery().taskId(taskHisId).singleResult();

        HistoricProcessInstance pi = this.myActivitiUtil.getHistoryService().createHistoricProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();

        ProcessInstanceTaskHisEntity myTask = new ProcessInstanceTaskHisEntity();
        myTask.setTaskId(taskHisId);
        myTask.setExecutionId(task.getExecutionId());
        myTask.setTaskAssignee(task.getAssignee());
        myTask.setTaskDefKey(task.getTaskDefinitionKey());
        myTask.setTaskFormKey(task.getFormKey());
        myTask.setTaskOwner(task.getOwner());
        myTask.setTaskName(task.getName());
        myTask.setTaskStartTime(task.getCreateTime());
        myTask.setBusinessKey(pi.getBusinessKey());
        myTask.setProcessInstanceStartTime(pi.getStartTime());
        myTask.setProcessInstanceId(pi.getId());
        myTask.setDefKey(pi.getProcessDefinitionKey());
        myTask.setDefName(pi.getProcessDefinitionName());
        myTask.setDeploymentId(pi.getDeploymentId());
        myTask.setProcDefId(pi.getProcessDefinitionId());
        myTask.setTaskHisEndTime(task.getEndTime());
        myTask.setProcessInstanceEndTime(pi.getEndTime());


        myTask.setTaskVar(task.getTaskLocalVariables());



        myTask.setProcessInstanceVar(task.getProcessVariables());


        return ResponseEntity.ok(myTask);
    }

    @RequestMapping("/queryProcInfo/{type}/{id}")
    @ResponseBody
    public ResponseEntity queryProcInfo(@PathVariable String type , @PathVariable String id){
        ProcConstant tp = ProcConstant.valueOf(type);
        ProcInfo procInfo = this.processService.queryProcInfo(tp,id);
        return ResponseEntity.ok(procInfo);
    }

    @RequestMapping("/queryAllProcDefKeyList")
    @ResponseBody
    public ResponseEntity queryAllProcDefKeyList(){
        List<ProcDefEntity> l = this.processService.queryAllProcDefKeyList();
        return ResponseEntity.ok(l);
    }
}
