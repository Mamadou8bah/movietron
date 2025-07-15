package MovieBackend.MovieBackend.dto;

import MovieBackend.MovieBackend.model.Preference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public class UserDTo {
    private int id;
    private String fullName;
    private String email;
    private List<Preference> preferences;
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    public UserDTo(int id, String fullName, String email, List<Preference> preferences) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.preferences = preferences;
    }
}
