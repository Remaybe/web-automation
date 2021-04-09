package other.utils;

import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

import static java.lang.String.format;

@Slf4j
public class AllureHelper {

    public static void addStep(String stepName) {
        AllureLifecycle lifecycle = Allure.getLifecycle();
        lifecycle.getCurrentTestCase().ifPresent(
                uuid -> {
                    final String stepUUID = UUID.randomUUID().toString();
                    lifecycle.startStep(stepUUID, new StepResult().setName(stepName).setStatus(Status.PASSED));
                    log.info(stepName);
                    lifecycle.stopStep(stepUUID);
                }
        );
    }

    public static void addStep(String stepName, Object... stepDetails) {
        if (stepDetails == null) {
            addStep(stepName);
        } else {
            AllureLifecycle lifecycle = Allure.getLifecycle();
            lifecycle.getCurrentTestCase().ifPresent(uuid -> {
                final String stepUUID = UUID.randomUUID().toString();
                lifecycle.startStep(stepUUID, new StepResult().setName(format(stepName, stepDetails)).setStatus(Status.PASSED));
                log.info(format(stepName, stepDetails));
                lifecycle.stopStep(stepUUID);
            });
        }
    }

    public static void addStep(String stepName, Supplier<?> stepAction) {
        addStepWrapper(stepName, stepAction);
    }

    public static <T> T addStepWithResult(String stepName, Supplier<T> stepAction) {
        return addStepWithResultWrapper(stepName, stepAction);
    }

    public static void addStep(String stepName, Runnable stepAction) {
        addStepWrapper(stepName, stepAction);
    }

    private static void addStepWrapper(String stepName, Runnable stepAction) {
        AllureLifecycle lifecycle = Allure.getLifecycle();
        AtomicReference<Exception> ex = new AtomicReference<>();
        lifecycle.getCurrentTestCase().ifPresent(
                uuid -> {
                    final String stepUUID = UUID.randomUUID().toString();

                    lifecycle.startStep(stepUUID, new StepResult().setName(stepName).setStatus(Status.PASSED));
                    try {
                        stepAction.run();
                    } catch (Exception e) {
                        ex.set(e);
                    } finally {
                        log.info(stepName);
                        lifecycle.stopStep(stepUUID);
                    }
                }
        );
    }

    private static <T> T addStepWithResultWrapper(String stepName, Supplier<T> stepAction) {
        AllureLifecycle lifecycle = Allure.getLifecycle();
        AtomicReference<Exception> ex = new AtomicReference<>();
        AtomicReference<T> result = new AtomicReference<>();
        lifecycle.getCurrentTestCase().ifPresent(
                uuid -> {
                    final String stepUUID = UUID.randomUUID().toString();

                    lifecycle.startStep(stepUUID, new StepResult().setName(stepName).setStatus(Status.PASSED));
                    try {
                        result.set(stepAction.get());
                    } catch (Exception e) {
                        ex.set(e);
                    } finally {
                        log.info(stepName);
                        lifecycle.stopStep(stepUUID);
                    }
                }
        );
        return result.get();
    }

    private static void addStepWrapper(String stepName, Supplier<?> stepAction) {
        AllureLifecycle lifecycle = Allure.getLifecycle();
        AtomicReference result = new AtomicReference<>();
        AtomicReference<Exception> ex = new AtomicReference<>();
        lifecycle.getCurrentTestCase().ifPresent(
                uuid -> {
                    final String stepUUID = UUID.randomUUID().toString();

                    lifecycle.startStep(stepUUID, new StepResult().setName(stepName).setStatus(Status.PASSED));
                    try {
                        result.set(stepAction.get());
                    } catch (Exception e) {
                        ex.set(e);
                    } finally {
                        log.info(stepName);
                        lifecycle.stopStep(stepUUID);
                    }
                }
        );
    }
}
