package pers.benj.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import pers.benj.service.CompanyService;

@Component
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Map> queryByAttribute(String attrName, String attrValue) {
        Query query = new Query(Criteria.where(attrName).is(attrValue));
        return mongoTemplate.find(query, Map.class, "mt_company");
    }
}
