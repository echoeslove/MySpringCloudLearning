package pers.benj.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pers.benj.entity.MtCompanyEntity;

@Repository
public interface MtCompanyDao extends JpaRepository<MtCompanyEntity, Long>, MtCompanyExtendDao {
}
