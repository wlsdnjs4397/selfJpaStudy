package com.study.board.demo.repository.specification;

import com.study.board.demo.code.DiaryCategory;
import com.study.board.demo.entity.DiaryEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;

public class DiarySpecification {

    public static Specification<DiaryEntity> titleContains(String title){
        return (root, query, criteriaBuilder) -> {
            if (title == null || title.isEmpty()) {
                return criteriaBuilder.conjunction(); // 조건이 없으면 모든 결과를 반환
            }
            return criteriaBuilder.like(root.get("title"), "%" + title + "%");
        };
    }

    public static Specification<DiaryEntity> authorContains(String author){
        return ((root, query, criteriaBuilder) -> {
           if(author == null || author.isEmpty()){
               return criteriaBuilder.conjunction();
           }
           return criteriaBuilder.like(root.get("author"), "%" + author + "%");
        });
    }

    public static Specification<DiaryEntity> dateBetween(Date startDate, Date endDate){
        return ((root, query, criteriaBuilder) -> {
            if(startDate == null && endDate == null){
                return criteriaBuilder.conjunction();
            }else if(startDate != null && endDate == null){
                return criteriaBuilder.greaterThanOrEqualTo(root.get("createDate"), startDate);
            }else if(startDate == null && endDate != null){
                return criteriaBuilder.lessThanOrEqualTo(root.get("createDate"), endDate);
            }else{
                return criteriaBuilder.between(root.get("createDate"), startDate, endDate);
            }
        });
    }

    public static Specification<DiaryEntity> categoryContains(DiaryCategory category){
        return ((root, query, criteriaBuilder) -> {
           if(category == null){
               return criteriaBuilder.conjunction();
           }
           return criteriaBuilder.equal(root.get("category"), category);
        });
    }
}
