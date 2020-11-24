package pers.benj.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pers.benj.entity.MtCompanyEntity;

public interface MtCompanyExtendDao {

    @Query(value = "select * from mt_company where info ->> :attrName = :attrValue" , nativeQuery = true)
    List<MtCompanyEntity> findByAttribute(@Param("attrName") String attrName,@Param("attrValue")  String attrValue);
}
