package cafe.jangboo.member.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Embeddable
public class Company {

    @Column(name = "company_name")
    private String name;
    private String teamName;

    public Company(String name, String teamName) {
        this.name = name;
        this.teamName = teamName;
    }

    public Company(String name) {
        this.name = name;
    }

}
