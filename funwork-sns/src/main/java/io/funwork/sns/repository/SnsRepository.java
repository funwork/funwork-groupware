package io.funwork.sns.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import io.funwork.sns.domain.Sns;

/**
 * Created by urosaria on 2015. 10. 23..
 */
public interface SnsRepository extends JpaRepository<Sns, Long> {
  List<Sns> findByEmailAndUseYn(String email, String useYn);
}