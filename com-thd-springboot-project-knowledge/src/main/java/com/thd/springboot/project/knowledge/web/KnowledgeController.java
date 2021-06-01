package com.thd.springboot.project.knowledge.web;

import com.thd.springboot.framework.constants.CommonConstants;
import com.thd.springboot.framework.model.Message;
import com.thd.springboot.framework.utils.MyStringUtils;
import com.thd.springboot.framework.web.controller.BasicController;
import com.thd.springboot.project.knowledge.entity.Product;
import com.thd.springboot.project.knowledge.mapper.ProductDao;
import com.thd.springboot.project.knowledge.service.KnowledgeEsService;
import com.thd.springboot.project.knowledge.service.KnowledgeService;
import com.thd.springboot.project.knowledge.vo.DocVO;
import com.thd.springboot.project.knowledge.vo.SearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    @Autowired
    private ProductDao productDao;
    @RequestMapping("/test")
    @ResponseBody
    // url : http://127.0.0.1:2348/knowledge/test
    public Message test(){
//        Product p = new Product();
//        p.setCategory("aaa");
//        p.setCreateTime(new Date());
//        p.setImages("asdfasdf img");
//        p.setPrice(5.2);
//        p.setTitle("测试数据");
//        productDao.save(p);


        Product p = productDao.findById("TO9GwHkBcxFugCnOCMZM").get();
        p.setTitle("update title");
        this.productDao.save(p);
        return Message.success(p);
    }

    @RequestMapping("/createDoc")
    @ResponseBody
    // url : http://127.0.0.1:2348/knowledge/createDoc
    public Message createDoc(@RequestBody DocVO docVo) throws Exception{
        if(MyStringUtils.isNotEmpty(docVo.getId())){
            this.knowledgeService.modifyDoc(docVo);
        }else {
            this.knowledgeService.createDoc(docVo);
        }
        return Message.success(CommonConstants.STATUS_SUCCESS);
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

    @RequestMapping(value="/loadDocById/{id}",method = RequestMethod.GET)
    @ResponseBody
    // url : http://127.0.0.1:2348/knowledge/loadDocById/xxxx
    public Message loadDocById(@PathVariable String id) throws Exception{
        DocVO r = this.knowledgeEsService.loadDocById(id);
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


    @RequestMapping(value="/deleteDocIndex",method = RequestMethod.DELETE)
    @ResponseBody
    public Message deleteDocIndex() throws Exception{
        this.knowledgeEsService.deleteDocIndex();
        return Message.success(CommonConstants.STATUS_SUCCESS);
    }
}
