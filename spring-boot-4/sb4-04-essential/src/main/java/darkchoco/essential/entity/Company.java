package darkchoco.essential.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * SQL문이 로그나 콘솔에 프린트할 때 column name과 table name의 구분을 쉽게 하기 위해서
 * name = <대문자>로 한다
 */
@Getter
@Setter
@Entity
@Table(name = "COMPANIES")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NAME", nullable = false,unique = true)
    private String name;

    @Column(name = "LOGO", length = 500)
    private String logo;

    @Column(name = "INDUSTRY", nullable = false, length = 100)
    private String industry;

    @Column(name = "SIZE", nullable = false, length = 50)
    private String size;

    /**
     * 회사 평점.
     * DECIMAL(3, 2)로 저장되므로 최대 9.99까지 표현 가능.
     */
    @Column(name = "RATING", nullable = false, precision = 3, scale = 2)
    private BigDecimal rating;

    @Column(name = "LOCATIONS", length = 1000)
    private String locations;

    @Column(name = "FOUNDED", nullable = false)
    private Integer founded;

    /**
     * 회사 소개 문구.
     * 긴 텍스트를 저장하기 위해 LOB로 매핑한다. (DB에서는 TEXT로 저장)
     */
    @Lob
    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "EMPLOYEES")
    private Integer employees;

    @Column(name = "WEBSITE", length = 500)
    private String website;

    /**
     * 칼럼명이 '_'로 구분시 CamelCase로.
     * DB에서 TIMESTAMP로 저장시 Instant 타입으로 한다.
     */
    @Column(name = "CREATED_AT", nullable = false)
    private Instant createdAt;

    @Column(name = "CREATED_BY", nullable = false, length = 20)
    private String createdBy;

    @Column(name = "UPDATED_AT")
    private Instant updatedAt;

    @Column(name = "UPDATED_BY", length = 20)
    private String updatedBy;
}
