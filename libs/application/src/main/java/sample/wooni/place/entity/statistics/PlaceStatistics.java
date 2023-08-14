package sample.wooni.place.entity.statistics;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sample.wooni.place.entity.BaseTimeEntity;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "place_statistics")
public class PlaceStatistics extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "keyword", nullable = false)
    private String keyword;

    @Column(name = "count", nullable = false)
    private int count;

    @Builder
    public PlaceStatistics(Long id,
                           String keyword,
                           int count) {
        this.id = id;
        this.keyword = keyword;
        this.count = count;
    }

    public void increaseCount() {
       this.count = this.count + 1;
    }
}
