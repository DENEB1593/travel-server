package io.everyone.travel.api.config.pagination;

import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.util.Assert;

@Getter
public class PageModel {

    private final int page;

    private final int size;

    /**
     * 기본 페이지는 1, size은 20으로 설정
     */
    public PageModel() {
        this(1, 20);
    }

    public PageModel(int page, int size) {
        Assert.isTrue(page > 0, "page 크기는 0보다 커야 합니다.");
        Assert.isTrue(size > 0, "size 크기는 0보다 커야 합니다.");
        this.page = page - 1;
        this.size = size;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
            .append("page", page)
            .append("size", size)
            .toString();
    }
}
