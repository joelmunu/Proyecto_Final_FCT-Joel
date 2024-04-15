package com.munushop.server.entities;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Usuario", uniqueConstraints = {@UniqueConstraint(columnNames = {"dni", "username"})})
public class Usuario implements UserDetails{
    @Id
    String dni;
    @Column(nullable = false)
    String nombre;
    String apellidos;
    String username;
    String email;
    String telefono;
    String contrasena;
    Boolean isAdmin;

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

	@Override
	public String getUsername() {
		return this.username;
	}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        throw new UnsupportedOperationException("Unimplemented method 'getAuthorities'");
    }

    @Override
    public String getPassword() {
		return this.contrasena;
    }
}
