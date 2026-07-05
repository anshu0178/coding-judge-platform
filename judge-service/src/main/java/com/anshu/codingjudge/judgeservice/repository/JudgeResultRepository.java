
package com.anshu.codingjudge.judgeservice.repository;

import com.anshu.codingjudge.judgeservice.entity.JudgeResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JudgeResultRepository
        extends JpaRepository<JudgeResult, Long> {
}