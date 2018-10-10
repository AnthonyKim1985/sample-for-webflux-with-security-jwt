package com.example.sampleforwebfluxwithsecurityjwt.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Document(collection = "users")
public class User implements UserDetails {
    @Id
    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    @NotBlank
    @Indexed(unique = true)
    private String username;

    @Getter
    @Setter
    @NotBlank
    @JsonIgnore
    private String password;

    @Getter
    @Setter
    @Email
    @NotBlank
    private String email;

    @Setter
    @NotNull
    private Boolean enabled;

    @Getter
    @Setter
    @NotNull
    private Date accountExpirationDate;

    @Getter
    @Setter
    private List<Role> roles;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    public User(@NotBlank String username,
                @NotBlank String password,
                @Email @NotBlank String email,
                @NotNull Date accountExpirationDate,
                @NotNull Boolean enabled,
                @NotNull List<Role> roles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.accountExpirationDate = accountExpirationDate;
        this.enabled = enabled;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountExpirationDate.after(new Date()) && enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountExpirationDate.after(new Date()) && enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.accountExpirationDate.after(new Date()) && enabled;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
