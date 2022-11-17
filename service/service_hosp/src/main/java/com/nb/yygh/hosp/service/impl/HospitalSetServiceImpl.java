package com.nb.yygh.hosp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nb.yygh.hosp.mapper.HospitalSetMapper;
import com.nb.yygh.hosp.service.HospitalSetService;
import com.nb.yygh.model.hosp.HospitalSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yunfu.ye
 * @date 2022/11/17 14:49
 * @desciption:
 */
@Service
public class HospitalSetServiceImpl extends ServiceImpl<HospitalSetMapper, HospitalSet>implements HospitalSetService {

    @Autowired
    private HospitalSetMapper hospitalSetMapper;

}
