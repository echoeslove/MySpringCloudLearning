package pers.benj.es;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ElasticArticleRepository extends ElasticsearchRepository<ElasticArticle,Long> {
}
