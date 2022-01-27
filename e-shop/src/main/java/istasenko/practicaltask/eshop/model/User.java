package istasenko.practicaltask.eshop.model;

import istasenko.practicaltask.eshop.util.UserRole;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "USERS")
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "user-basket-entity-graph",
                attributeNodes = {
                        @NamedAttributeNode(value = "basket")
                }
        )
})
public class User {

    @Id
    @GeneratedValue(generator = "ID_GENERATOR")
    @Column(name = "USER_ID", nullable = false, unique = true, updatable = false)
    private Long id;

    @NaturalId
    @Column(name = "USERNAME", nullable = false, unique = true, updatable = false)
    @Email
    private String username;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "USER_ROLE", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private UserRole role;

    @OneToMany(mappedBy = "user", cascade = ALL)
    private Set<Order> orders = new HashSet<>();

    @OneToOne(fetch = LAZY, optional = false, cascade = PERSIST)
    @JoinColumn(name = "BASKET_ID", unique = true)
    private Basket basket;

    public User() {
    }

    public User(String name, String password, UserRole role) {
        this.username = name;
        this.password = password;
        this.role = role;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
