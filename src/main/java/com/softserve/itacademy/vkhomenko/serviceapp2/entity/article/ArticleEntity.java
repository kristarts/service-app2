package com.softserve.itacademy.vkhomenko.serviceapp2.entity.article;

import com.softserve.itacademy.vkhomenko.serviceapp2.entity.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.UUID;

@Data
@AllArgsConstructor
@Entity
@Builder
@Table(name = "articles")
@SQLDelete(sql = "UPDATE articles SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class ArticleEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name= "UUID", strategy= "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @Column(name = "name")
    private String name;

    @Column(name = "text")
    private String text;

    @Column(name = "deleted")
    private boolean deleted = Boolean.FALSE;

    public ArticleEntity() {}
}
