package umc.spring.domain;

import lombok.*;
import umc.spring.domain.common.BaseEntity;

import javax.persistence.*;
@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String body;

    private Float score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    // 연관관계 편의 메서드
    public void setMember(Member member) {
        if(this.member != null)
            member.getReviewList().remove(this);
        this.member = member;
        member.getReviewList().add(this);
    }

    public void setStore(Store store) {
        if(this.store != null)
            store.getReviewList().remove(this);
        this.store = store;
        store.getReviewList().add(this);
    }
}