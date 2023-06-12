package org.example.dao;

import org.example.model.Member;
import org.example.model.Memo;
import org.example.model.ReceiveMemoRecord;
import org.example.model.SendMemoRecord;

import java.util.List;
import java.util.Map;

public interface MemoMapper {
    // 회원정보 등록
    int saveMember(Member member);

    // 이메일로 회원정보 검색(이메일 중북체크)
    Member findMemberByEmail(String email);

    // 로그인
    Member findMember(Member member);

    // 쪽지 저장
    int saveMemo(Memo memo);

    // 보낸 쪽지 저장
    int saveSendMemoRecord(SendMemoRecord sendMemoRecord);

    // 받은 쪽지 저장
    int saveReceiveMemoRecord(ReceiveMemoRecord receiveMemoRecord);

    // 보낸 쪽지 전체목록
    List<Map<String, Object>> findSendMemos(long sender_id);

    // 받은 쪽지 전체목록
    List<Map<String, Object>> findReceiveMemos(long receiver_id);
    
    // 받은 쪽지 읽기
    Map<String, Object> findReceiveMemoById(long receive_id);

    // 받은 쪽지 확인여부 수정
    int updateReceiveMemo(long receive_id);

}
