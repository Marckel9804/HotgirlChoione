package kr.bit.hotgirl.repository;

import kr.bit.hotgirl.entity.SampleEntity;
import org.hibernate.annotations.SQLSelect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

// 기본적으로 findAll, findById 등의 기본적인 쿼리 함수가 있지만
// Update나 세밀하게 하고 싶으면 Mybatis 사용시 mapper Interface 클래스 처럼 추가하면 됩니다.
public interface SampleRepository extends JpaRepository<SampleEntity, Long> {
    @Transactional
    @Modifying
    @Query("UPDATE SampleEntity s SET s.name = :name WHERE s.id = :sampleId")
    void updateSampleNameById(Long sampleId, String name);
}
