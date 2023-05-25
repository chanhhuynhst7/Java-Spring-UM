package com.project.trainingteam.service.impl.reason;

import com.project.trainingteam.dto.reason.ReasonDangKyDuThiDto;
import com.project.trainingteam.dto.reason.ReasonHoanThiDto;
import com.project.trainingteam.entities.reason.ReasonDangKyDuThi;
import com.project.trainingteam.entities.reason.ReasonHoanThi;
import com.project.trainingteam.repo.inf.reason.ReasonHoanThiRepo;
import com.project.trainingteam.service.inf.reason.ReasonHoanThiService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReasonHoanThiServiceImpl implements ReasonHoanThiService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ReasonHoanThiRepo reasonHoanThiRepo;
    @Override
    public ReasonHoanThiDto createdReasonHoanThi(ReasonHoanThi req) throws Exception {
        boolean existsByReasonHoanThiName = reasonHoanThiRepo.existsByReasonHoanThiName(req.getReasonHoanThiName());
        if(existsByReasonHoanThiName){
            throw new Exception("Reason đã tồn tại");
        }else{
            ReasonHoanThi reasonHoanThi = new ReasonHoanThi();
            reasonHoanThi.setReasonHoanThiName(req.getReasonHoanThiName());
            reasonHoanThi.setDescReasonHoanThi(req.getDescReasonHoanThi());
            ReasonHoanThi savedReasonHoanThi = reasonHoanThiRepo.save(reasonHoanThi);
            return modelMapper.map(savedReasonHoanThi, ReasonHoanThiDto.class);
        }
    };

    @Override
    public ReasonHoanThiDto updatedReasonHoanThi(ReasonHoanThi req) throws Exception {
        ReasonHoanThi reasonHoanThi = reasonHoanThiRepo.findById(req.getId()).get();
        if(reasonHoanThi != null){
            reasonHoanThi.setReasonHoanThiName(req.getReasonHoanThiName());
            reasonHoanThi.setDescReasonHoanThi(req.getDescReasonHoanThi());
            ReasonHoanThi savedReasonHoanThi = reasonHoanThiRepo.save(reasonHoanThi);
            return modelMapper.map(savedReasonHoanThi,ReasonHoanThiDto.class);
        }else{
            throw new Exception("Không thể update Reason");
        }
    };

    @Override
    public Page<ReasonHoanThiDto> getAllReasonHoanThi(Pageable pageable) throws Exception {
        Page<ReasonHoanThi> reasonHoanThiPage = reasonHoanThiRepo.findAll(pageable);
        List<ReasonHoanThi> reasonHoanThiList = reasonHoanThiPage.getContent();
        List<ReasonHoanThiDto> reasonHoanThiDtoList = reasonHoanThiList.stream().map((result) -> modelMapper.map(result,ReasonHoanThiDto.class)).collect(Collectors.toList());
        if(reasonHoanThiDtoList != null){
            return new PageImpl<>(reasonHoanThiDtoList,pageable,reasonHoanThiPage.getTotalElements());
        }else{
            throw new Exception("Không tìm thấy List Reason");
        }
    };

    @Override
    public String deletedReasonHoanThi(Long id) {
        reasonHoanThiRepo.deleteById(id);
        return "DELETE thành công";
    }
}
