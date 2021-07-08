package com.thd.springboot.project.knowledge.web;

import com.thd.springboot.framework.constants.CommonConstants;
import com.thd.springboot.framework.model.Message;
import com.thd.springboot.framework.utils.MyStringUtils;
import com.thd.springboot.framework.web.controller.BasicController;
import com.thd.springboot.project.knowledge.entity.Product;
import com.thd.springboot.project.knowledge.mapper.ProductDao;
import com.thd.springboot.project.knowledge.service.KnowledgeEsService;
import com.thd.springboot.project.knowledge.service.KnowledgeService;
import com.thd.springboot.project.knowledge.vo.ClassifyVO;
import com.thd.springboot.project.knowledge.vo.DocVO;
import com.thd.springboot.project.knowledge.vo.SearchVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.ResponseHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * com.thd.springboot.project.knowledge.web.KnowledgeController
 *
 * @author: wanglei62
 * @DATE: 2021/5/12 11:34
 **/
@Api(value="Knowledge 总接口" ,tags = "Knowledge 总接口.")
@Controller
@RequestMapping("/knowledge")
public class KnowledgeController  extends BasicController {
    @Autowired
    private KnowledgeEsService knowledgeEsService;
    @Autowired
    private KnowledgeService knowledgeService;
    @Autowired
    private ProductDao productDao;


    public static String decode(String s) {
        if (s == null)
            return null;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(s);
            return new String(b,"UTF-8");
        } catch (Exception e) {
            return null;
        }
    }


    // url : http://127.0.0.1:2348/knowledge/testAuth?sss=22222
    @RequestMapping(value="/testAuth",method = RequestMethod.GET)
    public void testAuth(HttpServletResponse res , HttpServletRequest req) throws Exception{
        System.out.println(req.getParameter("sss"));
        res.getWriter().write("aaabbcc");

        // 获取basic auth用户名密码
        String auth = req.getHeader("Authorization");
        if(MyStringUtils.isNotEmpty(auth)){

            System.out.println("auth :" + auth);
            auth = auth.substring(6, auth.length());
            System.out.println("auth encoded in base64 is " + decode(auth));


//            if(decode(auth).equals("thd:123456")){
//                response.getWriter().write("Login Success");
//                return ;
//            }
        }

        //弹出 basic 认证
        res.setHeader("WWW-Authenticate","Basic Realm=\"knowledge1\"");
        res.setStatus(401);
    }


    @RequestMapping(value="/test",method = RequestMethod.GET)
    @ResponseBody
    // url : http://127.0.0.1:2348/knowledge/test
    @ApiOperation(value="测试接口")
    @ResponseHeader(name="head1",description="response head conf")
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

    @ApiOperation(value="创建文档")
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


    @RequestMapping("/createClassifyIndex")
    @ResponseBody
    // url : http://127.0.0.1:2348/knowledge/createClassifyIndex
    public Message createClassifyIndex() {
        try {
            this.knowledgeEsService.createClassifyIndex();
            return Message.success(CommonConstants.STATUS_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return Message.error("-1",e.getMessage());
        }
    }



    @RequestMapping("/initClassifyData")
    @ResponseBody
    // url : http://127.0.0.1:2348/knowledge/initClassifyData
    public Message initClassifyData() {
        try {
            this.knowledgeEsService.initClassifyData();
            return Message.success(CommonConstants.STATUS_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return Message.error("-1",e.getMessage());
        }
    }

    @RequestMapping("/createClassify/{classify}")
    @ResponseBody
    // url : http://127.0.0.1:2348/knowledge/createClassify
    public Message createClassify(@PathVariable String classify) {
        try {
            this.knowledgeEsService.createClassify(classify);
            return Message.success(CommonConstants.STATUS_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return Message.error("-1",e.getMessage());
        }
    }




    @RequestMapping("/queryAllClassify")
    @ResponseBody
    // url : http://127.0.0.1:2348/knowledge/queryAllClassify
    public Message queryAllClassify() {
        try {
            List<ClassifyVO> l = this.knowledgeEsService.queryAllClassify();
            return Message.success(l);
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
    @RequestMapping("/reIndexThdTecFile")
    @ResponseBody
    // url : http://127.0.0.1:2348/knowledge/reIndexThdTecFile
    public Message reIndexThdTecFile() throws Exception{
        String folderPath = "D:\\devil13th\\Thirdteendevil\\Thirdteendevil\\resource\\tec";
        this.knowledgeEsService.reIndexThdTecFile(folderPath);
        return Message.success(CommonConstants.STATUS_SUCCESS);
    }
    @RequestMapping("/deleteIndexThdTecDoc")
    @ResponseBody
    // url : http://127.0.0.1:2348/knowledge/deleteIndexThdTecDoc
    public Message deleteIndexThdTecDoc() throws Exception{
        this.knowledgeEsService.deleteIndexThdTecDoc();
        return Message.success(CommonConstants.STATUS_SUCCESS);
    }



    @RequestMapping(value="/deleteDocIndex",method = RequestMethod.DELETE)
    @ResponseBody
    public Message deleteDocIndex() throws Exception{
        this.knowledgeEsService.deleteDocIndex();
        return Message.success(CommonConstants.STATUS_SUCCESS);
    }




}
