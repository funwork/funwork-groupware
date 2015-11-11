package io.funwork.sns.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import io.funwork.sns.domain.Sns;
import io.funwork.sns.repository.SnsRepository;

/**
 * Created by urosaria on 2015. 11. 11..
 */
@Service
public class SnsService {

  @Autowired
  private SnsRepository snsRepository;

  /**
   * sns 목록 (권한 테이블과 조인예정 임시)
   *
   * @param
   */
  @ResponseBody
  public List<Sns> listSns(){
    return snsRepository.findAll();
  }

  /**
   * sns 등록
   *
   * @param sns sns 등록 정보
   */
  @Transactional
  @ResponseBody
  public List<Sns> saveSns(Sns sns){

    sns = snsRepository.save(sns);
    sns.setUseYn("Y");
    List<Sns> snsList = snsRepository.findByEmailAndUseYn(sns.getEmail(), sns.getUseYn());

    return snsList;

  }

  /**
   * sns 상세보기
   *
   * @param number
   * @return
   */
  public Sns showSns(Long number){
    return snsRepository.findOne(number);
  }

  /**
   * sns 수정
   *
   * @param number
   * @param sns
   * @return
   */
  @Transactional
  @ResponseBody
  public Sns updateSns(Long number, Sns sns){

    Sns showSns = snsRepository.findOne(number);
    sns.setUseYn("Y");
    sns.setNumber(showSns.getNumber());
    sns = snsRepository.save(sns);

    return sns;

  }


}
