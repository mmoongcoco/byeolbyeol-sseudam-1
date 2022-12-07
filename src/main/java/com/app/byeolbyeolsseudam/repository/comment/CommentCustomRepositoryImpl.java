package com.app.byeolbyeolsseudam.repository.comment;

import com.app.byeolbyeolsseudam.domain.comment.CommentDTO;
import com.app.byeolbyeolsseudam.domain.comment.QCommentDTO;
import com.app.byeolbyeolsseudam.entity.board.Board;
import com.app.byeolbyeolsseudam.entity.board.QBoard;
import com.app.byeolbyeolsseudam.entity.comment.Comment;
import com.app.byeolbyeolsseudam.entity.comment.QComment;
import com.app.byeolbyeolsseudam.entity.member.Member;
import com.app.byeolbyeolsseudam.entity.member.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.byeolbyeolsseudam.entity.board.QBoard.board;
import static com.app.byeolbyeolsseudam.entity.comment.QComment.comment;
import static com.app.byeolbyeolsseudam.entity.member.QMember.member;

@Repository
@Slf4j
@RequiredArgsConstructor
public class CommentCustomRepositoryImpl implements CommentCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<CommentDTO> getCommentList(Long boardId){
        return jpaQueryFactory.select(new QCommentDTO(comment.commentId, comment.commentContent,
                comment.commentFileName, comment.commentFilePath, comment.commentFileUuid,
                comment.member.memberId, comment.member.memberName, comment.member.memberProfileName,
                comment.member.memberProfilePath, comment.member.memberProfileUuid,
                comment.board.boardId, comment.createdDate, comment.updatedDate))
                .from(comment)
                .where(comment.board.boardId.eq(boardId))
                .orderBy(comment.createdDate.desc())
                .fetch();
    }

    @Override
    public void saveComment(CommentDTO commentDTO, Comment comment){
        comment.changeBoard(
                jpaQueryFactory.selectFrom(board)
                        .where(board.boardId.eq(commentDTO.getBoardId()))
                        .fetchOne()
        );
        comment.changeMember(
                jpaQueryFactory.selectFrom(member)
                        .where(member.memberId.eq(commentDTO.getMemberId()))
                        .fetchOne()
        );
    }
}