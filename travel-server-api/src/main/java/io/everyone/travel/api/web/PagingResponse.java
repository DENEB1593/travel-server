package io.everyone.travel.api.web;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class PagingResponse<T> {

	@Schema(description = "내용")
	List<T> content;

	@Schema(description = "내용 개수")
	int count;

	@Schema(description = "페이지", example = "1")
	int page;

	@Schema(description = "페이지 크기", example = "10")
	int size;

	@Schema(description = "총 페이지 수", example = "7")
	int totalPage;

	@Schema(description = "내용 총 개수", example = "73")
	int totalElement;

}
