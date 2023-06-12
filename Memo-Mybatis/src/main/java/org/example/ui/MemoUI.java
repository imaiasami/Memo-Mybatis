package org.example.ui;

import org.example.exception.*;
import org.example.manager.*;
import org.example.model.*;

import java.util.*;

public class MemoUI {
    
    private MemoManager manager = new MemoManager();
    private Scanner scanner = new Scanner(System.in);
    private Member loginMember;
    
    public MemoUI() {
        while (true) {
            mainMenuBeforeLogin();
            String input = scanner.next();
            
            switch (input) {
                case "1":
                    joinMember(); break;
                case "2":
                    boolean isLogin = login();
                    while(isLogin) {
                        mainMenuAfterLogin();
                        input = scanner.next();
                        switch (input) {
                            case "1":
                                sendMemo(); break;
                            case "2":
                                readSendMemos(); break;
                            case "3":
                                readReceiveMemos(); break;
                            case "4":
                                isLogin = false;
                                break;
                            default:
                                System.out.println("다시 선택하세요");
                        }
                    }
                    break;
                default:
                    System.out.println("다시 선택하세요");
            }
        }
    }
    
    // 매인매뉴(로그인 전)
    public void mainMenuBeforeLogin() {
        System.out.println("=========================");
        System.out.println("쪽지 보내기");
        System.out.println("=========================");
        System.out.println("1. 회원가입");
        System.out.println("2. 로그인");
        System.out.print("> 선택: ");
    }

    // 회원가입 화면
    public void joinMember() {
        System.out.println("[회원가입]");
        System.out.print("이메일: ");
        String email = scanner.next();
        System.out.print("패스워드: ");
        String password = scanner.next();
        System.out.print("이름: ");
        String name = scanner.next();
        try {
            manager.saveMember(new Member(email, password, name));
        } catch (DuplicateMemberEmailException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean login() {
        System.out.println("[로그인 정보를 입력해주세요]");
        System.out.print("이메일: ");
        String email = scanner.next();
        System.out.print("패스워드: ");
        String password = scanner.next();

        try {
            loginMember = manager.login(new Member(email, password));
            return true;
        } catch (MemberNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }
    
    // 매인매뉴(로그인 후)
    public void mainMenuAfterLogin() {
        System.out.println("=========================");
        System.out.println(loginMember.getName() + "님 환영합니다.");
        System.out.println("=========================");
        System.out.println("1. 쪽지 보내기");
        System.out.println("2. 보낸 쪽지 확인");
        System.out.println("3. 받은 쪽지 확인");
        System.out.println("4. 로그아웃");
        System.out.print("> 선택: ");
    }

    // 쪽지 보내기
    public void sendMemo() {
        System.out.println("[쪽지 보내기]");
        // 받을 사람 찾기
        System.out.print("받을 사람 이메일: ");
        String email = scanner.next();
        scanner.nextLine();
        if (email.equals(loginMember.getEmail())) {
            System.out.println("자신에게는 쪽지를 보낼 수 없습니다.");
            return;
        }
        try {
            Member receiver = manager.findMemberByEmail(email);

            // 쪽지 만들기
            System.out.print("제목: ");
            String title = scanner.nextLine();
            System.out.print("내용: ");
            String contents = scanner.nextLine();
            Memo sendMemo = new Memo(title, contents);

            manager.sendMemo(sendMemo, loginMember, receiver);
        } catch (MemberNotFoundException e) {
            System.out.println("이메일 주소에 맞는 회원정보가 없습니다.");
        }
    }

    // 보낸 쪽지 목록 확인
    public void readSendMemos() {
        List<Map<String, Object>> sendMemos = manager.getSendMemos(loginMember);
        if (sendMemos == null || sendMemos.size() < 1) {
            System.out.println("보낸 쪽지 목록이 없습니다.");
            return;
        } else {
            System.out.println("======== 보낸 쪽지 목록 ========");
            for (Map<String, Object> sendMemo : sendMemos) {
                System.out.print("송신 아이디: " + sendMemo.get("SEND_ID"));
                System.out.print(", 받는 사람: " + sendMemo.get("RECEIVER_NAME") + "(" + sendMemo.get("RECEIVER_EMAIL") + ")");
                System.out.print(", 제목: " + sendMemo.get("TITLE"));
                System.out.println(", 보낸시간: " + sendMemo.get("SEND_TIME"));
            }
        }
    }

    // 받은 쪽지 목록 확인 및 읽기
    public void readReceiveMemos() {
        List<Map<String, Object>> receiveMemos = manager.getReceiveMemos(loginMember);
        if (receiveMemos != null && receiveMemos.size() > 0) {
            System.out.println("[받은 쪽지 목록]");
            for (Map<String, Object> receiveMemo : receiveMemos) {
                System.out.print("수신 아이디: " + receiveMemo.get("RECEIVE_ID"));
                System.out.print(", 보낸 사람: " + receiveMemo.get("SENDER_NAME") + "(" + receiveMemo.get("SENDER_EMAIL") + ")");
                System.out.print(", 제목: " + receiveMemo.get("TITLE"));
                System.out.println(", 확인여부: " + receiveMemo.get("IS_READ"));
            }
            System.out.print("읽을 쪽지 번호를 입력해주세요 : ");
            int receive_id = scanner.nextInt();
            Map<String, Object> receiveMemo = manager.getReceiveMemoById(receive_id);
            if (receiveMemo != null) {
                System.out.print("수신 아이디: " + receiveMemo.get("RECEIVE_ID"));
                System.out.print(", 보낸 사람: " + receiveMemo.get("SENDER_NAME") + "(" + receiveMemo.get("SENDER_EMAIL") + ")");
                System.out.print(", 제목: " + receiveMemo.get("TITLE"));
                System.out.println(", 내용: " + receiveMemo.get("CONTENTS"));
            } else {
                System.out.println("쪽지 정보가 없습니다.");
            }
        } else {
            System.out.println("받은 쪽지가 없습니다.");
        }
    }

    public static void main(String[] args) {
        new MemoUI();
    }
}
