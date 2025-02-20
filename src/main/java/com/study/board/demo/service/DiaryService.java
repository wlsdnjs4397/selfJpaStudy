package com.study.board.demo.service;

import com.study.board.demo.entity.DiaryEntity;
import com.study.board.demo.domain.DiarySearch;
import com.study.board.demo.repository.DiaryRepository;
import com.study.board.demo.repository.specification.DiarySpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;
    public DiaryEntity diaryBySeq(Integer seq) {
        return diaryRepository.findBySeq(seq);
    }

    public void create(DiaryEntity diary) {
        diaryRepository.save(diary);
    }

    public void modify(DiaryEntity diary) {
    }
    public void remove(Long seq) {
        diaryRepository.deleteBySeq(seq);
    }

    public Page<DiaryEntity> getList(DiarySearch search) {

        Pageable pageable = PageRequest.of(search.getPage()-1, 1, Sort.by(Sort.Direction.DESC, "seq"));
        Page<DiaryEntity> pageResult = diaryRepository.findAll(
                Specification.where(
                                DiarySpecification.categoryContains(search.getCategory()))
                        .and(DiarySpecification.titleContains(search.getTitle()))
                        .and(DiarySpecification.authorContains(search.getAuthor()))
                        .and(DiarySpecification.dateBetween(search.getStartDate(), search.getEndDate())), pageable
        );

        return pageResult;
    }

    public int getCount(DiarySearch search) {
        return (int) diaryRepository.count(Specification.where(
                        DiarySpecification.categoryContains(search.getCategory()))
                .and(DiarySpecification.titleContains(search.getTitle()))
                .and(DiarySpecification.authorContains(search.getAuthor()))
                .and(DiarySpecification.dateBetween(search.getStartDate(), search.getEndDate())));
    }

    public DiaryEntity getInfo(Integer seq) {
        return diaryRepository.findBySeq(seq);
    }
}
