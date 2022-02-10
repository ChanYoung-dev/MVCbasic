package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberRepository {
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    //싱글톤을 위해 private static final
    // 모든프로그램내에 이함수내에서만 단한번 생성뙨다
    private static final MemberRepository instance = new MemberRepository();

    // 이함수로만 조회가능
    public static MemberRepository getInstance(){
        return instance;
    }
    //다른곳에서 생성방지
    private MemberRepository(){

    }

    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll(){
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }



}
