package week.weekhomework.info.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import week.weekhomework.info.domain.Member;
import week.weekhomework.info.domain.Role;
import week.weekhomework.info.dto.MemberDto;
import week.weekhomework.info.repository.MemberRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Transactional
    public Long joinUser(MemberDto memberDto){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));

        return memberRepository.save(memberDto.toEntity()).getId();
    }

    @Transactional
    public List<MemberDto> getMemberList(){
        List<Member> members = memberRepository.findAll();
        List<MemberDto> memberDtoList = new ArrayList<>();

        for (Member member:members){
            MemberDto memberDto = MemberDto.builder()
                    .id(member.getId())
                    .email(member.getEmail())
                    .build();

            memberDtoList.add(memberDto);
        }

        return memberDtoList;
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException{
        Optional<Member> userEntityWrapper = memberRepository.findByEmail(userEmail);
        Member userEntity = userEntityWrapper.get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        if(("admin@naver.com").equals(userEmail)){
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else{
            authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        }

        return new User(userEntity.getEmail(), userEntity.getPassword(), authorities);
    }
}
