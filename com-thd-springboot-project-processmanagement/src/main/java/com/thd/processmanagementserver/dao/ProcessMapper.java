package com.thd.processmanagementserver.dao;

import com.thd.processmanagementserver.entity.ProcDefEntity;
import com.thd.processmanagementserver.entity.ProcessInstanceEntity;
import com.thd.processmanagementserver.entity.ProcessInstanceTaskEntity;
import com.thd.processmanagementserver.entity.ProcessInstanceTaskHisEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * com.thd.processmanagementserver.dao.ProcessMapper
 *
 * @author: wanglei62
 * @DATE: 2021/1/26 17:27
 **/
@Mapper
@Repository
public interface ProcessMapper {
    public List<ProcDefEntity> queryProcessDef(ProcDefEntity condition);

    public List<ProcessInstanceTaskEntity> queryTask(ProcessInstanceTaskEntity condition);
    public List<ProcessInstanceTaskHisEntity> queryTaskHis(ProcessInstanceTaskHisEntity condition);
    public List<ProcessInstanceEntity> queryProcessInstance(ProcessInstanceEntity condition);
    public List<ProcDefEntity> queryAllProcDefKeyList();

}
