package com.project.trainingteam.service.impl.letter;

import com.project.trainingteam.dto.letter.GroupLetterDto;
import com.project.trainingteam.entities.letter.GroupLetter;

import com.project.trainingteam.repo.inf.letter.GroupLetterRepo;
import com.project.trainingteam.service.inf.letter.GroupLetterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupLetterServiceImpl implements GroupLetterService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private GroupLetterRepo groupLetterRepo;
    @Override
    public GroupLetterDto createdGroupLetter(GroupLetter req) throws Exception {
        boolean existGroupLetter = groupLetterRepo.existsByGroupLetterName(req.getGroupLetterName());
        if(existGroupLetter){
            throw new Exception("GroupLetter đã tồn tại");
        }else{
            GroupLetter groupLetter = new GroupLetter();
            groupLetter.setGroupLetterName(req.getGroupLetterName());
            groupLetter.setGroupLetterCode(req.getGroupLetterCode());
            groupLetter.setDescGroupLetter(req.getDescGroupLetter());
            GroupLetter savedGroupLetter = groupLetterRepo.save(groupLetter);
            return modelMapper.map(savedGroupLetter, GroupLetterDto.class);
        }
    };

    @Override
    public GroupLetterDto updatedGroupLetter(GroupLetter req) throws Exception {
        GroupLetter groupLetter = groupLetterRepo.findById(req.getId()).get();
        if(groupLetter != null){
            groupLetter.setGroupLetterName(req.getGroupLetterName());
            groupLetter.setGroupLetterCode(req.getGroupLetterCode());
            groupLetter.setDescGroupLetter(req.getDescGroupLetter());
            GroupLetter savedGroupLetter = groupLetterRepo.save(groupLetter);
            return modelMapper.map(savedGroupLetter,GroupLetterDto.class);
        }else{
            throw new Exception("Không thể update GroupLetter");
        }
    };



    @Override
    public Page<GroupLetterDto> getAllGroupLetter(Pageable pageable) throws Exception {
        Page<GroupLetter> groupLetterPage = groupLetterRepo.findAll(pageable);
        List<GroupLetter> groupLetterList = groupLetterPage.getContent();
        List<GroupLetterDto> groupLetterDtoList = groupLetterList.stream().map((result) -> modelMapper.map(result,GroupLetterDto.class)).collect(Collectors.toList());
        if(groupLetterDtoList != null){
            return new PageImpl<>(groupLetterDtoList,pageable,groupLetterPage.getTotalElements());
        }else{
            throw new Exception("Không tìm thấy List GroupLetter");
        }
    };

    @Override
    public String deletedGroupLetter(Long id) {
        groupLetterRepo.deleteById(id);
        return "Delete Thành Công";
    };
}
