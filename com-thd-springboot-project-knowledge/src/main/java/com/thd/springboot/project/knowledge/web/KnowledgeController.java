package com.thd.springboot.project.knowledge.web;

import com.thd.springboot.framework.constants.CommonConstants;
import com.thd.springboot.framework.model.Message;
import com.thd.springboot.framework.web.controller.BasicController;
import com.thd.springboot.project.knowledge.service.KnowledgeEsService;
import com.thd.springboot.project.knowledge.service.KnowledgeInfoService;
import com.thd.springboot.project.knowledge.service.KnowledgeService;
import com.thd.springboot.project.knowledge.vo.DocVO;
import com.thd.springboot.project.knowledge.vo.SearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * com.thd.springboot.project.knowledge.web.KnowledgeController
 *
 * @author: wanglei62
 * @DATE: 2021/5/12 11:34
 **/
@Controller
@RequestMapping("/knowledge")
public class KnowledgeController  extends BasicController {
    @Autowired
    private KnowledgeEsService knowledgeEsService;
    @Autowired
    private KnowledgeService knowledgeService;
    @RequestMapping("/test")
    @ResponseBody
    // url : http://127.0.0.1:2348/knowledge/test
    public Message test(){
        return Message.success("SUCCESS...");
    }

    @RequestMapping("/createDoc")
    @ResponseBody
    // url : http://127.0.0.1:2348/knowledge/createDoc
    public Message createDoc(@RequestBody DocVO docVo) throws Exception{
        this.knowledgeService.createDoc(docVo);

        return Message.success("SUCCESS...");
    }



    @RequestMapping("/createDocIndex")
    @ResponseBody
    // url : http://127.0.0.1:2348/knowledge/createDocIndex
    public Message createDocIndex() {
        try {
            this.knowledgeEsService.createDocIndex();
            return Message.success(CommonConstants.STATUS_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return Message.error("-1",e.getMessage());
        }

    }

    @RequestMapping(value="/search",method = RequestMethod.POST)
    @ResponseBody
    // url : http://127.0.0.1:2348/knowledge/search
    public Message search(@RequestBody SearchVO vo) throws Exception{
        List<DocVO> r = this.knowledgeEsService.search(vo);
        return Message.success(r);
    }

    @RequestMapping("/indexThdTecFile")
    @ResponseBody
    // url : http://127.0.0.1:2348/knowledge/indexThdTecFile
    public Message indexThdTecFile() throws Exception{
        String folderPath = "D:\\devil13th\\Thirdteendevil\\Thirdteendevil\\resource\\tec";
        this.knowledgeEsService.indexThdTecFile(folderPath);
        return Message.success(CommonConstants.STATUS_SUCCESS);
    }
}
