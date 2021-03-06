package com.search.service;

import com.search.dao.SolrMapper;
import com.search.entity.Items;
import com.search.entity.ResultSearch;

import org.apache.http.HttpStatus;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

@Service
public class SolrSerive {
    @Autowired
    private SolrMapper solrMapper;
    @Value("${solr.url}")
    private String url;
    public int addData(HttpServletRequest request) {
        try {
            delete(request);
            SolrClient client = new HttpSolrClient.Builder(request.getScheme()+"://"+request.getServerName()+url).build();
            List<Items> itemList = solrMapper.getItemList();
            for (Items items : itemList) {
                SolrInputDocument document = new SolrInputDocument();
                document.addField("id", items.getId());
                document.addField("item_title", items.getTitle());
                document.addField("item_sell_point", items.getSellPoint());
                document.addField("item_image", items.getImage());
                document.addField("item_price", items.getPrice());
                document.addField("item_category_name", items.getCategoryName());
                client.add(document);
            }
            client.commit();
            return HttpStatus.SC_OK;
        } catch (Exception e) {
            return HttpStatus.SC_EXPECTATION_FAILED;
        }
    }

    public ResultSearch search(HttpServletRequest request,String keyWord, int page, int rows) {
        SolrClient client = new HttpSolrClient.Builder(request.getScheme()+"://"+request.getServerName()+url).build();
        try {
            SolrQuery query = new SolrQuery();
            query.setQuery(keyWord);
            query.setStart((page - 1) * rows);
            query.setRows(rows);
            query.set("df", "item_title");
            query.setHighlight(true);
            query.addHighlightField("item_title");
            query.setHighlightSimplePre("<em style=\"color:red\">");
            query.setHighlightSimplePost("</em>");
            QueryResponse queryResponse = client.query(query);
            SolrDocumentList solrDocumentList = queryResponse.getResults();
            long numFound = solrDocumentList.getNumFound();
            ResultSearch resultSearch = new ResultSearch();
            resultSearch.setTotal(numFound);
            ArrayList<Items> arrayList = new ArrayList<>();
            Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
            for (SolrDocument solrDocument : solrDocumentList) {
                Items items = new Items();
                items.setId((String) solrDocument.get("id"));
                items.setCategoryName((String) solrDocument.get("item_category_name"));
                items.setImage((String) solrDocument.get("item_image"));
                items.setPrice((String) solrDocument.get("item_price"));
                items.setSellPoint((String) solrDocument.get("item_sell_point"));
                List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
                String itemTitle = "";
                if (list != null && list.size() > 0) {
                    itemTitle = list.get(0);
                } else {
                    itemTitle = (String) solrDocument.get("item_title");
                }
                items.setTitle(itemTitle);
                arrayList.add(items);
            }
            resultSearch.setItemList(arrayList);
            return resultSearch;
        } catch (Exception e) {
            return null;
        }
    }
    public void delete(HttpServletRequest request) throws SolrServerException, IOException {
        SolrClient client = new HttpSolrClient.Builder(request.getScheme()+"://"+request.getServerName()+url).build();
        client.deleteByQuery("*:*");
        client.commit();
    }
}
