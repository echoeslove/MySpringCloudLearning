package pers.benj.es;

import java.util.List;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.google.common.collect.Lists;

@RestController
@RequestMapping("/service-es/")
public class ElasticController {

    @Autowired
    private ElasticArticleRepository elasticArticleRepository;

    @GetMapping("/find/content")
    public List<ElasticArticle> findByContent(@RequestParam("content") String content) {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("content", content));
        return Lists.newArrayList(elasticArticleRepository.search(queryBuilder));
    }

    @PostMapping("/save")
    public void saveArticle(@RequestBody ElasticArticle elasticArticle) {
        elasticArticleRepository.save(elasticArticle);
    }
}
