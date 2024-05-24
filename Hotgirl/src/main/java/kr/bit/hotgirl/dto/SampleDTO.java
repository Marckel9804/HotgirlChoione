package kr.bit.hotgirl.dto;

import kr.bit.hotgirl.entity.SampleEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

//DTO는 이제 원하는 데이터들만 주고 받을때 사용할때 주로 사용됩니다
@Setter @Getter
public class SampleDTO {
    private Long id;
    private String name;

    public SampleDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // 특정요소만 빌드 메소드로 불변 객체 생성하는법 -> 이후 수정 안될지도?
    @Builder
    public SampleDTO(SampleEntity sampleEntity) {
        this.id = sampleEntity.getId();
        this.name = sampleEntity.getName();
    }

    // 원래의 Entity나 다른 객체로 변환해서 리턴하는 메소드 예시
    public SampleEntity toSampleEntity(int age) {
        SampleEntity returnSampleEntity = new SampleEntity();

        returnSampleEntity.setId(id);
        returnSampleEntity.setName(name);
        returnSampleEntity.setAge(age);

        return returnSampleEntity;
    }
}
