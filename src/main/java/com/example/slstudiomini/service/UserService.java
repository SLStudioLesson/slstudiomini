package com.example.slstudiomini.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.slstudiomini.model.Authority;
import com.example.slstudiomini.model.User;
import com.example.slstudiomini.repository.AuthorityRepository;
import com.example.slstudiomini.repository.UserRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
@Service
public class UserService {

    @PersistenceContext
    private EntityManager entityManager;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    public List<User> findEnabledStudent(){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);

        Root<User> user = cq.from(User.class);
        cq.select(user);
        Join<User, Authority> authorities = user.join("authorities");
        cq.where(cb.equal(authorities.get("authority"), "ROLE_USER"));
        return entityManager.createQuery(cq).getResultList();
        
    }
    // public List<User> getUsersWithUserRole(String authorityName){
    //     Authority authority = authorityRepository.findByAuthority(authorityName)
    //                             .orElseThrow(() -> new EntityNotFoundException("Authority Not Found With Name = " + authorityName));
    //     return userRepository.findByAuthoritiesContaining(authority);
    // }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public User addEnableStudentAndHashPassword(User user){
		// 有効化
        user.setEnabled(true);
        // ハッシュ化するクラスの準備
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // ハッシュ化
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        // パスワードの詰め直し
        user.setPassword(hashedPassword);

        Authority authority = authorityRepository.findByAuthority("ROLE_USER")
                                .orElseThrow(() -> new EntityNotFoundException("Authority Not Found with name=USER"));

        user.setAuthorities(Set.of(authority));

        return userRepository.save(user);
    }
    
}
