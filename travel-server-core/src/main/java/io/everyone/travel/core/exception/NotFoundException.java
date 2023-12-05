package io.everyone.travel.core.exception;

public class NotFoundException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "resource not found";

    public NotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 여행(Travel) NOT FOUND
     */
    public static NotFoundException forUser() {
        return new NotFoundException("조회되지 않는 회원 정보입니다");
    }

    /**
     * 여행(Travel) NOT FOUND
     */
    public static NotFoundException forTravel() {
        return new NotFoundException("조회되지 않는 여행 정보입니다");
    }

    /**
     * 계획(Plan) NOT FOUND
     */
    public static NotFoundException forPlan() {
        return new NotFoundException("조회되지 않는 계획 정보입니다");
    }

    /**
     * 지출(Expense) NOT FOUND
     */
    public static NotFoundException forExpense() {
        return new NotFoundException("조회되지 않는 지출 정보입니다");
    }

}
