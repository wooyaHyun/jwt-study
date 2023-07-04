package com.example.jwtstudy.domain.token;

import com.example.jwtstudy.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName : com.example.jwtstudy.domain.token
 * fileName : RefreshToken
 * author : SHW
 * date : 2023-07-02
 * description :
 * ===========================================================
 * DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2023-07-02   SHW     최초 생성
 */

@Getter
@NoArgsConstructor
@Entity
public class RefreshToken extends BaseEntity {

    @Id
    @Column(name = "rt_key")
    private String key;

    @Column(name = "rt_value")
    private String value;

    @Builder
    public RefreshToken(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public RefreshToken updateValue(String token) {
        this.value = token;
        return this;
    }
}
