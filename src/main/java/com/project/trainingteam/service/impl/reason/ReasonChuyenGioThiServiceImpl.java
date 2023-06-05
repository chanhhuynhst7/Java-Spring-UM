package com.project.trainingteam.service.impl.reason;

import com.project.trainingteam.dto.reason.ReasonChuyenGioThiDto;
import com.project.trainingteam.entities.reason.ReasonChuyenGioThi;
import com.project.trainingteam.repo.inf.reason.ReasonChuyenGioThiRepo;
import com.project.trainingteam.service.inf.reason.ReasonChuyenGioThiService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReasonChuyenGioThiServiceImpl implements ReasonChuyenGioThiService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ReasonChuyenGioThiRepo reasonChuyenGioThiRepo;

    @Override
    public ReasonChuyenGioThiDto createdReasonChuyenGioThi(ReasonChuyenGioThi req) throws Exception {
        boolean existReasonChuyenGioThi = reasonChuyenGioThiRepo.existsByReasonChuyenGioThiName(req.getReasonChuyenGioThiName());
        if(existReasonChuyenGioThi){
            throw new Exception("Reason đã tồn tại");
        }else{
            ReasonChuyenGioThi reasonChuyenGioThi = new ReasonChuyenGioThi();
            reasonChuyenGioThi.setReasonChuyenGioThiName(req.getReasonChuyenGioThiName());
            reasonChuyenGioThi.setReasonChuyenGioThiDesc(req.getReasonChuyenGioThiDesc());
            ReasonChuyenGioThi savedreasonChuyenGioThi = reasonChuyenGioThiRepo.save(reasonChuyenGioThi);
            return modelMapper.map(savedreasonChuyenGioThi, ReasonChuyenGioThiDto.class);
        }
    };

    @Override
    public ReasonChuyenGioThiDto updatedReasonChuyenGioThi(ReasonChuyenGioThi req) throws Exception {
        ReasonChuyenGioThi reasonChuyenGioThi = reasonChuyenGioThiRepo.findById(req.getId()).get();
        if(reasonChuyenGioThi != null){
            reasonChuyenGioThi.setReasonChuyenGioThiName(req.getReasonChuyenGioThiName());
            reasonChuyenGioThi.setReasonChuyenGioThiDesc(req.getReasonChuyenGioThiDesc());
            ReasonChuyenGioThi savedreasonChuyenGioThi = reasonChuyenGioThiRepo.save(reasonChuyenGioThi);
            return modelMapper.map(savedreasonChuyenGioThi,ReasonChuyenGioThiDto.class);
        }else{
            throw new Exception("Không thể update Reason");
        }
    };

    @Override
    public Page<ReasonChuyenGioThiDto> getAllReasonChuyenGioThi(Pageable pageable) throws Exception {
        Page<ReasonChuyenGioThi> reasonChuyenGioThiPage = reasonChuyenGioThiRepo.findAll(pageable);
        List<ReasonChuyenGioThi> reasonChuyenGioThiList = reasonChuyenGioThiPage.getContent();
        List<ReasonChuyenGioThiDto> reasonChuyenGioThiDtoList = reasonChuyenGioThiList.stream().map((result) -> modelMapper.map(result,ReasonChuyenGioThiDto.class)).collect(Collectors.toList());
        if(reasonChuyenGioThiDtoList != null){
            return new PageImpl<>(reasonChuyenGioThiDtoList,pageable,reasonChuyenGioThiPage.getTotalElements());
        }else{
            throw new Exception("Không tìm thấy List Reason");
        }
    };

    @Override
    public String deletedReasonChuyenGioThi(Long id) {
        reasonChuyenGioThiRepo.deleteById(id);
        return "DELETE thành công";
    };
}
