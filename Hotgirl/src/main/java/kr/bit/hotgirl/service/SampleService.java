package kr.bit.hotgirl.service;

import kr.bit.hotgirl.entity.SampleEntity;
import kr.bit.hotgirl.repository.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SampleService {
    @Autowired
    private SampleRepository sampleRepository;

    public List<SampleEntity> getAllSamples() {
        return sampleRepository.findAll();
    }

    public Optional<SampleEntity> getSampleById(Long id) {
        return sampleRepository.findById(id);
    }

    public SampleEntity createSample(SampleEntity sample) {
        return sampleRepository.save(sample);
    }

    public void deleteSampleById(Long id) {
        sampleRepository.deleteById(id);
    }

    public void updateSampleName(Long userId, String name) {sampleRepository.updateSampleNameById(userId, name); }
}
