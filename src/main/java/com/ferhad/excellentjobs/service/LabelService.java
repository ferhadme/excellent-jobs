package com.ferhad.excellentjobs.service;

import com.ferhad.excellentjobs.model.Label;
import com.ferhad.excellentjobs.repository.LabelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LabelService {
    private final LabelRepository labelRepository;

    public Set<Label> getLabels(List<String> labels) {
        return labelRepository.findAllByNameIsIn(labels);
    }

    public Set<Label> initializeLabels(List<String> labels) {
        Map<String, Label> labelMap = labelRepository.findAll().stream()
                .collect(Collectors.toMap(Label::getName, Function.identity()));

        return labels.stream()
                .map(e -> {
                    if (labelMap.containsKey(e)) {
                        return labelMap.get(e);
                    }
                    Label label = new Label();
                    label.setName(e);
                    label.setCreatedAt(LocalDateTime.now(ZoneOffset.UTC));
                    return label;
                })
                .collect(Collectors.toSet());
    }
}
