package com.algoriddle.AlgoRiddleBackendApi.Entity;

import com.algoriddle.AlgoRiddleBackendApi.Access.Role;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.UUID;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AppUser implements UserDetails {
    @Id
    @Column(name = "User_ID")
    @GeneratedValue(strategy = GenerationType.UUID)
    public @Getter @Setter UUID ID;
    public @Getter @Setter String username;
    public @Getter @Setter String email;
    @Enumerated(EnumType.STRING)
    private Role role;


    public AppUser( String username, String email, Role role) {
        this.username = username;
        this.email = email;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
