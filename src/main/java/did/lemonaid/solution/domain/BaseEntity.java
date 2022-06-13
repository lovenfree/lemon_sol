package did.lemonaid.solution.domain;


import did.lemonaid.solution.common.util.Util;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.EntityListeners;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
public abstract class BaseEntity {
    @CreatedBy
    @Column(name = "FRST_RGSR_ID", nullable = false, updatable = false)
    private String firstRegistorId;

    @Column(name = "FRST_REG_PROG_ID", nullable = false, updatable = false)
    private String firstRegisterProgramId = Util.getMethodName();

    @Column(name = "FRST_REG_DTTM", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime frstRegDttm;


    @LastModifiedBy
    @Column(name = "LAST_EDTR_ID", nullable = false)
    private String lastEditorId ;

    @Column(name = "LAST_CHNG_PROG_ID", nullable = false)
    private String lastChangeProgramId = Util.getMethodName();

    @Column(name = "LAST_CHNG_DTTM")
    @UpdateTimestamp
    private LocalDateTime lastChngDttm;

    protected void setProgramIdInfo(String programId){
        String name = new Object() {}.getClass().getEnclosingMethod().getName();
        System.out.println("##########1"+ name);
        this.firstRegisterProgramId = programId;
        this.lastChangeProgramId = programId;
    }

    protected void updateProgramIdInfo(String programId){
        String name = new Object() {}.getClass().getEnclosingMethod().getName();
        System.out.println("##########2"+ name);
        this.lastChangeProgramId = programId;
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
