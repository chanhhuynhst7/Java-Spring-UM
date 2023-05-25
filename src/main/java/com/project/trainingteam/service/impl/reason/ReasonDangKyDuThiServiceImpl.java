package com.project.trainingteam.service.impl.reason;

import com.project.trainingteam.dto.reason.ReasonChuyenGioThiDto;
import com.project.trainingteam.dto.reason.ReasonDangKyDuThiDto;
import com.project.trainingteam.entities.reason.ReasonChuyenGioThi;
import com.project.trainingteam.entities.reason.ReasonDangKyDuThi;
import com.project.trainingteam.repo.inf.reason.ReasonDangKyDuThiRepo;
import com.project.trainingteam.service.inf.reason.ReasonDangKyDuThiService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReasonDangKyDuThiServiceImpl implements ReasonDangKyDuThiService {

    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    private ReasonDangKyDuThiRepo reasonDangKyDuThiRepo;

    @Override
    public ReasonDangKyDuThiDto createdReasonDangKyDuThi(ReasonDangKyDuThi req) throws Exception {
        boolean existReasonDangKyDuThi = reasonDangKyDuThiRepo.existsByReasonDangKyDuThiName(req.getReasonDangKyDuThiName());
        if(existReasonDangKyDuThi){
            throw new Exception("Reason đã tồn tại");
        }else{
            ReasonDangKyDuThi reasonDangKyDuThi = new ReasonDangKyDuThi();
            reasonDangKyDuThi.setReasonDangKyDuThiName(req.getReasonDangKyDuThiName());
            reasonDangKyDuThi.setDescReasonDangKyDuThi(req.getDescReasonDangKyDuThi());
            ReasonDangKyDuThi savedReasonDangKyDuThi = reasonDangKyDuThiRepo.save(reasonDangKyDuThi);
            return modelMapper.map(savedReasonDangKyDuThi, ReasonDangKyDuThiDto.class);
        }
    };

    @Override
    public ReasonDangKyDuThiDto updatedReasonDangKyDuThi(ReasonDangKyDuThi req) throws Exception {
        ReasonDangKyDuThi reasonDangKyDuThi = reasonDangKyDuThiRepo.findById(req.getId()).get();
        if(reasonDangKyDuThi != null){
            reasonDangKyDuThi.setReasonDangKyDuThiName(req.getReasonDangKyDuThiName());
            reasonDangKyDuThi.setDescReasonDangKyDuThi(req.getDescReasonDangKyDuThi());
            ReasonDangKyDuThi savedReasonDangKyDuThi = reasonDangKyDuThiRepo.save(reasonDangKyDuThi);
            return modelMapper.map(savedReasonDangKyDuThi,ReasonDangKyDuThiDto.class);
        }else{
            throw new Exception("Không thể update Reason");
        }
    };

    @Override
    public Page<ReasonDangKyDuThiDto> getAllReasonDangKyDuThi(Pageable pageable) throws Exception {
        Page<ReasonDangKyDuThi> reasonDangKyDuThiPage = reasonDangKyDuThiRepo.findAll(pageable);
        List<ReasonDangKyDuThi> reasonDangKyDuThiList = reasonDangKyDuThiPage.getContent();
        List<ReasonDangKyDuThiDto> reasonDangKyDuThiDtoList = reasonDangKyDuThiList.stream().map((result) -> modelMapper.map(result,ReasonDangKyDuThiDto.class)).collect(Collectors.toList());
        if(reasonDangKyDuThiDtoList != null){
            return new PageImpl<>(reasonDangKyDuThiDtoList,pageable,reasonDangKyDuThiPage.getTotalElements());
        }else{
            throw new Exception("Không tìm thấy List Reason");
        }
    };

    @Override
    public String deletedReasonDangKyDuThi(Long id) {
        reasonDangKyDuThiRepo.deleteById(id);
        return "DELETE thành công ";
    };
}
