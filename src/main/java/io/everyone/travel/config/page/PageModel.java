package io.everyone.travel.config.page;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.util.Assert;

public class PageModel {
    private final int page;

    private final int size;

    /**
     * 기본 페이지는 0, size은 20으로 설정
     */
    public PageModel() {
        this(0, 20);
    }

    public PageModel(int page, int size) {
        // TODO 앞단에서 요청하는 page 1은 DB offset 상 0이므로 해당 예외 처리가 필요하다.
        Assert.isTrue(page > 0, "page 크기는 0보다 커야 합니다.");
        Assert.isTrue(size > 0, "size 크기는 0보다 커야 합니다.");
        this.page = page;
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
            .append("page", page)
            .append("size", size)
            .toString();
    }
}
