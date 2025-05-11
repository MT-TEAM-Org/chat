package com.myteam.chat.global.page.request;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PageInfoServiceRequest {

    private int page;
    private int size;

    public Pageable toPageable() {
        return PageRequest.of(page - 1, size);
    }

    public PageInfoServiceRequest(int page, int size) {
        this.page = page;
        this.size = size;
    }
}
