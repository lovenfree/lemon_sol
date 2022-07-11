package did.lemonaid.solution.domain;
 

import did.lemonaid.solution.common.util.Util;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
public abstract class BaseEntity {
    @CreatedBy
    @Column(name = "FRST_RGSR_ID", nullable = false, updatable = false)
    private String registrationId="test";

    @Column(name = "FRST_REG_PROG_ID", nullable = false, updatable = false)
    private String registrationProgramId = Util.getMethodName();

    @Column(name = "FRST_REG_DTTM", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime registrationDate;


    @LastModifiedBy
    @Column(name = "LAST_EDTR_ID", nullable = false)
    private String revisedId ="test";

    @Column(name = "LAST_CHNG_PROG_ID", nullable = false)
    private String revisedProgramId = Util.getMethodName();

    @Column(name = "LAST_CHNG_DTTM")
    @UpdateTimestamp
    private LocalDateTime revisedDate;

    protected void setProgramIdInfo(String programId){
        String name = new Object() {}.getClass().getEnclosingMethod().getName();
        System.out.println("##########1"+ name);
        this.registrationProgramId = programId;
        this.revisedProgramId = programId;
    }

    protected void updateProgramIdInfo(String programId){
        String name = new Object() {}.getClass().getEnclosingMethod().getName();
        System.out.println("##########2"+ name);
        this.revisedProgramId = programId;
    }

    private String getMethodId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        authentication.getDetails();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        return null;
    }


}
