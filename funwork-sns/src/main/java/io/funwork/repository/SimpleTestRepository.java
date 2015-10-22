package io.funwork.repository;

import io.funwork.domain.SimpleTest;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 테스트용 제거예정.
 *
 * @author changhwaoh
 */
public interface SimpleTestRepository extends JpaRepository<SimpleTest, Long> {
}
