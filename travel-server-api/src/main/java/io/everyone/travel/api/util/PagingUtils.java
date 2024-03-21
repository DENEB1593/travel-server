package io.everyone.travel.api.util;

import io.everyone.travel.api.web.PagingResponse;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;

public class PagingUtils {

	public static <T,R> PagingResponse<R> sliceConvertToPage(
		Page<T> pageable,
		Function<? super T, R> convert
	) {
		List<T> sliced = pageable.getContent();
		List<R> converted = sliced.stream().map(convert).toList();

		return PagingResponse.<R>builder()
			.content(converted)
			.count(converted.size())
			.page(pageable.getNumber() + 1)
			.size(pageable.getSize())
			.totalElement((int) pageable.getTotalElements())
			.totalPage(pageable.getTotalPages())
			.build();
	}

}
