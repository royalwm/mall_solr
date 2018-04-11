package com.search.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.search.entity.Items;
import com.search.entity.ResultSearch;
import com.search.service.SolrSerive;

@Controller
@RequestMapping(value = "/solr")
public class SolrController {
    @Autowired
    private SolrSerive solrSerive;
    @Value("${solr.page.size}")
    private int pageSize;
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public int addIndex(HttpServletRequest request) {
        return solrSerive.addData(request);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String search(HttpServletRequest request,String keyword,@RequestParam(defaultValue="1") Integer page,Model model) {
        ResultSearch search = solrSerive.search(request,keyword, page, pageSize);
        if (search.getItemList().size()>0) {
            model.addAttribute("query",keyword);
            model.addAttribute("page",page);
            search.setPageSize(pageSize);
            model.addAttribute("totalPages",search.getPage());
            model.addAttribute("recourdCount",search.getTotal());
		}else {
			 ResultSearch searchAll = solrSerive.search(request,"*" ,page, pageSize);
			model.addAttribute("searchAll",searchAll.getItemList());
	        model.addAttribute("query",keyword);
	        model.addAttribute("page",page);
	        searchAll.setPageSize(pageSize);
	        model.addAttribute("totalPages",searchAll.getPage());
	        model.addAttribute("recourdCount",searchAll.getTotal());
	        model.addAttribute("searchAll",searchAll.getItemList());
		}
        model.addAttribute("itemList",search.getItemList());
        return "index";
    }
}
