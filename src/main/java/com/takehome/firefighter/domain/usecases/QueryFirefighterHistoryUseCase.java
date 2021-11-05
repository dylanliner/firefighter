package com.takehome.firefighter.domain.usecases;

import com.takehome.firefighter.domain.persistence.FirefighterHistoryRepository;
import com.takehome.firefighter.domain.model.FirefighterHistory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class QueryFirefighterHistoryUseCase {

    private final FirefighterHistoryRepository firefighterHistoryRepository;

    public QueryFirefighterHistoryUseCase(FirefighterHistoryRepository firefighterHistoryRepository) {
        this.firefighterHistoryRepository = firefighterHistoryRepository;
    }


    @Transactional
    public List<FirefighterHistory> findAllFirefighterHistory() {
        return firefighterHistoryRepository.findAllFirefighterHistory();
    }
}
