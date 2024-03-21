package io.everyone.travel.core.support;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class PagingResponse<T> {

	List<T> content;

	int count;

	int page;

	int size;

	int totalPage;

	int totalElement;

}
