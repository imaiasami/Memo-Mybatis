package org.example.manager;

import java.util.*;
import org.example.dao.MemoDao;
import org.example.exception.DuplicateMemberEmailException;
import org.example.exception.MemberNotFoundException;
import org.example.model.Member;
import org.example.model.Memo;

public class MemoManager {

    private MemoDao dao = new MemoDao();

    // 회원가입
    public void saveMember(Member member) throws DuplicateMemberEmailException {
        dao.saveMember(member);
    }

    // 로그인
    public Member login(Member member) throws MemberNotFoundException {
    	Member loginMember = dao.login(member);
    	if (loginMember == null) {
    		throw new MemberNotFoundException("회원정보가 없거나 패스워드가 틀립니다.");
    	}
    	return loginMember;
    }

    // 이메일로 회원정보 검색
    public Member findMemberByEmail(String email) throws MemberNotFoundException {
     	return	dao.findMemberByEmail(email);
    }

    // 쪽지 보내기
    public void sendMemo(Memo memo, Member sender, Member receiver) {
        dao.sendMemo(memo, sender.getMember_id(), receiver.getMember_id());
    }

    // 보낸 쪽지 전체목록
    public List<Map<String, Object>> getSendMemos(Member loginMember) {
        return dao.findSendMemos(loginMember.getMember_id());
    }

    // 받은 쪽지 전체목록
    public List<Map<String, Object>> getReceiveMemos(Member loginMember) {
        return dao.findReceiveMemos(loginMember.getMember_id());
    }

    // 받은 메모 읽기
    public Map<String, Object> getReceiveMemoById(long receive_id) {
        return dao.findReceiveMemoById(receive_id);
    }

}
