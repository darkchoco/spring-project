package com.springboot.relationship.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.proxy.HibernateProxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@Table(name = "sbg_provider")
@Entity
public class Provider extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // mappedBy로 설정된 필드는 DB 테이블에 칼럼이 생성되지 않는다.
    @OneToMany(mappedBy = "provider", fetch = FetchType.EAGER)  // OneToMany의 기본 fetch 전략이 Lazy라서 즉시 로딩으로 조정.
    @ToString.Exclude  // Lombok의 ToString에 의해 순환참조가 발생할 수 있어 제외 처리
    private List<Product> productList = new ArrayList<>();

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Provider provider = (Provider) o;
        return getId() != null && Objects.equals(getId(), provider.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
