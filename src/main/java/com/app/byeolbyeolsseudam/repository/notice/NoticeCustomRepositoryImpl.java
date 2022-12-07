package com.app.byeolbyeolsseudam.repository.notice;

import com.app.byeolbyeolsseudam.domain.notice.NoticeDTO;
import com.app.byeolbyeolsseudam.domain.notice.QNoticeDTO;
import com.app.byeolbyeolsseudam.entity.notice.Notice;
import com.app.byeolbyeolsseudam.entity.notice.QNotice;
import com.app.byeolbyeolsseudam.type.NoticeCategory;
import com.querydsl.core.QueryFactory;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.byeolbyeolsseudam.entity.notice.QNotice.notice;

@Repository
@RequiredArgsConstructor
public class NoticeCustomRepositoryImpl implements NoticeCustomRepository {
    public final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<NoticeDTO> showAll(Pageable pageable){
        List<NoticeDTO> resultlist =  jpaQueryFactory.select(new QNoticeDTO(
                notice.noticeId, notice.noticeTitle,
                notice.noticeContent, notice.noticeCategory, notice.createdDate))
                .from(notice)
                .fetch();

        return resultlist;
    }

    // 공지사항 중요5개, 일반 5개 조회
    @Override
    public List<NoticeDTO> showCategory(){
        List<NoticeDTO> notices = jpaQueryFactory.select(new QNoticeDTO(
                notice.noticeId, notice.noticeTitle,
                notice.noticeContent, notice.noticeCategory, notice.createdDate))
                .from(notice)
                .where(notice.noticeCategory.eq(NoticeCategory.중요))
                .orderBy(notice.createdDate.desc())
                .limit(5)
                .fetch();

        notices.addAll(jpaQueryFactory.select(new QNoticeDTO(
                notice.noticeId, notice.noticeTitle, notice.noticeContent,
                notice.noticeCategory, notice.createdDate))
                .from(notice)
                .where(notice.noticeCategory.eq(NoticeCategory.일반))
                .orderBy(notice.createdDate.desc())
                .limit(5)
                .fetch());
        return notices;
    }

    // 공지사항 상세보기
    @Override
    public NoticeDTO showDetail(Long noticeId){
        return jpaQueryFactory.select(new QNoticeDTO(
                notice.noticeId, notice.noticeTitle,
                notice.noticeContent, notice.noticeCategory, notice.createdDate))
                .from(notice)
                .where(notice.noticeId.eq(noticeId))
                .fetchOne();
    }

    //공지사항 검색
    @Override
    public List<NoticeDTO> searchNotice(String keyword){
        return jpaQueryFactory.select(new QNoticeDTO(
                notice.noticeId, notice.noticeTitle,
                notice.noticeContent, notice.noticeCategory, notice.createdDate))
                .from(notice)
                .where(notice.noticeTitle.contains(keyword).or(notice.noticeContent.contains(keyword)))
                .orderBy(notice.createdDate.desc())
                .fetch();
    }
}