package com.crm.miniCRM.dto;

import com.crm.miniCRM.model.persistence.helpers.MemberID;

import java.time.LocalDate;

public class MemberDto {
    private MemberID id;
    private LocalDate since;
    private LocalDate until;//default 9999-21-31

    public MemberDto(MemberID id, LocalDate since, LocalDate until) {
        this.id = id;
        this.since = since;
        this.until = until;
    }

    public MemberID getId() {
        return id;
    }

    public void setId(MemberID id) {
        this.id = id;
    }

    public LocalDate getSince() {
        return since;
    }

    public void setSince(LocalDate since) {
        this.since = since;
    }

    public LocalDate getUntil() {
        return until;
    }

    public void setUntil(LocalDate until) {
        this.until = until;
    }
}
