package io.everyone.travel.runner;

import com.github.javafaker.Faker;
import io.everyone.travel.domain.Expense;
import io.everyone.travel.domain.Plan;
import io.everyone.travel.domain.Travel;
import io.everyone.travel.domain.enums.Nation;
import io.everyone.travel.repository.ExpenseRepository;
import io.everyone.travel.repository.PlanRepository;
import io.everyone.travel.repository.TravelRepository;
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

@Profile("local")
@Slf4j
@Component
@RequiredArgsConstructor
public class InitDataLoader implements ApplicationListener<ApplicationStartedEvent> {

    private final TravelRepository travelRepository;
    private final PlanRepository planRepository;
    private final ExpenseRepository expenseRepository;

    private static final Random random = new Random();
    private static final List<Nation> nations = Arrays.stream(Nation.values()).toList();
    private static final int TRAVEL_SAVE_COUNT = 100;

    @Override
    public void onApplicationEvent(@NonNull ApplicationStartedEvent event) {
        log.info(">>> init data load started <<<");

        Faker faker = new Faker(new Locale("en"));

        // travel
        for (int i = 0; i < TRAVEL_SAVE_COUNT; i++) {

            String title = faker.hobbit().location();
            Nation nation = randomNation();
            LocalDateTime startAt = randomDateTime();
            LocalDateTime endAt = randomPlusTime(startAt);

//            log.info("travel title: {}, nation: {}, startAt:{}, endAt: {}",
//                title, nation, startAt, endAt );

            Travel travel = Travel.builder()
                .title(title)
                .nation(nation)
                .startAt(startAt)
                .endAt(endAt)
                .build();

            // plan
            int planSize = random.nextInt(3);
            List<Plan> plans = new ArrayList<>();
            for (int j = 0; j < planSize; j++) {

                String planTitle = faker.lorem().sentence();
                String planMemo = faker.lorem().sentence();
                LocalDateTime planStartAt = randomDateTime();
                LocalDateTime planEndAt = randomPlusTime(planStartAt);

                Plan plan = Plan.builder()
                    .title(planTitle)
                    .memo(planMemo)
                    .startAt(planStartAt)
                    .endAt(planEndAt)
                    .build();

                plans.add(plan);
            }
            travel.setPlans(plans);

            // expense
            int expenseSize = random.nextInt(5);
            List<Expense> expenses = new ArrayList<>();
            for (int j = 0; j < expenseSize; j++) {
                Expense expense = Expense.builder()
                    .amt(randomAmt())
                    .build();

                expenses.add(expense);
            }
            travel.setExpenses(expenses);

            //save
            travelRepository.save(travel);
        }



        log.info(">>> init data load finished <<<");
    }

    private static Nation randomNation() {
        return nations.get(random.nextInt(nations.size()));
    }

    private static BigDecimal randomAmt() {
        return new BigDecimal(100_000).setScale(0);
    }

    private static LocalDateTime randomDateTime() {
        final int minYear = 2023;
        final int maxYear = 2030;

        int year = random.nextInt(maxYear - minYear + 1) + minYear;
        int month = random.nextInt(12) + 1;
        int day = random.nextInt(28) + 1;
        int hour = random.nextInt(24);
        int minute = random.nextInt(60);
        int second = random.nextInt(60);

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
