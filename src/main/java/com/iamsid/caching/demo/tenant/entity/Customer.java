package com.iamsid.caching.demo.tenant.entity;

import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "customers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@FilterDef(name = "tenantFilter", parameters = {@ParamDef(name = "tenantKey", type = "string")})
@Filter(name = "tenantFilter", condition = "tenant = :tenantKey")
public class Customer implements Serializable {
    @Id
    String id;
    String name;
    String tenant;
}
