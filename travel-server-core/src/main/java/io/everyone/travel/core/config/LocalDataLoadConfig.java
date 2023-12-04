package io.everyone.travel.core.config;

import com.github.javafaker.Faker;
import io.everyone.travel.core.domain.Expense;
import io.everyone.travel.core.domain.Plan;
import io.everyone.travel.core.domain.Travel;
import io.everyone.travel.core.domain.enums.Nation;
import io.everyone.travel.core.repository.ExpenseRepository;
import io.everyone.travel.core.repository.PlanRepository;
import io.everyone.travel.core.repository.TravelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

/**
 * local profile 구동 시 더미 데이터를 적재한다.
 */
@Profile("local")
@Slf4j
@Component
@RequiredArgsConstructor
public class LocalDataLoadConfig implements ApplicationListener<ApplicationStartedEvent> {

    private final TravelRepository travelRepository;
    private final PlanRepository planRepository;
    private final ExpenseRepository expenseRepository;

    private static final Random random = new Random();
    private static final List<Nation> nations = Arrays.stream(Nation.values()).toList();
    private static final int TRAVEL_SAVE_COUNT = 50;
    private static final String DEFAULT_THUMBNAIL = "http://localhost:8080/images/default_travel_image.png";

    @Override
    public void onApplicationEvent(@NonNull ApplicationStartedEvent event) {
        log.info(">>>>>>>>>>>> init data load started <<<");

        var faker = new Faker(new Locale("en"));

        // travel
        var travels = new ArrayList<Travel>();
        for (var i = 0; i < TRAVEL_SAVE_COUNT; i++) {

            var startAt = randomDateTime();
            var endAt = randomPlusTime(startAt);

            var travel = Travel.builder()
                .title(faker.hobbit().location())
                .nation(randomNation())
                .thumbnail(DEFAULT_THUMBNAIL)
                .startAt(startAt)
                .endAt(endAt)
                .build();

            // plan
            var planSize = random.nextInt(3);
            var plans = new HashSet<Plan>();
            for (var j = 0; j < planSize; j++) {

                var planStartAt = randomDateTime();
                var planEndAt = randomPlusTime(planStartAt);

                var plan = Plan.builder()
                    .title(faker.lorem().sentence())
                    .memo(faker.lorem().sentence())
                    .startAt(planStartAt)
                    .endAt(planEndAt)
                    .build();

                plans.add(plan);
            }
            travel.setPlans(plans);

            // expense
            var expenseSize = random.nextInt(5);
            var expenses = new HashSet<Expense>();
            for (var j = 0; j < expenseSize; j++) {
                var expense = Expense.builder()
                    .amt(randomAmt())
                    .spendAt(randomDateTime())
                    .build();

                expenses.add(expense);
            }
            travel.setExpenses(expenses);
            travels.add(travel);

        }
        //save
        travelRepository.saveAll(travels);

        log.info(">>>>>>>>>>>> init data load finished - {} travels saved ", TRAVEL_SAVE_COUNT);
    }

    private static Nation randomNation() {
        return nations.get(random.nextInt(nations.size()));
    }

    private static BigDecimal randomAmt() {
        return new BigDecimal(random.nextInt(100_000)).setScale(0);
    }

    private static LocalDateTime randomDateTime() {
        var minYear = 2023;
        var maxYear = 2030;

        var year = random.nextInt(maxYear - minYear + 1) + minYear;
        var month = random.nextInt(12) + 1;
        var day = random.nextInt(28) + 1;
        var hour = random.nextInt(24);
        var minute = random.nextInt(60);
        var second = random.nextInt(60);

        return LocalDateTime.of(year, month, day, hour, minute, second);
    }

    private static LocalDateTime randomPlusTime(LocalDateTime from) {
        return from
            .plusDays(random.nextInt(365))
            .plusHours(random.nextInt(24))
            .plusMinutes(random.nextInt(60))
            .plusSeconds(random.nextInt(60));
    }

}
